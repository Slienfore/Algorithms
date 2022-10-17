package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/23 18:14
 */
//931-下降最小路径和
public class Demo_931 {
    public static void main(String[] args) {
        int[][] nums = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println(minFallingPathSum_1(nums));
        System.out.println(minFallingPathSum_2(nums));
    }

    /**
     * 动规(边界处理)<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了55.01%的用户<br>
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了5.18%的用户
     */
    public static int minFallingPathSum_2(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        if (row == 1 && col == 1)
            return matrix[0][0];

        for (int curRow = 1; curRow < row; ++curRow)
            for (int curCol = 0; curCol < col; ++curCol) {
                int best = matrix[curRow - 1][curCol];

                if (curCol > 0)//不是第一列
                    best = Math.min(best, matrix[curRow - 1][curCol - 1]);

                if (curCol + 1 < col)//不是最后一列
                    best = Math.min(best, matrix[curRow - 1][curCol + 1]);

                matrix[curRow][curCol] += best;
            }

        int min = Integer.MAX_VALUE;
        for (int curCol = 0; curCol < col; ++curCol)
            min = Math.min(matrix[row - 1][curCol], min);

        return min;
    }


    /**
     * 动规(二维)<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了55.01%的用户<br>
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了52.28%的用户
     */
    public static int minFallingPathSum_1(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        if (row == 1 && col == 1)
            return matrix[0][0];

        int[][] dp = new int[row][col];

        System.arraycopy(matrix[0], 0, dp[0], 0, col);

        for (int curRow = 1; curRow < row; ++curRow)
            for (int curCol = 0; curCol < col; ++curCol) {
                if (curCol > 0 && curCol < col - 1)
                    dp[curRow][curCol] = Math.min(
                            Math.min(
                                    dp[curRow - 1][curCol - 1], dp[curRow - 1][curCol + 1]
                            ), dp[curRow - 1][curCol]
                    );
                else if (curCol == 0) //第一列
                    dp[curRow][curCol] = Math.min(dp[curRow - 1][curCol + 1], dp[curRow - 1][curCol]);

                else if (curCol == col - 1) //最后一列
                    dp[curRow][curCol] = Math.min(dp[curRow - 1][curCol - 1], dp[curRow - 1][curCol]);

                dp[curRow][curCol] += matrix[curRow][curCol];
            }

        int min = Integer.MAX_VALUE;
        for (int curCol = 0; curCol < col; ++curCol)
            min = Math.min(dp[row - 1][curCol], min);

        return min;
    }
}
