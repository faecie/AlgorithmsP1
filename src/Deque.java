/******************************************************************************
 *  Compilation:  javac-algs4 Deque.java
 *  Execution:    java-algs4 Deque < file.txt
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Deque.
 *
 * @param <Item> the type parameter
 */
public class Deque<Item> implements Iterable<Item> {

    /**
     * Pointer to the first element
     */
    private Element first = null;

    /**
     * Pointer to the last element
     */
    private Element last = null;

    /**
     * The size of a deque
     */
    private int size = 0;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        int rand = StdRandom.uniform(200);
        int size = rand;
        StdOut.printf("%s times adding a first\n", rand);

        while (rand > 0 && !StdIn.isEmpty()) {
            deque.addFirst(StdIn.readString());

            rand--;
        }

        rand = StdRandom.uniform(20);
        StdOut.printf("%s times adding a last\n", rand);
        size += rand;
        while (rand > 0 && !StdIn.isEmpty()) {
            deque.addLast(StdIn.readString());

            rand--;
        }

        StdOut.printf("%s times remove a last\n", size);
        while (size > 0 && !StdIn.isEmpty()) {
            deque.removeLast();

            size -= 1;
        }

        for (String item: deque) {
            StdOut.printf("%s\n", item);
        }
    }

    /**
     * Is the deque empty?
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the number of items on the deque
     *
     * @return the int
     */
    public int size() {
        return size;
    }

    /**
     * Add the item to the front
     *
     * @param item the item
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Adding null value is meaningless");
        }

        if (first == null) {
            first = new Element(item);
            last = first;

            size += 1;

            return;
        }

        Element newElement = new Element(first, item);
        first.next = newElement;
        first = newElement;

        if (last == null) {
            last = first;
        }

        size += 1;
    }

    /**
     * Add the item to the end
     *
     * @param item the item
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Adding null value is meaningless");
        }

        if (last == null) {
            last = new Element(item);
            first = last;

            size += 1;

            return;
        }

        Element newElement = new Element(item, last);
        last.prev = newElement;
        last = newElement;

        if (first == null) {
            first = last;
        }

        size += 1;
    }

    /**
     * Remove and return the item from the front
     *
     * @return the item
     */
    public Item removeFirst() {
        return remove(false);
    }

    /**
     * Remove last item from the end
     *
     * @return the item
     */
    public Item removeLast() {
        return remove(true);
    }

    /**
     * Remove first or last
     *
     * @param toRemoveLast Whether it is to remove the last element from a deque
     *
     * @return item
     */
    private Item remove(boolean toRemoveLast) {
        if (isEmpty()) {
            throw new NoSuchElementException("The deque is empty");
        }

        Item result;

        if (toRemoveLast) {
            result = last.item;
            last = last.next;

            if (last != null) {
                last.prev = null;
            }
        }
        else {
            result = first.item;
            first = first.prev;

            if (first != null) {
                first.next = null;
            }
        }

        size -= 1;

        if (size() == 1) {
            last = first;
        }

        return result;
    }

    /**
     * Inner class representing the element
     */
    private class Element {

        /**
         * Poiner to the next element in a list
         */
        private Element next = null;

        /**
         * Pointer to the next element in a list
         */
        private Element prev = null;

        /**
         * The item
         */
        private final Item item;

        /**
         * Linked list element constructor
         *
         *
         * @param prevElement Previous element
         * @param item The item
         */
        private Element(Element prevElement, Item item) {
            this.item = item;
            prev = prevElement;
        }

        /**
         * Linked list element constructor
         *
         * @param item The item
         */
        private Element(Item item) {
            this.item = item;
        }

        /**
         * Linked list element constructor
         *
         * @param item The item
         * @param nextElement Next element
         */
        private Element(Item item, Element nextElement) {
            this.item = item;
            next = nextElement;
        }
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
     * Inner left to right iterator
     */
    private class FrontToEndIterator implements Iterator<Item> {

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            return size() != 0;
        }

        /**
         * {@inheritDoc}
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The DequeR is empty!");
            }

            return removeLast();
        }
    }
}
