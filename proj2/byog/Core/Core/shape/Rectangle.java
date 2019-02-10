package byog.Core.Core.shape;

import byog.Core.Core.generator.ShapeGenerator;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends GenericShape{

    public Rectangle(TETile[][] tiles, Position p, Size s, int orient) {
        super(tiles, p, s, orient);
    }

    public void draw() {
        //adjust start position
        Position startPosition = this.p;

        this.p = getStartPosition();

        if (!updatePositon()) {
            return;
        }

        System.out.println("Position : " + p.x + " " + p.y);
        System.out.println("Size :" + s.width + " " + s.height);
        List<Position> corners = getCorners();
        //draw walls
        Wall w_h0 = new Wall(tiles, corners.get(0), new Size(s.width, 1), 0);
        Wall w_h1 = new Wall(tiles, corners.get(2), new Size(s.width, 1), 1);
        Wall w_v0 = new Wall(tiles, corners.get(0), new Size(1, s.height), 2);
        Wall w_v1 = new Wall(tiles, corners.get(1), new Size(1, s.height), 3);

        w_h0.draw();
        w_h1.draw();
        w_v0.draw();
        w_v1.draw();

        //draw floors
        Position p_f = new Position(p.x + 1, p.y + 1);
        Floor f = new Floor(tiles, p_f, new Size(s.width - 2, s.height -2), 0);
        f.draw();

    }

    private boolean updatePositon() {
        while(!isValidPosition()) {

            System.out.println("Position : " + p.x + " " + p.y);
            System.out.println("Size :" + s.width + " " + s.height);

            if (s.width > 3 && s.height > 3) {
                update();
            } else {
                return false;
            }
        }
        return true;
    }

    private void update() {
        this.s = new Size(s.width - 1, s.height - 1);
        this.p = ShapeGenerator.getStartPosition(p, s, orient);

    }

    protected boolean isValidPosition() {
        if (!isAllCornersInMap()) {
            return false;
        }

        for (Position p : getCorners()) {
            if (!isEmptyTiles(p)) {
                return false;
            }
        }
        return true;
    }

    // must h0 & v1 in map
    private boolean isAllCornersInMap() {
        Position h0 = getCorners().get(0);
        Position v1 = getCorners().get(3);

        return isCornersInMap(h0) && isCornersInMap(v1);
    }

    private boolean isCornersInMap(Position corner) {
        int mapWidth = tiles.length;
        int mapHeight = tiles[0].length;

        return corner.x >= 0 && corner.y >= 0 && corner.x < mapWidth && corner.y < mapHeight;
    }

    private boolean isEmptyTiles(Position p) {
        return tiles[p.x][p.y].equals(Tileset.NOTHING);
    }

    public ArrayList<Position> getCorners() {
        //draw walls
        // v0 $  $  v1
        // $  f  f  $
        // $  f  f  $
        // h0 $  $  h1
        Position p_h0 = p;
        Position p_v0 = new Position(p.x, p.y + s.height - 1);
        Position p_h1 = new Position(p.x + s.width - 1, p.y);
        Position p_v1 = new Position(p.x + s.width - 1, p.y + s.height - 1);

        ArrayList<Position> corners = new ArrayList();
        corners.add(p_h0);
        corners.add(p_h1);
        corners.add(p_v0);
        corners.add(p_v1);

        return corners;
    }



    private Position getStartPosition() {
        return ShapeGenerator.getStartPosition(this.p, this.s, this.orient);
    }
}
























