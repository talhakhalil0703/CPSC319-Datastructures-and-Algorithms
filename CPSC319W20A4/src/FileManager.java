import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileManager {
    String fileToRead;
    public Matrix readFile(String fileToRead) {
        this.fileToRead = fileToRead;

        try {
            String fileLocation = System.getProperty("user.dir") + "/" + fileToRead;
            System.out.println("Trying to read file located at:" + fileLocation);
            FileReader reader;

            try {
                reader = new FileReader(fileLocation);
            } catch (FileNotFoundException x){
                System.out.println("File could not be found!");
                System.exit(0);
                return null;
            }

            Scanner scan = new Scanner(reader);

            int sizeOfSubsections = scan.nextInt();
            scan.nextInt();
            int numberOfVertices = scan.nextInt();
            System.out.println("Creating a graph with size: " + sizeOfSubsections + "X" + sizeOfSubsections + " with " + numberOfVertices + " vertices");
            int index;
            int [] values = new int[sizeOfSubsections*sizeOfSubsections];
            Node [] vertices = new Node[numberOfVertices];
            while (scan.hasNext()) {
                index = scan.nextInt();
                for (int i = 0; i < sizeOfSubsections*sizeOfSubsections;i++){
                    values[i] = scan.nextInt();
                }
                vertices[index] = new Node(index, sizeOfSubsections, values);
            }

            return new Matrix(sizeOfSubsections, numberOfVertices, vertices);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    

}
