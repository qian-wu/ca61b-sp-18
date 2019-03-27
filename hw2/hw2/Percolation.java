package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int openSize;
    private boolean[][] sites;
    private boolean isPercolates;
    private int N;
    private WeightedQuickUnionUF antiBackwash;
    private WeightedQuickUnionUF uf;
    private int ocean;
    private int floor;

    public Percolation(int N) {  // create N-by-N grid, with all sites initially blocked
        sites = new boolean[N][N];
        this.N = N;
        ocean = N * N;
        floor = N * N + 1;

        antiBackwash = new WeightedQuickUnionUF(N * N + 1);
        uf = new WeightedQuickUnionUF(N * N + 2);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0) {
                    antiBackwash.union(xyTo1D(i, j), ocean);
                }
                if (i == N - 1) {
                    uf.union(xyTo1D(i, j), floor);
//                    System.out.println("union " + xyTo1D(i, j) + " " + floors[j]);
                }

            }
        }

    }

    private static class Neighbor {
        int row;
        int col;

        Neighbor(int i, int j) {
            row = i;
            col = j;
        }
    }

    public void open(int row, int col) {     // open the site (row, col) if it is not open already
        vlidateIndex(row, col);

        if (isOpen(row, col)) return;

        sites[row][col] = true;

        unionAllNeighbors(row, col);
        openSize += 1;
    }


    private void unionAllNeighbors(int row, int col) {
        int curr1D = xyTo1D(row, col);

        for (Neighbor n : getAllNeighbors(row, col)) {
            if (n != null) {
                int ngb1D = xyTo1D(n.row, n.col);
                if (isOpen(n.row, n.col)) {
                    antiBackwash.union(ngb1D, curr1D);
                    uf.union(ngb1D, curr1D);
                    if (antiBackwash.connected(ocean, ngb1D)) {
                        uf.union(ocean, curr1D);
                        antiBackwash.union(ocean, curr1D);
                    }
                }
            }
        }
    }

    private Neighbor[] getAllNeighbors(int row, int col) {
        Neighbor[] tmp = new Neighbor[]{null, null, null, null};

        int upRow = row - 1;
        int blowRow = row + 1;
        int leftCol = col - 1;
        int rightCol = col + 1;

        if (row > 0) {
            tmp[0] = new Neighbor(upRow, col);
        }
        if (row < N - 1) {
            tmp[1] = new Neighbor(blowRow, col);
        }
        if (col > 0) {
            tmp[2] = new Neighbor(row, leftCol);
        }
        if (col < N - 1) {
            tmp[3] = new Neighbor(row, rightCol);
        }

        return tmp;
    }

    private boolean getSite(int row, int col) {
        return sites[row][col];
    }

    // check if passed position is validate
    private void vlidateIndex(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IllegalArgumentException("index out of boundry");
        }
    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        return sites[row][col];
    }

    public boolean isFull(int row, int col) { // is the site (row, col) full?
        return isOpen(row, col) && antiBackwash.connected(xyTo1D(row, col), ocean);
    }

    public int numberOfOpenSites() {          // number of open sites
        return openSize;
    }

    public boolean percolates() {             // does the system percolate?
        return uf.connected(ocean, floor);
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }
    public static void main(String[] args) {  // use for unit testing (not required)

    }

}
