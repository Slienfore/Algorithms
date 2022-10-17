package 动态规划;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/8 16:41
 */
//63-不同路径-II
public class Demo_63 {
    public static void main(String[] args) {
        int[][] grid =
                {
/*                        {0, 1, 0},
                        {1, 0, 0},
                        {0, 0, 0}*/
//                        {1, 0}
                        {0, 1},
                        {1, 0}
//                        {0}
                };

        System.out.println(uniquePathsWithObstacles_1(grid));
        System.out.println(uniquePathsWithObstacles_2(grid));

    }

    /**
     * 滚动数组<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了26.82%的用户
     */
    public static int uniquePathsWithObstacles_2(int[][] obstacleGrid) {
        int row = obstacleGrid.length, col = obstacleGrid[0].length;

        //dp滚动数组
        int[] dp = new int[col];

        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;//若有障碍物，那么这一列或者这一行都不能进行累加

        for (int i = 0; i < row; ++i)
            for (int j = 0; j < col; ++j) {
                if (obstacleGrid[i][j] == 1) { //遇到了障碍物(需将这列重置为 0)
                    dp[j] = 0;

                } else if (j != 0) {//不是第一列的时候，累加前一项

                    dp[j] += dp[j - 1];
                }
            }

        return dp[col - 1];
    }


    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了24.63%的用户
     */
    public static int uniquePathsWithObstacles_1(int[][] obstacleGrid) {
        int row = obstacleGrid.length, col = obstacleGrid[0].length;

        //dp[row][col] = dp[row - 1][col] + dp[row][col - 1]
        int[][] dp = new int[row][col];//dp：到达该坐标的路径种类

        for (int i = 0; i < col && obstacleGrid[0][i] == 0; i++) //边界遇到障碍物之后就没有通路了
            dp[0][i] = 1;

        for (int i = 0; i < row && obstacleGrid[i][0] == 0; i++)//边界遇到障碍物之后就没有通路了
            dp[i][0] = 1;


        for (int i = 1; i < row; i++)
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) //遇到障碍物，需要进行绕行
                    continue;

                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }

        return dp[row - 1][col - 1];

    }
}
