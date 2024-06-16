import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] getDims(Scanner scanner) {
        String dimLn = scanner.nextLine().toLowerCase();
        String[] dimsStrArr = dimLn.substring(dimLn.lastIndexOf("x") + 5, dimLn.length()).trim().split(",");

        int[] dimsArr = new int[2];

        for (int i = 0; i < dimsStrArr.length; i++) {
            dimsArr[i] = Integer.parseInt(dimsStrArr[i]);
        }
        return dimsArr;
    }

    static int[][] getMat(int[] dimsArr, Scanner scanner) {
        int[][] arr = new int[dimsArr[0]][dimsArr[1]];

        for (int i = 0; i < dimsArr[0]; i++) {
            String ln = scanner.nextLine();
            String[] rowStrArr = ln.split(" ");

            int[] rowVals = new int[0];

            for (int j = 0; j < rowStrArr.length; j++) {
                if (rowStrArr[j].isBlank()) {
                    continue;
                }

                int[] newArr = new int[rowVals.length + 1];

                for (int k = 0; k < rowVals.length; k++) {
                    newArr[k] = rowVals[k];
                }

                newArr[rowVals.length] = Integer.parseInt(rowStrArr[j]);
                rowVals = newArr;
            }

            arr[i] = rowVals;
        }

        return arr;
    }

    static int[] getPeakCols(int[][] mat) {
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

                for (int k = 0; k < mat.length; k++) {
                    if (mat[k][j] < minInCol) {
                        minInCol = mat[k][j];
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
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(
                "Sample input: \nMatrix A: 3,3\n" +
                        "12  2  4\n" +
                        "17 10  1\n" +
                        "92 80 79\n");
        int[] dimsArr = getDims(scanner);
        int[][] mat = getMat(dimsArr, scanner);

        System.out.println("\nOutput\n");
        int[] peakCols = getPeakCols(mat);
    }
}