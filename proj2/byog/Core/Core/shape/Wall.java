package byog.Core.Core.shape;

import byog.Core.Core.generator.ShapeGenerator;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

public class Wall extends AbstractShape {

    private static final TETile t = Tileset.WALL;
    private int width;
    private boolean isExtend;  // is to extend other shape

    public Wall(TETile[][] tiles, Position entrance, int width, int orient, boolean isExtend) {
        super(tiles, entrance, entrance, null, orient);
        this.width = width;
        this.isExtend = isExtend;

        if (orient < 2) {
            s = new Size(width, 1);
        } else {
            s = new Size(1, width);
        }
    }

    public void draw() {
        // draw wall
        draw(t);
    }

    public void extendWall() {
        if (isExtend) {
            // get door on the wall
            Position door = getDoor();

            // get Shape on new entrance
            // get new entrance
            Position newEntrance = getNewEntrance(door);
            if (newEntrance != null) {

                // test if new entrance is valid
//            setFloor(newEntrance);
                Shape shape = ShapeGenerator.getRectangle(tiles, newEntrance, orient);
                if (shape != null) {
                    // set door and new entrance to be floor
                    System.out.println("    Draw door on " + orient + " at " + door.x + " " + door.y);
                    setFloor(door);
                    System.out.println("    Draw new entrance on " + orient + " at " + newEntrance.x + " " + newEntrance.y);
                    setFloor(newEntrance);
                    shape.draw();
                }
            }

        }
    }

    public Position getDoor() {

        if (orient < 2) {
            int shift = ShapeGenerator.getRandomShift(1, s.width - 2);
            return new Position(entrance.x + shift, entrance.y);
        }
        int shift = ShapeGenerator.getRandomShift(1, s.height - 2);
        return new Position(entrance.x, entrance.y + shift);
    }

    private Position getNewEntrance(Position door) {

        int x = door.x;
        int y = door.y;

        int mapWidth = tiles.length;
        int mapHeight = tiles[0].length;

        switch (orient) {
            case 0:
                y -= 1;break;
            case 1:
                y += 1;break;
            case 2:
                x -= 1;break;
            case 3:
                x += 1;break;
        }

        //check new entrance is validate
        if (x < 0 || x >= mapWidth || y < 0 || y >= mapHeight) {
            System.out.println("    new entrance is fail: " + x + " " + y);
            return null;
        }
        return new Position(x, y);
    }

}
