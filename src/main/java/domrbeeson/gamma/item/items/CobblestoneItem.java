package domrbeeson.gamma.item.items;

import domrbeeson.gamma.item.Material;
import domrbeeson.gamma.item.SmeltableItem;

public class CobblestoneItem extends SmeltableItem {

    public CobblestoneItem(short metadata, int amount) {
        super(Material.COBBLESTONE, amount);
    }

    @Override
    public Material getSmeltingOutput() {
        return Material.STONE;
    }
}
