import java.util.ArrayList;

/**
 * Binary Tree that contains nodes of words in the file, and containing the frequency of each word.
 *
 * @author Talha Khalil
 * @since March 26, 2020
 */

public class BinaryTree {
    /**
     * Head is the first word in the file that was read and is the head of the tree.
     */
    Node head;
    /**
     * ArrayList of words that are to be used to create the tree
     */
    ArrayList<String> words;

    /**
     * Constructor for the Binary Tree, creates the Tree given an ArrayList of words.
     *
     * @param words
     */
    public BinaryTree(ArrayList<String> words) {
        this.words = words;
        head = new Node(words.get(0));
        createTree();
    }

    /**
     * Creates the Binary tree by doing lexiographical comparison.
     */
    private void createTree() {
        for (int i = 1; i < words.size(); i++) {
            Node node = head;
            while (true) {
                if (node.getWord().compareTo(words.get(i)) > 0) {
                    if (node.getLeftNode() != null) {
                        node = node.leftNode;
                    } else {
                        node.leftNode = new Node(words.get(i));
                        break;
                    }

                } else if (node.getWord().compareTo(words.get(i)) < 0) {
                    if (node.getRightNode() != null) {
                        node = node.rightNode;
                    } else {
                        node.rightNode = new Node(words.get(i));
                        break;
                    }
                } else {
                    node.incrementFrequency();
                    break;
                }
            }
        }
        System.out.println("Tree has been created.");
    }

    /**
     * Prints the words in the tree give what type the user requests.
     */
    public void printTree() {
        InputManager input = new InputManager();
        int Type = input.getPrintType(); //Ensures Type is either 1, 2 or 3

        if (Type == 1) {
            System.out.print("Pre-order output: ");
            prePrint(head);
            System.out.println();
        } else if (Type == 2) {
            System.out.print("In-order output: ");
            orderPrint(head);
            System.out.println();
        } else {
            System.out.print("Post-order output: ");
            postPrint(head);
            System.out.println();
        }
    }

    /**
     * Prints the words in post order
     */
    private void postPrint(Node node) {
        if (node == null) {
            return;
        }
        postPrint(node.leftNode);
        postPrint(node.rightNode);
        System.out.print(node.getWord() + " ");
    }

    /**
     * Prints the words in pre order
     */
    private void prePrint(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getWord() + " ");
        prePrint(node.leftNode);
        prePrint(node.rightNode);
    }

    /**
     * Prints the words in order
     */
    private void orderPrint(Node node) {
        if (node == null) {
            return;
        }
        orderPrint(node.leftNode);
        System.out.print(node.getWord() + " ");
        orderPrint(node.rightNode);
    }

    /**
     * Calls the function numberOfWords, which is a recursive fucntion and returns how many nodes there are in the tree.
     * @return the number of nodes are in the tree
     */
    public int totalNumberOfWords(){
        return numberOfWords(head, 0);
    }

    /**
     * A recursive fucntion and returns how many nodes there are in the tree.
     * @param node Node at which you are
     * @param count How many nodes have been counted so far
     * @return the number of nodes  counted so far
     */
    private int numberOfWords(Node node, int count){
        if (node == null) {
            return count;
        }
        count = numberOfWords(node.leftNode, count);
        count++;
        count = numberOfWords(node.rightNode, count);
        return count;
    }

    /**
     * Calls the function numberOfUniqueWords, which is a recursive fucntion and returns how many nodes there are in the tree with a frequency of 1.
     * @return the number of nodes in the tree with a frequency of 1
     */
    public int totalNumberOfUniqueWords(){
        return numberOfUniqueWords(head, 0);
    }

    /**
     * A recursive function and returns how many nodes there are in the tree with a frequency of 1.
     * @param node Node at which you are
     * @param count How many nodes have been counted so far with a frequency of 1.
     * @return the number of nodes counted so far with a frequency of 1.
     */
    private int numberOfUniqueWords(Node node, int count){
        if (node == null) {
            return count;
        }
        count = numberOfUniqueWords(node.leftNode, count);
        if (node.getFrequency() == 1) {
            count++;
        }
        count = numberOfUniqueWords(node.rightNode, count);
        return count;
    }
}

/**
 * Nodes that are used by the Binary Tree contains, the word stored, it's frequency, the left node, and the right node
 *
 * @author Talha Khalil
 * @since March 26, 2020
 */

class Node {

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
     * @param word
     */
    public Node(String word) {
        this.word = word;
        this.frequency = 1;
    }

    /**
     * Sets the left node
     *
     * @param leftNode
     */
    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * Sets the right node
     *
     * @param rightNode
     */
    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
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
