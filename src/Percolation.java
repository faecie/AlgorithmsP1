/******************************************************************************
 *  Compilation:  javac 
 *  Execution:    java
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * The type Percolation.
 */
public class Percolation {

    private static final int OPEN_SITE = 0;
    private static final int FIRST_ROW = 1;
    private WeightedQuickUnionUF grid;
    private int gridSize;
    private boolean openSites[];

    /**
     * Instantiates a new Percolation.
     *
     * @param n The size of the grid
     * @throws IllegalArgumentException if the grid size {@code n} is less than 1
     */
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("The examined array should contain at least one element");
        }

        gridSize = n;
        grid     = new WeightedQuickUnionUF(n * n);
        openSites = new boolean[n];
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

    }

    /**
     * Open site ({@code row}, {@code col}) if it is not open already
     *
     * @param row the row of the grid
     * @param col the col of the grid
     */
    public void open(int row, int col) {
        validate(row, col);

        openSites[getSite(row, col)] = true;

        if (row == FIRST_ROW) {
            connect(col, OPEN_SITE);

            return;
        }

        connectWithUpper(row, col);
        connectWithBottom(row, col);
        connectWithRight(row, col);
        connectWithLeft(row, col);
    }

    /**
     * Is open boolean.
     *
     * @param row the row
     * @param col the col
     *
     * @return the boolean
     * @throws IllegalArgumentException If {@code row} or {@code col} is out of range
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[getSite(row, col)];
    }

    /**
     * Is full boolean.
     *
     * @param row the row
     * @param col the col
     * @return the boolean
     */
    public boolean isFull(int row, int col) {

    }

    /**
     * Number of open sites int.
     *
     * @return the int
     */
    public int NumberOfOpenSites() {

    }

    /**
     * Percolates boolean.
     *
     * @return the boolean
     */
    public boolean percolates() {

    }

    /**
     * Get site id in the grid
     *
     * @param row the row
     * @param col the col
     *
     * @return the boolean
     */
    private int getSite(int row, int col)
    {
        return getRowOffset(row) + col - 1;
    }

    private void connectWithLeft(int row, int col) {
        try {
            if (isOpen(row, col - 1)) {
                int site = getSite(row, col);
                int previousSite = getSite(row, col -1);
                connect(site, previousSite);
            }
        } catch (IllegalArgumentException $e) {
            // Skip if left side is out of range
        }
    }

    private int getRowOffset(int row) {
        return (row - 1) * gridSize - 1;
    }

    private void connectWithRight(int row, int col) {
        int rowOffset = getRowOffset(row);
        int site      = rowOffset + col;
        if (site != rowOffset + gridSize) {

            connect(site, site + 1);
        }
    }

    private void connectWithBottom(int row, int col) {
        int rowOffset = getRowOffset(row);
        int site = rowOffset + col;

        if (row != gridSize) {
            connect(site, site + gridSize);
        }
    }

    private void connectWithUpper(int row, int col) {
        int rowOffset = getRowOffset(row);
        int site = rowOffset + col;

        if (row != gridSize) {
            connect(site, site - gridSize);
        }
    }

    private void connect(int site, int anotherSite) {
        if (!grid.connected(site, anotherSite)) {
            grid.union(site, anotherSite);
        }
    }

    /**
     * Validate the input row and col values
     *
     * @param row the row
     * @param col the col
     * @throws IllegalArgumentException If {@code row} or {@code col} is out of range
     */
    private void validate(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
            throw new IllegalArgumentException("Row and col values {" + row +
                    "; " + col + "} is out of range {" + gridSize + "; " + gridSize + "}");
        }
    }

}
