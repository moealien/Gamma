package domrbeeson.gamma.network.packet.out;

import domrbeeson.gamma.block.tile.SignTileEntity;
import domrbeeson.gamma.network.packet.Packet;
import domrbeeson.gamma.network.packet.PacketOut;

import java.io.DataOutputStream;
import java.io.IOException;

public class SignUpdatePacketOut extends PacketOut {

    private final SignTileEntity sign;

    public SignUpdatePacketOut(SignTileEntity sign) {
        super(Packet.SIGN_UPDATE);
        this.sign = sign;
    }

    @Override
    public void send(int protocol, DataOutputStream stream) throws IOException {
        stream.writeInt(sign.getX());
        stream.writeShort(sign.getY());
        stream.writeInt(sign.getZ());
        for (String line : sign.getLines()) {
            writeString(protocol, stream, line);
        }
    }

}
