/******************************************************************************
 *  Compilation:  javac-algs4 PercolationStats.java
 *  Execution:    java-algs4 PercolationStats n T
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * The type Percolation stats.
 */
public class PercolationStats {

    private static final double COEFF = 1.96;
    private double[] stats;

    /**
     * Perform trials independent experiments on an n-by-n grid
     *
     * @param n      the n
     * @param trials the trials
     * @throws IllegalArgumentException Exception is thrown when input arguments are less then 1
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Grid size and trials number should be at least 1");
        }

        int elementsCount = n * n;
        int[] sites = new int[elementsCount];
        stats = new double[trials];
        for (int i = 0; i < elementsCount; i++) {
            sites[i] = i;
        }


        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            int current = 0;
            int[] sitesInTrial = sites.clone();

            while (!perc.percolates() && current < elementsCount) {
                int siteInCurrent = sitesInTrial[current];
                int siteIndex = StdRandom.uniform(current, (elementsCount) - 1);
                int site = sitesInTrial[siteIndex];

                int row = (site / n) + 1;
                int col = (site % n) + 1;

                perc.open(row, col);
                sitesInTrial[current] = site;
                sitesInTrial[siteIndex] = siteInCurrent;
                current++;
            }

            stats[i] = ((double) perc.numberOfOpenSites()) / ((double) elementsCount);
        }
    }

    /**
     * Test client (described below)
     *
     * @param args the args
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid arguments count " + args.length);
        }

        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);

        StdOut.printf("mean                    %f\n", stats.mean());
        StdOut.printf("stddev                  %f\n", stats.stddev());
        StdOut.printf("95%% confidence interval [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
    }

    /**
     * Sample mean of percolation threshold
     *
     * @return the double
     */
    public double mean() {
        return StdStats.mean(stats);
    }

    /**
     * Sample standard deviation of percolation threshold
     *
     * @return the double
     */
    public double stddev() {
        return StdStats.stddev(stats);
    }

    /**
     * Low  endpoint of 95% confidence interval
     *
     * @return the double
     */
    public double confidenceLo() {
        return (this.mean() - ((COEFF * this.stddev()) / Math.sqrt(stats.length)));
    }

    /**
     * High endpoint of 95% confidence interval
     *
     * @return the double
     */
    public double confidenceHi() {
        return (this.mean() + ((COEFF * this.stddev()) / Math.sqrt(stats.length)));
    }
}
