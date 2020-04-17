import java.util.Arrays;

public class Node {
    private int size;
    private int [][] pixels;
    private int index;

    public Node(int index, int size, int [] pixelsArray){
        this.index = index;
        this.size =  size;
        this.pixels = new int [size][size];
        populatePixels(pixelsArray);
    }

    private void populatePixels(int [] pixelsArray){
        if (pixelsArray.length != size * size){
            System.out.println("The number of pixels does not match the size of the pixel array");
            return;
        }

        int count = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                pixels[i][j] = pixelsArray[count++];
            }
        }
    }

    public int [][] getPixels(){
        return pixels;
    }

    public int compareWeight(Node otherNode){
        int difference = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (pixels[i][j] !=  otherNode.getPixels()[i][j])
                difference++;
            }
        }
        return difference;
    }

    @Override
    public String toString() {
        String S = String.valueOf(index) + '\n';
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                S += pixels[i][j] + " ";
            }
            S += '\n';
        }

        return S;
    }

}
