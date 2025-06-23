package domrbeeson.gamma.block.handler;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.event.events.block.BlockChangeEvent;
import domrbeeson.gamma.player.Player;
import domrbeeson.gamma.world.Chunk;

public class FireBlockHandler implements BlockHandler {

    @Override
    public void onPlayerPlace(MinecraftServer server, BlockChangeEvent event, Chunk chunk, int x, int y, int z, byte newId, byte newMetadata, int clickedX, byte clickedY, int clickedZ, Player player) {
        // TODO detect empty nether portal
    }

    @Override
    public void randomTick(MinecraftServer server, Chunk chunk, int x, int y, int z, byte id, byte metadata, long tick) {
        // TODO spread
    }

}
