import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //    Get user matrix dimensions from the user's input, and return an array with two values where at index 0 we have rows, and index 1 cols
    static int[] getDims(Scanner scanner) {
        try {
            String dimLn = scanner.nextLine().toLowerCase();
            String[] dimsStrArr = dimLn.substring(dimLn.lastIndexOf("x") + 5).trim().split(",");
            int[] dimsArr = new int[2];
            for (int i = 0; i < dimsStrArr.length; i++) {
                dimsArr[i] = Integer.parseInt(dimsStrArr[i]);
            }
            return dimsArr;
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    //    Process the matrix values from the input and return a 2 dimensional of numbers representing the matrix
    static int[][] getMat(int[] dimsArr, Scanner scanner) {
        try {

            int[][] arr = new int[dimsArr[0]][dimsArr[1]];

            for (int i = 0; i < dimsArr[0]; i++) {
                String ln = scanner.nextLine();
                String[] rowStrArr = ln.split(" ");

                int[] rowVals = new int[0];

                for (String s : rowStrArr) {
                    if (s.isBlank()) {
                        continue;
                    }

                    int[] newArr = new int[rowVals.length + 1];

                    System.arraycopy(rowVals, 0, newArr, 0, rowVals.length);

                    newArr[rowVals.length] = Integer.parseInt(s);
                    rowVals = newArr;
                }

                arr[i] = rowVals;
            }
            return arr;
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    //    iterate over rows, then cols, then rows to find peak values
    static int[] getPeakCols(int[][] mat) {
        try {
            int[] peakCols = new int[0];

            for (int i = 0; i < mat.length; i++) {
                int maxInRow = 0;

                for (int j = 0; j < mat[i].length; j++) {
                    if (mat[i][j] > maxInRow) {
                        maxInRow = mat[i][j];
                    }
                }

                for (int j = 0; j < mat[i].length; j++) {
                    int minInCol = mat[i][j];

                    for (int[] ints : mat) {
                        if (ints[j] < minInCol) {
                            minInCol = ints[j];
                        }
                    }

                    if (minInCol == maxInRow) {
                        int[] newArr = new int[peakCols.length + 1];
                        Arrays.fill(newArr, minInCol);
                        newArr[peakCols.length] = minInCol;
                        peakCols = newArr;

                        System.out.println("(" + (i + 1) + "," + (j + 1) + ") = " + minInCol);
                    }
                }
            }

            return peakCols;
        } catch (Exception err) {
            throw new RuntimeException(err);
        }
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print(
                    """
                            Sample input:\s
                            Matrix A: 3,3
                            12  2  4
                            17 10  1
                            92 80 79
                            """);
            int[] dimsArr = getDims(scanner);
            int[][] mat = getMat(dimsArr, scanner);
            System.out.println("\nOutput\n");
            int[] peakCols = getPeakCols(mat);
        } catch (RuntimeException err) {
            System.out.println("Error: input error. \nEnsure your input is properly formatted");
        }
    }
}