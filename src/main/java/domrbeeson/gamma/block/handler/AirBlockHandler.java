package domrbeeson.gamma.block.handler;

public class AirBlockHandler implements BlockHandler {

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean isPermeable() {
        return true;
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

}
