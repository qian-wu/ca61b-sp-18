package byog.Core.Core.generator;

import byog.Core.Core.shape.*;
import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.lab5.Position;
import org.w3c.dom.css.Rect;

import java.util.Random;

//Random generator Shape
public class ShapeGenerator {
    private static final long SEED = 43;
    private static final Random RANDOM = new Random(SEED);

    private static Shape getShape(TETile[][] tiles, Position p, boolean isWall, int orient) {
        // 50% chance get null
//        int chance = RandomUtils.uniform(RANDOM, 2);
//        if (chance == 1) {
//            return null;
//        }

        Shape result;
        Size s;

        //get Size
        int width = RandomUtils.uniform(RANDOM, 6,10);
        int height = RandomUtils.uniform(RANDOM, 6,10);
//        int width = 8;
//        int height = 6;

        s = new Size(width, height);

        //get orientation down left right  up
        //                  0    2    3    1
//        int orient = RandomUtils.uniform(RANDOM, 4);

        if (isWall) {
            result = new Hallway(tiles, p, s, orient);
        } else {
            result = new Rectangle(tiles, p, s, orient);
        }

        return result;
    }

    public static Rectangle getRectangle(TETile[][] tiles, Position p, int orient) {
        return (Rectangle) getShape(tiles, p, false, orient);
    }

    public static Wall getWall(TETile[][] tiles, Position p, int orient) {
        return (Wall)getShape(tiles, p, true, orient);
    }

    public static Position getStartPosition(Position p, Size s, int orient) {
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
            return new Size(s.width, s.height - 1);
        } else {
            return new Size(s.width - 1, s.height);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Position p = new Position(10, 10);
            System.out.println("Start position " + p.x + " " + p.y);
            Size s = new Size(4, 3);
            p = getStartPosition(p, s, 0);
            System.out.println("Adjust position " + p.x + " " + p.y);
        }
    }
}
