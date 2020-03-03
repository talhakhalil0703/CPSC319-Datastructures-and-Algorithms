import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public void readWords(String fileName, ArrayList<Word> words) {

        try {
            String fileLocation = System.getProperty("user.dir") + "/" + fileName;
            FileReader fileToRead = new FileReader(fileLocation);
            BufferedReader buffer = new BufferedReader(fileToRead);

            String line = "";

            while ((line = buffer.readLine()) != null) {
                if (line != "" && line != "\n" && line != "\r") {
                    Word word = new Word(line);
                    words.add(word);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeWords(String fileName, ArrayList<LinkedList> anagram) {
        try {
            File fileToWrite = new File(fileName);
            if (fileToWrite.createNewFile())
                System.out.println("Text file created!");
            else {
                fileToWrite.delete();
            }
        } catch (IOException e) {
            System.out.println("An error occured while trying to create print file!");
        }

        try {
            FileWriter writeToFile = new FileWriter(fileName);
            for (int i = 0; i < anagram.size(); i++) {
                writeToFile.write(anagram.get(i).toString() + "\n");
            }
            writeToFile.close();
        } catch (IOException e) {
            System.out.println("Could not write to Order file");
        }
    }
}
