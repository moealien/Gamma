package domrbeeson.gamma.network.packet.out;

import domrbeeson.gamma.network.packet.Packet;
import domrbeeson.gamma.network.packet.PacketOut;
import domrbeeson.gamma.world.Chunk;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

public class ChunkPacketOut extends PacketOut {

    private static final int DATA_SIZE = (int) Math.round(Chunk.WIDTH * Chunk.HEIGHT * Chunk.WIDTH * 2.5);
    private static final int BLOCK_METADATA_OFFSET = 32768;
    private static final int SKY_LIGHT_OFFSET = 49152;
    private static final int BLOCK_LIGHT_OFFSET = 65536;

    private final int blockStartX, blockStartZ;
    private final byte[] compressedChunkData;
    private final int compressedDataSize;

    private final Chunk chunk;

    public ChunkPacketOut(Chunk chunk, int compressionLevel) {
        super(Packet.CHUNK);
        this.chunk = chunk;

        blockStartX = chunk.getChunkX() * Chunk.WIDTH;
        blockStartZ = chunk.getChunkZ() * Chunk.WIDTH;

        byte[][][] blocks = chunk.getRawBlocks();
        byte[][][] metadata = chunk.getRawBlockMetadata();
        byte[][][] skyLight = chunk.getRawSkyLight();
        byte[][][] blockLight = chunk.getRawBlockLight();
        byte x, z;
        int y;
        byte[] chunkData = new byte[DATA_SIZE];
        for (int i = 0; i < 32768; i++) {
            x = (byte) (i >> 11);
            y = i & 127;
            z = (byte) ((i >> 7) & 15);

            chunkData[i] = blocks[x][y][z];
            int i2 = i >> 1;
            if ((i & 1) == 0) {
                chunkData[i2 + BLOCK_METADATA_OFFSET] = setLowerNibble(chunkData[i2 + BLOCK_METADATA_OFFSET], metadata[x][y][z]);
                chunkData[i2 + SKY_LIGHT_OFFSET] = setLowerNibble(chunkData[i2 + SKY_LIGHT_OFFSET], skyLight[x][y][z]);
                chunkData[i2 + BLOCK_LIGHT_OFFSET] = setLowerNibble(chunkData[i2 + BLOCK_LIGHT_OFFSET], blockLight[x][y][z]);
            } else {
                chunkData[i2 + BLOCK_METADATA_OFFSET] = setUpperNibble(chunkData[i2 + BLOCK_METADATA_OFFSET], metadata[x][y][z]);
                chunkData[i2 + SKY_LIGHT_OFFSET] = setUpperNibble(chunkData[i2 + SKY_LIGHT_OFFSET], skyLight[x][y][z]);
                chunkData[i2 + BLOCK_LIGHT_OFFSET] = setUpperNibble(chunkData[i2 + BLOCK_LIGHT_OFFSET], blockLight[x][y][z]);
            }
        }

        Deflater deflater = new Deflater(compressionLevel);
        try {
            deflater.setInput(chunkData);
            deflater.finish();
            compressedChunkData = new byte[chunkData.length];
            compressedDataSize = deflater.deflate(compressedChunkData);
        } finally {
            deflater.end();
        }
    }

    private byte setUpperNibble(byte data, byte upper) {
        return (byte)(data | upper & 15);
    }

    private byte setLowerNibble(byte data, byte lower) {
        return (byte)(lower << 4 | data & 15);
    }

    @Override
    public void send(int protocol, DataOutputStream stream) throws IOException {
        stream.writeInt(blockStartX);
        stream.writeShort(0);
        stream.writeInt(blockStartZ);
        stream.write(Chunk.WIDTH - 1);
        stream.write(Chunk.HEIGHT - 1);
        stream.write(Chunk.WIDTH - 1);
        stream.writeInt(compressedDataSize);
        stream.write(compressedChunkData, 0, compressedDataSize);
    }

    public Chunk getChunk() {
        return chunk;
    }

}
