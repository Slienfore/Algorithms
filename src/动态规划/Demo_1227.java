package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/16 22:00
 */
//1227-统计全为 1 的正方形子矩阵
public class Demo_1227 {

    public static void main(String[] args) {
        int[][] matrix =
                {
/*                        {0, 1, 1, 1},
                        {1, 1, 1, 1},
                        {0, 1, 1, 1},*/
                        {1, 0, 1},
                        {1, 1, 0},
                        {1, 1, 0}
                };

        System.out.println(countSquares(matrix));
    }

    /**
     * 动规<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了98.58%的用户<br>
     * 内存消耗：50 MB, 在所有 Java 提交中击败了72.77%的用户
     */
    public static int countSquares(int[][] matrix) {
        int rowL = matrix.length, colL = matrix[0].length;

        int[][] dp = new int[rowL + 1][colL + 1];//表示正方形从左上方可拓展形成的最大正方形的边长

        int res = 0;

        for (int row = 1; row <= rowL; ++row)//行
            for (int col = 1; col <= colL; ++col) {//列
                if (matrix[row - 1][col - 1] == 0)//遇到 0 了，直接跳过
                    continue;

                //保证是正方形->左边、上边、左上都需要判断最小值
                dp[row][col] = min(dp[row][col - 1], dp[row - 1][col], dp[row - 1][col - 1]) + 1;

                res += dp[row][col];//即统计其边长
            }

        return res;
    }

    private static int min(int one, int two, int three) {
        return Math.min(Math.min(one, two), three);
    }

}
