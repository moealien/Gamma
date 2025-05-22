package domrbeeson.gamma.event.events.block;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.item.Item;
import domrbeeson.gamma.world.Chunk;

import java.util.List;

public class BlockBreakEvent extends BlockChangeEvent {

    private final List<Item> drops;

    public BlockBreakEvent(MinecraftServer server, Chunk chunk, int x, int y, int z, byte currentId, byte currentMetadata, boolean update) {
        super(chunk, x, y, z, currentId, currentMetadata, (byte) 0, (byte) 0, update);
        drops = server.getBlockHandlers().getBlockHandler(currentId).getDrops(server, chunk, x, y, z, currentId, currentMetadata);
    }

    public List<Item> getDrops() {
        return drops;
    }

    public BlockBreakEvent addDrop(Item item) {
        drops.add(item);
        return this;
    }

}
