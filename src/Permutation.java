/******************************************************************************
 *  Compilation:  javac-algs4 ArrayDeque.java
 *  Execution:    java-algs4 ArrayDeque
 ******************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * The type Permutation.
 */
public class Permutation {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rqueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty() && k > 0) {
            rqueue.enqueue(StdIn.readString());

            k -= 1;
        }

        for (String item: rqueue) {
            StdOut.printf("%s\n", item);
        }
    }
}
