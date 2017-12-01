/******************************************************************************
 *  Compilation:  javac-algs4 PercolationStats.java
 *  Execution:    java-algs4 PercolationStats
 ******************************************************************************/

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

/**
 * The type Percolation stats.
 */
public class PercolationStats {

    private Percolation perc;
    private int gridSize;

    /**
     * Perform trials independent experiments on an n-by-n grid
     *
     * @param n      the n
     * @param trials the trials
     */
    public PercolationStats(int n, int trials) {
        gridSize = n;
        perc = new Percolation(gridSize);
        for (int i = 0; i < trials; i ++) {
            while (perc.percolates() || perc.NumberOfOpenSites() != n * n) {
                int site = StdRandom.uniform(0, (n * n) + 1);


            }
            openRandom(0, n * n);
        }
    }

    private void openRandom(int site)
    {
        int row = (site / n) + 1;
        int col = (site % n) + 1;

        if (perc.isOpen(, (site % n) + 1)) {

        }
    }

    /**
     * Sample mean of percolation threshold
     *
     * @return the double
     */
    public double mean() {
    }

    /**
     * Sample standard deviation of percolation threshold
     *
     * @return the double
     */
    public double stddev() {
    }

    /**
     * Low  endpoint of 95% confidence interval
     *
     * @return the double
     */
    public double confidenceLo() {
    }

    /**
     * High endpoint of 95% confidence interval
     *
     * @return the double
     */
    public double confidenceHi() {
    }

    /**
     * Test client (described below)
     *
     * @param args the args
     */
    public static void main(String[] args) {
    }
}
