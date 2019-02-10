package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.lab5.Position;

public abstract class GenericShape implements Shape {
    protected Position p;
    protected Size s;
    protected int orient;

    public GenericShape(Position p, Size s, int orient) {
        this.p = p;
        this.s = s;
        this.orient = orient;
    }

    @Override
    public abstract void draw(TETile[][] tiles);


    public void draw(TETile[][] tiles, TETile t) {
        for (int i = 0; i < s.width; i++) {
            for (int j = 0; j < s.height; j++) {
                tiles[p.x + i][p.y + j] = t;
            }
        }
    }
}

