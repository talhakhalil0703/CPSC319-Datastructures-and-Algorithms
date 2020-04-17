/**
 * Assignment 4, this file runs the code of the Graph and creates the files
 *
 * @author Talha Khalil
 * @since April 16, 2020
 */
public class CPSC319W20A4 {
    public static void main(String args[]) {
        FileManager fileManager = new FileManager();

        if (args.length == 0) {
            System.out.println("You did not enter a filename"); // Checking to see if the user added a filename
            System.exit(0);
        }

        Matrix matrix = fileManager.readFile(args[0]);
        fileManager.writeToFile(matrix);
    }

}
