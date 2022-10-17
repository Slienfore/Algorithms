package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/22 17:12
 */
//309-最佳买卖股票时机含冷冻期
public class Demo_309 {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit_1(prices));
        System.out.println(maxProfit_2(prices));
    }

    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了37.58%的用户
     */
    public static int maxProfit_2(int[] prices) {
        int length = prices.length;

        int poss = -prices[0], preSell = 0, sell = 0;

        for (int cur = 1; cur < length; ++cur) {
            int curPrc = prices[cur];

            int nextSell = Math.max(sell, poss + curPrc);

            int nextPos = Math.max(poss, preSell - curPrc);//冷静期需隔一天天的不持有现金

            preSell = sell;

            poss = nextPos;

            sell = nextSell;
        }

        return sell;
    }


    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了60.10%的用户
     */
    public static int maxProfit_1(int[] prices) {
        int length = prices.length;

        //dp[][0]:持有股票、dp[][1]:不持有股票
        int[][] dp = new int[length][2];
        dp[0][0] = -prices[0];//第一天本金为 0 持有股票

        for (int cur = 1; cur < length; ++cur) {
            int curPrc = prices[cur];

            dp[cur][1] = Math.max(dp[cur - 1][1], dp[cur - 1][0] + curPrc);//不持有股票

            if (cur >= 2) {
                dp[cur][0] = Math.max(dp[cur - 1][0], dp[cur - 2][1] - curPrc);//冷冻期间隔一天进行购买
            } else
                dp[cur][0] = Math.max(dp[cur - 1][0], dp[cur - 1][1] - curPrc);

        }

        return dp[length - 1][1];
    }
}
