import java.util.ArrayList;
import java.util.Scanner;

/**
 * InputManager class that reads user input from the terminal
 * @author Talha Khalil 30037871
 * @since  March 4, 2020
 * @version 1.0
 */

public class InputManager {

    /**
     * Reads and stores the input data from the console to an ArrayList
     * @param words ArrayList of object Word
     */
    public void readWords(ArrayList<Word> words) {

        try {
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty())
                    break;
                Word word = new Word(line);
                words.add(word);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
