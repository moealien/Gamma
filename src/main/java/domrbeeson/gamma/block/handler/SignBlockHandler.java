package domrbeeson.gamma.block.handler;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.block.tile.SignTileEntity;
import domrbeeson.gamma.item.Item;
import domrbeeson.gamma.item.Material;
import domrbeeson.gamma.world.Chunk;

import java.util.List;

public class SignBlockHandler extends TileEntityBlockHandler<SignTileEntity> {

    private static final List<Item> DROPS = List.of(Material.SIGN.getItem());

    public SignBlockHandler() {
        super(SignTileEntity.class);
    }

    @Override
    public void onPlace(MinecraftServer server, Block block) {
        // TODO currently there's no way of checking which player edited which sign, so anyone can send a sign change packet and set any sign to whatever they want
        block.chunk().addTileEntity(new SignTileEntity((x, z) -> block.chunk(), block.x(), block.y(), block.z()));
    }

    @Override
    public List<Item> getDrops(MinecraftServer server, Chunk chunk, int x, int y, int z, byte blockId, byte blockMetadata, short toolId) {
        return DROPS;
    }

}
