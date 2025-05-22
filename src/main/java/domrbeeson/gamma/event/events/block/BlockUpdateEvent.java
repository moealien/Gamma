package domrbeeson.gamma.event.events.block;

import domrbeeson.gamma.block.Block;
import domrbeeson.gamma.event.Event;
import domrbeeson.gamma.event.events.CancellableEvent;

public class BlockUpdateEvent extends CancellableEvent implements Event.WorldEvent {

    private final long ticks;
    private final Block block;

    public BlockUpdateEvent(long ticks, Block block) {
        this.ticks = ticks;
        this.block = block;
    }

    public long getTicks() {
        return ticks;
    }

    public Block getBlock() {
        return block;
    }

}
