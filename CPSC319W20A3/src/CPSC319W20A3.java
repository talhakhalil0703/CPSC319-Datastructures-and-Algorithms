import java.util.ArrayList;

public class CPSC319W20A3 {
    public static void main(String [] args ){
        CPSC319W20A3 cpsc = new CPSC319W20A3();
        InputManager input = new InputManager();
        String fileName = input.promptUserForFile();
        BinaryTree BT = new BinaryTree(input.getWords(),fileName);
    }

}
