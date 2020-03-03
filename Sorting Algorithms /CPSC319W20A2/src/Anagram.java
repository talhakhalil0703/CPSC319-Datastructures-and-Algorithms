import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Anagram {
    ArrayList<String> words = new ArrayList<String>();


    public void printReadWords(int start, int end){
        if (this.words.size() <= 0){
            System.out.println("No words have been read as of yet.");
            return;
        }
        for (int i = start; i < end; i++){
            String wordToPrint = this.words.get(i);
            System.out.println(wordToPrint);
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
        System.out.println(indexToPivot);
        String pivot = this.words.get(end);
        String temp;

        int pivotIndex = start;

        for (int i = start; i <= (end-1); i++){
            System.out.println(i);
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
        String temp = this.words.get(a);
        this.words.set(a, this.words.get(b));
        this.words.set(b, temp);
    }

    private boolean isALessThanB(String A, String B){
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

}
