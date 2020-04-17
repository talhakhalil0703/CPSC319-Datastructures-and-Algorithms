import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * FileManager is a class that reads a file provided by the user as an argument in main, and it also creates the files
 * @author Talha Khalil
 * @since April 16, 2020
 */
public class FileManager {

    /**
     * The name of the file to be read, this is stored so that it can be later renamed to create files
     */
    String fileName;

    /**
     * Creates the matrix by reading in the data file
     * @param fileToRead location of the data file
     * @return Matrix
     */
    public Matrix readFile(String fileToRead) {
        try {
            fileName = fileToRead;

            String fileLocation = System.getProperty("user.dir") + "/" + fileToRead;
            System.out.println("Trying to read file located at:" + fileLocation);
            FileReader reader;

            try {
                reader = new FileReader(fileLocation);
            } catch (FileNotFoundException x) {
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
            int[] values = new int[sizeOfSubsections * sizeOfSubsections];
            Node[] vertices = new Node[numberOfVertices];
            while (scan.hasNext()) {
                index = scan.nextInt();
                for (int i = 0; i < sizeOfSubsections * sizeOfSubsections; i++) {
                    values[i] = scan.nextInt();
                }
                vertices[index] = new Node(sizeOfSubsections, values);
            }

            return new Matrix(sizeOfSubsections, numberOfVertices, vertices);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Creates files based on matrix
     * @param data Matrix which provides the data the file needs to write
     */
    public void writeToFile(Matrix data) {
        try {

            FileWriter writer = new FileWriter(fileName.replace("data", "GRAPH"), false);
            writer.write("Edge\tWeight\n");
            for (int i = 0; i < data.getNumberOfVertices(); i++) {
                for (int j = i; j < data.getNumberOfVertices(); j++) {
                    if (i == j)
                        continue;
                    writer.write(i + " - " + j + "\t" + data.getEdges()[i][j] + "\n");
                }
            }
            writer.close();

            writer = new FileWriter(fileName.replace("data", "MST"), false);
            writer.write("Edge\tWeight\n");
            for (int i = 1; i < data.getNumberOfVertices(); i++)
                writer.write(data.getParent()[i] + " - " + i + "\t" + data.getEdges()[i][data.getParent()[i]] + "\n");
            writer.close();

            writer = new FileWriter(fileName.replace("data", "DFT"), false);
            writer.write(data.depthFirstTraversalToString());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }


}
