import java.io.FileWriter;

/**
 * Different alogirithims for the fibonacci sequence used for evaluating there running time
 * @author Talha Khalil
 * @version 1.0
 * @since Feburary 3, 2020
 */

public class Fibonacci {

    /**
     * Algorithim that implements the Fibonacci sequence recursively
     *
     * @param n the value at which to calculate the fibonacci sequence
     * @return returns the fibonacci number corresponding to index n
     */
    public static int recursive(int n) {
        if (n <= 1) {
            return n;
        } else {
            return recursive(n - 1) + recursive(n - 2);
        }
    }

    /**
     * Algorithim that implements the Fibonacci sequence using memoization, which means it runs the function recursively and stores values for said index
     * and if those have been already calculated it does not calculate them again instead grabs them
     *
     * @param n the value at which to calculate the fibonacci sequence
     * @return returns the fibonacci number corresponding to index n
     */

    public static int memoization(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int [] previousCallValues = new int [n+1];
        previousCallValues[0] = 0;
        previousCallValues[1] = 1;
        Fibonacci memoizationHelper = new Fibonacci();
        return memoizationHelper.memoizationHelper(n, previousCallValues);
    }

    /**
     * Helper for the memoization algorithim which does the recursive calls and checks if a value has already been calculated
     *
     * @param n        the value at which to calculate the fibonacci sequence
     * @param memArray the Array that stores previously calculated values
     * @return returns the fibonacci number corresponding to index n
     */
    private int memoizationHelper(int n, int [] memArray) {
        if (n <= 1) {
            return n;
        } else if (memArray[n] == 0){
            memArray[n] =  memoizationHelper(n-1, memArray) + memoizationHelper(n-2, memArray);
        }

        return memArray[n];
    }

    /**
     * Algorithim that implements the Fibonacci sequence using dynamic, which means it builds the sequence up from the bottom and stores values in an array
     *
     * @param n the value at which to calculate the fibonacci sequence
     * @return returns the fibonacci number corresponding to index n
     */

