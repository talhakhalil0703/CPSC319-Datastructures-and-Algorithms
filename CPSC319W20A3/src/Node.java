/**
 * Nodes that are used by the Binary Tree contains, the word stored, it's frequency, the left node, and the right node
 *
 * @author Talha Khalil
 * @since March 26, 2020
 */

public class Node {

    /**
     * Word stored in the node
     */
    String word;
    /**
     * The number of times the word is repeated in the file
     */
    int frequency;
    /**
     * The left node
     */
    Node leftNode;
    /**
     * The right node
     */
    Node rightNode;

    /**
     * Constructor for the Node, takes word as an argument and increments the frequency by 1
     *
     * @param word String word that is to be stored in the node.
     */
    public Node(String word) {
        this.word = word;
        this.frequency = 1;
    }

    /**
     * Gets the left node
     *
     * @return the node located to the left
     */
    public Node getLeftNode() {
        return leftNode;
    }

    /**
     * Gets the right node
     *
     * @return the node located to the right
     */
    public Node getRightNode() {
        return rightNode;
    }

    /**
     * Gets the frequency of the word
     *
     * @return the frequency of the word
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Sets the frequency to a given value
     *
     * @param f Value to set the frequency to
     */
    public void setFrequency(int f) {
        frequency = f;
    }

    /**
     * Increments the frequency by 1
     */
    public void incrementFrequency() {
        frequency++;
    }

    /**
     * The word stored in the node.
     *
     * @return The String stored in the node
     */
    public String getWord() {
        return word;
    }
}
