package domrbeeson.gamma.block.tile;

import domrbeeson.gamma.inventory.FurnaceInventory;
import domrbeeson.gamma.world.ChunkGetter;

public class FurnaceTileEntity extends InventoryTileEntity<FurnaceInventory> {

    private static final short COOK_TICKS = 200;

    private boolean burning = false;
    private short burnTime = 0; // Amount of ticks the current item will take to cook
    private short cookTime = 0; // Progress towards cooking the current item

    public FurnaceTileEntity(ChunkGetter chunk, int x, int y, int z) {
        super(chunk, x, y, z, new FurnaceInventory());
    }

    public short getBurnTime() {
        return burnTime;
    }

    public short getCookTime() {
        return cookTime;
    }

}