    public static int dynamic(int n) {
        if (n <= 1)
            return n;

        int [] fib = new int [n+1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    /**
     * Algorithim that implements the Fibonacci sequence using iteration, which means it builds the sequence up from the bottom without storing any value
     *
     * @param n the value at which to calculate the fibonacci sequence
     * @return returns the fibonacci number corresponding to index n
     */

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

    /**
     * Algorithim that implements the Fibonacci sequence using matrics
     *
     * @param n the value at which to calculate the fibonacci sequence
     * @return returns the fibonacci number corresponding to index n
     */

    public static int matrix(int n) {
        if (n == 0) {
            return 0;
        }

        int[][] FM = new int[][]{{1, 1}, {1, 0}};
        Fibonacci power = new Fibonacci();
        FM = power.matrixPower(n - 1, FM);
        return FM[0][0];
    }

    /**
     * Algorithim that does the matrix calculations
     *
     * @param n  the value at which to calculate the fibonacci sequence
     * @param FM is the matrix storing the values
     * @return a matrix which has the n fibonacci sequence at index 0,0
     */

    private int[][] matrixPower(int n, int[][] FM) {
        if (n > 1) {
            FM = matrixPower(n / 2, FM);
            Fibonacci multiplication = new Fibonacci();
            FM = multiplication.matrixMultiplication(FM, FM);
            if (n % 2 != 0) {
                int[][] initialFM = new int[][]{{1, 1}, {1, 0}};
                FM = multiplication.matrixMultiplication(FM, initialFM);
            }
        }
        return FM;
    }

    /**
     * Does matrix multiplication
     *
     * @param FM  the first matrix
     * @param uFM the second matrix
     * @return the product of the two matrices
     */
    private int[][] matrixMultiplication(int[][] FM, int[][] uFM) {
        int[][] returnFM = new int[][]{{1, 1}, {1, 0}};
        returnFM[0][0] = FM[0][0] * uFM[0][0] + FM[0][1] * uFM[1][0];
        returnFM[0][1] = FM[0][0] * uFM[0][1] + FM[0][1] * uFM[1][1];
        returnFM[1][0] = FM[1][0] * uFM[0][0] + FM[1][1] * uFM[1][0];
        returnFM[1][1] = FM[1][0] * uFM[0][1] + FM[1][1] * uFM[1][1];

        return returnFM;
    }

    /**
     * Calls the recursive function upto the n value starting from 0
     *
     * @param n the number of times to call the recursive function
     * @return the time taken to make all the calls
     */
    public static long[] callRecursive(int n) {
        long[] timeTaken = new long[n];
        long before;
        long after;

        for (int i = 0; i < n; i++) {
            before = System.nanoTime();
            Fibonacci.recursive(i);
            after = System.nanoTime();
            timeTaken[i] = after - before;
        }
        return timeTaken;
    }

    /**
     *Calls the memoization function upto the n value starting from 0
     * @param n the number of times to call the memoization function
     * @return the time taken to make all the calls
     */

    public static long[] callMemoization(int n) {
        long[] timeTaken = new long[n];
        long before;
        long after;

        for (int i = 0; i < n; i++) {
            before = System.nanoTime();
            Fibonacci.memoization(i);
            after = System.nanoTime();
            timeTaken[i] = after - before;
        }
        return timeTaken;
    }

    /**
     * Calls the dynamic function upto the n value starting from 0
     * @param n the number of times to call the dynamic function
     * @return the time taken to make all the calls
     */
    public static long[] callDynamic(int n) {
        long[] timeTaken = new long[n];
        long before;
        long after;

        for (int i = 0; i < n; i++) {
            before = System.nanoTime();
            Fibonacci.dynamic(i);
            after = System.nanoTime();
            timeTaken[i] = after - before;
        }
        return timeTaken;
    }

    /**
     * Calls the iterator function upto the n value starting from 0
     * @param n the number of times to call the iterator function
     * @return the time taken to make all the calls
     */

    public static long[] callIterator(int n) {
        long[] timeTaken = new long[n];
        long before;
        long after;

        for (int i = 0; i < n; i++) {
            before = System.nanoTime();
            Fibonacci.iterator(i);
            after = System.nanoTime();
            timeTaken[i] = after - before;
        }
        return timeTaken;
    }

    /**
     * Calls the matrix function upto the n value starting from 0
     * @param n the number of times to call the iterator function
     * @return the time taken to make all the calls
     */

    public static long[] callMatrix(int n) {
        long[] timeTaken = new long[n];
        long before;
        long after;

        for (int i = 0; i < n; i++) {
            before = System.nanoTime();
            Fibonacci.matrix(i);
            after = System.nanoTime();
            timeTaken[i] = after - before;
        }
        return timeTaken;
    }

    /**
     * Measures the execution time for all algorithims
     * @param howManyTimesToRun How many times to run each algorithim so the data can be averaged
     */
    public static void measureExecutionTime(int howManyTimesToRun) {

        int largestRecursiveN = 30;
        int largestMemoizationN = 1000;
        int largestDynamicN = 30000;
        int largestIteratorN = 30000;
        int largestMatrixN = 30000;

        long[][] usedForAveragingRecursive = new long[howManyTimesToRun][];
        long[][] usedForAveragingMemoization = new long[howManyTimesToRun][];
        long[][] usedForAveragingDynamic = new long[howManyTimesToRun][];
        long[][] usedForAveragingIterator = new long[howManyTimesToRun][];
        long[][] usedForAveragingMatrix = new long[howManyTimesToRun][];

        for (int i = 0; i < howManyTimesToRun; i++) {
            usedForAveragingRecursive[i] = callRecursive(largestRecursiveN);
        }

        for (int i = 2; i < howManyTimesToRun; i++) {
            for (int j = 0; j < largestRecursiveN; j++) {
                usedForAveragingRecursive[1][j] += usedForAveragingRecursive[i][j];
            }
        }

        for (int j = 0; j < largestRecursiveN; j++) {
            usedForAveragingRecursive[1][j] = usedForAveragingRecursive[1][j] / (howManyTimesToRun - 1);
            System.out.println(usedForAveragingRecursive[1][j] + " time to compute F" + j + " with alg. 1");
        }
        System.out.println();

        writeToFile(usedForAveragingRecursive[1], "recursive");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < howManyTimesToRun; i++) {
            usedForAveragingMemoization[i] = callMemoization(largestMemoizationN);
        }

        for (int i = 2; i < howManyTimesToRun; i++) {
            for (int j = 0; j < largestMemoizationN; j++) {
                usedForAveragingMemoization[1][j] += usedForAveragingMemoization[i][j];
            }
        }

        for (int j = 0; j < largestMemoizationN; j++) {
            usedForAveragingMemoization[1][j] = usedForAveragingMemoization[1][j] / (howManyTimesToRun - 1);
            System.out.println(usedForAveragingMemoization[1][j] + " time to compute F" + j + " with alg. 2");

        }
        System.out.println();

        writeToFile(usedForAveragingMemoization[1], "memoization");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < howManyTimesToRun; i++) {
            usedForAveragingDynamic[i] = callDynamic(largestDynamicN);
        }

        for (int i = 2; i < howManyTimesToRun; i++) {
            for (int j = 0; j < largestDynamicN; j++) {
                usedForAveragingDynamic[1][j] += usedForAveragingDynamic[i][j];
            }
        }

        for (int j = 0; j < largestDynamicN; j++) {
            usedForAveragingDynamic[1][j] = usedForAveragingDynamic[1][j] / (howManyTimesToRun - 1);
            System.out.println(usedForAveragingDynamic[1][j] + " time to compute F" + j + " with alg. 3");

        }
        System.out.println();

        writeToFile(usedForAveragingDynamic[1], "dynamic");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < howManyTimesToRun; i++) {
            usedForAveragingIterator[i] = callDynamic(largestIteratorN);

        }

