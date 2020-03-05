import java.util.ArrayList;

/**
 * Anagram class that take an ArrayList of Word and can perform functions such as quicksorting the list alphabetically and
 * @author Talha Khalil 30037871
 * @since  March 4, 2020
 * @version 1.0
 */
public class Anagram {
    /**
     * A list of words, which are later sorted
     */
    ArrayList<Word> words;
    /**
     * List of LinkedList containing words that are anagrams of one another
     */
    ArrayList<LinkedList> anagramList;

    /**
     *Takes an argument of an ArrayList of Words that are already filled in, but not sorted, also creates a new LinkedList ArrayList
     * @param wordList List of Words that are too be sorted alphabetically, and anagrams found of
     */
    public Anagram(ArrayList<Word> wordList){
        this.words = wordList;
        this.anagramList = new ArrayList<>();
    }

    /**
     *  Sorts the words given using quicksort alphabetically, the quick sort uses a random pivot
     * @param start The start index of the list to start sorting from
     * @param end The end index of the list to sort
     */
    public void quickSortWordsAlphabetically(int start, int end){
        //Average running time is nlogn, and worst case is n^2 . However it is in place sorting which allows us to not consume a lot of memory especially when dealing with large data set
        if(start >= end)
            return;
        int pivotIndex = getPivot(start, end);
        quickSortWordsAlphabetically(start, pivotIndex-1);
        quickSortWordsAlphabetically(pivotIndex+1, end);
    }

    /**
     * If a given index has a value of less than the pivot its moved to the left, if greater its moved to the right
     * @param start The start index of the list to start sorting from
     * @param end The end index of the list to sort
     * @return The index of the pivot point
     */
    private int partitionSort(int start, int end){
        Word pivot = this.words.get(end);

        int pivotIndex = start;

        for (int i = start; i <= (end-1); i++){
            if (isALessThanB(this.words.get(i), pivot)){
                swapValuesAtIndex(i, pivotIndex);
                pivotIndex++;
            }
        }
        swapValuesAtIndex(pivotIndex, end);
        return pivotIndex;
    }

    /**
     * Generates a index to use as the pivot, and calls partionSort to sort the List
     * @param start The start index of the list to start sorting from
     * @param end The end index of the list to sort
     * @return The index of the pivot point
     */
    private int getPivot(int start, int end) {
        int rand =  (int)(Math.random()*((end-start)+1)  + start);
        swapValuesAtIndex(rand, end);
        return partitionSort(start, end);
    }

    /**
     * Swaps the elements at a and b for the ArrayList of Words
     * @param a Element to be swapped
     * @param b Element to be swapped
     */

    private void swapValuesAtIndex(int a, int b){
        Word temp = this.words.get(a);
        this.words.set(a, this.words.get(b));
        this.words.set(b, temp);
    }

    /**
     * Returns true if A comes before B alphabetically, makes a comparison using ASCII values
     * @param wordA first word
     * @param wordB second word
     * @return true if the words are in alphabetical order, false otherwise
     */
    private boolean isALessThanB(Word wordA, Word wordB){
        String A = wordA.getWord();
        String B = wordB.getWord();

        int shortestLength = A.length();

        if (A.length() > B.length())
            shortestLength = B.length();

        char [] Achar = A.toCharArray();
        char [] Bchar = B.toCharArray();

        for (int i = 0; i < shortestLength; i++) {
            if (Achar[i] < Bchar[i])
                return true;
            else if (Achar[i] > Bchar[i])
                return false;
        }
        return true;
    }

    /**
     * Finds the Anagrams in ArrayList of Words, and adds them to the LinkedList ArrayList.
     */
    public void findAnagrams(){
        ArrayList<String> foundAnagrams = new ArrayList<>();

        for (int i = 0; i < words.size(); i++){
            Word lookingAtWord = words.get(i);

            if (foundAnagrams.size() == 0){
                anagramList.add(new LinkedList(lookingAtWord));
                foundAnagrams.add(lookingAtWord.getSortedWord());
                continue;
            }

            int x = isInArray(foundAnagrams, lookingAtWord.getSortedWord());
            if (x == -1){
                anagramList.add(new LinkedList(lookingAtWord));
                foundAnagrams.add(lookingAtWord.getSortedWord());
            } else {
                anagramList.get(x).addToList(lookingAtWord);
            }
        }

    }

    /**
     * Checks if the word is already in the Array foundAnagrams
     * @param foundAnagrams Array of Anagrams that have already been found
     * @param word sorted word, used to check if an anagram of it has already been stored
     * @return the index of the anagram if it has been stored before, or -1 if it has not
     */
    private int isInArray(ArrayList<String> foundAnagrams, String word){
        for (int i = 0; i < foundAnagrams.size(); i++){
            if (foundAnagrams.get(i).equals(word)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints the Anagram LinkedList to the console
     */
    public void printAnagrams() {
        for (int i = 0; i < anagramList.size(); i++){
            System.out.println(anagramList.get(i));
        }
    }

}
