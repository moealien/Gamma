package domrbeeson.gamma.item;

import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.block.handler.WheatBlockHandler;
import domrbeeson.gamma.crafting.CraftingRecipe;
import domrbeeson.gamma.item.items.*;

import java.util.*;

public enum Material {

    // TODO max stacks

    AIR(0),
    STONE(1),
    GRASS(2),
    DIRT(3),
    COBBLESTONE(builder(4).itemCreator(CobblestoneItem::new)),
    OAK_PLANKS(5),
    OAK_SAPLING(6),
    SPRUCE_SAPLING(builder(6).metadata(1)),
    BIRCH_SAPLING(builder(6).metadata(2)),
    BEDROCK(7),
    WATER_FLOWING(builder(8)),
    WATER_SOURCE(builder(9)),
    LAVA_FLOWING(builder(10)),
    LAVA_SOURCE(builder(11)),
    SAND(12),
    GRAVEL(13),
    GOLD_ORE(14),
    IRON_ORE(15),
    COAL_ORE(16),
    OAK_LOG(17),
    SPRUCE_LOG(builder(17).metadata(1)),
    BIRCH_LOG(builder(17).metadata(2)),
    OAK_LEAVES(18),
    SPRUCE_LEAVES(builder(18).metadata(1)),
    BIRCH_LEAVES(builder(18).metadata(2)),
    SPONGE(19),
    GLASS(20),
    LAPIS_ORE(21),
    LAPIS_BLOCK(22),
    DISPENSER(23),
    SANDSTONE(24),
    NOTE_BLOCK(25),
    // BED_BOTTOM
    POWERED_RAIL(27),
    DETECTOR_RAIL(28),
    STICKY_PISTON(29),
    COBWEB(30),
    DEFAULT_FERN(31),
    SHORT_GRASS(builder(31).metadata(1)),
    FERN(builder(31).metadata(2)),
    SNOW_FERN(builder(31).metadata(3)),
    DEAD_BUSH(32),
    // SHRUB
    PISTON(33),
    // PISTON_EXTENSION
    WHITE_WOOL(35),
    ORANGE_WOOL(builder(35).metadata(1)),
    MAGENTA_WOOL(builder(35).metadata(2)),
    LIGHT_BLUE_WOOL(builder(35).metadata(3)),
    YELLOW_WOOL(builder(35).metadata(4)),
    LIME_WOOL(builder(35).metadata(5)),
    PINK_WOOL(builder(35).metadata(6)),
    DARK_GREY_WOOL(builder(35).metadata(7)),
    GREY_WOOL(builder(35).metadata(8)),
    CYAN_WOOL(builder(35).metadata(9)),
    PURPLE_WOOL(builder(35).metadata(10)),
    BLUE_WOOL(builder(35).metadata(11)),
    BROWN_WOOL(builder(35).metadata(12)),
    GREEN_WOOL(builder(35).metadata(13)),
    RED_WOOL(builder(35).metadata(14)),
    BLACK_WOOL(builder(35).metadata(15)),
    PISTON_EXTENSION(36),
    DANDELION(37),
    ROSE(38),
    BROWN_MUSHROOM(39),
    RED_MUSHROOM(40),
    GOLD_BLOCK(41),
    IRON_BLOCK(42),
    STONE_DOUBLE_SLAB(43),
    SANDSTONE_DOUBLE_SLAB(builder(43).metadata(1)),
    OAK_DOUBLE_SLAB(builder(43).metadata(2)),
    COBBLESTONE_DOUBLE_SLAB(builder(43).metadata(3)),
    STONE_SLAB(44),
    SANDSTONE_SLAB(builder(44).metadata(1)),
    OAK_SLAB(builder(44).metadata(2)),
    COBBLESTONE_SLAB(builder(44).metadata(3)),
    BRICK_BLOCK(45),
    TNT(46),
    BOOKSHELF(47),
    MOSSY_COBBLESTONE(48),
    OBSIDIAN(49),
    TORCH(50),
    FIRE(51),
    MONSTER_SPAWNER(52),
    OAK_STAIRS(53),
    CHEST(54),
    REDSTONE_WIRE(55),
    DIAMOND_ORE(56),
    DIAMOND_BLOCK(57),
    CRAFTING_TABLE(58),
    WHEAT_CROPS(builder(59).itemCreator((metadata, amount) -> {
        if (metadata == WheatBlockHandler.FULLY_GROWN_METADATA) {
            return new Item((short) 296, (short) 0, amount);
        } else {
            return new Item((short) 295, (short) 0, amount);
        }
    })),
    FARMLAND(60),
    FURNACE(61),
    FURNACE_BURNING(62),
    SIGN_POST(63),
    OAK_DOOR_BOTTOM(64),
    LADDER(65),
    RAIL(66),
    COBBLESTONE_STAIRS(67),
    WALL_SIGN(68), // TODO use direction when placing
    LEVER(69),
    STONE_PRESSURE_PLATE(70),
    IRON_DOOR_BOTTOM(71),
    OAK_PRESSURE_PLATE(72),
    REDSTONE_ORE(73),
    REDSTONE_ORE_GLOWING(74),
    REDSTONE_TORCH_OFF(75),
    REDSTONE_TORCH(76),
    STONE_BUTTON(77),
    SNOW_LAYER(78),
    ICE(79),
    SNOW_BLOCK(80),
    CACTUS(81),
    CLAY_BLOCK(82),
    SUGAR_CANE_BLOCK(83),
    JUKEBOX(84),
    FENCE(85),
    PUMPKIN(86),
    NETHERRACK(87),
    SOUL_SAND(88),
    GLOWSTONE(89),
    PORTAL(90),
    JACK_O_LANTERN(91),
    CAKE_BLOCK(92),
    LOCKED_CHEST(95),
    TRAPDOOR(96),

