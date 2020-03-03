import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileManager {

    public void readWords(String fileName, ArrayList<Word> words){

        try {
            String fileLocation = System.getProperty("user.dir") + "/" +fileName;
            System.out.println(fileLocation);
            FileReader fileToRead = new FileReader(fileLocation);
            BufferedReader buffer =  new BufferedReader(fileToRead);

            String line = "";

            while((line = buffer.readLine()) != null){
                Word word = new Word(line);
                words.add(word);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
