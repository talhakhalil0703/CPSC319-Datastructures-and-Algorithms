/**
 * Matrix file which contains the edges, vertices, and creates the graphs, the minimim spanning tree, and depth traversal
 * @author Talha Khalil
 * @since April 16, 2020
 */
public class Matrix {
    /**
     * The size of the pixel subesections, example 3, for 3x3, 5 for 5x5 and so on
     */
    private int sizeOfSubsections;
    /**
     * The amount of vertices in the graph
     */
    private int numberOfVertices;
    /**
     * Contains the weight of all edges
     */
    private int[][] edges;
    /**
     * Contains all the vertices and their subsections
     */
    private Node[] vertices;
    /**
     * Notes nodes that have been visited
     */
    private int[] visited;
    /**
     * Used to create the traversal string
     */
    private String traversal;
    /**
     * Notes which vertices is the parent when creating a minimum spanning tree
     */
    private int[] parent;

    /**
     * Constructor for the matrix, creates the graph and the minimum spanning tree
     * @param sizeOfSubsections he size of the pixel subesections, example 3, for 3x3, 5 for 5x5 and so on
     * @param numberOfVertices The amount of vertices in the graph
     * @param vertices Contains all the vertices and their subsections
     */
    public Matrix(int sizeOfSubsections, int numberOfVertices, Node[] vertices) {
        this.sizeOfSubsections = sizeOfSubsections;
        this.numberOfVertices = numberOfVertices;
        this.vertices = vertices;
        edges = new int[numberOfVertices][numberOfVertices];
        createMatrix(); // Creates the graph
        minimumSpanningTree(); //Assigns the parent of each vertices in a minimum spanning tree
    }

    /**
     * Creates the graph
     */
    private void createMatrix() {
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j <= i; j++) {
                edges[i][j] = vertices[i].compareWeight(vertices[j]);
                edges[j][i] = edges[i][j];
            }
        }
    }

    /**
     * Creates a string which can be used to print the Depth first traversal
     * @return String which can be written to a file
     */
    public String depthFirstTraversalToString() {
        if (edges.length == 0){
            System.out.println("There are was no graph created");
            return "";
        }
        traversal = "Edge\tWeight\n";
        visited = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++)
            visited[i] = 0;

        DFS(0);
        return traversal;
    }

    /**
     * Recursive function which traverses the graph
     * @param i the index of the node which it is currently at
     */
    private void DFS(int i) {
        visited[i] = 1;
        for (int j = 0; j < numberOfVertices; j++) {
            if (visited[j] == 0 && edges[i][j] > 0) {
                traversal += i + " - " + j + "\t" + edges[i][j] + "\n";
                DFS(j);
            }
        }
    }

    /**
     * Creates a minimum spanning tree and updates the parent array, with the parents of each vertice
     */
    public void minimumSpanningTree() {
        parent = new int[numberOfVertices];
        int[] key = new int[numberOfVertices]; // Used to store weight
        boolean[] extracted = new boolean[numberOfVertices]; // Determines if the vertice has already been extracted

        key[0] = 0; // Setting the root
        extracted[0] = false;
        parent[0] = -1;

        for (int i = 1; i < numberOfVertices; i++) { //Setting all other vertices to max weight and extracted to false;
            key[i] = Integer.MAX_VALUE;
            extracted[i] = false;
            parent[i] = -1;
        }

        for (int count = 0; count < numberOfVertices; count++){ // For the number of vertices
            int min = returnMinimumKeyIndex(extracted, key); // Extract the minimal vertice if it has not already been extracted
            for(int i = 0; i < numberOfVertices; i++){ // For the number of vertices that have not been extracted
                if (!extracted[i]){
                    if (edges[min][i] < key[i]){ // If their weight is less than the key they have update the key and parent
                        key[i] = edges[min][i];
                        parent[i] = min;
                    }
                }
            }
        }
    }

    /**
     * Returns the index of smallest vertice edge given that it has not already been extracted
     * @param extracted Array of extracted vertices
     * @param key Array of the smmallest edges for a vertice
     * @return the index of the smallest vertice edge
     */
    private int returnMinimumKeyIndex(boolean[] extracted, int[] key) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfVertices; i++) {
            if (key[i] < min && !extracted[i]) {
                min = key[i];
                minIndex = i;
            }
        }
        extracted[minIndex] = true;
        return minIndex;
    }

    /**
     * Gets the vertices
     * @return vertices
     */
    public Node[] getVertices() {
        return vertices;
    }

    /**
     * Gets the number of vertices in the graph
     * @return int of the number of vertices in the graph
     */
    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    /**
     * Gets the edges of the graph
     * @return the edges of the graph
     */
    public int[][] getEdges() {
        return edges;
    }

    /**
     * Gets the parents of each vertice
     * @return the parent of each vertice
     */
    public int[] getParent() {
        return parent;
    }
}
