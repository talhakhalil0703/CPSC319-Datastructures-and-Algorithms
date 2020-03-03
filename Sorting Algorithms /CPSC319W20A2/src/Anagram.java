import java.util.ArrayList;
import java.util.Random;

public class Anagram {
    ArrayList<Word> words;
    ArrayList<LinkedList> anagramList;

    public Anagram(ArrayList<Word> wordList){
        this.words = wordList;
        this.anagramList = new ArrayList<>();
    }

    public void printReadWords(int start, int end){
        if (this.words.size() <= 0){
            System.out.println("No words have been read as of yet.");
            return;
        }
        for (int i = start; i < end; i++){
            this.words.get(i).printWordAndSortedWord();
        }
    }

    public void quickSortWordsAlphabetically(int start, int end){
        //Average running time is nlogn, and worst case is n^2 . However it is in place sorting which allows us to not consume a lot of memory especially when dealing with large data set
        if(start >= end)
            return;
        int pivotIndex = partitionSort(start, end);
        quickSortWordsAlphabetically(start, pivotIndex-1);
        quickSortWordsAlphabetically(pivotIndex+1, end);
    }

    private int partitionSort(int start, int end){
        int indexToPivot = getPivot(start, end);
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

    private static int getPivot(int start, int end) {
        Random r = new Random();
        return r.nextInt((end - start) + 1) + end;
    }

    private void swapValuesAtIndex(int a, int b){
        Word temp = this.words.get(a);
        this.words.set(a, this.words.get(b));
        this.words.set(b, temp);
    }

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

    private int isInArray(ArrayList<String> foundAnagrams, String word){
        for (int i = 0; i < foundAnagrams.size(); i++){
            if (foundAnagrams.get(i).equals(word)){
                return i;
            }
        }
        return -1;
    }

    public void printAnagrams() {
        for (int i = 0; i < anagramList.size(); i++){
            System.out.println(anagramList.get(i));
        }
    }

}
