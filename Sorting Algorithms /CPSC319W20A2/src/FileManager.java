import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

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
