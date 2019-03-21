package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Percolation {
    private int openSize;
    private Site[][] sites;
    private boolean isPercolates;
    private int N;
    private WeightedQuickUnionUF connectedSet;
    private int ocean;
    private int[] floors;
    private List<Site> openSiteList = new ArrayList<>();

    public Percolation(int N) {  // create N-by-N grid, with all sites initially blocked
        sites = new Site[N][N];
        openSize = 0;
        this.N = N;
        ocean = N * N;
        floors = new int[N];
        connectedSet = new WeightedQuickUnionUF(N * N + N + 1);

        for (int i = 0; i < N; i++) {
            floors[i] = N * N + 1 + i;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Site tmp = new Site();
                sites[i][j] = tmp;

                if (i == 0) {
                    connectedSet.union(xyTo1D(i, j), ocean);
                }
                if (i == N - 1) {
                    connectedSet.union(xyTo1D(i, j), floors[j]);
//                    System.out.println("union " + xyTo1D(i, j) + " " + floors[j]);
                }

            }
        }

    }

    public void open(int row, int col) {     // open the site (row, col) if it is not open already
        //union with all neighbors to one set
        unionAllNeighbors(row, col);
        openSize += 1;
    }


    private void unionAllNeighbors(int row, int col) {
        vlidateIndex(row, col);

        Site curr = getSite(row, col);
        curr.isOpen = true;

        int curr1D = xyTo1D(row, col);
        curr.id = curr1D;
        boolean beforeOpenState = curr.isFull;

        //union up
        if (row == 0) {
            curr.isFull = true;
        } else {
            int upRow = row - 1;
            if (isOpen(upRow, col)) {
                int upNgb1D = xyTo1D(upRow, col);
                connectedSet.union(curr1D, upNgb1D);
                fillTo(upRow, col, row, col);
            }
        }

        //union below
        if (row < N - 1) {
            int blowRow = row + 1;
            if (isOpen(blowRow, col)) {
                int blowNgb1D = xyTo1D(blowRow, col);
                connectedSet.union(curr1D, blowNgb1D);
                fillTo(blowRow, col, row, col);
            }
        }

        //union left
        if (col > 0) {
            int leftCol = col - 1;
            if (isOpen(row, leftCol)) {
                int leftNgb1D = xyTo1D(row, leftCol);
                connectedSet.union(curr1D, leftNgb1D);
                fillTo(row, leftCol, row, col);
            }
        }

        //union right
        if (col < N - 1) {
            int rightCol = col + 1;
            if (isOpen(row, rightCol)) {
                int rightNgb1D = xyTo1D(row, rightCol);
                connectedSet.union(curr1D, rightNgb1D);
                fillTo(row, rightCol, row, col);
            }
        }

        //update union set if curr block is full
        if (!beforeOpenState && isFull(row, col)) {
            updateSetToFull(row, col);
            isPercolates = checkPercolates(row, col);
        }

        openSiteList.add(curr);
//        System.out.println("Open " + row + " " + col + ", on set " + connectedSet.find(curr1D));
    }

    private void updateSetToFull(int row, int col) {
        int setNumber = connectedSet.find(xyTo1D(row, col));
//        System.out.println("update on : " + row + " " + col + ", set : " + setNumber);

//        List removeSites = new ArrayList();
        for (int i = 0; i < openSiteList.size(); i++) {
            Site s = openSiteList.get(i);
            if (connectedSet.find(s.id) == setNumber && s.isOpen && !s.isFull) {
//                System.out.println("update " + i + " " + j);
                s.isFull = true;
//                removeSites.add(i);
            }
        }

//        for (int j = 0; j < removeSites.size(); j++) {
//            openSiteList.remove(j);
//        }
    }

    private void fillTo(int fromRow, int fromCol, int toRow, int toCol) {
        if (!isFull(toRow, toCol) && isFull(fromRow, fromCol)) {
            getSite(toRow, toCol).isFull = getSite(fromRow, fromCol).isFull;
        }
    }

    private boolean checkPercolates(int row, int col) {
        int id = xyTo1D(row, col);

        if (connectedSet.connected(id, ocean)) {
            for (int i = 0; i < N; i++) {
                if (connectedSet.connected(id, floors[i]))
                    return true;
            }
        }

        return  false;
    }

    private Site getSite(int row, int col) {
        return sites[row][col];
    }

    // check if passed position is validate
    private void vlidateIndex(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IllegalArgumentException("index out of boundry");
        }
    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        return getSite(row, col).isOpen;
    }

    public boolean isFull(int row, int col) { // is the site (row, col) full?
        return getSite(row, col).isFull;
    }

    public int numberOfOpenSites() {          // number of open sites
        return openSize;
    }

    public boolean percolates() {             // does the system percolate?
        return isPercolates;
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }
    public static void main(String[] args) {  // use for unit testing (not required)

    }

}
