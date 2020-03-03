import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CPSC319W20A2 {

    public static void main(String [] args){
        Anagram anagram = new Anagram();

        for(int i = 0; i < args.length; i++) {
            System.out.println(i + " "+ args[i]);
        }
        anagram.readWords(args[0]);
        anagram.printReadWords(0, anagram.words.size());
        anagram.quickSortWordsAlphabetically(0, (anagram.words.size()-1));
        System.out.println("Sorted words below:");
        anagram.printReadWords(0, anagram.words.size());
    }


}
