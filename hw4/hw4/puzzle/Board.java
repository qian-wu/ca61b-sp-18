package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;


public class Board implements WorldState {
    private int[][] tiles;
    private int size;
//    private static int[][] goal;
    private int rowBlank;
    private int colBlank;
    private int m = 0;
    private int h = 0;

    public Board(int[][] tiles) {
        this.size = tiles[0].length;
        this.tiles = new int[size][size];

//        if (goal == null) {
//            System.out.println("get goal tiles");
//            this.goal = goalTiles(size);
//        }

        int goal = 1;
        for (int i = 0; i < size; i++) {
//            System.arraycopy(tiles[i], 0, this.tiles[i], 0, size);
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];

                if (tiles[i][j] == 0) {
                    rowBlank = i;
                    colBlank = j;
                }
                //compute h
                if (goal != size * size) {
                    if (tiles[i][j] != goal++) {
                        h += 1;
                    }
                }

                //compute m
                if (tiles[i][j] != 0) {
                    int value = tiles[i][j] - 1;

                    m += Math.abs(value / size - i) + Math.abs(value % size - j);
                }
            }
        }
    }

    private int[][] goalTiles(int tileSize) {
        int n = 1;
        int[][] g = new int[tileSize][tileSize];
        for (int i = 0; i < tileSize; i++) {
            for (int j = 0; j < tileSize; j++) {
                g[i][j] = n++;
            }
        }
        g[tileSize - 1][tileSize - 1] = 0;
        return g;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException("Tial position out of boundry");
        }
        return tiles[i][j];
    }

    public int size() {
        return size;
    }

    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();

        // up blank
        if (rowBlank != 0) {
            neighbors.enqueue(exchangeBlank(rowBlank - 1, colBlank));
        }
        // blow blank
        if (rowBlank != size - 1) {
            neighbors.enqueue(exchangeBlank(rowBlank + 1, colBlank));
        }
        // left blank
        if (colBlank != 0) {
            neighbors.enqueue(exchangeBlank(rowBlank, colBlank - 1));
        }
        // right blank
        if (colBlank != size - 1) {
            neighbors.enqueue(exchangeBlank(rowBlank, colBlank + 1));
        }
        return neighbors;
    }

    private Board exchangeBlank(int r2, int c2) {
        tiles[rowBlank][colBlank] = tiles[r2][c2];
        tiles[r2][c2] = 0;

        Board b = new Board(tiles);

        tiles[r2][c2] = tiles[rowBlank][colBlank];
        tiles[rowBlank][colBlank] = 0;


        return b;
    }

    public int hamming() {
        return h;
    }

    public int manhattan() {
        return m;
    }

    public int estimatedDistanceToGoal() {
        return m;
    }

    public boolean equals(Object y) {
//        System.out.println("called equals");
        if (y == null) {
            return false;
        }
        if ( y != null && getClass() != y.getClass()) {
            return false;
        }

        Board b = (Board) y;
        for (int i = 0; i < size; i++) {
            if (!Arrays.equals(b.tiles[i], this.tiles[i])) {
                return false;
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
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public int hashCode() {
        int result = tiles != null ? tiles.hashCode() : 0;
        result = result * 31;
        return result;
    }

//    public static void main(String[] args) {
//        int[][] t = new int[3][3];
//        int n = 1;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                t[i][j] = n++;
//            }
//        }
//        t[2][2] = t[1][0];
//        t[1][0] = 0;
//        Board b = new Board(t);
//
//        System.out.println(b);
//        for (WorldState ws : b.neighbors()) {
//            System.out.println(ws);
//            System.out.println("dist : " + ws.estimatedDistanceToGoal());
//        }
//    }
}
