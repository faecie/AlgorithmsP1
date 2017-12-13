/******************************************************************************
 *  Compilation:  javac-algs4 ArrayDeque.java
 *  Execution:    java-algs4 ArrayDeque
 ******************************************************************************/

import java.util.Iterator;

/**
 * The type Randomized queue.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int QUANTIFIER = 2;

    private Item[] items;

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {
    }

    /**
     * Is the randomized queue empty?
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return items == null;
    }

    /**
     * Return the number of items on the randomized queue
     *
     * @return the int
     */
    public int size() {
        return items == null ? 0 : items.length;
    }

    /**
     * Add the item
     *
     * @param item the item
     */
    public void enqueue(Item item) {
        if (isEmpty()) {
            items = (Item[]) new Object[1];
            items[0] = item;
        }
    }

    /**
     * Remove and return a random item
     *
     * @return the item
     */
    public Item dequeue() {
    }

    /**
     * Return a random item (but do not remove it)
     *
     * @return the item
     */
    public Item sample() {
    }

    /**
     * Return an independent iterator over items in random order
     *
     * @return the iterator
     */
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
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
                throw new java.util.NoSuchElementException("The ArrayDeque is empty!");
            }

            cursor += 1;
            return items[cursor - 1];
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
    }
}
