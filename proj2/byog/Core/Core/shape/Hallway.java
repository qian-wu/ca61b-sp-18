package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.lab5.Position;

public class Hallway extends Rectangle{

    public Hallway(Position p, Size s, int orient) {
        super(p, s, orient);
    }

    @Override
    public void draw(TETile[][] tiles) {
        super.draw(tiles);
    }
}
