package domrbeeson.gamma.world.terrain;

import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.item.Material;
import domrbeeson.gamma.world.Chunk;
import domrbeeson.gamma.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlphaGenerator implements TerrainGenerator {

//    private static final Structure OAK_TREE = new Structure("OakTree.json");

    private final Random random = new Random();

    private long worldSeed = 0;

    @Override
    public void load(World world) {
        worldSeed = world.getFormat().getSeed();
        random.setSeed(worldSeed);
    }

    @Override
    public void generate(Chunk.Builder builder) {
//        for (int i = 0; i < 4; i++) {
//            for (int i2 = 0; i2 < 4; i2++) {
//                for (int i3 = 0; i3 < 16; i3++) {
//
//
//                    for (int i4 = 0; i4 < 8; i4++) {
//
//
//                        for (int i5 = 0; i5 < 4; i5++) {
//
//                        }
//                    }
//                }
//            }
//        }

        List<LazyBlock> lazyBlocks = new ArrayList<>();

        int chunkStartX = builder.x * 16;
        int chunkStartZ = builder.z * 16;

        random.setSeed(worldSeed);
        long chunkSeedX = random.nextLong() / 2L * 2L + 1L;
        long chunkSeedZ = random.nextLong() / 2L * 2L + 1L;
        random.setSeed(builder.x * chunkSeedX + builder.z * chunkSeedZ ^ worldSeed);

        int x, y, z;

        if (random.nextInt(4) == 0) {
            x = chunkStartX + random.nextInt(16) + 8;
            y = random.nextInt(128);
            z = chunkStartX + random.nextInt(16) + 8;
            generateLake(builder, x, y, z, Material.WATER_FLOWING, lazyBlocks);
        }

        if (random.nextInt(8) == 0) {
            x = chunkStartX + random.nextInt(16) + 8;
            y = random.nextInt(random.nextInt(120) + 8);
            z = chunkStartX + random.nextInt(16) + 8;
            if (y < 64 || random.nextInt(10) == 0) {
                generateLake(builder, x, y, z, Material.LAVA_FLOWING, lazyBlocks);
            }
        }

        int something;
        for (x = 0; x < 8; x++) {
            y = chunkStartX + random.nextInt(16) + 8;
            z = random.nextInt(128);
            something = chunkStartZ + random.nextInt(16) + 8;
            // TODO generate dungeon
        }

        for (x = 0; x < 10; x++) {
            y = chunkStartX + random.nextInt(16);
            z = random.nextInt(128);
            something = chunkStartZ + random.nextInt(16);
            // TODO generate clay
        }

        for (x = 0; x < 20; x++) {
            y = chunkStartX + random.nextInt(16);
            z = random.nextInt(128);
            something = chunkStartZ + random.nextInt(16);
            // TODO generate dirt
        }

        for (x = 0; x < 10; x++) {
            y = chunkStartX + random.nextInt(16);
            z = random.nextInt(128);
            something = chunkStartZ + random.nextInt(16);
            // TODO generate gravel
        }

        for (x = 0; x < 20; x++) {
            y = chunkStartX + random.nextInt(16);
            z = random.nextInt(128);
            something = chunkStartZ + random.nextInt(16);
            // TODO generate coal ore
        }

        for (x = 0; x < 20; x++) {
            y = chunkStartX + random.nextInt(16);
            z = random.nextInt(64);
            something = chunkStartZ + random.nextInt(16);
            // TODO generate iron ore
        }

        for (x = 0; x < 2; x++) {
            y = chunkStartX + random.nextInt(16);
            z = random.nextInt(32);
            something = chunkStartZ + random.nextInt(16);
            // TODO generate gold ore
        }

        for (x = 0; x < 8; x++) {
            y = chunkStartX + random.nextInt(16);
            z = random.nextInt(16);
            something = chunkStartZ + random.nextInt(16);
            // TODO generate redstone ore
        }

        for (x = 0; x < 1; x++) {
            y = chunkStartX + random.nextInt(16);
            z = random.nextInt(16);
            something = chunkStartZ + random.nextInt(16);
            // TODO generate diamond ore
        }

        for (byte x1 = 0; x1 < Chunk.WIDTH; x1++) {
            for (byte z1 = 0; z1 < Chunk.WIDTH; z1++) {

                for (int y1 = 127; y1 >= 0; y1--) {
                    if (y1 <= random.nextInt(6) - 1) {
                        builder.block(x1, y1, z1, Material.BEDROCK.blockId);
                    }
                }
                builder.block(x1, 0, z1, Material.BEDROCK.blockId);
            }
        }

        // TODO skyLight
        // TODO blockLight

        for (LazyBlock lb : lazyBlocks) {
//            lb.place();
        }
    }

    private void generateLake(Chunk.Builder builder, int x, int y, int z, Material liquid, List<LazyBlock> lazyBlocks) {
        x -= 8;
        y -= 4;

        boolean[] var6 = new boolean[2048];

        for (int i = 0; i < random.nextInt(4) + 4; i++) {
            double var9 = random.nextDouble() * 6 + 3;
            double var11 = random.nextDouble() * 4 + 2;
            double var13 = random.nextDouble() * 6 + 3;
            double var15 = random.nextDouble() * (16.0D - var9 - 2.0D) + 1.0D + var9 / 2.0D;
            double var17 = random.nextDouble() * (8.0D - var11 - 4.0D) + 2.0D + var11 / 2.0D;
            double var19 = random.nextDouble() * (16.0D - var13 - 2.0D) + 1.0D + var13 / 2.0D;

            for(int var21 = 1; var21 < 15; ++var21) {
                for(int var22 = 1; var22 < 15; ++var22) {
                    for(int var23 = 1; var23 < 7; ++var23) {
                        double var24 = ((double)var21 - var15) / (var9 / 2.0D);
                        double var26 = ((double)var23 - var17) / (var11 / 2.0D);
                        double var28 = ((double)var22 - var19) / (var13 / 2.0D);
                        double var30 = var24 * var24 + var26 * var26 + var28 * var28;
                        if(var30 < 1.0D) {
                            var6[(var21 * 16 + var22) * 8 + var23] = true;
                        }
                    }
                }
            }
        }

        int dz;
        int dy;
        for (int dx = 0; dx < 16; ++dx) {
            for (dy = 0; dy < 16; ++dy) {
                for (dz = 0; dz < 8; ++dz) {
                    boolean var33 = !var6[(dx * 16 + dy) * 8 + dz] && (dx < 15 && var6[((dx + 1) * 16 + dy) * 8 + dz] || dx > 0 && var6[((dx - 1) * 16 + dy) * 8 + dz] || dy < 15 && var6[(dx * 16 + dy + 1) * 8 + dz] || dy > 0 && var6[(dx * 16 + (dy - 1)) * 8 + dz] || dz < 7 && var6[(dx * 16 + dy) * 8 + dz + 1] || dz > 0 && var6[(dx * 16 + dy) * 8 + (dz - 1)]);
                    if (var33 && builder.isInChunk(x + dx, z + dy)) {
                        Block block = builder.getWorld().getBlock(x + dx, y + dz, z + dy);
                        Material material = Material.get(block.id(), block.metadata());
                        if (dz >= 4 && material.liquid) {
                            return;
                        }

                        // TODO material.solid check might need to be material.transparent?
                        if (dz < 4 && !material.solid && builder.getWorld().getBlock(x + dx, y + dz, z + dy).id() != liquid.id) {
                            return;
                        }
                    }
                }
            }
        }

        for (int var8 = 0; var8 < 16; ++var8) {
            for (dy = 0; dy < 16; ++dy) {
                for (dz = 0; dz < 8; ++dz) {
                    if (var6[(var8 * 16 + dy) * 8 + dz]) {
                        Material material = dz >= 4 ? Material.AIR : liquid;
                        setBlock(builder, x + var8, y + dz, z + dy, material, lazyBlocks);
                    }
                }
            }
        }

        for (int var8 = 0; var8 < 16; ++var8) {
            for(dy = 0; dy < 16; ++dy) {
                for(dz = 4; dz < 8; ++dz) {
                    Block block = builder.world.getBlock(x + var8, y + dz - 1, z + dy);
                    if(var6[(var8 * 16 + dy) * 8 + dz] && block.id() == Material.DIRT.blockId) { // && world.getSavedLightValue(EnumSkyBlock.Sky, x + var8, y + var10, z + var32) > 0) {
//                        world.setBlockWithNotify(x + var8, y + var10 - 1, z + var32, Block.grass.blockID);
                        setBlock(builder, x + var8, y + dz - 1, z + dy, Material.GRASS, lazyBlocks);
                    }
                }
            }
        }
    }

    private void setBlock(Chunk.Builder builder, int x, int y, int z, Material material, List<LazyBlock> lazyBlocks) {
        if (builder.isInChunk(x, z)) {
            builder.block(Block.getChunkRelativeX(x), y, Block.getChunkRelativeZ(z), (byte) material.id, (byte) material.metadata);
        } else {
//            lazyBlocks.add(new LazyBlock(builder.world, x, y, z, material));
        }
    }

    private class LazyBlock {
        private final World world;
        private final int x, y, z;
        private final Material material;

        public LazyBlock(World world, int x, int y, int z, Material material) {
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.material = material;
        }

        public void place() {
            world.setBlock(x, y, z, material);
        }
    }

}
