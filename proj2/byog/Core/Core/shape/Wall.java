package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public class Wall extends GenericShape {

    private static final TETile t = Tileset.WALL;

    public Wall(Position p, Size s, int orient) {
        super(p, s, orient);
    }

    public void draw(TETile[][] tiles) {
        draw(tiles, t);

    }
}
