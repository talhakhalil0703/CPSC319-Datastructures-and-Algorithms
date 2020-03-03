import java.util.ArrayList;

public class CPSC319W20A2 {

    public static void main(String [] args){
        ArrayList<Word> words = new ArrayList<>();
        FileManager manager = new FileManager();

        for(int i = 0; i < args.length; i++) {
            System.out.println(i + " "+ args[i]);
        }

        manager.readWords(args[0], words);
        Anagram anagram = new Anagram(words);
        anagram.printReadWords(0, anagram.words.size());
        anagram.quickSortWordsAlphabetically(0, (anagram.words.size()-1));
        System.out.println("Sorted words below:");
        anagram.printReadWords(0, anagram.words.size());
        anagram.findAnagrams();
        System.out.println("Anagrams below:");
        anagram.printAnagrams();
    }

}
