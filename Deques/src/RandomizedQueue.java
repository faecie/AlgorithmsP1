import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * The type Randomized queue.
 *
 * @param <Item> the type parameter
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    /**
     * Position of the first element
     */
    private static final int FIRST_ITEM_POS = 0;

    /**
     * Initial size of an array
     */
    private static final int INITIAL_SIZE = 1;

    /**
     * Array resizing factor
     */
    private static final int QUANTIFIER = 2;

    /**
     * Array with items
     */
    private Item[] items;

    /**
     * Actual size of the queue
     */
    private int size;

    /**
     * Construct an empty randomized queue
     */
    private RandomizedQueue(RandomizedQueue<Item> another) {
        size = another.size();
        items = another.items.clone();
    }

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {
        size = 0;
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
        return size;
    }

    /**
     * Add the item
     *
     * @param item the item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Invalid argument item provided for add last");
        }

        if (size() == 0) {
            items = (Item[]) new Object[INITIAL_SIZE];
            items[FIRST_ITEM_POS] = item;
            size += 1;

            return;
        }

        if (size() == items.length) {
            resize(items.length * QUANTIFIER);
        }

        size += 1;

        items[size() - 1] = item;
    }

    /**
     * Remove and return a random item
     *
     * @return the item
     */
    public Item dequeue() {
        int randIx = getRandIx();

        Item result = items[randIx];

        if (size() == 1) {
            items = null;
            size -= 1;

            return result;
        }

        items[randIx] = items[size() - 1];
        items[size() - 1] = null;
        size -= 1;

        if (size() < items.length / QUANTIFIER) {
            resize(items.length / QUANTIFIER);
        }

        return result;
    }

    /**
     * Return a random item (but do not remove it)
     *
     * @return the item
     */
    public Item sample() {
        return items[getRandIx()];
    }

    /**
     * Return an independent iterator over items in random order
     *
     * @return the iterator
     */
    public Iterator<Item> iterator() {
        return new RandomizedIterator(this);
    }

    /**
     * Resize an array
     *
     * @param newSize new size of an array
     */
    private void resize(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        System.arraycopy(items, FIRST_ITEM_POS, newItems, FIRST_ITEM_POS, size());

        items = newItems;
    }

    /**
     * Get random index from the array
     *
     *
     * @return random number
     */
    private int getRandIx() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is empty!");
        }

        int randIx;

        if (size() == 1) {
            randIx = FIRST_ITEM_POS;
        }
        else {
            randIx = StdRandom.uniform(size());
        }
        return randIx;
    }

    /**
     * The iterator
     */
    private class RandomizedIterator implements Iterator<Item> {

        private final RandomizedQueue<Item> queue;

        /**
         * Instantiates a new Front to end iterator.
         */
        private RandomizedIterator(RandomizedQueue<Item> another) {
            queue = new RandomizedQueue<>(another);
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            return queue.size() != 0;
        }

        /**
         * {@inheritDoc}
         */
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("The Deque is empty!");
            }

            return queue.dequeue();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        RandomizedQueue<String> randQueue = new RandomizedQueue<>();

        for (String item: args) {
            randQueue.enqueue(item);
        }

        StdOut.print("First random set");
        for (String item: randQueue) {
            StdOut.printf("rand %s\n", item);
        }

        StdOut.print("Second random set");
        for (String item: randQueue) {
            StdOut.printf("rand %s\n", item);
        }

        StdOut.print("Third random set");
        for (String item: randQueue) {
            StdOut.printf("rand %s\n", item);
        }
    }
}
