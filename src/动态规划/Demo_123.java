package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/21 21:45
 */
//123-买卖股票的最佳时机-III
public class Demo_123 {
    public static void main(String[] args) {
//        int[] nums = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(maxProfit_1(nums));
        System.out.println(maxProfit_2(nums));
        System.out.println(maxProfit_3(nums));
    }

    /**
     * 动规<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了91.08%的用户<br>
     * 内存消耗：57.1 MB, 在所有 Java 提交中击败了39.87%的用户
     */
    public static int maxProfit_3(int[] prices) {
        int length = prices.length;

        int poss0 = -prices[0], sell0 = 0, poss1 = -prices[0], sell1 = 0;

        for (int cur = 1; cur < length; ++cur) {
            int curVal = prices[cur];

            //第二次 "不持股" 的现金
            sell1 = Math.max(sell1, poss1 + curVal);
            //第二次 "持股" 的现金
            poss1 = Math.max(poss1, sell0 - curVal);

            //第一次 "不持股" 的现金
            sell0 = Math.max(sell0, poss0 + curVal);
            //第一次 "持股" 的现金
            poss0 = Math.max(poss0, -curVal);

        }

        return sell1;
    }

    /**
     * 动规<br>
     * 执行用时：57 ms, 在所有 Java 提交中击败了27.20%的用户<br>
     * 内存消耗：55.1 MB, 在所有 Java 提交中击败了50.74%的用户
     */
    public static int maxProfit_2(int[] prices) {
        int length = prices.length;

        //dp[][][0]: 持股 dp[][][1]：不持股  dp[][0][]:第一次卖出股票 dp[][1][]:第二次卖出股票
        int[][][] dp = new int[length][2][2];
        dp[0][0][0] = -prices[0];//第一次交易持股的现金
        dp[0][0][1] = 0;//第一次交易不持股卖出的股票

        dp[0][1][0] = -prices[0];//第二次交易持股的现金
        dp[0][1][1] = 0;//第二次交易不持股卖出的股票

        for (int cur = 1; cur < length; ++cur) {
            int curVal = prices[cur];

            //第一次持股的现金
            dp[cur][0][0] = Math.max(dp[cur - 1][0][0], -curVal);
            //第一次不持股的现金
            dp[cur][0][1] = Math.max(dp[cur - 1][0][1], dp[cur - 1][0][0] + curVal);

            //第二次持股的现金
            dp[cur][1][0] = Math.max(dp[cur - 1][1][0], dp[cur - 1][0][1] - curVal);//使用第一次不持股的现金买入
            //第二次不持股的现金
            dp[cur][1][1] = Math.max(dp[cur - 1][1][1], dp[cur - 1][1][0] + curVal);
        }

        return dp[length - 1][1][1];
    }

    /**
     * 动规<br>
     * 执行用时：19 ms, 在所有 Java 提交中击败了54.03%的用户<br>
     * 内存消耗：52.7 MB, 在所有 Java 提交中击败了64.26%的用户
     */
    public static int maxProfit_1(int[] prices) {
        int length = prices.length;
        /*买卖两次(5种状态)
        0-> 不采取任何操作
        1-> 买一次   2-> 买一次
        3-> 买两次   4-> 卖两次
        dp[][0]:表示本金为 0
        dp[][1]: Math.max(dp[][1], dp[][0] - curPrice)第一次买入获得的金额与休息获得金额的最大值
        dp[][2]: Math.max(dp[][2], dp[][1] + curPrice)第一次卖出获得的金额与休息获得金额的最大值
        dp[][3]: Math.max(dp[][3], dp[][3] - curPrice)使用 "第一次买入的本金" 进行 "第二次买入" 股票和休息获得金额的最大值
        dp[][4]: Math.max(dp[][4], dp[][3] + curPrice)第二次卖出获得的金额与休息获得金额的最大值*/
        int[][] dp = new int[length][5];

        dp[0][1] = -prices[0];//第一次买入获得的现金
        dp[0][3] = -prices[0];//第二次买入获得的现金

        for (int cur = 1; cur < length; ++cur) {
            int curPrice = prices[cur];
            //本金为 0 时，第一次买入获得的金额与休息获得金额的最大值
            dp[cur][1] = Math.max(dp[cur - 1][1], dp[cur - 1][0] - curPrice);

            //第一次卖出获得的金额与休息获得金额的最大值
            dp[cur][2] = Math.max(dp[cur - 1][2], dp[cur - 1][1] + curPrice);

            //使用 "第一次买入的本金" 进行 "第二次买入" 股票和休息获得金额的最大值
            dp[cur][3] = Math.max(dp[cur - 1][3], dp[cur - 1][2] - curPrice);

            //第二次卖出获得的金额与休息获得金额的最大值
            dp[cur][4] = Math.max(dp[cur - 1][4], dp[cur - 1][3] + curPrice);
        }
        return dp[length - 1][4];
    }
}
