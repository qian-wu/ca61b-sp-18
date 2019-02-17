package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public class Floor extends AbstractShape {
    private static final TETile t = Tileset.FLOOR;

    public Floor(TETile[][] tiles, Position entrance, Size s) {
        super(tiles, entrance, entrance, s, 0);
    }

    public void draw() {
        draw(t);
    }

}
