package byog.Core.Core.shape;

import byog.TileEngine.TETile;
import byog.lab5.Position;

public class Hallway extends GenericShape{
    protected boolean isVertical;

    public Hallway(Position p, int width, boolean isVertical) {
        super(p, 1, 1);
        this.isVertical = isVertical;

        if (isVertical) {
            this.high = width;
        } else {
            this.width = width;
        }
    }
}
