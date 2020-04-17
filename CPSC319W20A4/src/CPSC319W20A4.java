public class CPSC319W20A4 {
    public static void main (String args []){
        FileManager fileManager = new FileManager();

        if (args.length == 0){
            System.out.println("You did not enter a filename");
            System.exit(0);
        }

        fileManager.readFile(args[0]);
    }

}
