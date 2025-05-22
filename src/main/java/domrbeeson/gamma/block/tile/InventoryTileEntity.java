package domrbeeson.gamma.block.tile;

import domrbeeson.gamma.inventory.Inventory;
import domrbeeson.gamma.world.ChunkGetter;

public abstract class InventoryTileEntity<T extends Inventory> extends TileEntity {

    private final T inv;

    public InventoryTileEntity(ChunkGetter chunk, int x, int y, int z, T inv) {
        super(chunk, x, y, z);
        this.inv = inv;
    }

    @Override
    public void tick(long ticks) {
        inv.tick(ticks);
        getInventory().tick(ticks);
        if (getInventory().hasChangedThisTick()) {
            getChunk().markForSaving();
        }
    }

    public final T getInventory() {
        return inv;
    }

}
