package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.lab5.Position;

public class GenericShape implements Shape {
    protected Position p;
    protected int width;
    protected int high;

    public GenericShape(Position p, int width, int high) {
        this.p = p;
        this.width = width;
        this.high = high;
    }

    @Override
    public void draw(TETile[][] tiles, TETile t) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < high; j++) {
                tiles[p.x + i][p.y + j] = t;
            }
        }
    }
}

