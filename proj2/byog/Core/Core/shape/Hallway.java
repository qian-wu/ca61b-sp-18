package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.lab5.Position;

public class Hallway extends Rectangle{

    public Hallway(TETile[][] tiles, Position p, Size s, int orient) {
        super(tiles, p, s, orient);
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    protected boolean isValidPosition() {
        return super.isValidPosition();
    }
}
