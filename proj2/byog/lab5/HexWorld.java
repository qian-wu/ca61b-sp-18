package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private int width;
    private int height;
    private int n;
    private TERenderer ter = new TERenderer();
    private TETile[][] tiles;

    private static final long SEED = 43;
    private static final Random RANDOM = new Random(SEED);

    //return width for the hexworld
    private int getWorldWidth(int n) {
        return 11 * n - 6;
    }

    //return height for the hexworld
    private int getWorldHeight(int n) {
        return 10 * n;
    }

    public HexWorld(int n) {
        this.width = getWorldWidth(n);
        this.height = getWorldHeight(n);
        this.n = n;

        ter.initialize(this.width, this.height);

        tiles = new TETile[this.width][this.height];
        initWorld(tiles);
    }

    private Position startPosition() {
        return new Position(5 * n - 2, 0);
    }

    private Position nextColPosition(Position p, boolean isLeft) {
        int nextX = p.x;
        int nextY = p.y + n;

        if (isLeft) {
            nextX -= 2 * n - 1;
        } else {
            nextX += 2 * n - 2;
        }

        return new Position(nextX, nextY);
    }

    private Position nextRowPosition(Position p) {
        return new Position(p.x, p.y + 2 * n);
    }

    private void initWorld(TETile[][] tiles) {
        for (int x = 0; x < this.width; x += 1) {
            for (int y = 0; y < this.height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.MOUNTAIN;
            default: return Tileset.FLOOR;
        }
    }

    private void drawRows(TETile[][] tiles, Position p, int count) {
        for (int i = 0; i < count; i += 1) {
            DrawHexgons.addHexagon(tiles, p, n, randomTile());
            p = nextRowPosition(p);
        }
    }

    private void draw(TETile[][] tiles, Position p, int count, boolean isLeft) {
        if (count < 3) {
            return;
        }
        p = nextColPosition(p, isLeft);
        drawRows(tiles, p, count);
        draw(tiles, p, count - 1, isLeft);
    }


    public void draw() {

        //get start position
        Position start = startPosition();
        drawRows(tiles, start, 5);

        draw(tiles, start, 4, true);
        draw(tiles, start, 4, false);

        ter.renderFrame(tiles);
    }

    public static void main(String[] args) {
        int n = 4;
        HexWorld hex = new HexWorld(n);
        hex.draw();
    }
}
