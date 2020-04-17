import java.util.Arrays;

public class Matrix {
    int sizeOfSubsections;
    int numberOfVertices;
    int [][] edges;
    Node [] vertices;

    public Matrix(int sizeOfSubsections, int numberOfVertices, Node [] vertices){
        this.sizeOfSubsections = sizeOfSubsections;
        this.numberOfVertices = numberOfVertices;
        this.vertices = vertices;
        edges = new int [numberOfVertices][numberOfVertices];
        createMatrix();
        printMatrix();
    }

    private void createMatrix(){
        for (int i =0; i < numberOfVertices; i++){
            for (int j = 0; j <= i; j++){
                edges[i][j] = vertices[i].compareWeight(vertices[j]);
                edges[j][i] = edges[i][j];
            }
        }
    }

    private void printMatrix(){
        String S = "Edge    Weight\n";
        for (int i =0; i < numberOfVertices; i++){
            for (int j = i; j < numberOfVertices; j++){
                if (i==j)
                    continue;
                S += i + " - " + j + "  " + edges[i][j] + "\n";
            }
        }
        System.out.println(S);
    }

    public Node [] getVertices(){
        return vertices;
    }

    @Override
    public String toString() {
        String S = "";
        for (int i = 0; i < numberOfVertices; i++){
            S += vertices[i];
        }
        return S;
    }
}
