package byog.Core.Core.shape;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public class TestShapes {
    private int width = 80;
    private int height = 30;
    private TETile[][] tiles;

    private void initWorld(TETile[][] tiles) {
        for (int x = 0; x < this.width; x += 1) {
            for (int y = 0; y < this.height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public void drawWall(Position p) {
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        tiles = new TETile[width][height];
        initWorld(tiles);

        Wall w = new Wall(p, 3, true);
        w.draw(tiles);



        ter.renderFrame(tiles);
    }

    public static void main(String[] args) {
        TestShapes test = new TestShapes();
        test.drawWall(new Position(10, 20));
    }
}
