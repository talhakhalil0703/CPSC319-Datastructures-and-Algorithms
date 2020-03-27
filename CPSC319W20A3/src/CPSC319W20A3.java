import java.util.ArrayList;

public class CPSC319W20A3 {
    public static void main(String [] args ){
        CPSC319W20A3 cpsc = new CPSC319W20A3();
        InputManager fileReader = new InputManager();
        fileReader.readFile();
        System.out.println();
        BinaryTree BT = new BinaryTree(fileReader.getWords());
        System.out.println("Number of words: " +  BT.totalNumberOfWords());
        System.out.println("Number of unique words: " +  BT.totalNumberOfUniqueWords());
        BT.printTree();
    }

}
