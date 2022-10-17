package 前缀和与差分;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/24 8:27
 */
public class Demo_661 {
    public static void main(String[] args) {
//        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] matrix = {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        uu.print(imageSmoother_1(matrix));
        uu.print(imageSmoother_2(matrix));
    }

    /**前缀和<br>
     执行用时：5 ms, 在所有 Java 提交中击败了90.44%的用户<br>
     内存消耗：42.5 MB, 在所有 Java 提交中击败了6.19%的用户*/
    public static int[][] imageSmoother_2(int[][] img) {
        int row = img.length, col = img[0].length;

        int[][] preSum = new int[row + 1][col + 1];
        for (int curRow = 1; curRow <= row; ++curRow)
            for (int curCol = 1; curCol <= col; ++curCol)//左、上、左上
                preSum[curRow][curCol] = preSum[curRow][curCol - 1] +
                        preSum[curRow - 1][curCol] -
                        preSum[curRow - 1][curCol - 1] +
                        img[curRow - 1][curCol - 1];

        int[][] res = new int[row][col];
        for (int curRow = 1; curRow <= row; ++curRow)
            for (int curCol = 1; curCol <= col; ++curCol) {

                int row_1 = Math.max(1, curRow - 1), col_1 = Math.max(1, curCol - 1),
                        row_2 = Math.min(row, curRow + 1), col_2 = Math.min(col, curCol + 1);

                int num = (row_2 - row_1 + 1) * (col_2 - col_1 + 1);

                res[curRow - 1][curCol - 1] =
                        (preSum[row_2][col_2] -
                                preSum[row_2][col_1 - 1] -
                                preSum[row_1 - 1][col_2] +
                                preSum[row_1 - 1][col_1 - 1]) / num;
            }

        return res;
    }


    /**
     * 暴力<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了96.11%的用户<br>
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了14.86%的用户
     */
    public static int[][] imageSmoother_1(int[][] img) {
        int row = img.length, col = img[0].length;
        int[][] matrix = new int[row][col];

        for (int curRow = 0; curRow < row; ++curRow)
            for (int curCol = 0; curCol < col; ++curCol)
                matrix[curRow][curCol] = getCac(img, curRow, curCol);

        return matrix;
    }

    private static int getCac(int[][] matrix, int curRow, int curCol) {
        int row = matrix.length, col = matrix[0].length;

        int num = 0, sum = 0;

        for (int rowBegin = Math.max(0, curRow - 1); rowBegin <= Math.min(row - 1, curRow + 1); ++rowBegin)
            for (int colBegin = Math.max(0, curCol - 1); colBegin <= Math.min(col - 1, curCol + 1); ++colBegin) {
                sum += matrix[rowBegin][colBegin];
                ++num;
            }

        return sum / num;
    }
}
