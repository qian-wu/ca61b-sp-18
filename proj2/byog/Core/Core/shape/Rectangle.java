package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.lab5.Position;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends AbstractShape {

    public Rectangle(TETile[][] tiles, Position entrance, Position drawPosition, Size s, int orient) {
        super(tiles, entrance, drawPosition, s, orient);
    }

    public void draw() {
        System.out.println("Draw rectangle on : " + entrance.x + " " + entrance.y);
        System.out.println("    size : " + s.width + " " + s.height);
        System.out.println("    orient : " + orient);

        List<Position> corners = getCorners();

        //check is the wall need extend other shape
        boolean[] isExtends = new boolean[]{true, true, true, true};
        checkWallExtention(isExtends);
//        draw walls

        // v0 $  $  v1
        // $  f  f  $
        // $  f  f  $
        // h0 $  $  h1
        //          0  1  2  3
        // corners h0 h1 v0 v1
        Wall w_h0 = new Wall(tiles, corners.get(0), s.width, 0, isExtends[0]);
        Wall w_h1 = new Wall(tiles, corners.get(2), s.width, 1, isExtends[1]);
        Wall w_v0 = new Wall(tiles, corners.get(0), s.height, 2, isExtends[2]);
        Wall w_v1 = new Wall(tiles, corners.get(1), s.height, 3, isExtends[3]);

        w_h0.draw();
        w_h1.draw();
        w_v0.draw();
        w_v1.draw();

        w_h0.extendWall();
        w_h1.extendWall();
        w_v0.extendWall();
        w_v1.extendWall();

        //draw floors
        Position p_f = new Position(drawPosition.x + 1, drawPosition.y + 1);
        Floor f = new Floor(tiles, p_f, new Size(s.width - 2, s.height -2));
        f.draw();

        // set entrance be floor
        setFloor(entrance);
    }

    private void checkWallExtention(boolean[] isExtends) {
        switch (orient) {
            case 0:
                isExtends[1] = false;break;
            case 1:
                isExtends[0] = false;break;
            case 2:
                isExtends[3] = false;break;
            case 3:
                isExtends[2] = false;break;
        }
    }

//    private boolean updatePositon(Position entrance) {
//        while(!isValidPosition()) {
//
//            if (s.width > 2 && s.height > 2 ) {
//
//                update(entrance);
//            } else {
//                System.out.println("    size less than 3 :" + s.width + " " + s.height);
//                System.out.println("    or not validation position p : " + p.x + " " + p.y + " entrance : " + entrance.x + " " + entrance.y);
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private void update(Position entrance) {
////
//        this.p = ShapeGenerator.getStartPosition(new Position(entrance.x, entrance.y), s, orient);
//
//    }
//
//    protected boolean isValidPosition() {
////        if (!isAllCornersInMap()) {
////            System.out.println("corner not in map");
////            return false;
////        }
//
//        for (Position p : getCorners()) {
//            if (!isEmptyTiles(p)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    // must h0 & v1 in map
//    private boolean isAllCornersInMap() {
//        Position h0 = getCorners().get(0);
//        Position v1 = getCorners().get(3);
//
//        return isCornersInMap(h0) && isCornersInMap(v1);
//    }
//
//    private boolean isCornersInMap(Position corner) {
//        int mapWidth = tiles.length;
//        int mapHeight = tiles[0].length;
//
//        return corner.x >= 0 && corner.y >= 0 && corner.x < mapWidth && corner.y < mapHeight;
//    }
//
//    private boolean isEmptyTiles(Position p) {
//        int mapWidth = tiles.length;
//        int mapHeight = tiles[0].length;
//
//        for (int i = p.x; i < p.x + s.width; i++) {
//            if (i < 0 || i >= mapWidth) {
//                System.out.println("    out of map on x " + i);
//                s.width -= 1;
//                System.out.println("    update size to " + s.width + " " + s.height);
//                return false;
//            }
//
//            for (int j = p.y; j < p.y + s.height; j++) {
//                if (j < 0 || j >= mapHeight) {
//                    System.out.println("    out of map on y " + j);
//                    s.height -= 1;
//                    System.out.println("    update size to " + s.width + " " + s.height);
//                    return false;
//                }
//
//                if (!tiles[i][j].equals(Tileset.NOTHING)) {
//                    System.out.println("    map is not empty on x " + i + " y " + j);
//                    this.s = ShapeGenerator.resize(s);
//                    System.out.println("    update size to " + s.width + " " + s.height);
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
    public ArrayList<Position> getCorners() {
        //draw walls
        // v0 $  $  v1
        // $  f  f  $
        // $  f  f  $
        // h0 $  $  h1
        Position p_h0 = drawPosition;
        Position p_v0 = new Position(drawPosition.x, drawPosition.y + s.height - 1);
        Position p_h1 = new Position(drawPosition.x + s.width - 1, drawPosition.y);
        Position p_v1 = new Position(drawPosition.x + s.width - 1, drawPosition.y + s.height - 1);

        ArrayList<Position> corners = new ArrayList();
        corners.add(p_h0);
        corners.add(p_h1);
        corners.add(p_v0);
        corners.add(p_v1);

        return corners;
    }



//    private Position getStartPosition() {
//        return ShapeGenerator.getStartPosition(this.p, this.s, this.orient);
//    }
}
























