package domrbeeson.gamma.world.terrain;

import domrbeeson.gamma.item.Material;
import domrbeeson.gamma.utils.OpenSimplex.OpenSimplex2S;
import domrbeeson.gamma.world.Chunk;

import java.util.Random;

public class AlienGenerator implements TerrainGenerator {
//    todo import actual world seed (?)
    private final Random chaos = new Random();
    private static final double frequency = 1.0 / 12.0;
    private static final double frequencyLow = 1.0 / 128.0;
    private static final double frequencyMid = 1.0 / 69.420;

//    1.0 / 128.0 = expansive
//    1.0 / 12.0 = compact

    @Override
    public void generate(Chunk.Builder chunk) {
        System.out.print("Generating chunk XZ [" + chunk.x + ", " + chunk.z + "] ");

//        problem: looping 0-15 for each chunk
//        must add offset via chunk.x/z which is the chunk relative coordinate
//        but when we set a block it sets ok regardless of that fact
//        so perhaps the noise functions must use the offset values but always write to the base xyz
//        tf man

        double oX = chunk.x * Chunk.WIDTH;
        double oZ = chunk.z * Chunk.WIDTH;

        for (byte x = 0; x < Chunk.WIDTH; x++) {
            System.out.print(" x" + x);

            for (byte z = 0; z < Chunk.WIDTH; z++) {
//              bedrock floor
                chunk.block(x, 0, z, Material.BEDROCK.blockId);
//              stone subfloor
                chunk.block(x, 1, z, Material.STONE.blockId);

//              make SINE wave hills along x where height of hill is y
                int sineYMaxHeight = 12;
                int sineY = steppedSine(x, Chunk.WIDTH, sineYMaxHeight);

//              grass/dirt
//                chunk.block(x, sineY, z, Material.GRASS.blockId);

//                for (int y = sineY - 1; y >= 1; y--) {
//                    chunk.block(x, y, z, Material.DIRT.blockId); // dirt... todo detritus
//                }

//              noise is -1 to 1 range (i think)

//              scale that range to 0-127 and use for hills check should beat steppedSine

                double hillsNoiseValue = OpenSimplex2S.noise2_ImproveX(124556, ((double) x + oX) * frequencyMid, ((double) z + oZ) * frequencyMid);
                int finalHeight = (int) ((hillsNoiseValue + 1) / 2 * 18);

                for (int y = 127 - 1; y >= 1; y--) {
                    if (y <= finalHeight) {
                        chunk.block(x, y, z, Material.DIRT.blockId);
                    } else {
                        if (y < 10) {
                            chunk.block(x, y, z, Material.WATER_SOURCE.blockId);
                        }
                    }
                }

//                for (int y = 20 - 1; y >= 1; y--) {
//                    double weight = OpenSimplex2S.noise3_ImproveXZ(123456, ((double) x + oX) * frequency, (double) y * frequency, ((double) z + oZ) * frequency);
//
////                    cave experiment
//                    if (weight >= 0.25) {
//                        chunk.block(x, y, z, Material.STONE.blockId);
//                    } else if (weight <= -0.25) {
//                        chunk.block(x, y, z, Material.STONE.blockId);
//                    }
//                }

//                todo chance to spawn floating ominous spheres
//                todo chunk variants (alien biomes)

                //                basic scattering...  chance for a tile to have a strange pillar
                if (chaos.nextInt(64 * 32) == 0) {
                    int maxPillarHeight = 42;
                    int minPillarHeight = 5;
                    int pillarHeight = chaos.nextInt((maxPillarHeight - minPillarHeight) + 1) + minPillarHeight; // gives 5–12

//                  make obsidian column
                    for (int y = pillarHeight; y >= 1; y--) {
                        chunk.block(x, y, z, Material.OBSIDIAN.blockId);
                    }

//                  todo gnarlier
//                  we can use distance function to make it a better shape
                }

            }
        }

        System.out.println();
    }


    private static int steppedSine(int stepIndex, int totalSteps, int max) {
        // normalised sine [-1, 1]
        double angle = 2 * Math.PI * stepIndex / totalSteps;
        double sine = Math.sin(angle);

        double scaled = 2 + (sine + 1) * (max - 2) / 2;

        return (int) Math.round(scaled); // step int
    }
}
