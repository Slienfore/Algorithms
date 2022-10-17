package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/23 10:49
 */
//64-最小路径和
public class Demo_64 {
    public static void main(String[] args) {
        int[][] nums = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum_1(nums));
        System.out.println(minPathSum_2(nums));
    }

    /**
     * 动规(一维)<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了95.07%的用户<br>
     * 内存消耗：43.9 MB, 在所有 Java 提交中击败了38.11%的用户
     */
    public static int minPathSum_2(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (row == 1 && col == 1)//只有一个格子
            return grid[0][0];

        //到达该格子的最小路径和(+1 方便初始化)
        int[] dp = new int[col + 1];

        for (int curCol = 1; curCol <= col; ++curCol) //初始化行
            dp[curCol] += dp[curCol - 1] + grid[0][curCol - 1];

        for (int curRow = 1; curRow < row; ++curRow)
            for (int curCol = 1; curCol <= col; ++curCol)
                dp[curCol] = (curCol == 1 ? dp[curCol] : Math.min(dp[curCol], dp[curCol - 1])) + grid[curRow][curCol - 1];

        return dp[col];
    }

    /**
     * 动规(二维)<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了95.07%的用户<br>
     * 内存消耗：43.8 MB, 在所有 Java 提交中击败了42.40%的用户
     */
    public static int minPathSum_1(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (row == 1 && col == 1)//只有一个格子
            return grid[0][0];

        //到达该格子的最小路径和
        int[][] dp = new int[row + 1][col + 1];

        for (int curCol = 1; curCol <= col; ++curCol)//初始化行
            dp[1][curCol] += dp[1][curCol - 1] + grid[0][curCol - 1];

        for (int curRow = 2; curRow <= row; ++curRow)//初始化列
            dp[curRow][1] += dp[curRow - 1][1] + grid[curRow - 1][0];

        for (int curRow = 2; curRow <= row; ++curRow)
            for (int curCol = 2; curCol <= col; ++curCol)//左边路径和还有上边路径和最小
                dp[curRow][curCol] = Math.min(dp[curRow][curCol - 1], dp[curRow - 1][curCol]) + grid[curRow - 1][curCol - 1];

        return dp[row][col];
    }
}
