package domrbeeson.gamma.inventory;

public class ChestInventory extends Inventory {

    public ChestInventory(int rows) {
        super(InventoryType.values()[rows], "Chest");
    }

}
