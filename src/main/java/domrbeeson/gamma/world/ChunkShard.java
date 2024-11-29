package domrbeeson.gamma.world;

import domrbeeson.gamma.Tickable;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChunkShard implements Tickable {

    private final Set<Chunk> chunks = new CopyOnWriteArraySet<>();

    private int xMin, xMax, zMin, zMax;

    public ChunkShard(Chunk chunk) {
        xMin = chunk.getChunkX();
        xMax = xMin;
        zMin = chunk.getChunkZ();
        zMax = zMin;
        add(chunk);
    }

    public boolean add(Chunk chunk) {
        int x = chunk.getChunkX();
        int z = chunk.getChunkZ();

        // Check if chunk is within bounds +- 1
        if (x < xMin - 1) {
            return false;
        }
        if (x > xMax + 1) {
            return false;
        }
        if (z < zMin - 1) {
            return false;
        }
        if (z > zMax + 1) {
            return false;
        }

        if (chunks.add(chunk)) {
            xMin = Math.min(x, xMin);
            xMax = Math.max(x, xMax);
            zMin = Math.min(z, zMin);
            zMax = Math.max(z, zMax);
            return true;
        }
        return false;
    }

    public void remove(Chunk chunk) {
        chunks.remove(chunk);
        recalculateBounds();
    }

    public void remove(Collection<Chunk> chunks) {
        this.chunks.removeAll(chunks);
        recalculateBounds();
    }

    private void recalculateBounds() {
        int x, z;
        for (Chunk chunk : chunks) {
            x = chunk.getChunkX();
            z = chunk.getChunkZ();
            xMin = Math.min(x, xMin);
            xMax = Math.max(x, xMax);
            zMin = Math.min(z, zMin);
            zMax = Math.max(z, zMax);
        }
    }

    public Set<Chunk> getChunks() {
        return chunks;
    }

    public int getXMin() {
        return xMin;
    }

    public int getXMax() {
        return xMax;
    }

    public int getZMin() {
        return zMin;
    }

    public int getZMax() {
        return zMax;
    }

    public boolean merge(ChunkShard shard) {
        if (shard == this) {
            return false;
        }

        // TODO bounds check

        chunks.addAll(shard.getChunks());
        xMin = Math.min(shard.getXMin(), xMin);
        xMax = Math.max(shard.getXMax(), xMax);
        zMin = Math.min(shard.getZMin(), zMin);
        zMax = Math.max(shard.getZMax(), zMax);
        return true;
    }

    @Override
    public void tick(long ticks) {
        for (Chunk chunk : chunks) {
            chunk.tick(ticks);
        }
    }
}
