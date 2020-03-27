import java.util.ArrayList;

public class CPSC319W20A3 {
    public static void main(String [] args ){
        CPSC319W20A3 cpsc = new CPSC319W20A3();
        InputManager fileReader = new InputManager();
        fileReader.readFile();
        System.out.println();
        String fileName = "test.txt";
        BinaryTree BT = new BinaryTree(fileReader.getWords(), fileName);
        BT.searchForWord("Thi21s");
        BT.printTree();

    }

}
