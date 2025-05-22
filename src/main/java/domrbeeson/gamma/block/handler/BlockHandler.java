package domrbeeson.gamma.block.handler;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.item.Item;
import domrbeeson.gamma.player.Player;
import domrbeeson.gamma.world.Chunk;

import java.util.List;

public abstract class BlockHandler {

    public void onBreak(MinecraftServer server, Block block, Player player) {
        block.chunk().breakBlockAsPlayer(player, block.x(), block.y(), block.z());
    }

    public void onPlace(MinecraftServer server, Block block) {
    }

    public final List<Item> getDrops(MinecraftServer server, Chunk chunk, int x, int y, int z, byte blockId, byte blockMetadata) {
        return getDrops(server, chunk, x, y, z, blockId, blockMetadata, (short) 0);
    }

    public List<Item> getDrops(MinecraftServer server, Chunk chunk, int x, int y, int z, byte blockId, byte blockMetadata, short toolId) {
        return List.of();
    }

    public void onLeftClick(MinecraftServer server, Block block, Player player) {
    }

    public boolean onRightClick(MinecraftServer server, Block block, Player player) {
        return false;
    }

    public boolean update(MinecraftServer server, Block block, long tick) {
        return false;
    }

    public void randomTick(MinecraftServer server, Chunk chunk, int x, int y, int z, byte id, byte metadata, long tick) {
    }

    public void onContactWithWater(MinecraftServer server, Block block) {
    }

    public void onContactWithLava(MinecraftServer server, Block block) {
    }

    public boolean canPlace(Chunk chunk, int x, int y, int z) {
        return true;
    }

    public boolean isSolid() {
        return true;
    }

    public boolean isLiquid() {
        return false;
    }

    public boolean isPermeable() {
        return false;
    }

    public byte getEmittedLight() {
        return 0;
    }
}
