package domrbeeson.gamma.network.packet.in;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.network.packet.Packet;
import domrbeeson.gamma.player.Player;
import domrbeeson.gamma.player.PlayerConnection;
import domrbeeson.gamma.version.MinecraftVersion;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class PacketIn {

    @FunctionalInterface
    public interface PacketCreator {
        PacketIn apply(MinecraftServer server, PlayerConnection connection, DataInputStream stream) throws IOException;
    }

    private static final PacketCreator[] PACKETS = new PacketCreator[256];

    private final Packet packet;
    private final MinecraftServer server;
    private final PlayerConnection connection;
    private final DataInputStream stream;

    protected PacketIn(Packet packet, MinecraftServer server, PlayerConnection connection, DataInputStream stream) throws IOException {
        this.packet = packet;
        this.server = server;
        this.connection = connection;
        this.stream = stream;
    }

    public final Packet getPacket() {
        return packet;
    }

    public final MinecraftServer getServer() {
        return server;
    }

    public final PlayerConnection getConnection() {
        return connection;
    }

    protected final DataInputStream getStream() {
        return stream;
    }

    public final int getProtocol() {
        Player player = server.getPlayerManager().get(connection);
        if (player == null) {
            // Can be null when player is first connecting because the initial packet doesn't send the client's protocol version
            return -1;
        }
        return player.getProtocol();
    }

    public abstract void queue(); // Queues it for processing with the correct packet handler - world packets e.g. movement, server packets e.g. ping, chat
    public abstract void handle(); // Does something with the packet

    protected final String readString(int protocol, DataInputStream stream) throws IOException {
        if (protocol <= MinecraftVersion.BETA_1_4.features.protocol()) {
            // <= Beta 1.4
            return stream.readUTF();
        } else {
            // >= Beta 1.5
            short length = stream.readShort();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(stream.readChar());
            }
            return builder.toString();
        }
    }

    @Nullable
    public static PacketCreator getById(int id) {
        return PACKETS[id] != null ? PACKETS[id] : null;
    }
    
    private static void register(Packet packet, PacketCreator func) {
        PACKETS[packet.id] = func;
    }

    static {
        register(Packet.KEEP_ALIVE, KeepAlivePacketIn::new);
        register(Packet.LOGIN, LoginPacketIn::new);
        register(Packet.HANDSHAKE, HandshakePacketIn::new);
        register(Packet.CHAT, ChatPacketIn::new);

        register(Packet.ENTITY_USE, EntityUsePacketIn::new);

        register(Packet.PLAYER_RESPAWN, PlayerRespawnPacketIn::new);
        register(Packet.FLYING, PlayerFlyingPacketIn::new);
        register(Packet.PLAYER_POSITION, PlayerPositionPacketIn::new);
        register(Packet.PLAYER_LOOK, PlayerLookPacketIn::new);
        register(Packet.PLAYER_POSITION_AND_LOOK, PlayerPositionAndLookPacketIn::new);
        register(Packet.PLAYER_DIGGING, PlayerDiggingPacketIn::new);
        register(Packet.PLAYER_BLOCK_PLACE, PlayerRightClickBlockPacketIn::new);
        register(Packet.PLAYER_HELD_ITEM_CHANGE, PlayerHeldItemChangePacketIn::new);

        register(Packet.PLAYER_ANIMATION, PlayerAnimationPacketIn::new);
        register(Packet.ENTITY_ACTION, EntityActionPacketIn::new);

        register(Packet.CLOSE_WINDOW, WindowClosePacketIn::new);
        register(Packet.WINDOW_CLICK, WindowClickPacketIn::new);

        register(Packet.SIGN_UPDATE, SignUpdatePacketIn::new);

        register(Packet.PLAYER_KICK, PlayerKickPacketIn::new);
    }

}
