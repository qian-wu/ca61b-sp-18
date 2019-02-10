package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public class Wall extends GenericShape {
    protected boolean isVertical;
    private static final TETile t = Tileset.WALL;

    public Wall(Position p, int width, boolean isVertical) {
        super(p, 1, 1);
        this.isVertical = isVertical;

        if (isVertical) {
            this.high = width;
        } else {
            this.width = width;
        }
    }

    public void draw(TETile[][] tiles) {
        draw(tiles, t);
    }
}
