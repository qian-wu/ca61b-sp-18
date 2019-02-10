package byog.Core.Core.shape;

import byog.Core.Core.generator.ShapeGenerator;
import byog.Core.RandomUtils;
import byog.TileEngine.TETile;
import byog.lab5.Position;

public class Rectangle extends GenericShape{

    public Rectangle(Position p, Size s, int orient) {
        super(p, s, orient);
    }

    public void draw(TETile[][] tiles) {
        //adjust start position
        Position startPosition = this.p;
        this.p = adjustPositon();

        //draw walls
        // h1 h1 h1 h1
        // v0 f  f  v1
        // v0 f  f  v1
        // h0 h0 h0 h0
        Position p_h0 = p;
        Position p_h1 = new Position(p.x, p.y + s.height - 1);
        Position p_v0 = p;
        Position p_v1 = new Position(p.x + s.width - 1, p.y);

//        Wall w_h0 = ;
//        Wall w_h1 = ;
//        Wall w_v0 = ;
//        Wall w_v1 = ;
//
//        w_h0.draw(tiles);
//        w_h1.draw(tiles);
//        w_v0.draw(tiles);
//        w_v1.draw(tiles);
//
//        //draw floors
//        Position p_f = new Position(p.x + 1, p.y + 1);
//
//        Floor f = new Floor();
//
//        f.draw(tiles);

    }

    private Position adjustPositon() {
        return ShapeGenerator.adjustPosition(this.p, this.s, this.orient);
    }
}
























