package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/14 15:18
 */
//322-零钱兑换
public class Demo_322 {
    public static void main(String[] args) {
/*        int[] coins = {1, 2, 5};
        int amount = 11;*/

/*        int[] coins = {2};
        int amount = 3;*/

/*        int[] coins = {2};
        int amount = 4;*/

        int[] coins = {411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422};
        int amount = 9864;

/*        int[] coins = {1, 2, 5};
        int amount = 5;*/

        System.out.println(coinChange_1(coins, amount));

        System.out.println(coinChange_3(coins, amount));

        System.out.println(coinChange_4(coins, amount));


    }

    static int res = Integer.MAX_VALUE;


    /**
     * 动规(一维)<br>
     * 执行用时：11 ms, 在所有 Java 提交中击败了92.35%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了19.55%的用户
     */
    public static int coinChange_3(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int[] dp = new int[amount + 1];//表示凑成指定金额硬币数量最小

        //dp[val] = Math.min(dp[val], dp[val - coins[ mon ] + 1])与其它硬币凑成金额的时候还需要加上自己

        for (int amo = 1; amo <= amount; ++amo)//价值为 0 时 方便与其它硬币凑成一对
            dp[amo] = Integer.MAX_VALUE;

        for (int mon : coins)//先遍历硬币，后遍历金额
            for (int amo = mon; amo <= amount; ++amo) {

                if (dp[amo - mon] == Integer.MAX_VALUE)//若之前的硬币凑不成
                    continue;

                dp[amo] = Math.min(dp[amo], dp[amo - mon] + 1);

            }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


    /**
     * 动规(一维)<br>
     * 执行用时：13 ms, 在所有 Java 提交中击败了52.35%的用户<br>
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了47.89%的用户
     */
    public static int coinChange_4(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int[] dp = new int[amount + 1];//表示凑成指定金额硬币数量最小

        //dp[val] = Math.min(dp[val], dp[val - coins[ mon ] + 1])与其它硬币凑成金额的时候还需要加上自己

        for (int amo = 1; amo <= amount; ++amo)//价值为 0 时 方便与其它硬币凑成一对
            dp[amo] = Integer.MAX_VALUE;

        for (int amo = 1; amo <= amount; ++amo)//先遍历金额后遍历硬币(最值：总是取最小的，不考虑顺序)
            for (int mon : coins)
                if (amo >= mon && dp[amo - mon] != Integer.MAX_VALUE)
                    dp[amo] = Math.min(dp[amo], dp[amo - mon] + 1);

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * 动规(二维)<br>
     * 执行用时：15 ms, 在所有 Java 提交中击败了36.08%的用户<br>
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    public static int coinChange_1(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int coinNum = coins.length;
        int[][] dp = new int[coinNum + 1][amount + 1];

        for (int i = 1; i <= amount; ++i)
            dp[0][i] = Integer.MAX_VALUE;

        for (int mon = 1; mon <= coinNum; ++mon)//硬币
            for (int amo = 1; amo <= amount; ++amo) {

                dp[mon][amo] = dp[mon - 1][amo];//拷贝

                int coin = coins[mon - 1];
                if (coin <= amo) {//可以凑、但是需要看前面是否有硬币凑
                    if (dp[mon][amo - coin] == Integer.MAX_VALUE)
                        continue;

                    dp[mon][amo] = Math.min(dp[mon][amo], dp[mon][amo - coin] + 1);
                }
            }

        return dp[coinNum][amount] == Integer.MAX_VALUE ? -1 : dp[coinNum][amount];
    }

    public static int coinChange_2(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        Arrays.sort(coins);//使用大的硬币先凑

        backTrack(coins.length - 1, amount, 0, coins);

        return res == Integer.MAX_VALUE ? -1 : res;

    }

    /**
     * 深搜：超时
     */
    private static void backTrack(int index, int amount, int num, int[] coins) {
        if (amount == 0) {
            res = Math.min(res, num);
            return;
        } else if (index == -1)//硬币花光了
            return;

        int coinNum = amount / coins[index];//当前硬币可以投放的个数

        for (int add = coinNum; add >= 0; --add) {//投放过大择减少投放量

            if (add + num > res)//投放的硬币过多
                return;

            backTrack(index - 1, amount - add * coins[index], num + add, coins);
        }

    }
}
