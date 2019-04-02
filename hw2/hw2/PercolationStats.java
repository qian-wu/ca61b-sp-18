package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] result;
    private double N, T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        validate(N, T);

        this.N = N;
        this.T = T;
        result = new double[T];

        for (int i = 0; i < T; i++) {
//            System.out.println("Run test " + i);
            Percolation p = pf.make(N);
            double fails = runTest(N, p);
            result[i] = fails / (N * N);
//            System.out.println("Fails on " + fails / (N * N));
        }
    }

    private double runTest(int size, Percolation p) {
        double i = 0;
        while (!p.percolates()) {
            int openRow = StdRandom.uniform(size);
            int openCol = StdRandom.uniform(size);
            if (!p.isOpen(openRow, openCol)) {
                p.open(openRow, openCol);
                i += 1;
            }
        }

        return i;
    }

    private void validate(int size, int round) {
        if (size <= 0 || round <= 0) {
            throw new IllegalArgumentException();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow()      {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

//    public static void main(String[] args) {
//        PercolationStats s = new PercolationStats(100, 90, new PercolationFactory());
//        System.out.println(s.mean());
//        System.out.println(s.stddev());
//        System.out.println(s.confidenceLow());
//        System.out.println(s.confidenceHigh());
//    }

}
