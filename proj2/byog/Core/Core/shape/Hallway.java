package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.lab5.Position;

public class Hallway extends Rectangle{

    public Hallway(TETile[][] tiles, Position entrance, Position p, Size s, int orient) {
        super(tiles, entrance, p, s, orient);
    }

    @Override
    public void draw() {
        super.draw();
    }

}
