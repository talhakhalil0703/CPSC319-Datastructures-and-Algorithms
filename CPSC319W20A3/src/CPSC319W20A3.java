/**
 * Assignment 3, this file run the code of te Binary Tree and gets the inital user input
 *
 * @author Talha Khalil
 * @since March 26, 2020
 */
public class CPSC319W20A3 {
    public static void main(String [] args ){
        InputManager input = new InputManager();
        String fileName = input.promptUserForFile();
        BinaryTree BT = new BinaryTree(input.getWords(), fileName);
    }
}
