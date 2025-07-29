package domrbeeson.gamma.block.handler;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.item.Item;
import domrbeeson.gamma.item.Material;
import domrbeeson.gamma.world.Chunk;
import domrbeeson.gamma.world.World;

import java.util.List;
import java.util.SplittableRandom;

public class GrassBlockHandler extends FarmlandBlockHandler {

    private static final List<Item> DROPS = List.of(Material.DIRT.getItem());

    private final SplittableRandom random = new SplittableRandom();

    @Override
    public List<Item> getDrops(MinecraftServer server, Chunk chunk, int x, int y, int z, byte id, byte metadata, short toolId) {
        return DROPS;
    }

    @Override
    public void randomTick(MinecraftServer server, Chunk chunk, int x, int y, int z, byte id, byte metadata, long tick) {
        // TODO https://minecraft.wiki/w/Grass_Block#Post-generation

        if (!server.getBlockHandlers().getBlockHandler(chunk.getBlockId(x, y + 1, z)).isTransparent()) {
            chunk.setBlock(x, y, z, Material.DIRT);
            return;
        }

        World world = chunk.getWorld();
        for (int i = 0; i < 4; i++) {
            int checkX = random.nextInt(3);
            int checkY = random.nextInt(5);
            int checkZ = random.nextInt(3);

            chunk = world.getLoadedChunk(Block.getChunkRelativeCoord(x + checkX), Block.getChunkRelativeCoord(z + checkZ));
            if (chunk == null) {
                continue;
            }
            if (chunk.getBlockId(checkX, checkY, checkZ) != Material.DIRT.blockId) {
                continue;
            }
            Block blockAbove = chunk.getBlock(checkX, checkY + 1, checkZ);
            if (blockAbove.blockLight() < 9) {
                continue;
            }
            if (!server.getBlockHandlers().getBlockHandler(blockAbove.id()).isTransparent()) {
                continue;
            }

            chunk.setBlock(checkX, checkY, checkZ, Material.GRASS);
        }
    }

}
