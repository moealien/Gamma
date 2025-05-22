package domrbeeson.gamma.block.tile;

import domrbeeson.gamma.inventory.ChestInventory;
import domrbeeson.gamma.inventory.InventoryType;
import domrbeeson.gamma.world.ChunkGetter;

public class ChestTileEntity extends InventoryTileEntity<ChestInventory> {

    public ChestTileEntity(ChunkGetter chunk, int x, int y, int z) {
        this(chunk, x, y, z, new ChestInventory(InventoryType.CHEST_3_ROWS.ordinal()));
    }

    public ChestTileEntity(ChunkGetter chunk, int x, int y, int z, ChestInventory inv) {
        super(chunk, x, y, z, inv);
    }

}
