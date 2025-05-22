package domrbeeson.gamma.block.handler;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.item.Material;
import domrbeeson.gamma.world.Chunk;

public abstract class PlantStackBlockHandler extends BlockHandler {

    private final short blockId;

    public PlantStackBlockHandler(short blockId) {
        this.blockId = blockId;
    }

    @Override
    public boolean update(MinecraftServer server, Block block, long tick) {
        System.out.println("updating block " + Material.get(block).name() + " at Y " + block.y());
        if (!canPlace(block.chunk(), block.x(), block.y(), block.z())) {
            breakStack(block);
            return true;
        }
        return false;
    }

    protected final int getGroundY(Chunk chunk, int x, int y, int z) {
        while (--y >= 0) {
            if (chunk.getBlockId(x, y, z) != blockId) {
                return y;
            }
        }
        return y;
    }

    protected final int getStackMaxY(Chunk chunk, int x, int y, int z) {
        while (++y < Chunk.HEIGHT) {
            if (chunk.getBlockId(x, y, z) != blockId) {
                return y - 1;
            }
        }
        return y;
    }

    protected final void breakStack(Block block) {
        Chunk chunk = block.chunk();
        int x = block.x();
        int y = block.y();
        int z = block.z();
        short aboveBlockId;
        do {
            chunk.breakBlock(x, y, z);
            System.out.println("breaking block at y " + y + ", checking if block above is equal to " + blockId + ": " + chunk.getBlockId    (x, y + 1, z));
        } while ((aboveBlockId = chunk.getBlockId(x, ++y, z)) == this.blockId && aboveBlockId != Material.AIR.blockId);
    }

}
