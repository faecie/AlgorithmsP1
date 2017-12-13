/******************************************************************************
 *  Compilation:  javac-algs4 DequeR.java
 *  Execution:    java-algs4 DequeR
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * The type DequeR.
 *
 * @param <Item> the type parameter
 */
public class Deque<Item> implements Iterable<Item> {

    private Element first = null;
    private Element last = null;
    private int size = 0;

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

        Element newElement = new Element(first, item);
        first.next = newElement;
        first = newElement;
        last = last == null ? first : last;

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

        Element newElement = new Element(item, last);
        last.prev = newElement;
        last = newElement;
        first = first == null ? last : first;

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
        } else {
            result = first.item;
            first = first.prev;

            if (first != null) {
                first.next = null;
            }
        }

        size -= 1;

        if (size() <= 1) {
            last = first;
        }

        return result;
    }

    private class Element {
        private Element next = null;
        private Element prev = null;
        private Item item;

        private Element(Element prevElement, Item item) {
            this.item = item;
            prev = prevElement;
        }

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

    private class FrontToEndIterator implements Iterator<Item> {

        public boolean hasNext() {
            return size() != 0;
        }


        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("The DequeR is empty!");
            }

            return removeLast();
        }
    }
}
