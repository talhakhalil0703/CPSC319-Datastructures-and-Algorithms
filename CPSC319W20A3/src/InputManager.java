import sun.security.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * InputManager is a class that reads the file provided by the user. It also prompts the user to enter the file that is to be read.
 *
 * @author Talha Khalil
 * @since March 26, 2020
 */
public class InputManager {
    /**
     * Name of the file to read
     */
    String fileToRead = "test.txt";
    /**
     * An ArrayList of words in the file
     */
    ArrayList<String> words;

    /**
     * Prompts the user to enter the name of the file to analyze, and if the file exists reads the file and stores the words in an ArrayList, if it doesn't keeps asking the user until they do enter one.
     */
    public String promptUserForFile() {
        try {
            while (words == null) {
                System.out.println("Name of file to be read: ");
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    System.out.println("No file was selected.");
                } else {
                    fileToRead = line;
                }

                readFile();

                if (words == null) {
                    System.out.println("Could not read the file labeled: " + fileToRead + ", or the file contained no words, please try another file.");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return fileToRead;
    }

    /**
     * Reads the file and stores the words in an ArrayList. While the words are being read they are converted to lowercase, and anything but letters and numbers is removed.
     */
    public void readFile() {
        try {
            if (fileToRead == "") {
                System.out.println("No file was selected to be read");
                return;
            }
            String fileLocation = System.getProperty("user.dir") + "/" + fileToRead;
            System.out.println("Trying to read file located at:" + fileLocation);
            FileReader reader = new FileReader(fileLocation);
            BufferedReader buffer = new BufferedReader(reader);

            String line = "";
            words = new ArrayList<>();

            while ((line = buffer.readLine()) != null) {
                String[] lineWords = line.replaceAll("[^0-9a-zA-Z ]", " ").toLowerCase().split("\\s+"); // Cleans the line of any unwanted symbols
                for (String singleWord : lineWords) {
                    words.add(singleWord); //Takes the single words in lineWords and adds them to ArrayList
                }
            }

            if (words.size() == 0) {
                words = null;
            } else {
                System.out.println(fileLocation + " was read.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the ArrayList of words
     *
     * @return ArrayList of words that have been read.
     */
    public ArrayList<String> getWords() {
        return words;
    }


    /**
     * Returns the Binary Tree display type the user wants
     *
     * @return An int, 1 for in order, 2 for pre order, and 3 for post order, displaying of the tree
     */
    public int getPrintType() {

        int type = 0;

        try {
            Scanner scanner = new Scanner(System.in);
            while (type != 1 && type != 2 && type != 3) {
                System.out.println("Enter the BST traversal method: (1 = In-order, 2 = Pre-order, 3 = Post-order) for " + fileToRead + ": ");
                try {
                    String line = scanner.nextLine();
                    type = Integer.valueOf(line);
                } catch (NumberFormatException e) {
                    continue;
                }

                if (type != 1 && type != 2 && type != 3) {
                    System.out.println("Incorrect option, please try again.");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return type;
    }

}