    IRON_SHOVEL(builder(256).maxStack(1)),
    IRON_PICKAXE(builder(257).maxStack(1)),
    IRON_AXE(258),
    FLINT_AND_STEEL(259),
    APPLE(builder(260).maxStack(1).itemCreator(AppleItem::new)),
    BOW(261),
    ARROW(262),
    COAL(263),
    CHARCOAL(builder(263).metadata(1)),
    DIAMOND(264),
    IRON_INGOT(265),
    GOLD_INGOT(266),
    IRON_SWORD(builder(267).maxDurability(250)),
    WOOD_SWORD(builder(268).maxDurability(59)),
    WOOD_SHOVEL(builder(269).maxDurability(59)),
    WOOD_PICKAXE(builder(270).maxDurability(59)),
    WOOD_AXE(builder(271).maxDurability(59)),
    STONE_SWORD(builder(272).maxDurability(130)),
    STONE_SHOVEL(builder(273).maxDurability(131)),
    STONE_PICKAXE(builder(274).maxDurability(131)),
    STONE_AXE(builder(275).maxDurability(131)),
    DIAMOND_SWORD(builder(276).maxDurability(1560)),
    DIAMOND_SHOVEL(builder(277).maxDurability(1561)),
    DIAMOND_PICKAXE(builder(278).maxDurability(1561)),
    DIAMOND_AXE(builder(279).maxDurability(1561)),
    STICK(280),
    BOWL(281),
    MUSHROOM_SOUP(builder(282).maxStack(1).itemCreator(MushroomSoupItem::new)),
    GOLD_SWORD(builder(283).maxDurability(31)),
    GOLD_SHOVEL(builder(284).maxDurability(32)),
    GOLD_PICKAXE(builder(285).maxDurability(32)),
    GOLD_AXE(builder(286).maxDurability(32)),
    STRING(287),
    FEATHER(288),
    GUNPOWDER(289),
    WOOD_HOE(builder(290).maxDurability(59)),
    STONE_HOE(builder(291).maxDurability(131)),
    IRON_HOE(builder(292).maxDurability(251)),
    DIAMOND_HOE(builder(293).maxDurability(1561)),
    GOLD_HOE(builder(294).maxDurability(32)),
    WHEAT_SEEDS(295),
    WHEAT(296),
    BREAD(builder(297).maxStack(1).itemCreator(BreadItem::new)),
    LEATHER_HELMET(298),
    LEATHER_TUNIC(299),
    LEATHER_LEGS(300),
    LEATHER_BOOTS(301),
    CHAIN_HELMET(302),
    CHAIN_CHEST(303),
    CHAIN_LEGS(304),
    CHAIN_BOOTS(305),
    IRON_HELMET(306),
    IRON_CHEST(307),
    IRON_LEGS(308),
    IRON_BOOTS(309),
    DIAMOND_HELMET(builder(310).maxDurability(1561)),
    DIAMOND_CHEST(builder(311).maxDurability(1561)),
    DIAMOND_LEGS(builder(312).maxDurability(1561)),
    DIAMOND_BOOTS(builder(313).maxDurability(1561)),
    GOLD_HELMET(builder(314).maxDurability(32)),
    GOLD_CHEST(builder(315).maxDurability(32)),
    GOLD_LEGS(builder(316).maxDurability(32)),
    GOLD_BOOTS(builder(317).maxDurability(32)),
    FLINT(318),
    RAW_PORK(builder(319).maxStack(1).itemCreator(RawPorkItem::new)),
    COOKED_PORK(builder(320).maxStack(1).itemCreator(CookedPorkItem::new)),
    PAINTING(321),
    GOLDEN_APPLE(builder(322).maxStack(1).itemCreator(GoldenAppleItem::new)),
    NOTCH_APPLE(builder(322).metadata(1).maxStack(1).metadata(10)), // TODO item creator
    SIGN(builder(323).blockId(Material.SIGN_POST.blockId).maxStack(1)), // TODO sign has two block IDs, 63 sign post and 68 wall sign
    OAK_DOOR(324),
    BUCKET(325),
    WATER_BUCKET(326),
    LAVA_BUCKET(327),
    MINECART(328),
    SADDLE(329),
    IRON_DOOR(330),
    REDSTONE(builder(331).blockId(REDSTONE_WIRE.blockId)),
    SNOWBALL(332),
    BOAT(333),
    LEATHER(334),
    MILK_BUCKET(335),
    BRICK(336),
    CLAY(337),
    SUGAR_CANE_ITEM(builder(338).blockId(SUGAR_CANE_BLOCK.blockId)),
    PAPER(339),
    BOOK(340),
    SLIME_BALL(341),
    CHEST_MINECART(342),
    FURNACE_MINECART(343),
    EGG(344),
    COMPASS(345),
    FISHING_ROD(346),
    CLOCK(347),
    GLOWSTONE_DUST(348),
    FISH(builder(349).maxStack(1).itemCreator(FishItem::new)),
    COOKED_FISH(builder(350).maxStack(1).itemCreator(CookedFishItem::new)),
    INK_SAC(351),
    ROSE_RED(builder(351).metadata(1)),
    CACTUS_GREEN(builder(351).metadata(2)),
    COCOA_BEANS(builder(351).metadata(3)),
    LAPIS_LAZULI(builder(351).metadata(4)),
    PURPLE_DYE(builder(351).metadata(5)),
    CYAN_DYE(builder(351).metadata(6)),
    LIGHT_GREY_DYE(builder(351).metadata(7)),
    GREY_DYE(builder(351).metadata(8)),
    PINK_DYE(builder(351).metadata(9)),
    LIME_DYE(builder(351).metadata(10)),
    YELLOW_DYE(builder(351).metadata(11)),
    LIGHT_BLUE_DYE(builder(351).metadata(12)),
    MAGENTA_DYE(builder(351).metadata(13)),
    ORANGE_DYE(builder(351).metadata(14)),
    BONE_MEAL(builder(351).metadata(15)),
    BONE(352),
    SUGAR(353),
    CAKE(builder(354).maxStack(1).blockId(CAKE_BLOCK.blockId)),
    BED(builder(355).maxStack(1)),
    REDSTONE_REPEATER(builder(356).maxStack(1)),
    COOKIE(builder(357).maxStack(8)),
    MAP(builder(358).maxStack(1)),
    SHEARS(builder(359).maxStack(1)),
    DISC_11(builder(2256).maxStack(1)),
    DISC_CAT(builder(2257).maxStack(1)),
    ;

//    private static final Material[] MATERIALS = new Material[2258];
    private static final Map<Integer, Material> MATERIAL_BY_ID_AND_META = new HashMap<>();

