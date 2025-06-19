package domrbeeson.gamma.event.events.block.tile;

import domrbeeson.gamma.block.tile.SignTileEntity;
import domrbeeson.gamma.event.Event;
import domrbeeson.gamma.event.events.CancellableEvent;

public class SignEditorOpenEvent extends CancellableEvent implements Event.WorldEvent {

    private final SignTileEntity sign;

    public SignEditorOpenEvent(SignTileEntity sign) {
        this.sign = sign;
    }

    public SignTileEntity getSign() {
        return sign;
    }

}
