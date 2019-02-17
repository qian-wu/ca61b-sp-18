package byog.Core.Core.shape;

import byog.Core.Core.generator.ShapeGenerator;
import byog.Core.RandomUtils;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.util.Random;

public class TestShapes {
    private int width = 80;
    private int height = 30;
    private TETile[][] tiles;

    private static final long SEED = 43;
    private static final Random RANDOM = new Random(SEED);

    private static int[] a = {1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2};


    private void initWorld(TETile[][] tiles) {
        for (int x = 0; x < this.width; x += 1) {
            for (int y = 0; y < this.height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    private Position getRandomPosition() {
        return new Position(RandomUtils.uniform(RANDOM, 80), RandomUtils.uniform(RANDOM, 30));
    }

    //    public void drawWall(Position p) {
//        TERenderer ter = new TERenderer();
//        ter.initialize(width, height);
//
//        tiles = new TETile[width][height];
//        initWorld(tiles);
//
//        Wall w = new Wall(p, 3, true);
//        w.draw(tiles);
//
//        Floor f = new Floor(new Position(p.x + 1, p.y), 3, 3);
//        f.draw(tiles);
//
//        Wall w2 = new Wall(new Position(p.x + 4, p.y), 3, true);
//        w2.draw(tiles);
//
//        ter.renderFrame(tiles);
//    }
//
    public void drawRectangle() {
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        tiles = new TETile[width][height];
        initWorld(tiles);

        Position p = new Position(40, 10);
        Rectangle r = ShapeGenerator.getRectangle(tiles, p, 3);
        r.draw();

        tiles[p.x][p.y] = Tileset.LOCKED_DOOR;


        ter.renderFrame(tiles);
    }

    public static void main(String[] args) {
        TestShapes test = new TestShapes();
        test.drawRectangle();
//        test.drawWall();
    }
}
