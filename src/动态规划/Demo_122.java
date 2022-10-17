package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/20 20:47
 */
//122-买卖股票的最佳时机-II
public class Demo_122 {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        System.out.println(maxProfit_1(prices));
        System.out.println(maxProfit_2(prices));

    }


    /**
     * 动规<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了33.06%的用户<br>
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了17.29%的用户
     */
    public static int maxProfit_2(int[] prices) {
        int length = prices.length;

        int possess = 0 - prices[0], sell = 0;//第一天的 <前一天> 不持有股票金额为 0

        for (int cur = 1; cur < length; ++cur) {
            int curVal = prices[cur];

            int profit = sell;

            //持有股票的所得现金会变成正数
            sell = Math.max(sell, curVal + possess);//今天不持有-->昨天持有的股票所得现金----休息获得金额最大值

            //使用昨天不持有(卖出<盈利>)的金额----持有(买入)今天的股票---休息获得金额最大值
            possess = Math.max(possess, profit - curVal);
        }

        return sell;
    }

    /**
     * 动规<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了23.81%的用户<br>
     * 内存消耗：41 MB, 在所有 Java 提交中击败了57.26%的用户
     */
    public static int maxProfit_1(int[] prices) {
        int length = prices.length;

        //dp[][0]: 使用不持有股票的盈利金额买入今天的股票(盈利金额)、dp[][1]:不持有股票获得的利润(昨天持有股票盈利金额<盈利后持有股票金额为正数>)
        int[][] dp = new int[length][2];

        dp[0][0] = 0 - prices[0];//第一天的 <前一天> 不持有股票金额为 0
        dp[0][1] = 0;

        for (int cur = 1; cur < length; ++cur) {
            int curVal = prices[cur];

            //持有股票的所得现金会变成正数
            dp[cur][1] = Math.max(dp[cur - 1][1], curVal + dp[cur - 1][0]);//今天不持有-->昨天持有的股票所得现金----休息获得金额最大值

            //使用昨天不持有(卖出<盈利>)的金额----持有(买入)今天的股票---休息获得金额最大值
            dp[cur][0] = Math.max(dp[cur - 1][0], dp[cur - 1][1] - curVal);
        }

        return dp[length - 1][1];
    }
}