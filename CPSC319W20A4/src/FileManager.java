import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {

    String fileName;

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
                vertices[index] = new Node(index, sizeOfSubsections, values);
            }

            return new Matrix(sizeOfSubsections, numberOfVertices, vertices);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

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
