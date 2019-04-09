package hw4.puzzle;

import java.util.HashSet;
import java.util.Set;

public class Board implements WorldState {
    private int[][] tiles;
    private int size;
    private int[][] goal;
    private int rowBlank;
    private int colBlank;

    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.size = tiles[0].length;
        this.goal = goalTiles(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(tiles[i][j] ==0) {
                    rowBlank = i;
                    colBlank = j;
                }
            }
        }
    }

    private int[][] goalTiles(int size) {
        int n = 1;
        int[][] g = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                g[i][j] = n++;
            }
        }
        g[size - 1][size - 1] = 0;
        return g;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= size() || j < 0 || j >= size()) {
            throw new IndexOutOfBoundsException("Tial position out of boundry");
        }
        return tiles[i][j];
    }

    public int size() {
        return size;
    }

    public Iterable<WorldState> neighbors() {
        Set<WorldState> neighbors = new HashSet<>();

        // up blank
        if (rowBlank > 0) {
            neighbors.add(exchangeBlank(rowBlank - 1, colBlank));
        }
        // blow blank
        if (rowBlank < size() - 1) {
            neighbors.add(exchangeBlank(rowBlank + 1, colBlank));
        }
        // left blank
        if (colBlank > 0) {
            neighbors.add(exchangeBlank(rowBlank, colBlank - 1));
        }
        // right blank
        if (colBlank < size() - 1) {
            neighbors.add(exchangeBlank(rowBlank, colBlank + 1));
        }
        return neighbors;
    }

    public Board exchangeBlank(int r2, int c2) {
        int[][] target = new int[size()][size()];
        for (int i = 0; i < size(); i++) {
            System.arraycopy(tiles[i], 0, target[i], 0, size());
        }

        int tmp = target[rowBlank][colBlank];
        target[rowBlank][colBlank] = target[r2][c2];
        target[r2][c2] = tmp;

        return new Board(target);
    }

    public int hamming() {
        int n = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != goal[i][j]) {
                    n += 1;
                }
            }
        }
        return n;
    }

    public int manhattan() {
        return 0;
    }

    public int estimatedDistanceToGoal() {
        return hamming();
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if ( y != null && getClass() != y.getClass()) {
            return false;
        }

        Board b = (Board) y;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) != b.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public static void main(String[] args) {
        int[][] t = new int[3][3];
        int n = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                t[i][j] = n++;
            }
        }
        t[1][0] = 0;
        Board b = new Board(t);

        System.out.println(b);
        for (WorldState ws : b.neighbors()) {
            System.out.println(ws);
            System.out.println("dist : " + ws.estimatedDistanceToGoal());
        }
    }
}
