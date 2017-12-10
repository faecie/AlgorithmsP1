/******************************************************************************
 *  Compilation:  javac-algs4 Deque.java
 *  Execution:    java-algs4 Deque
 ******************************************************************************/

import java.util.Iterator;


/**
 * The type Deque.
 *
 * @param <Item> the type parameter
 */
public class Deque<Item> implements Iterable<Item> {

    private static final int SIZE_QUANTIFIER = 2;
    private Item[] items;
    private int hi = 0;
    private int lo = 0;
    private int pivot = 0;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
    }

    /**
     * Is the deque empty?
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return items == null;
    }

    /**
     * Return the number of items on the deque
     *
     * @return the int
     */
    public int size() {
        return (hi - lo) + 1;
    }

    /**
     * Add the item to the front
     *
     * @param item the item
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Invalid argument item provided for add first");
        }

        if (items == null) {
            initializeItems(item);
        } else {
            maintainRightSize();
            hi += 1;
            items[hi] = item;
        }
    }

    /**
     * Add the item to the end
     *
     * @param item the item
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Invalid argument item provided for add last");
        }

        if (items == null) {
            initializeItems(item);
        } else {
            lo -= 1;
            maintainLeftSize();
            items[lo] = item;
        }
    }

    /**
     * Remove and return the item from the front
     *
     * @return the item
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is empty!");
        }

        Item item = items[hi];
        if (size() == 1) {
            items = null;
        } else {
            items[hi] = null;
            hi -= 1;
            maintainRightSize();

            pivot = hi < pivot ? pivot - 1 : pivot;

            maintainLeftSize();
        }

        return item;
    }

    /**
     * Remove last item from the end
     *
     * @return the item
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is empty!");
        }

        Item item = items[lo];

        if (size() == 1) {
            items = null;
        } else {
            items[lo] = null;
            lo += 1;
            maintainLeftSize();

            pivot = lo > pivot ? pivot + 1 : pivot;
            maintainRightSize();
        }

        return item;
    }

    /**
     * Return an iterator over items in order from front to end
     *
     * @return the iterator
     */
    public Iterator<Item> iterator() {
        return new FrontToEndIterator();
    }

    /**
     * Initialize items array
     *
     * @param item the item
     */
    private void initializeItems(Item item) {
        items = (Item[]) new Object[1];
        pivot = 0;
        hi = 0;
        lo = 0;

        items[pivot] = item;
    }

    private class FrontToEndIterator implements Iterator<Item> {
        private int cursor;

        /**
         * Instantiates a new Front to end iterator.
         */
        private FrontToEndIterator() {
            cursor = lo;
        }


        public boolean hasNext() {
            return items != null && cursor <= hi;
        }


        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("The Deque is empty!");
            }

            cursor += 1;
            return items[cursor - 1];
        }
    }

    private void extendFromRight() {
        int size = (SIZE_QUANTIFIER * hi - pivot) + SIZE_QUANTIFIER;
        Item[] newItems = (Item[]) new Object[size];

        System.arraycopy(items, lo, newItems, lo, hi + 1 - lo);
        items = newItems;
    }

    private void extendFromLeft() {
        int newPivot = (2 * pivot) + 1;
        lo = newPivot - pivot - 1;
        int newHi = (hi - pivot) + newPivot;
        int newSize = (newPivot - pivot) + items.length;
        Item[] newItems = (Item[]) new Object[newSize];
        System.arraycopy(items, 0, newItems, lo + 1, hi + 1);
        items = newItems;

        hi = newHi;
        pivot = newPivot;
    }

    private void reduceFromRight() {
        int newSize = (items.length + pivot) / 2;

        Item[] newItems = (Item[]) new Object[newSize];
        System.arraycopy(items, lo, newItems, lo, (hi - lo) + 1);
        items = newItems;
    }

    private void reduceFromLeft() {
        int newPivot = (pivot - 1) / 2;
        int newLo = newPivot - pivot + lo;
        int newHi = (hi - pivot) + newPivot;
        int newSize = items.length - (pivot - newPivot);
        Item[] newItems = (Item[]) new Object[newSize];
        System.arraycopy(items, lo, newItems, 0, (hi - lo) + 1);
        items = newItems;

        hi = newHi;
        lo = newLo;
        pivot = newPivot;
    }

    private void maintainLeftSize() {
        if (pivot != lo && (pivot - lo) + 1 == (pivot + 1)/2) {
            reduceFromLeft();

            return;
        }

        if (lo < 0) {
            extendFromLeft();
        }
    }

    private void maintainRightSize() {
        if ((hi - pivot) + 1 <= (items.length - pivot)/2) {
            reduceFromRight();

            return;
        }

        if (items.length - 1 < hi + 1) {
            extendFromRight();
        }
    }
}
