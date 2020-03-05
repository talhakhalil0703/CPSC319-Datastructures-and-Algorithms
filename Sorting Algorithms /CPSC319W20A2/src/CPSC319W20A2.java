import java.util.ArrayList;

/**
 * Main class for the purpose of running this assignment used to call the anagram functions.
 * @author Talha Khalil 30037871
 * @since  March 4, 2020
 * @version 1.0
 */

public class CPSC319W20A2 {

    public static void main(String [] args){
        ArrayList<Word> words = new ArrayList<>(); // Creating an empty list of words, that we send to Anagram
        InputManager manager = new InputManager(); // Used for the purpose of reading input data
        manager.readWords(words); // Stores input data in the earlier Arraylist
        Anagram anagram = new Anagram(words); // Creates an object of Anagram by passing the ArrayList on which functions will be performed on
        anagram.quickSortWordsAlphabetically(0, (anagram.words.size()-1)); // Sorts the List of words in alphabetical orders, List 1
        anagram.findAnagrams(); // Finds all the anagrams in List1 and creates List2 consisting of linked lists of type word
        anagram.printAnagrams(); // Prints the linked list onto the terminal
    }

}
