package domrbeeson.gamma.world;

@FunctionalInterface
public interface ChunkGetter {

    Chunk get(int x, int z);

}
