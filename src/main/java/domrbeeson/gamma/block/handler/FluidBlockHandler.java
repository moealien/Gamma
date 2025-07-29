package domrbeeson.gamma.block.handler;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.world.Chunk;
import domrbeeson.gamma.world.Dimension;
import domrbeeson.gamma.world.Direction;
import domrbeeson.gamma.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class FluidBlockHandler implements BlockHandler {

    private static final int MAX_FLOW_DISTANCE = 8;

    private final byte sourceBlockId;
    private final byte flowingBlockId;
    private final Map<Dimension, Long> updateFrequencyTicks;
    private final Map<Dimension, Byte> dropoff;

    public FluidBlockHandler(byte sourceBlockId, byte flowingBlockId, long updateFrequencyTicks, byte dropoff) {
        this.sourceBlockId = sourceBlockId;
        this.flowingBlockId = flowingBlockId;
        this.updateFrequencyTicks = new HashMap<>();
        for (Dimension dimension : Dimension.values()) {
            this.updateFrequencyTicks.put(dimension, updateFrequencyTicks);
        }
        this.dropoff = new HashMap<>();
        for (Dimension dimension : Dimension.values()) {
            this.dropoff.put(dimension, dropoff);
        }
    }

    public FluidBlockHandler(byte sourceBlockId, byte flowingBlockId, Map<Dimension, Long> updateFrequencyTicks, Map<Dimension, Byte> dropoff) {
        this.sourceBlockId = sourceBlockId;
        this.flowingBlockId = flowingBlockId;
        this.updateFrequencyTicks = updateFrequencyTicks;
        for (Dimension dimension : Dimension.values()) {
            this.updateFrequencyTicks.putIfAbsent(dimension, 5L);
        }
        this.dropoff = dropoff;
        for (Dimension dimension : Dimension.values()) {
            this.dropoff.putIfAbsent(dimension, (byte) 1);
        }
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isLiquid() {
        return true;
    }

    @Override
    public boolean isPermeable() {
        return true;
    }

    @Override
    public boolean update(MinecraftServer server, Block block, long ticks) {
        // TODO this shit makes memory usage go nuts
        int x = block.x();
        int y = block.y();
        int z = block.z();
        if (!shouldUpdateThisTick(ticks, block.world().getFormat().getDimension())) {
            block.chunk().scheduleBlockUpdate(x, y, z);
            return false;
        }

        World world = block.world();
        if (flow(ticks, world, x, y - 1, z, (byte) 0)) {
            // Fluids do not continue flowing on this Y level after reaching a hole
            return true;
        }

        byte height = (byte) (block.metadata() + dropoff.get(world.getFormat().getDimension()));
        if (height >= MAX_FLOW_DISTANCE) {
            return false;
        }

        Direction holeDirection = getHoleDirection(block.world(), x, y, z, height);
        if (holeDirection == null) {
            return true;
        }
//        System.out.println("hole direction: " + holeDirection.name());

        if (holeDirection == Direction.WEST || holeDirection == Direction.NONE) {
            flow(ticks, world, x + 1, y, z, height);
        }
        if (holeDirection == Direction.EAST || holeDirection == Direction.NONE) {
            flow(ticks, world, x - 1, y, z, height);
        }
        if (holeDirection == Direction.NORTH || holeDirection == Direction.NONE) {
            flow(ticks, world, x, y, z + 1, height);
        }
        if (holeDirection == Direction.SOUTH || holeDirection == Direction.NONE) {
            flow(ticks, world, x, y, z - 1, height);
        }

        return true;
    }

    @Nullable
    private Direction getHoleDirection(World world, int x, int y, int z, byte height) {
        int checkDistance = (MAX_FLOW_DISTANCE - height) / dropoff.get(world.getFormat().getDimension());
//        System.out.println("checkDistance: " + checkDistance);
        if (checkDistance == 0) {
            return null;
        }

        for (int i = 1; i < checkDistance; i++) {
            Block block = world.getBlock(x + i, y, z);
            if (world.getServer().getBlockHandlers().getBlockHandler(block.id()).isPermeable()) {
                block = world.getBlock(x + i, y - 1, z);
                if (world.getServer().getBlockHandlers().getBlockHandler(block.id()).isPermeable()) {
                    return Direction.WEST;
                }
            }

            block = world.getBlock(x - i, y, z);
            if (world.getServer().getBlockHandlers().getBlockHandler(block.id()).isPermeable()) {
                block = world.getBlock(x - i, y - 1, z);
                if (world.getServer().getBlockHandlers().getBlockHandler(block.id()).isPermeable()) {
                    return Direction.EAST;
                }
            }

            block = world.getBlock(x, y, z + i);
            if (world.getServer().getBlockHandlers().getBlockHandler(block.id()).isPermeable()) {
                block = world.getBlock(x, y - 1, z + i);
                if (world.getServer().getBlockHandlers().getBlockHandler(block.id()).isPermeable()) {
                    return Direction.NORTH;
                }
            }

            block = world.getBlock(x, y, z - i);
            if (world.getServer().getBlockHandlers().getBlockHandler(block.id()).isPermeable()) {
                block = world.getBlock(x, y - 1, z - i);
                if (world.getServer().getBlockHandlers().getBlockHandler(block.id()).isPermeable()) {
                    return Direction.SOUTH;
                }
            }
        }

        return Direction.NONE;
    }

    private boolean flow(long ticks, World world, int x, int y, int z, byte newHeight) {
        // Fluids do not load new chunks
        Chunk chunk = world.getLoadedChunk(x >> 4, z >> 4);
        if (chunk == null) {
            return false;
        }
        byte blockId = chunk.getBlockId(x, y, z);
        if (blockId == sourceBlockId) {
            return true;
        }
        if (blockId == flowingBlockId) {
            if (chunk.getBlockMetadata(x, y, z) <= newHeight) {
                return false;
            }
        }
        if (!world.getServer().getBlockHandlers().getBlockHandler(chunk.getBlockId(x, y, z)).isPermeable()) {
            return false;
        }

        long nextUpdate = getTicksUntilNextUpdate(ticks, world.getFormat().getDimension());
        if (getSourceBlocksAdjacent(chunk, x, y, z) >= 2) {
            chunk.setBlock(x, y, z, sourceBlockId, (byte) 0, true);
        } else {
            chunk.setBlock(x, y, z, flowingBlockId, newHeight, true);
        }
        chunk.scheduleBlockUpdate(x, y, z, nextUpdate);
        return true;
    }

    private boolean shouldUpdateThisTick(long ticks, Dimension dimension) {
        return ticks % updateFrequencyTicks.get(dimension) == 0;
    }

    private long getTicksUntilNextUpdate(long ticks, Dimension dimension) {
        long updateFrequency = updateFrequencyTicks.get(dimension);
        long difference = ticks % updateFrequency;
        return updateFrequency - difference;
    }

    private int getSourceBlocksAdjacent(Chunk chunk, int x, int y, int z) {
        int sources = 0;
        sources += chunk.getBlockId(x + 1, y, z) == sourceBlockId ? 1 : 0;
        sources += chunk.getBlockId(x, y, z + 1) == sourceBlockId ? 1 : 0;
        sources += chunk.getBlockId(x - 1, y, z) == sourceBlockId ? 1 : 0;
        sources += chunk.getBlockId(x, y, z - 1) == sourceBlockId ? 1 : 0;
        return sources;
    }

}
