package 动态规划;

import utils.uu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/23 11:36
 */
//118-杨辉三角
public class Demo_118 {
    public static void main(String[] args) {
        int numRow = 5;
        System.out.println(generate_1(numRow));
        System.out.println(generate_2(numRow));
    }

    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39 MB, 在所有 Java 提交中击败了44.91%的用户
     */
    public static List<List<Integer>> generate_2(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        int[] dp = new int[numRows + 1];
        res.add(Arrays.asList(dp[1] = 1));

        for (int row = 1; row < numRows; ++row) {
            List<Integer> temp = new ArrayList<>();

            for (int col = row + 1; col >= 1; --col)
                temp.add(dp[col] = dp[col] + dp[col - 1]);

            res.add(temp);
        }

        return res;
    }


    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了12.54%的用户
     */
    public static List<List<Integer>> generate_1(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        int[][] dp = new int[numRows][numRows + 1];

        res.add(Arrays.asList(dp[0][1] = 1));//第一行第一列第一个数

        for (int row = 1; row < numRows; ++row) {
            List<Integer> temp = new ArrayList<>();

            for (int col = 1; col <= row + 1; ++col) {
                dp[row][col] = dp[row - 1][col - 1] + dp[row - 1][col];
                temp.add(dp[row][col]);
            }

            res.add(temp);
        }

        return res;
    }
}
