import java.util.ArrayList;

public class Fibonacci {

    public static int recursive(int n) {
        if (n <= 1) {
            return n;
        } else {
            return recursive(n - 1) + recursive(n - 2);
        }
    }

    public static int memoization(int n) {
        ArrayList<Integer> previousCallValues = new ArrayList<Integer>();
        previousCallValues.add(0, 0);
        previousCallValues.add(1, 1);
        Fibonacci memoizationHelper = new Fibonacci();
        return memoizationHelper.memoizationHelper(n, previousCallValues);
    }

    private int memoizationHelper(int n, ArrayList<Integer> memArray) {
        if (n <= 1) {
            return n;
        } else if (memArray.size() == (n + 1)) {
            return memArray.get(n);
        } else {
            memArray.add(n, memoizationHelper(n - 1, memArray) + memoizationHelper(n - 2, memArray));
            return memArray.get(n);
        }
    }

    public static int dynamic(int n) {
        ArrayList<Integer> fib = new ArrayList<Integer>();
        fib.add(0, 0);
        fib.add(1, 1);
        for (int i = 2; i <= n; i++) {
            fib.add(i, fib.get(i - 1) + fib.get(i - 2));
        }
        return fib.get(n);
    }

    public static int iterator(int n) {
        int np1 = 1;
        int np2 = 0;
        int intermediate;

        for (int i = 2; i <= n; i++) {
            intermediate = np1;
            np1 = np1 + np2;
            np2 = intermediate;
        }

        return np1;
    }

    public static int matrix(int n) {
        if (n == 0) {
            return 0;
        }

        int[][] FM = new int[][]{{1, 1}, {1, 0}};
        Fibonacci power =  new Fibonacci();
        FM = power.matrixPower(n-1, FM);
        return FM[0][0];
    }

    private int [][] matrixPower(int n, int [][] FM){
        if (n>1){
            FM = matrixPower(n/2, FM);
            Fibonacci multiplication =  new Fibonacci();
            FM = multiplication.matrixMultiplication(FM, FM);
            if (n%2 != 0){
                int[][] initialFM = new int[][]{{1, 1}, {1, 0}};
                FM = multiplication.matrixMultiplication(FM, initialFM);
            }
        }
        return FM;
    }

    private int [][] matrixMultiplication(int [][] FM, int [][] uFM){
        int [][] returnFM = new int [][]{{1, 1}, {1, 0}};
        returnFM [0][0] =  FM [0][0] * uFM [0][0] + FM [0][1] * uFM [1][0];
        returnFM [0][1] =  FM [0][0] * uFM [0][1] + FM [0][1] * uFM [1][1];
        returnFM [1][0] =  FM [1][0] * uFM [0][0] + FM [1][1] * uFM [1][0];
        returnFM [1][1] =  FM [1][0] * uFM [0][1] + FM [1][1] * uFM [1][1];

        return returnFM;
    }


    public static void main(String[] args) {

        int recursive = Fibonacci.recursive(7);
        int memoization = Fibonacci.memoization(7);
        int dynamic = Fibonacci.dynamic(7);
        int iteration = Fibonacci.iterator(7);
        int matrix = Fibonacci.matrix(7);
        System.out.printf("Recursive: %d \n", recursive);
        System.out.printf("Memoization: %d \n", memoization);
        System.out.printf("Dynamic: %d \n", dynamic);
        System.out.printf("Iteration: %d \n", iteration);
        System.out.printf("Matrix: %d \n", matrix);
    }

}