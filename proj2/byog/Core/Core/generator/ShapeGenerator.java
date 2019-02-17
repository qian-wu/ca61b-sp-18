package byog.Core.Core.generator;

import byog.Core.Core.shape.*;
import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.util.Random;

//Random generator Shape
public class ShapeGenerator {
    private static final long SEED = 43;
    private static final Random RANDOM = new Random(SEED);
    private static int count = 0;

    private static Shape getShape(TETile[][] tiles, Position entrance, boolean isWall, int orient) {
        // 50% chance get null
//        int chance = RandomUtils.uniform(RANDOM, 4);
//        if (chance == 1) {
//            return null;
//        }
//        System.out.println("Entrance Position : " + entrance.x + " " + entrance.y);
//        if (count > 34) {
//            return null;
//        }

        Shape result;
        Size s = getRandomSize();

        Position drawPosition = getValidDrawPositon(tiles, entrance, s, orient);
        if (drawPosition == null) {
            return null;
        }

        if (isWall) {
            result = new Hallway(tiles, entrance, s, orient);
        } else {
            result = new Rectangle(tiles, entrance, drawPosition, s, orient);
        }

        count ++;
        return result;
    }

    private static Position getValidDrawPositon(TETile[][] tiles, Position entrance, Size s, int orient) {
        // get start position
        Position drawPosition = getStartPosition(entrance, s, orient);
//        System.out.println("Start Position : " + drawPosition.x + " " + drawPosition.y);

        while(!isEmptyTiles(tiles, drawPosition, s)) {

            if (s.width > 2 && s.height > 2 ) {
                drawPosition = getStartPosition(entrance, s, orient);
            } else {
//                System.out.println("    size less than 3 :" + s.width + " " + s.height);
//                System.out.println("    or not validation start position : " + drawPosition.x + " " + drawPosition.y);
                return null;
            }
        }
        return drawPosition;
    }

    private static boolean isEmptyTiles(TETile[][] tiles, Position p, Size s) {
        int mapWidth = tiles.length;
        int mapHeight = tiles[0].length;

        for (int i = p.x; i < p.x + s.width; i++) {
            if (i < 0 || i >= mapWidth) {
//                System.out.println("    out of map on x " + i);
                s.width -= 1;
//                System.out.println("    update size to " + s.width + " " + s.height);
                return false;
            }

            for (int j = p.y; j < p.y + s.height; j++) {
                if (j < 0 || j >= mapHeight) {
//                    System.out.println("    out of map on y " + j);
                    s.height -= 1;
//                    System.out.println("    update size to " + s.width + " " + s.height);
                    return false;
                }

                if (!tiles[i][j].equals(Tileset.NOTHING)) {
//                    System.out.println("    map is not empty on x " + i + " y " + j);
                    Size newSize = ShapeGenerator.resize(s);
                    s.width = newSize.width;
                    s.height = newSize.height;
//                    System.out.println("    update size to " + s.width + " " + s.height);
                    return false;
                }
            }
        }
        return true;
    }

    private static Size getRandomSize() {
        Size s;// get Random Size
        int width = RandomUtils.uniform(RANDOM, 5,10);
        int height = RandomUtils.uniform(RANDOM, 5,10);
        s = new Size(width, height);
        return s;
    }

    public static Position getRandomPosition(int mapWidth, int mapHeight) {
        Position p;// get Random Size
        int x = (int) RandomUtils.gaussian(RANDOM, mapWidth / 2,10);
        int y = (int) RandomUtils.gaussian(RANDOM, mapHeight / 2,10);
        p = new Position(x, y);
        return p;
    }

    public static int getRandomOrient() {
        return RandomUtils.uniform(RANDOM, 4);
    }

    public static Rectangle getRectangle(TETile[][] tiles, Position p, int orient) {
        return (Rectangle) getShape(tiles, p, false, orient);
    }

    public static Wall getWall(TETile[][] tiles, Position p, int orient) {
        return (Wall)getShape(tiles, p, true, orient);
    }

    public static Position getStartPosition(Position entrance, Size s, int orient) {
        Position p = new Position(entrance.x, entrance.y);

        int adjustX = RandomUtils.uniform(RANDOM, 1, s.width - 1);
        int adjustY = RandomUtils.uniform(RANDOM, 1, s.height - 1);

        if (adjustX == 0) {
            adjustX = 1;
        }

        if (adjustY == 0) {
            adjustY = 1;
        }

        switch (orient) {
            case 0:
                p.y -= s.height - 1;
                p.x -= adjustX; break;
            case 1:
                p.x -= adjustX; break;
            case 2:
                p.y -= adjustY;
                p.x -= s.width - 1; break;
            case 3:
                p.y -= adjustY; break;
        }

        return p;
    }

    public static Size resize(Size s) {
        int chance = RandomUtils.uniform(RANDOM, 2);
        if (chance == 0) {
            return new Size(s.width, s.height - 3);
        } else {
            return new Size(s.width - 3, s.height);
        }
    }

    public static int getRandomShift(int start, int end) {
        if (start == end) {
            return 1;
        }
        return RandomUtils.uniform(RANDOM, start, end);
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 20; i++) {
//            System.out.println("Orient : " + ShapeGenerator.getRandomOrient());
//        }
//    }
}
