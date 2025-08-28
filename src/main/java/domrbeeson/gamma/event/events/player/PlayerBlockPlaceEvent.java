package domrbeeson.gamma.event.events.player;

import domrbeeson.gamma.event.events.block.BlockChangeEvent;
import domrbeeson.gamma.player.Player;
import domrbeeson.gamma.world.Chunk;

public class PlayerBlockPlaceEvent extends BlockChangeEvent {

    private final Player player;

    public PlayerBlockPlaceEvent(Player player, Chunk chunk, int x, int y, int z, byte currentId, byte currentMetadata, byte newId, byte newMetadata, int clickedX, byte clickedY, int clickedZ, boolean tick) {
        super(chunk, x, y, z, currentId, currentMetadata, newId, newMetadata, clickedX, clickedY, clickedZ, tick);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
