package domrbeeson.gamma.item;

import domrbeeson.gamma.player.Player;

public class Item {

    public static final Item AIR = new Item((short) 0, (short) 0, 0);

    private final short id;
    private final short metadata;
    private final byte amount;
    private final Material material;

    protected Item(Material material) {
        this(material, 1);
    }

    protected Item(Material material, int amount) {
        this(material, material.metadataMin, amount);
    }

    protected Item(Material material, short metadata, int amount) {
        this.id = material.id;
        this.metadata = metadata;
        this.amount = (byte) amount;
        this.material = material;
    }

    protected Item(short id, short metadata) {
        this(id, metadata, (byte) 1);
    }

    protected Item(short id, short metadata, int amount) {
        this.id = id;
        this.metadata = metadata;
        this.amount = (byte) amount;
        this.material = Material.get(id, metadata);
    }

    public short getId() {
        return id;
    }

    public short getMetadata() {
        return metadata;
    }

    public byte getAmount() {
        return amount;
    }

    public Material getMaterial() {
        return material;
    }

    public void use(Player player) {

    }

    public boolean isPickaxe() {
        return id == Material.WOOD_PICKAXE.id
                || id == Material.STONE_PICKAXE.id
                || id == Material.IRON_PICKAXE.id
                || id == Material.DIAMOND_PICKAXE.id
                || id == Material.GOLD_PICKAXE.id;
    }

    public boolean isShovel() {
        return id == Material.WOOD_SHOVEL.id
                || id == Material.STONE_SHOVEL.id
                || id == Material.IRON_SHOVEL.id
                || id == Material.DIAMOND_SHOVEL.id
                || id == Material.GOLD_SHOVEL.id;
    }

    public boolean isSword() {
        return id == Material.WOOD_SWORD.id
                || id == Material.STONE_SWORD.id
                || id == Material.IRON_SWORD.id
                || id == Material.DIAMOND_SWORD.id
                || id == Material.GOLD_SWORD.id;
    }

    public boolean isAxe() {
        return id == Material.WOOD_AXE.id
                || id == Material.STONE_AXE.id
                || id == Material.IRON_AXE.id
                || id == Material.DIAMOND_AXE.id
                || id == Material.GOLD_AXE.id;
    }

    public boolean isHoe() {
        return id == Material.WOOD_HOE.id
                || id == Material.STONE_HOE.id
                || id == Material.IRON_HOE.id
                || id == Material.DIAMOND_HOE.id
                || id == Material.GOLD_HOE.id;
    }

    @Override
    public String toString() {
        return "[id=" + id + ",metadata=" + metadata + ",amount=" + amount + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item item)) {
            return false;
        }
        return item.id == id
                && item.metadata == metadata
                && item.amount == amount;
    }

}
