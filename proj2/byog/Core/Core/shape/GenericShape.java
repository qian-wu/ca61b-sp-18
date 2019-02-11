package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.lab5.Position;

public abstract class GenericShape implements Shape {
    protected Position p, entrance;
    protected Size s;
    protected int orient;
    protected TETile[][] tiles;

    public GenericShape(TETile[][] tiles, Position entrance, Position p, Size s, int orient) {
        this.p = p;
        this.s = s;
        this.orient = orient;
        this.tiles = tiles;
        this.entrance = entrance;
    }

    @Override
    public abstract void draw();

    public void draw(TETile[][] tiles, TETile t) {
        for (int i = 0; i < s.width; i++) {
            for (int j = 0; j < s.height; j++) {
                tiles[p.x + i][p.y + j] = t;
            }
        }
    }
}

