package 动态规划;

import utils.uu;

import java.awt.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/22 15:21
 */
//188-买卖股票的最佳时机-IIII
public class Demo_188 {
    public static void main(String[] args) {
/*        int[] prices = {2, 4, 3};
        int k = 2;*/
        int[] prices = {3, 2, 6, 5, 0, 3};
        int k = 7;

        System.out.println(maxProfit_1(k, prices));
        System.out.println(maxProfit_2(k, prices));

    }

    /**
     * 动规<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.82%的用户<br>
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了63.73%的用户
     */
    public static int maxProfit_2(int k, int[] prices) {
        int length = prices.length;
        if (length == 0)
            return 0;

        if (k >= length / 2)//有效交易次数为 length / 2,一次完整的交易包含两天(买入、卖出)
            return maxProfit(prices);

        //dp[k + 1][]:第几次交易、dp[][0]：持股、dp[][1]:不持股
        int[][] dp = new int[k + 1][2];
        for (int op = 0; op <= k; ++op)//最多进行 K 次， 不一定必须要进行 K 次交易
            dp[op][0] = -prices[0];


        for (int cur = 1; cur < length; ++cur) {//一共cur天
            int curPrc = prices[cur];

            for (int op = 1; op <= k; ++op) {//进行交易
                //不持股获得最大现金
                dp[op][1] = Math.max(dp[op][1], dp[op][0] + curPrc);

                //持股获得最大现金
                dp[op][0] = Math.max(dp[op][0], dp[op - 1][1] - curPrc);//持股需要加上上一次交易获得最大现金
            }
        }

        return dp[k][1];
    }


    /**
     * 动规(三维)<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了49.74%的用户<br>
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了17.07%的用户
     */
    public static int maxProfit_1(int k, int[] prices) {
        int length = prices.length;
        if (length == 0)
            return 0;

        //有效的交易次数的间隔天数为两天(说明交易无数次)
        if (k >= length / 2)
            return maxProfit(prices);

        //二维：交易 K 次， 三维：持股还是不持股
        int[][][] dp = new int[length][k + 1][2];

        for (int op = 0; op <= k; ++op)//第一天----交易 K 次的持股初始化为 -prices[0]
            dp[0][op][0] = -prices[0];

        for (int cur = 1; cur < length; ++cur) {
            int curPrc = prices[cur];//今日股票价格

            for (int op = 1; op <= k; ++op) {

                //dp[cur][1][0]: Math.max(dp[cur - 1][1][0], dp[cur - 1][0][1] - curPrice)第一次持有股票的现金
                dp[cur][op][0] = Math.max(dp[cur - 1][op][0], dp[cur - 1][op - 1][1] - curPrc);//持有股票
                dp[cur][op][1] = Math.max(dp[cur - 1][op][1], dp[cur - 1][op][0] + curPrc);//不持有股票(卖出)

            }
        }

        return dp[length - 1][k][1];//执行 k 次后的不持有股票
    }

    //操作多次，超过了数组一半可以认为操作无数次
    private static int maxProfit(int[] prices) {
        int length = prices.length;
        int buy = -prices[0], sell = 0;

        for (int cur = 1; cur < length; ++cur) {
            int temp = sell;
            //今天 "不持股" 还是休息
            sell = Math.max(sell, buy + prices[cur]);
            //今天 "持股" 还是休息
            buy = Math.max(buy, temp - prices[cur]);
        }
        return sell;
    }
}
