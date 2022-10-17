package 动态规划;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/8 14:22
 */
//62-不同路径
public class Demo_62 {
    public static void main(String[] args) {
        int row = 3, col = 3;

        System.out.println(uniquePaths_1(row, col));
        System.out.println(uniquePaths_2(row, col));
        System.out.println(uniquePaths_3(row, col));
        System.out.println(dfs(row, col));

    }

    /**动规(滚动数组)<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：38.1 MB, 在所有 Java 提交中击败了29.63%的用户
    */
    public static int uniquePaths_3(int m, int n) {
        int[] dp = new int[n];

        for (int row = 0; row < m; row++)
            for (int col = 0; col < n; col++)
                if (col == 0 || row == 0)//第一行，第一列
                    dp[col] = 1;
                else
                    dp[col] += dp[col - 1];//二维同一列进行累加，同一行 + 左边

        return dp[n - 1];
    }


    /**
     * 动规(一维)<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了5.05%的用户
     */
    public static int uniquePaths_2(int m, int n) {
        int total = m * n;
        int[] dp = new int[total];

        for (int i = 0; i < total; i++) {
            if (i < n || i % m == 0)//如果为第一行，或者是第一列，那么就是左边界或者上边界
                dp[i] = 1;
            else
                dp[i] = dp[i - 1] + dp[i - n];
        }

        return dp[total - 1];
    }


    /**
     * 动规(二维)<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了5.05%的用户
     */
    public static int uniquePaths_1(int m, int n) {
        int[][] dp = new int[m][n];//表示每一个格子的路径个数(左、上)
        //dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        for (int row = 0; row < m; row++)//每一行
            for (int col = 0; col < n; col++) {
                if (row == 0 || col == 0)//左边界和上边界没有通路
                    dp[row][col] = 1;
                else
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }

        return dp[m - 1][n - 1];
    }

    /**
     * 递归(超时)
     */
    private static int dfs(int row, int col) {
        if (row == 1 || col == 1)
            return 1;

        return dfs(row - 1, col) + dfs(row, col - 1);//向下或者向右
    }

    static int[] tar = {-1, 0, -1};//控制移动方向(只能向右、或者只能向下)
    static int res = 0;

    private static void recur(int down, int right) {
        if (down == 1 || right == 1) {
            res++;//说明不能再向下，或者不能再向左了
            return;
        }

        for (int i = 0; i < 2; i++) //二叉树(向左、向右)
            recur(down + tar[i], right + tar[i + 1]);

    }
}