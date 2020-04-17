import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {
    int sizeOfSubsections;
    int numberOfVertices;
    int[][] edges;
    Node[] vertices;
    int[] visited;
    String traversal;

    public Matrix(int sizeOfSubsections, int numberOfVertices, Node[] vertices) {
        this.sizeOfSubsections = sizeOfSubsections;
        this.numberOfVertices = numberOfVertices;
        this.vertices = vertices;
        edges = new int[numberOfVertices][numberOfVertices];
        createMatrix();

    }

    private void createMatrix() {
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j <= i; j++) {
                edges[i][j] = vertices[i].compareWeight(vertices[j]);
                edges[j][i] = edges[i][j];
            }
        }
    }

    public String matrixToString() {
        String S = "Edge\tWeight\n";
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = i; j < numberOfVertices; j++) {
                if (i == j)
                    continue;
                S += i + " - " + j + "\t" + edges[i][j] + "\n";
            }
        }
        return S;
    }

    public String depthFirstTraversalToString() {
        traversal = "Edge\tWeight\n";
        visited = new int[numberOfVertices];

        for (int i = 0; i < numberOfVertices; i++)
            visited[i] = 0;

        DFS(0);
        return traversal;
    }

    private void DFS(int i) {
        visited[i] = 1;
        for (int j = 0; j < numberOfVertices; j++) {
            if (visited[j] == 0 && edges[i][j] > 0) {
                traversal += i + " - " + j + "\t" + edges[i][j] + "\n";
                DFS(j);
            }
        }
    }

    public String minimumSpanningTreeToString() {
        int[] parent = new int[numberOfVertices];
        int[] key = new int[numberOfVertices];
        boolean[] extracted = new boolean[numberOfVertices];

        key[0] = 0;
        extracted[0] = false;
        parent[0] = -1;

        for (int i = 1; i < numberOfVertices; i++) { //Setting the root
            key[i] = Integer.MAX_VALUE;
            extracted[i] = false;
            parent[i] = -1;

        }

        for (int count = 0; count < numberOfVertices; count++){
            int min = returnMinimumKeyIndex(extracted, key);
            for(int i = 0; i < numberOfVertices; i++){
                if (!extracted[i]){
                    if (edges[min][i] < key[i]){
                        key[i] = edges[min][i];
                        parent[i] = min;
                    }
                }
            }
        }

       String S = "Edge\tWeight";
        for (int i = 1; i < numberOfVertices; i++)
            S += parent[i] + " - " + i + "\t" + edges[i][parent[i]];

        return S;
    }

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


    public Node[] getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        String S = "";
        for (int i = 0; i < numberOfVertices; i++) {
            S += vertices[i];
        }
        return S;
    }
}
