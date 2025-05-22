package domrbeeson.gamma.block.tile;

import domrbeeson.gamma.inventory.DispenserInventory;
import domrbeeson.gamma.world.ChunkGetter;

public class DispenserTileEntity extends InventoryTileEntity<DispenserInventory> {

    public DispenserTileEntity(ChunkGetter chunk, int x, int y, int z) {
        this(chunk, x, y, z, new DispenserInventory());
    }

    public DispenserTileEntity(ChunkGetter chunk, int x, int y, int z, DispenserInventory inv) {
        super(chunk, x, y, z, inv);
    }

}
