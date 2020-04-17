/**
 * Nodes that are used by the Matrix contains, the pixels that are stored, it's size, and the pixels in 1 dimensional array
 *
 * @author Talha Khalil
 * @since April 16, 2020
 */
public class Node {
    /**
     * The size of the pixel subsections, 3 for 3x3, 5 for 5x5, and so on
     */
    private int size;
    /**
     * The pixel values in the subsection
     */
    private int[][] pixels;
    /**
     * The pixels stored in a one dimensional array, this is how the file manager piped in the pixels
     */
    private int[] pixelsArray;
    /**
     * Index of the vertice
     */
    private int index;

    /**
     * Constructor for the Node which takes in the pixel size, and the pixels array from which to create the 2D array
     *
     * @param size        the size of the pixel subsections, 3 for 3x3, 5 for 5x5, and so on
     * @param pixelsArray The pixels stored in a one dimensional array, this is how the file manager piped in the pixels
     */
    public Node(int index, int size, int[] pixelsArray) {
        this.size = size;
        this.index = index;
        this.pixelsArray = pixelsArray;
        this.pixels = new int[size][size];
        populatePixels();
    }

    /**
     * Creates the 2D pixel array given the 1D array of values
     */
    private void populatePixels() {
        if (pixelsArray.length != size * size) {
            System.out.println("The number of pixels does not match the size of the pixel array");
            return;
        }

        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pixels[i][j] = pixelsArray[count++];
            }
        }
    }

    /**
     * Gets the 2D pixels array
     *
     * @return 2D pixels array
     */
    public int[][] getPixels() {
        return pixels;
    }

    /**
     * Gets all the pixels in the array
     *
     * @return all the pixels in the array
     */
    public int[] getPixelsArray() {
        return pixelsArray;
    }

    /**
     * Calculates the difference between two nodes
     *
     * @param otherNode node to which to compare to
     * @return the amount of pixels that are different
     */
    public int compareWeight(Node otherNode) {
        int difference = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (pixels[i][j] != otherNode.getPixels()[i][j])
                    difference++;
            }
        }
        return difference;
    }
}
