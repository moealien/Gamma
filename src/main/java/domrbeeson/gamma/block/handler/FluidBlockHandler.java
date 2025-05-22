package domrbeeson.gamma.block.handler;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.world.Chunk;
import domrbeeson.gamma.world.Dimension;
import domrbeeson.gamma.world.World;

import java.util.HashMap;
import java.util.Map;

public class FluidBlockHandler extends BlockHandler {

    private static final int MAX_FLOW_DISTANCE = 8;

    private final byte sourceBlockId;
    private final byte flowingBlockId;
    private final Map<Dimension, Long> updateFrequencyTicks;
    private final Map<Dimension, Integer> dropoff;

    public FluidBlockHandler(byte sourceBlockId, byte flowingBlockId, long updateFrequencyTicks, int dropoff) {
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

    public FluidBlockHandler(byte sourceBlockId, byte flowingBlockId, Map<Dimension, Long> updateFrequencyTicks, Map<Dimension, Integer> dropoff) {
        this.sourceBlockId = sourceBlockId;
        this.flowingBlockId = flowingBlockId;
        this.updateFrequencyTicks = updateFrequencyTicks;
        for (Dimension dimension : Dimension.values()) {
            this.updateFrequencyTicks.putIfAbsent(dimension, 5L);
        }
        this.dropoff = dropoff;
        for (Dimension dimension : Dimension.values()) {
            this.dropoff.putIfAbsent(dimension, 1);
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

        byte height = block.metadata();
        if (height >= MAX_FLOW_DISTANCE) {
            return false;
        }
        height += dropoff.get(world.getFormat().getDimension());

        flow(ticks, world, x + 1, y, z, height);
        flow(ticks, world, x, y, z + 1, height);
        flow(ticks, world, x - 1, y, z, height);
        flow(ticks, world, x, y, z - 1, height);

        return true;
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