    public final short id;
    public final short metadata; // This is an alias of metadataMin
    public final short metadataMin, metadataMax;
    public final boolean block;
    public final short maxStack;
    public final byte blockId;
    public final CraftingRecipe[] recipes;
    private final ItemCreator itemCreator;

    Material(int id) {
        this((short) id, (short) 0, (byte) 64, (byte) 0, new CraftingRecipe[0], null);
    }

    Material(Builder builder) {
        this(builder.id, builder.metadataMin, builder.metadataMax, builder.maxStack, builder.blockId, builder.recipes.toArray(new CraftingRecipe[0]), builder.itemCreator);
    }

    Material(short id, short metadata, byte maxStack, byte blockId, CraftingRecipe[] recipe, ItemCreator itemCreator) {
        this(id, metadata, metadata, maxStack, blockId, recipe, itemCreator);
    }

    Material(short id, short metadataMin, short metadataMax, byte maxStack, byte blockId, CraftingRecipe[] recipes, ItemCreator itemCreator) {
        this.id = id;
        this.metadata = metadataMin;
        this.metadataMin = metadataMin;
        this.metadataMax = metadataMax;
        this.maxStack = maxStack;
        this.recipes = recipes;

        block = id <= Byte.MAX_VALUE;
        if (block) {
            this.blockId = (byte) id;
        } else {
            this.blockId = blockId;
        }

        this.itemCreator = Objects.requireNonNullElseGet(itemCreator, () -> (metadata, amount) -> {
            if (amount == 0) {
                return Item.AIR;
            }
            return new Item(id, metadata, amount);
        });
    }

