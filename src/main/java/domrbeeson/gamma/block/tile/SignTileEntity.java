package domrbeeson.gamma.block.tile;

import domrbeeson.gamma.network.packet.out.SignUpdatePacketOut;
import domrbeeson.gamma.player.Player;
import domrbeeson.gamma.world.ChunkGetter;

public class SignTileEntity extends TileEntity {

    public static final int MAX_LINE_LENGTH = 15;

    private final String[] lines;

    public SignTileEntity(ChunkGetter chunk, int x, int y, int z) {
        this(chunk, x, y, z, new String[] { "", "", "", "" });
    }

    public SignTileEntity(ChunkGetter chunk, int x, int y, int z, String[] lines) {
        super(chunk, x, y, z);
        this.lines = lines;
    }

    @Override
    public void tick(long ticks) {

    }

    public String getLine(int line) {
        if (!isLineValid(line)) {
            return null;
        }
        return lines[line];
    }

    public String[] getLines() {
        return lines;
    }

    public void setLine(int line, String text) {
        if (!isLineValid(line)) {
            return;
        }
        if (!isTextValid(text)) {
            return;
        }
        lines[line] = text;

        SignUpdatePacketOut packet = new SignUpdatePacketOut(this);
        for (Player viewer : getChunk().getViewers()) {
            viewer.sendPacket(packet);
        }
    }

    private boolean isLineValid(int line) {
        return line >= 0 && line < lines.length;
    }

    private boolean isTextValid(String text) {
        return text.length() <= MAX_LINE_LENGTH;
    }

}
