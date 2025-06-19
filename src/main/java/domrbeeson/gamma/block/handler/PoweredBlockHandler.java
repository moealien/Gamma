package domrbeeson.gamma.block.handler;

import domrbeeson.gamma.MinecraftServer;
import domrbeeson.gamma.block.Block;

public abstract class PoweredBlockHandler implements BlockHandler {

//    @Override
//    public void updateAdjacent(Block me, Block adjacent) {
//        // TODO call onPower with the amount of power this block is receiving
//    }

    public abstract void onDirectPower(MinecraftServer server, Block me, byte powerLevel);

}
