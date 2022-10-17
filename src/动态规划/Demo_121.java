package 动态规划;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/20 16:36
 */
//121-买卖股票的最佳时机
public class Demo_121 {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit_1(prices));
        System.out.println(maxProfit_2(prices));
        System.out.println(maxProfit_3(prices));
        System.out.println(maxProfit_4(prices));
        System.out.println(maxProfit_5(prices));
        System.out.println(maxProfit_6(prices));
        System.out.println(maxProfit_7(prices));
        System.out.println(maxProfit_8(prices));

    }

    /**
     * 动规<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了68.42%的用户<br>
     * 内存消耗：58.2 MB, 在所有 Java 提交中击败了5.00%的用户
     */
    public static int maxProfit_8(int[] prices) {
        int length = prices.length;

        //第一天持股(买入)获得的最大盈利
        int buy = -prices[0], sell = 0;//第一天不持股(卖出)获得的最大盈利

        for (int cur = 1; cur < length; ++cur) {
            int curPrice = prices[cur];//今天股票价格

            sell = Math.max(sell, curPrice + buy);//昨天持股与今天不持股(卖出)获得的最大利润

            buy = Math.max(buy, -curPrice);//昨天持股与今天持股(买入)获得最大金额

        }

        return sell;
    }

    /**
     * 动规<br>
     * 执行用时：24 ms, 在所有 Java 提交中击败了12.05%的用户<br>
     * 内存消耗：53.9 MB, 在所有 Java 提交中击败了61.05%的用户
     */
    public static int maxProfit_7(int[] prices) {
        int length = prices.length;
        //dp[][0]:表示今天持股(买入)获得的最大金额、dp[][1]:表示今天不持股(卖出)获得最大的盈利
        int[][] dp = new int[length][2];

        dp[0][0] = -prices[0];//第一天持股(买入)获得的最大盈利1
        dp[0][1] = 0;//第一天不持股(卖出)获得的最大盈利

        for (int cur = 1; cur < length; ++cur) {
            int curPrice = prices[cur];//今天股票价格

            dp[cur][0] = Math.max(dp[cur - 1][0], -curPrice);//昨天持股与今天持股(买入)获得最大金额
            dp[cur][1] = Math.max(dp[cur - 1][1], curPrice + dp[cur - 1][0]);//昨天持股与今天不持股(卖出)获得的最大利润
        }

        return dp[length - 1][1];
    }


    /**
     * 单调栈<br>
     * 执行用时：18 ms, 在所有 Java 提交中击败了15.60%的用户<br>
     * 内存消耗：53.2 MB, 在所有 Java 提交中击败了66.68%的用户
     */
    public static int maxProfit_6(int[] prices) {
        //单调递减栈(寻找第一大)
        Deque<Integer> stack = new ArrayDeque<>();
        int minBuy = Integer.MAX_VALUE, max = 0;

        for (int val : prices) {

            while (!stack.isEmpty() && stack.peekLast() < val) {
                minBuy = Math.min(stack.pollLast(), minBuy);//保存最小值

                max = Math.max(val - minBuy, max);
            }

            stack.offerLast(val);
        }
        return max;
    }


    /**
     * 动规(优化)<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了68.76%的用户<br>
     * 内存消耗：57.6 MB, 在所有 Java 提交中击败了26.66%的用户
     */
    public static int maxProfit_5(int[] prices) {
        int length = prices.length;

        int buy = prices[0], sell = 0;//第一天买入与第一天卖出的价格是否会是最高

        for (int cur = 1; cur < length; ++cur) {
            int curVal = prices[cur];
            sell = Math.max(curVal - buy, sell);//今天卖出股票价格是否会高

            buy = Math.min(curVal, buy);//今天买入股票价格是否会低
        }

        return sell;
    }


    /**
     * 动规(二维)<br>
     * 执行用时：28 ms, 在所有 Java 提交中击败了5.54%的用户<br>
     * 内存消耗：54.1 MB, 在所有 Java 提交中击败了58.75%的用户
     */
    public static int maxProfit_4(int[] prices) {
        int length = prices.length;

        //dp[][0]表示每天买入股票所花费的最低金额、dp[][1]表示当天卖出股票的价格与前面最低价格买入股票最大值
        int[][] dp = new int[length][2];
        dp[0][0] = prices[0];//第一天买入花费的金额最低
        dp[0][1] = 0;//第一天买入所获得的最大利润

        for (int cur = 1; cur < length; ++cur) {
            int curVal = prices[cur];
            dp[cur][0] = Math.min(dp[cur - 1][0], curVal);//表示今天买入股票是否会更便宜

            dp[cur][1] = Math.max(curVal - dp[cur - 1][0], dp[cur - 1][1]);//表示今天卖出股票是否是最大值
        }

        return dp[length - 1][1];
    }

    /**
     * 贪心<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：57.5 MB, 在所有 Java 提交中击败了32.08%的用户
     */
    public static int maxProfit_3(int[] prices) {
        int length = prices.length;

        int max = Integer.MIN_VALUE, res = 0;

        for (int cur = length - 1; cur >= 0; --cur) {
            max = Math.max(max, prices[cur]);//维护右边区间最大值

            res = Math.max(max - prices[cur], res);
        }
        return res;
    }


    /**
     * 动规<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了33.02%的用户<br>
     * 内存消耗：52 MB, 在所有 Java 提交中击败了69.84%的用户
     */
    public static int maxProfit_2(int[] prices) {
        int length = prices.length;

        int[] max = new int[length];//记录未来日子最大值

        max[length - 1] = prices[length - 1];
        for (int cur = length - 2; cur >= 0; --cur)
            max[cur] = Math.max(prices[cur], max[cur + 1]);

        int maxPrc = 0;
        for (int cur = 0; cur < length - 1; ++cur)
            maxPrc = Math.max(max[cur] - prices[cur], maxPrc);

        return maxPrc;
    }

    /**
     * 暴力
     */
    public static int maxProfit_1(int[] prices) {
        int length = prices.length;

        int max = 0;
        for (int cur = 0; cur < length; ++cur) {
            int val = prices[cur];
            for (int next = cur + 1; next < length; ++next)
                if (prices[next] > val)
                    max = Math.max(prices[next] - val, max);
        }

        return max;
    }
}
