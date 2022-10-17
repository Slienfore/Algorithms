package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/16 21:13
 */
//221-最大正方形
public class Demo_221 {
    public static void main(String[] args) {
        char[][] matrix =
                {
                        {'1', '0', '1', '0', '0'},
                        {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'},
                        {'1', '0', '0', '1', '0'},
                };

        System.out.println(maximalSquare(matrix));
    }


    /**
     * 动规<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了93.25%的用户<br>
     * 内存消耗：52.4 MB, 在所有 Java 提交中击败了43.18%的用户
     */
    public static int maximalSquare(char[][] matrix) {
        int rowL = matrix.length, colL = matrix[0].length;

        int[][] dp = new int[rowL + 1][colL + 1];//表示正方形从左上方可拓展形成的最大正方形的边长

        int maxWidth = Integer.MIN_VALUE;

        for (int row = 1; row <= rowL; ++row)//行
            for (int col = 1; col <= colL; ++col) {//列
                if (matrix[row - 1][col - 1] == '0')//遇到 0 了，直接跳过
                    continue;

                //保证是正方形->左边、上边、左上都需要判断最小值
                dp[row][col] = min(dp[row][col - 1], dp[row - 1][col], dp[row - 1][col - 1]) + 1;
                maxWidth = Math.max(maxWidth, dp[row][col]);
            }

        return maxWidth * maxWidth;
    }

    private static int min(int one, int two, int three) {
        return Math.min(Math.min(one, two), three);
    }
}
