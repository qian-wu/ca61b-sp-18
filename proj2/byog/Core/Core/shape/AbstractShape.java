package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public abstract class AbstractShape implements Shape {
    protected Position drawPosition, entrance;
    protected Size s;
    protected int orient;
    protected TETile[][] tiles;

    public AbstractShape(TETile[][] tiles, Position entrance, Position drawPosition, Size s, int orient) {
        this.drawPosition = drawPosition;
        this.s = s;
        this.orient = orient;
        this.tiles = tiles;
        this.entrance = entrance;
    }

    @Override
    public abstract void draw();

    public void draw(TETile t) {
        for (int i = 0; i < s.width; i++) {
            for (int j = 0; j < s.height; j++) {
                tiles[drawPosition.x + i][drawPosition.y + j] = t;
            }
        }
    }

    public void setFloor(Position p)  {
        tiles[p.x][p.y] = Tileset.FLOOR;
    }
}

