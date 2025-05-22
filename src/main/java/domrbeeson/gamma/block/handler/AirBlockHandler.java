package domrbeeson.gamma.block.handler;

public class AirBlockHandler extends BlockHandler {

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isPermeable() {
        return true;
    }

}
