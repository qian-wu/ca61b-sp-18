package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public class Floor extends GenericShape {
    private static final TETile t = Tileset.FLOOR;

    public Floor(Position p, int width, int high) {
        super(p, width, high);
    }

    public void draw(TETile[][] tiles) {
        draw(tiles, t);
    }
}
