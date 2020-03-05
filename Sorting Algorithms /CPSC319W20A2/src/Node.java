/**
 * Node used for the LinkedList
 * @author Talha Khalil 30037871
 * @since  March 4, 2020
 * @version 1.0
 */

public class Node {
    /**
     * Word containing the String read as input and the sorted version of the word
     */
    Word word;
    /**
     * Pointer to the next node in the list
     */
    Node next;

    /**
     * Constructor for the Node which takes in a word as an argument and stores it in the this.word, and sets next to null
     * @param word
     */
    public Node(Word word) {
        this.word = word;
        next = null;
    }
}