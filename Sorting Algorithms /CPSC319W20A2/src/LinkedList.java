public class LinkedList {
    Node head;

    public LinkedList(Word word) {
        head = new Node(word);
    }

    public void addToList(Word word) {
        Node pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        pointer.next = new Node(word);
    }

    public void printList() {

        Node pointer = head;
        while (pointer != null) {
            System.out.print(" " + pointer.word.getWord());
            pointer = pointer.next;
        }
    }

    @Override
    public String toString() {
        String s = head.word.toString();

        Node pointer = head.next;
        while (pointer != null) {
            s += " " + pointer.word;
            pointer = pointer.next;
        }

        return s;
    }


}

class Node {
    Word word;
    Node next;

    public Node(Word word) {
        this.word = word;
        next = null;
    }
}
