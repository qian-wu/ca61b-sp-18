package byog.lab5;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Position b = (Position) obj;
        return x == b.x && y == b.y;
    }
}
