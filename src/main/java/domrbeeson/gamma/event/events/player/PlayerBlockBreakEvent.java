package domrbeeson.gamma.event.events.player;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.event.events.block.BlockBreakEvent;
import domrbeeson.gamma.player.Player;
import domrbeeson.gamma.world.Chunk;

public class PlayerBlockBreakEvent extends BlockBreakEvent {

    private final Player player;
    private final short toolId;
    private final boolean reduceToolDurability;

    public PlayerBlockBreakEvent(MinecraftServer server, Player player, Chunk chunk, int x, int y, int z, byte currentId, byte currentMetadata, boolean tick, short toolId, boolean reduceToolDurability) {
        super(server, chunk, x, y, z, currentId, currentMetadata, tick);
        this.player = player;
        this.toolId = toolId;
        this.reduceToolDurability = reduceToolDurability;
    }

    public Player getPlayer() {
        return player;
    }

    public short getTool() {
        return toolId;
    }

    public boolean shouldReduceToolDurability() {
        return reduceToolDurability;
    }

}
