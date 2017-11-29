/******************************************************************************
 *  Compilation:  javac 
 *  Execution:    java
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * The type Percolation.
 */
public class Percolation {

    private int topSite = 0;
    private int firstRow = 1;
    private WeightedQuickUnionUF grid;
    private int gridSize;
    private int lastRow;
    private boolean openSites[];
    private int numberOfOpenSites;
    private boolean percolates = false;
    private boolean connectedWithBottom[];

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

        int elementsCount = n * n;
        numberOfOpenSites = 0;
        gridSize = lastRow = n;
        grid = new WeightedQuickUnionUF(elementsCount);
        openSites = new boolean[elementsCount];
        connectedWithBottom = new boolean[elementsCount];
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
     * @throws IllegalArgumentException If {@code row} or {@code col} is out of range
     */
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        int site = getSite(row, col);

        openSites[site] = true;
        numberOfOpenSites++;
        if (row == firstRow) {
            grid.union(site, topSite);
        }

        if (row == lastRow) {
            connectedWithBottom[site] = true;
        }

        connectWithNeighbours(row, col);
    }

    /**
     * Is open boolean.
     *
     * @param row the row
     * @param col the col
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
     * @throws IllegalArgumentException If {@code row} or {@code col} is out of range
     */
    public boolean isFull(int row, int col) {
        return isOpen(row, col) && (row == firstRow || grid.connected(getSite(row, col), topSite));
    }

    /**
     * Number of open sites int.
     *
     * @return the int
     */
    public int NumberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * Percolates boolean.
     *
     * @return the boolean
     */
    public boolean percolates() {
        return percolates;
    }

    /**
     * Get site id in the grid
     *
     * @param row the row
     * @param col the col
     * @return the boolean
     */
    private int getSite(int row, int col) {
        return ((row - 1) * (gridSize)) + (col - 1);
    }

    private void connectWithNeighbours(int row, int col) {
        connectWithOpen(row, col, row - 1, col);
        connectWithOpen(row, col, row + 1, col);
        connectWithOpen(row, col, row, col - 1);
        connectWithOpen(row, col, row, col + 1);
    }

    private void connectWithOpen(int currentRow, int currentCol, int targetRow, int targetCol) {
        try {
            validate(currentRow, currentCol);
            validate(targetRow, targetCol);

            int targetSite = getSite(targetRow, targetCol);
            int currentSite = getSite(currentRow, currentCol);
            if (openSites[targetSite]) {
                if (!grid.connected(currentSite, targetSite)) {
                    int currentRoot = grid.find(currentSite);
                    int targetRoot  = grid.find(targetSite);
                    if (!percolates) {
                        boolean areHitBottom = connectedWithBottom[currentRoot] || connectedWithBottom[targetRoot];
                        connectedWithBottom[currentRoot] = connectedWithBottom[targetRoot] = areHitBottom;
                    }

                    grid.union(currentRoot, targetRoot);
                    percolates = grid.connected(currentRoot, topSite) && connectedWithBottom[currentRoot];
                }
            }
        } catch (IllegalArgumentException $e) {
            // Skip if target site is out of range
        }
    }

    /**
     * Validate the site
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
