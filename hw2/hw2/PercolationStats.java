package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

import java.util.ArrayList;
import java.util.List;

public class PercolationStats {
    private int[] result;
    private int N, T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        validate(N, T);

        N = N;
        T = T;
        result = new int[T];

        for (int i = 0; i < T; i ++) {
            Percolation p = pf.make(N);
            int fails = runTest(N, p);
            result[i] =fails;
        }
    }

    private int runTest(int N, Percolation p) {
        int i = 0;
        for (; i < N * N; i++) {
            int openRow = StdRandom.uniform(N);
            int openCol = StdRandom.uniform(N);

            p.open(openRow, openCol);
            if (p.percolates()) break;
        }

        return i;
    }

    private void validate(int N, int T) {
        if (N <= 0 || T <= 0) {
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
        double mu = StdStats.mean(result);
        double sigma = StdStats.stddev(result);
        return mu - 1.96 * sigma / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mu = StdStats.mean(result);
        double sigma = StdStats.stddev(result);
        return mu + 1.96 * sigma / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationStats s = new PercolationStats(100, 90, new PercolationFactory());
        System.out.println(s.mean());
        System.out.println(s.stddev());
    }

}
