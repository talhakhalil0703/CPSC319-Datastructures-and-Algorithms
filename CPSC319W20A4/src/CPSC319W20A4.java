public class CPSC319W20A4 {
    public static void main (String args []){
        FileManager fileManager = new FileManager();

        if (args.length == 0){
            System.out.println("You did not enter a filename");
            System.exit(0);
        }

        Matrix matrix = fileManager.readFile(args[0]);
        fileManager.writeToFile(matrix.matrixToString(), args[0].replace("data", "GRAPH"));
        fileManager.writeToFile(matrix.depthFirstTraversalToString(), args[0].replace("data", "DFT"));
        fileManager.writeToFile(matrix.minimumSpanningTreeToString(), args[0].replace("data", "MST"));

    }

}
