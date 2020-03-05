/**
 * Striped down version of a LinkedList containing only functions that are used.
 * @author Talha Khalil 30037871
 * @since  March 4, 2020
 * @version 1.0
 */

public class LinkedList {
    /**
     * The head of the linked list
     */
    Node head;

    /**
     * Constructor of the linked list which takes a word as an argument and places it in the head node.
     * @param word Word
     */
    public LinkedList(Word word) {
        head = new Node(word);
    }

    /**
     * Adds the Word to the end of the Linked List
     * @param word Word
     */
    public void addToList(Word word) {
        Node pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        pointer.next = new Node(word);
    }

    /**
     * Creates a toString for the LinkedList which contains all the data members in the list
     * @return String of the data members seperated by a space
     */
    @Override
    public String toString() {
        String s = head.word.toString();

        Node pointer = head.next;
        while (pointer != null) {
            s += " " + pointer.word;
            pointer = pointer.next;
        }

        return s;
    }
}


