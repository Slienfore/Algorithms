package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/22 19:06
 */
//714-买卖股票的最佳时机含手续费
public class Demo_714 {
    public static void main(String[] args) {
/*        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;*/
        int[] prices = {1, 3, 7, 5, 10, 3};
        int fee = 3;
        System.out.println(maxProfit_1(prices, fee));
        System.out.println(maxProfit_2(prices, fee));
    }

    /**
     * 动规<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：49.7 MB, 在所有 Java 提交中击败了13.87%的用
     */
    public static int maxProfit_2(int[] prices, int fee) {
        int length = prices.length;

        int possess = -prices[0], sell = 0;

        for (int cur = 1; cur < length; ++cur) {
            int temp = sell;

            //不持有股票
            sell = Math.max(sell, possess + prices[cur] - fee);//卖出支付手续费

            //持有股票
            possess = Math.max(possess, temp - prices[cur]);
        }

        return sell;
    }


    /**
     * 动规<br>
     * 执行用时：18 ms, 在所有 Java 提交中击败了46.80%的用户<br>
     * 内存消耗：48.6 MB, 在所有 Java 提交中击败了57.27%的用户
     */
    public static int maxProfit_1(int[] prices, int fee) {
        int length = prices.length;

        //dp[][0]:持有股票、dp[][1]:卖出股票
        int[][] dp = new int[length][2];
        dp[0][0] = -prices[0];

        for (int cur = 1; cur < length; ++cur) {
            int curPrc = prices[cur];
            //持有股票的现金
            dp[cur][0] = Math.max(dp[cur - 1][0], dp[cur - 1][1] - curPrc);

            //不持有股票的现金
            dp[cur][1] = Math.max(dp[cur - 1][1], dp[cur - 1][0] + curPrc - fee);//卖时进行递交手续费
        }

        return dp[length - 1][1];
    }
}
