public class Node {
    private int Size;
    private int [][] pixels;

    public Node(int Size, int [] pixelsArray){
        this.Size =  Size;
        this.pixels = new int [Size][Size];
        populatePixels(pixelsArray);
    }

    private void populatePixels(int [] pixelsArray){
        if (pixelsArray.length != Size*Size){
            System.out.println("The number of pixels does not match the size of the pixel array");
            return;
        }

        int count = 0;
        for (int i = 0; i < Size; i++){
            for (int j = 0; j < Size; j++){
                pixels[i][j] = pixelsArray[count++];
            }
        }
    }

    public int [][] getPixels(){
        return pixels;
    }

    public int compareWeight(Node otherNode){
        int difference = 0;
        for (int i = 0; i < Size; i++){
            for (int j = 0; j < Size; j++){
                if (pixels[i][j] !=  otherNode.getPixels()[i][j])
                difference++;
            }
        }
        return difference;
    }

}
