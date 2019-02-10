package byog.Core.Core.generator;

import byog.Core.Core.shape.*;
import byog.Core.RandomUtils;
import byog.lab5.Position;

import java.util.Random;

//Random generator Shape
public class ShapeGenerator {
    private static final long SEED = 43;
    private static final Random RANDOM = new Random(SEED);

    private static Shape getShape(Position p, boolean isWall) {
        // 50% chance get null
        int chance = RandomUtils.uniform(RANDOM, 1);
        if (chance == 1) {
            return null;
        }

        Shape result;
        Size s;

        //get Size
        int width = RandomUtils.uniform(RANDOM, 3,10);
        int height = RandomUtils.uniform(RANDOM, 3,10);

        s = new Size(width, height);

        //get orientation down left right  up
        //                  0    2    3    1
        int orient = RandomUtils.uniform(RANDOM, 4);

        if (isWall) {
            result = new Hallway(p, s, orient);
        } else {
            result = new Rectangle(p, s, orient);
        }

        return result;
    }

    public static Shape getRectangle(Position p) {
        return getShape(p, false);
    }

    public static Shape getWall(Position p) {
        return getShape(p, true);
    }

    public static Position adjustPosition(Position p, Size s, int orient) {
        switch (orient) {
            case 0:
                p.y -= s.height;
                p.x -= RandomUtils.uniform(RANDOM, 1, s.width);break;
            case 1:
                p.x += RandomUtils.uniform(RANDOM, 1, s.width);break;
            case 2:
                p.y += RandomUtils.uniform(RANDOM, 1, s.height);
                p.x -= s.width;break;
            case 3:
                p.y -= RandomUtils.uniform(RANDOM, 1, s.height);
        }

        return p;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Position p = new Position(10, 10);
            Size s = new Size(4, 3);
            p = adjustPosition(p, s, 3);
            System.out.println(p.x + " " + p.y);
        }
    }
}
