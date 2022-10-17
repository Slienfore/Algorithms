package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/12 14:59
 */
//474-一和零
public class Demo_474 {
    public static void main(String[] args) {
        String[] str = {"10", "0001", "111001", "1", "0"};
        int zeroNum = 5;
        int oneNum = 3;
/*
        String[] str = {"10", "0001", "11101", "1", "0"};
        int zeroNum = 3;
        int oneNum = 3;*/
        System.out.println(findMaxForm_1(str, zeroNum, oneNum));
        System.out.println(findMaxForm_2(str, zeroNum, oneNum));

    }

    /**
     * 动规(三维)<br>
     * 执行用时：64 ms, 在所有 Java 提交中击败了11.92%的用户<br>
     * 内存消耗：66.8 MB, 在所有 Java 提交中击败了23.76%的用户
     */
    public static int findMaxForm_2(String[] strs, int m, int n) {
        int length = strs.length;
        //三维 DP(字符串、 0 的数量、 1 的数量)
        int[][][] dp = new int[length + 1][m + 1][n + 1];

        for (int str = 1; str <= length; ++str) {

            int[] result = help(strs[str - 1]);//统计该段字符的 0 与 1的数量
            int zeroNum = result[0], oneNum = result[1];

            for (int zero = 0; zero <= m; ++zero)
                for (int one = 0; one <= n; ++one)
                    if (zero >= zeroNum && one >= oneNum)
                        dp[str][zero][one] =
                                Math.max(dp[str - 1][zero][one], dp[str - 1][zero - zeroNum][one - oneNum] + 1);
                    else
                        dp[str][zero][one] = dp[str - 1][zero][one];

        }

        return dp[length][m][n];
    }


    private static int[] help(String str) {//统计 0 与 1 的数量
        int[] res = new int[2];
        for (char val : str.toCharArray())
            ++res[val - '0'];

        return res;
    }


    /**
     * 动规<br>
     * 执行用时：18 ms, 在所有 Java 提交中击败了85.60%的用户<br>
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了63.01%的用户
     */
    public static int findMaxForm_1(String[] strs, int m, int n) {
        /*
        01-背包：
        物品：字符串数组中的每一个子集
        物品重量：字符串中的 0 与  1 的数量
        因此：
        数据中的DP数组代表：
            最多有 M 个 0 与 N 个 1的最大子集
        只是物品的重量分为两个维度进行存放
         */

        int[][] dp = new int[m + 1][n + 1];//代表着 组成各个 (0 与 1) 的子集的组成最大长度

        for (String string : strs) {//遍历每一个字符串，统计其每一个字符串的 (0, 1)数量
            int zeroNum = 0, oneNum = 0;
            for (char val : string.toCharArray()) {//统计该字符串的
                if (val == '1')
                    ++oneNum;
                else
                    ++zeroNum;
            }

            //只能从后向前遍历：防止数据覆盖
            for (int zero = m; zero >= zeroNum; --zero)
                for (int one = n; one >= oneNum; --one)
                    dp[zero][one] = Math.max(dp[zero][one], dp[zero - zeroNum][one - oneNum] + 1);
        }
        return dp[m][n];
    }


}
