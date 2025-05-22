package domrbeeson.gamma.event.events.block;

import domrbeeson.gamma.event.Event;
import domrbeeson.gamma.event.events.CancellableEvent;
import domrbeeson.gamma.world.Chunk;

public class BlockChangeEvent extends CancellableEvent implements Event.WorldEvent {

    private final Chunk chunk;
    private final int x, y, z;
    private final byte currentId, currentMetadata, newId, newMetadata;
    private final boolean update;

    public BlockChangeEvent(Chunk chunk, int x, int y, int z, byte currentId, byte currentMetadata, byte newId, byte newMetadata, boolean update) {
        this.chunk = chunk;
        this.x = x;
        this.y = y;
        this.z = z;
        this.currentId = currentId;
        this.currentMetadata = currentMetadata;
        this.newId = newId;
        this.newMetadata = newMetadata;
        this.update = update;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public byte getCurrentId() {
        return currentId;
    }

    public byte getCurrentMetadata() {
        return currentMetadata;
    }

    public byte getNewId() {
        return newId;
    }

    public byte getNewMetadata() {
        return newMetadata;
    }

    public boolean doUpdate() {
        return update;
    }

}