    public Item getItem() {
        return getItem(1);
    }

    public Item getItem(int amount) {
        return getItem(metadataMin, amount);
    }

    public Item getItem(int metadata, int amount) {
        return itemCreator.create((short) metadata, amount);
    }

    public Item getItem(short metadata, int amount) {
        return itemCreator.create(metadata, amount);
    }

    public static Material get(short id, short metadata) {
        return MATERIAL_BY_ID_AND_META.getOrDefault(id << 16 | metadata, AIR);
    }

    public static Material get(Block block) {
        return get(block.id(), block.metadata());
    }

    private static Builder builder(int id) {
        return new Builder(id);
    }

    static {
        for (Material material : values()) {
            for (short i = material.metadataMin; i <= material.metadataMax; i++) {
                MATERIAL_BY_ID_AND_META.put(material.id << 16 | i, material);
            }
        }
    }

    private static final class Builder {
        private final short id;
        private final List<CraftingRecipe> recipes = new ArrayList<>();

        private short metadataMin = 0;
        private short metadataMax = 0;
        private byte maxStack = 64;
        private byte blockId = 0;
        private ItemCreator itemCreator = null;

        public Builder(int id) {
            this.id = (short) id;
        }

        public Builder metadata(int metadata) {
            this.metadataMin = (short) metadata;
            this.metadataMax = this.metadataMin;
            return this;
        }

        public Builder maxStack(int maxStack) {
            this.maxStack = (byte) maxStack;
            return this;
        }

        public Builder blockId(int blockId) {
            this.blockId = (byte) blockId;
            return this;
        }

        public Builder recipe(CraftingRecipe recipe) {
            recipes.add(recipe);
            return this;
        }

        public Builder itemCreator(ItemCreator itemCreator) {
            this.itemCreator = itemCreator;
            return this;
        }

        public Builder maxDurability(int maxDurability) {
            this.metadataMin = 0;
            this.metadataMax = (short) maxDurability;
            return this;
        }
    }

    @FunctionalInterface
    private interface ItemCreator {
        Item create(short metadata, int amount);
    }

}
