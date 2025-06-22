package domrbeeson.gamma.event.events.block;

import domrbeeson.gamma.event.Event;
import domrbeeson.gamma.event.events.CancellableEvent;
import domrbeeson.gamma.item.Material;
import domrbeeson.gamma.world.Chunk;

public class BlockChangeEvent extends CancellableEvent implements Event.WorldEvent {

    private final Chunk chunk;
    private final int x, y, z;
    private final byte currentId, currentMetadata;
    private final boolean update;
    private final int clickedX, clickedZ;
    private final byte clickedY;

    private byte newId, newMetadata;

    public BlockChangeEvent(Chunk chunk, int x, int y, int z, byte currentId, byte currentMetadata, byte newId, byte newMetadata, boolean update) {
        this(chunk, x, y, z, currentId, currentMetadata, newId, newMetadata, x, (byte) y, z, update);
    }

    public BlockChangeEvent(Chunk chunk, int x, int y, int z, byte currentId, byte currentMetadata, byte newId, byte newMetadata, int clickedX, byte clickedY, int clickedZ, boolean update) {
        this.chunk = chunk;
        this.x = x;
        this.y = y;
        this.z = z;
        this.currentId = currentId;
        this.currentMetadata = currentMetadata;
        this.newId = newId;
        this.newMetadata = newMetadata;
        this.update = update;
        this.clickedX = clickedX;
        this.clickedY = clickedY;
        this.clickedZ = clickedZ;
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

    public int getClickedX() {
        return clickedX;
    }

    public byte getClickedY() {
        return clickedY;
    }

    public int getClickedZ() {
        return clickedZ;
    }

    public boolean doUpdate() {
        return update;
    }

    public void setNewId(byte newId) {
        this.newId = newId;
    }

    public void setNewMetadata(byte newMetadata) {
        this.newMetadata = newMetadata;
    }

    public void setMaterial(Material material) {
        this.newId = material.blockId;
        this.newMetadata = (byte) material.metadata;
    }

}
