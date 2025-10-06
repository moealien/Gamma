package domrbeeson.gamma.world.terrain;

import domrbeeson.gamma.item.Material;
import domrbeeson.gamma.world.Chunk;
import domrbeeson.gamma.utils.OpenSimplex.OpenSimplex2S;

import java.util.Random;

public class AlienGenerator implements TerrainGenerator {
//    todo import actual world seed (?)
    private final Random chaos = new Random();

    @Override
    public void generate(Chunk.Builder chunk) {
        for (byte x = 0; x < Chunk.WIDTH; x++) {
            for (byte z = 0; z < Chunk.WIDTH; z++) {
//              bedrock floor
                chunk.block(x, 0, z, Material.BEDROCK.blockId);
//              stone subfloor
                chunk.block(x, 1, z, Material.STONE.blockId);

//              make SINE wave hills along x where height of hill is y
                int sineYMaxHeight = 12;
                int sineY = steppedSine(x, Chunk.WIDTH, 2, sineYMaxHeight);

//              grass/dirt
                chunk.block(x, sineY, z, Material.GRASS.blockId);

                for (int y = sineY - 1; y >= 1; y--) {
                    chunk.block(x, y, z, Material.DIRT.blockId); // dirt... todo detritus
                }

//                basic scattering... 1/32 chance for a tile to have a strange pillar
                if (chaos.nextInt(32) == 0) {
                    int maxPillarHeight = 12;
                    int minPillarHeight = 5;
                    int pillarHeight = chaos.nextInt((maxPillarHeight - minPillarHeight) + 1) + minPillarHeight; // gives 5–12

//                  make obsidian column
                    for (int y = pillarHeight; y >= 1; y--) {
                        chunk.block(x, y, z, Material.OBSIDIAN.blockId);
                    }

//                  todo gnarlier
                }

//                todo investigate this


//                todo chance to spawn floating ominous spheres
//                todo chunk variants (alien biomes)

            }
        }
    }




    private static int steppedSine(int stepIndex, int totalSteps, int min, int max) {
        // normalised sine [-1, 1]
        double angle = 2 * Math.PI * stepIndex / totalSteps;
        double sine = Math.sin(angle);

        double scaled = min + (sine + 1) * (max - min) / 2;

        return (int) Math.round(scaled); // step int
    }
}
