package domrbeeson.gamma.block.handler;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.event.events.block.BlockChangeEvent;
import domrbeeson.gamma.world.Chunk;

public class FireBlockHandler implements BlockHandler {

    @Override
    public void onPlace(MinecraftServer server, BlockChangeEvent event, Chunk chunk, int x, int y, int z, byte newId, byte newMetadata, int clickedX, byte clickedY, int clickedZ) {
        // TODO detect empty nether portal
    }

    @Override
    public void randomTick(MinecraftServer server, Chunk chunk, int x, int y, int z, byte id, byte metadata, long tick) {
        // TODO spread
    }

}
