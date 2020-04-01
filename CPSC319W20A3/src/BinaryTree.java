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
     * @param words ArrayList of words to be put into the BinaryTree
     */
    public BinaryTree(ArrayList<String> words, String fileName) {
        this.words = words;
        head = new Node(words.get(0));
        createTree();
        this.fileName = fileName;
        printStatisticsOfTree();
        optionMenu();
    }

    /**
     * Creates the Binary tree by doing lexiographical comparison.
     */
    private void createTree() {
        for (int i = 1; i < words.size(); i++) {
            insertWord(head, words.get(i));
        }
    }

    /**
     * Creates the Binary tree by doing lexiographical comparison.
     * @param node The node at which you are at
     * @param word the word to add to the tree
     */
    private void insertWord(Node node, String word) {
        if (node.getWord().compareTo(word) > 0) {
            if (node.getLeftNode() != null) {
                insertWord(node.getLeftNode(), word);
            } else {
                node.leftNode = new Node(word);
            }
        } else if (node.getWord().compareTo(word) < 0) {
            if (node.getRightNode() != null) {
                insertWord(node.getRightNode(), word);
            } else {
                node.rightNode = new Node(word);
            }
        } else {
            node.incrementFrequency();
        }
    }

    /**
     * Prints the words in the tree given the type that the user requests.
     */
    public void printTree() {
        InputManager input = new InputManager();
        int Type = input.getType("Enter the BST traversal method: (1 = In-order, 2 = Pre-order , 3 = Post-order) for " + fileName + ": "); //Ensures Type is either 1, 2 or 3
        if (Type == 1) {
            System.out.print("In-order output: ");
            orderPrint(head);
            System.out.println();
        } else if (Type == 2) {
            System.out.print("Pre-order output: ");
            prePrint(head);
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
     * Calls the function numberOfWords, which is a recursive function and returns how many nodes there are in the tree.
     *
     * @return the number of nodes are in the tree
     */
    private int totalNumberOfWords() {
        return numberOfWords(head, 0);
    }

    /**
     * A recursive fucntion and returns how many nodes there are in the tree.
     *
     * @param node  Node at which you are
     * @param count How many nodes have been counted so far
     * @return the number of nodes  counted so far
     */
    private int numberOfWords(Node node, int count) {
        if (node == null) {
            return count;
        }
        count = numberOfWords(node.leftNode, count);
        count++;
        count = numberOfWords(node.rightNode, count);
        return count;
    }

    /**
     * Calls the function numberOfUniqueWords, which is a recursive function and returns how many nodes there are in the tree with a frequency of 1.
     *
     * @return the number of nodes in the tree with a frequency of 1
     */
    private int totalNumberOfUniqueWords() {
        return numberOfUniqueWords(head, 0);
    }

    /**
     * A recursive function and returns how many nodes there are in the tree with a frequency of 1.
     *
     * @param node  Node at which you are
     * @param count How many nodes have been counted so far with a frequency of 1.
     * @return the number of nodes counted so far with a frequency of 1.
     */
    private int numberOfUniqueWords(Node node, int count) {
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
    private void mostOftenWords() {
        ArrayList<Node> nodes = new ArrayList<>();
        Node empty = new Node("empty"); // this empty word with frequency 0  is added to ensure that if the head has the highest frequency that it doesn't get reperesented twice, so a empty starting point is needed
        empty.setFrequency(0);
        nodes.add(empty);
        nodes = oftenWords(head, nodes);
        for (Node node : nodes) {
            System.out.println(node.getWord() + " = " + node.getFrequency() + " times");
        }
    }

    /**
     * Recursive function that finds the the highest frequency, and adds that Node to an ArrayList of nodes.
     *
     * @param node      Node which contains the word with the highest frequency
     * @param nodes     an ArrayList of all other words with a matching frequency to that of the highest frequency present in the tree
     * @return ArrayList of Nodes which cna be used to print the words with the highest frequency
     */
    private ArrayList<Node> oftenWords(Node node , ArrayList<Node> nodes) {
        if (node == null) {
            return nodes;
        }

        if (node.getFrequency() == nodes.get(0).getFrequency()) {
            nodes.add(node);
        } else if (node.getFrequency() > nodes.get(0).getFrequency()) { // Checks to see if the frequency is greater than the previous highest recorded frequency, if it is it creates a new ArrayList, essentially deleting the old one, if the frequency matches appends the node on to the Arraylist.
            nodes = new ArrayList<>();
            nodes.add(node);
        }

        nodes = oftenWords(node.leftNode, nodes);
        nodes = oftenWords(node.rightNode, nodes);

        return nodes;
    }

    /**
     * Finds the maximum depth of the tree.
     *
     * @return Integer with the value of the depth of the tree
     */

    private int maximumDepth() {
        return depth(head);
    }

    /**
     * Recursive function calculating the highest depth of the tree, incremented only if the there exists either a left or right node
     *
     * @param node Node at which you are
     * @return Deepest level in the tree
     */
    private int depth(Node node) {
        if (node == null) {
            return 0;
        } else {
            int leftLevel = depth(node.leftNode);
            int rightLevel = depth(node.rightNode);

            if (leftLevel > rightLevel) {
                return (leftLevel + 1);
            } else {
                return (rightLevel + 1);
            }
        }

    }

    /**
     * After the tree is created, this prints the statistics of the tree including, number of words, number of words with frequency of 1, words with the highest frequency and the depth of the tree.
     */
    private void printStatisticsOfTree() {
        System.out.println("Total number of words in " + fileName + " = " + totalNumberOfWords());
        System.out.println("Number of unique words in " + fileName + " = " + totalNumberOfUniqueWords());
        System.out.println("The word(s) which occur(s) most often and the number of times that it/they occur(s) = ");
        mostOftenWords();
        System.out.println("The maximum height of the tree = " + maximumDepth());
    }

    /**
     * Searches for a word in the Tree, and prints out the frequency of the word if the word is found.
     *
     * @param word The word you want to search for.
     */
    public void searchForWord(String word) {
        word = word.toLowerCase();
        int x = nodeSearch(head, word, 0);

        if (x != 0) {
            System.out.println("Found! It appears " + x + " times in the input text file.");
        } else {
            System.out.println("Word not found!");
        }
    }

    /**
     * Recursive search in the tree for the Word
     *
     * @param node Node at which you are at
     * @param word The word to search for
     * @return A node is returned if the word is found, else it's null
     */
    private int nodeSearch(Node node, String word, int frequency) {
        if (node == null) {
            return frequency;
        }

        if (node.getWord().compareTo(word) > 0) {
            frequency = nodeSearch(node.leftNode, word, frequency);
        } else if (node.getWord().compareTo(word) < 0) {
            frequency = nodeSearch(node.rightNode, word, frequency);
        } else {
            return node.getFrequency();
        }

        return frequency;

    }

    /**
     * Option menu that allows the user to search for a word in a tree, or print the tree, or quit the application.
     */
    public void optionMenu() {
        InputManager input = new InputManager();
        int Type = 0;
        while (Type != 3) {
            Type = input.getType("If you would like to search for a word in the tree press (1), if you would like to print the tree press (2), if you would like to quit press (3)"); //Ensures Type is either 1, 2 or 3
            if (Type == 1) {
                searchForWord(input.readSingleWord());
            } else if (Type == 2) {
                printTree();
            }
        }

    }

}

