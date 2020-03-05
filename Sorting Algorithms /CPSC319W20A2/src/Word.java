/**
 * An Object of Word
 * @author Talha Khalil 30037871
 * @since  March 4, 2020
 * @version 1.0
 */

public class Word {
    /**
     * The word stored as a String as its read from the user input
     */
    private String word;
    /**
     * Alphabetically sorted version of the word read from user input
     */
    private String sortedWord;

    /**
     * Constructor for the Word, which takes a word as an argument and stores it and calls the sortWord function to find the sorted version
     * @param word
     */
    public Word(String word) {
        this.word = word;
        sortWord();
    }

    /**
     * Returns the String word as read from the user input
     * @return String of word as read from the user input
     */
    public String getWord() {
        return word;
    }
    /**
     * Returns the String sortedWord, which is sorted alphabetically
     * @return String sortedWord, which is sorted alphabetically
     */
    public String getSortedWord() {
        return sortedWord;
    }

    /**
     * Sorts the word alphabetically using insertion sort
     */
    private void sortWord() {
        this.sortedWord = word;
        int len = this.sortedWord.length();
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                for (int j = i; j > 0 && this.sortedWord.charAt(j - 1) > this.sortedWord.charAt(j); j--) {
                    swapChars(j, j - 1);
                }
            }
        }

    }

    /**
     * Swaps the characters a and b in the word
     * @param a character to be swapped
     * @param b character to be swapped
     */

    private void swapChars(int a, int b) {
        char temp = this.sortedWord.charAt(a);
        char[] stringArray = sortedWord.toCharArray();
        stringArray[a] = this.sortedWord.charAt(b);
        stringArray[b] = temp;
        sortedWord = String.valueOf(stringArray);
    }

    /**
     * toString which returns the String word, that was read from user input
     * @return String word
     */
    @Override
    public String toString(){
        return this.word;
    }
}