        for (int i = 2; i < howManyTimesToRun; i++) {
            for (int j = 0; j < largestIteratorN; j++) {
                usedForAveragingIterator[1][j] += usedForAveragingIterator[i][j];
            }
        }

        for (int j = 0; j < largestIteratorN; j++) {
            usedForAveragingIterator[1][j] = usedForAveragingIterator[1][j] / (howManyTimesToRun - 1);
            System.out.println(usedForAveragingIterator[1][j] + " time to compute F" + j + " with alg. 4");

        }
        System.out.println();

        writeToFile(usedForAveragingIterator[1], "iterator");
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < howManyTimesToRun; i++) {
            usedForAveragingMatrix[i] = callMatrix(largestMatrixN);

        }

        for (int i = 2; i < howManyTimesToRun; i++) {
            for (int j = 0; j < largestMatrixN; j++) {
                usedForAveragingMatrix[1][j] += usedForAveragingMatrix[i][j];
            }
        }

        for (int j = 0; j < largestMatrixN; j++) {
            usedForAveragingMatrix[1][j] = usedForAveragingMatrix[1][j] / (howManyTimesToRun - 1);
            System.out.println(usedForAveragingMatrix[1][j] + " time to compute F" + j + " with alg. 5");

        }
        System.out.println();

        writeToFile(usedForAveragingMatrix[1], "matrix");

        return;
    }

    /**
     * Writes an array into a file with the name fileName, writes the value and the index in csv format
     * @param data the data to be written
     * @param fileName the name to save the file as
     */
    public static void writeToFile(long[] data, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName + ".txt", false);

            for (int i = 1; i < data.length; i++) {
                writer.write(i + ", " + +data[i] + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Created Averaged Data File for: " + fileName);
    }

    public static void main(String[] args) {
        long before = System.nanoTime();
        measureExecutionTime(200);

        long after = System.nanoTime();
        System.out.printf("Time Taken Total: %f seconds taken\n", (after - before) / Math.pow(10, 9));
    }

}