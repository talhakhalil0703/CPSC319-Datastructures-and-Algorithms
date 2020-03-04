import java.util.ArrayList;

public class CPSC319W20A2 {

    public static void main(String [] args){
        ArrayList<Word> words = new ArrayList<>();
        FileManager manager = new FileManager();
        manager.readWords(words);
        Anagram anagram = new Anagram(words);ß
        anagram.quickSortWordsAlphabetically(0, (anagram.words.size()-1));
        anagram.findAnagrams();
        anagram.printAnagrams();
    }

}
