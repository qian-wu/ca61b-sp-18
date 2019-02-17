package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.lab5.Position;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestRectangle {
    TETile[][] tiles = new TETile[80][30];

    @Test
    public void TestGetCorners() {

        Position p = new Position(5, 5);
        Size s = new Size(4, 3);

        Rectangle r = new Rectangle(tiles, p, p, s, 0);
        ArrayList<Position> a = r.getCorners();

        assertTrue(a.get(0).equals(new Position(5, 5)));
        assertTrue(a.get(1).equals(new Position(8, 5)));
        assertTrue(a.get(2).equals(new Position(5, 7)));
        assertTrue(a.get(3).equals(new Position(8, 7)));
    }
}
