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
     * Name of the file that was read
     */
    String fileName;
    /**
     * Constructor for the Binary Tree, creates the Tree given an ArrayList of words.
     *
     * @param words
     */
    public BinaryTree(ArrayList<String> words, String fileName) {
        this.words = words;
        head = new Node(words.get(0));
        createTree();
        this.fileName = fileName;
        printStatisticsOfTree();
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
     * Prints the words in the tree given the type that the user requests.
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
    private int totalNumberOfWords(){
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
    private int totalNumberOfUniqueWords(){
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

    /**
     * Calls the function oftenWords, this function finds the word(s) with the highest frequency and prints them out with their frequency
     */
    private void mostOftenWords(){
        ArrayList<Node> nodes = new ArrayList<>();
        nodes = oftenWords(head, 0, nodes);
        for (Node node: nodes){
            System.out.println(node.getWord() + " = " + node.getFrequency() + " times");
        }
    }

    /**
     * Recursive function that finds the the highest frequency, and adds that Node to an ArrayList of nodes.
     * @param node Node which contains the word with the highest frequency
     * @param frequency the highest frequency present of a word in the tree
     * @param nodes an ArrayList of all other words with a matching frequency to that of the highest frequency present in the tree
     * @return ArrayList of Nodes which cna be used to print the words with the highest frequency
     */
    private ArrayList<Node> oftenWords(Node node, int frequency, ArrayList<Node> nodes){
        if (node == null) {
            return nodes;
        }

        if (node.getFrequency() > frequency){ // Checks to see if the frequency is greater than the previous highest recorded frequency, if it is it creates a new ArrayList, essentially deleting the old one, if the frequency matches appends the node on to the Arraylist.
            frequency = node.getFrequency();
            nodes = new ArrayList<Node>();
            nodes.add(node);
        } else if (node.getFrequency() == frequency){
            nodes.add(node);
        }

        nodes = oftenWords(node.leftNode,frequency, nodes);
        nodes = oftenWords(node.rightNode, frequency,nodes);
        return nodes;
    }

    /**
     * Finds the maximum depth of the tree.
     * @return Integer with the value of the depth of the tree
     */

    private int maximumDepth(){
        return depth(head);
    }

    /**
     * Recursive function calculating the highest depth of the tree, incremented only if the there exists either a left or right node
     * @param node Node at which you are
     * @return Deepest level in the tree
     */
    private int depth(Node node){
        if (node == null) {
            return 0;
        }
        else  {
            int leftLevel = depth(node.leftNode);
            int rightLevel = depth(node.rightNode);

            if (leftLevel > rightLevel){
                return (leftLevel +1);
            } else {
                return (rightLevel +1);
            }
        }


    }

    /**
     * After the tree is created, this prints the statistcs of the tree including, number of words, number of words with frequency of 1, words with the highest frequency and the depth of the tree.
     */
    private void printStatisticsOfTree(){
        System.out.println("Total number of words in " + fileName + " = " + totalNumberOfWords());
        System.out.println("Number of unique words in " + fileName + " = " + totalNumberOfUniqueWords());
        System.out.println("The word(s) which occur(s) most often and the number of times that it/they occur(s) = ");
        mostOftenWords();
        System.out.println("The maximum height of the tree = " + maximumDepth());
    }

    /**
     * Searches for a word in the Tree, and prints out the frequency of the word if the word is found.
     * @param word The word you want to search for.
     */
    public void searchForWord(String word){
        word = word.toLowerCase();
        Node x = nodeSearch(head, word);

        if (x != null){
            System.out.println("Found! It appears " + x.getFrequency() +" times in the input text file.");
        } else {
            System.out.println("Word not found!");
        }
    }

    /**
     * Recursive serach in the tree for the Word
     * @param node Node at which you are at
     * @param word The word to search for
     * @return A node is returned if the word is found, else it's null
     */
    private Node nodeSearch(Node node, String word){
        if (node ==  null){
            return null;
        }
        if (node.getWord().compareTo(word) > 0){
            nodeSearch(node.leftNode, word);
        } else if (node.getWord().compareTo(word) < 0){
            nodeSearch(node.rightNode, word);
        } else {
            return node;
        }

        return null;

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
