package domrbeeson.gamma.network.packet.in;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.block.tile.SignTileEntity;
import domrbeeson.gamma.block.tile.TileEntity;
import domrbeeson.gamma.network.packet.Packet;
import domrbeeson.gamma.player.Player;
import domrbeeson.gamma.player.PlayerConnection;

import java.io.DataInputStream;
import java.io.IOException;

public class SignUpdatePacketIn extends WorldPacketIn {

    private final int x;
    private final short y;
    private final int z;
    private final String[] lines = new String[4];

    protected SignUpdatePacketIn(MinecraftServer server, PlayerConnection connection, DataInputStream stream) throws IOException {
        super(Packet.SIGN_UPDATE, server, connection, stream);

        x = stream.readInt();
        y = stream.readShort();
        z = stream.readInt();

        int protocol = getProtocol();
        lines[0] = readString(protocol, stream);
        lines[1] = readString(protocol, stream);
        lines[2] = readString(protocol, stream);
        lines[3] = readString(protocol, stream);
    }

    @Override
    public void handle() {
        TileEntity te = getPlayer().getWorld().getTileEntity(x, y, z);
        if (te == null) {
            return;
        }
        if (!(te instanceof SignTileEntity sign)) {
            return;
        }

        Player player = getServer().getPlayerManager().get(getConnection());
        if (sign.setLines(lines, player)) {
            player.setEditingSign(null);
        }
    }

}
