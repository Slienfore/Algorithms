package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/13 19:49
 */
//518-零钱兑换
public class Demo_518 {
    public static void main(String[] args) {
        int[] coin = {1, 2, 5};
        int amount = 5;

        System.out.println(change_1(amount, coin));
        System.out.println(change_2(amount, coin));
        System.out.println(change_3(amount, coin));
        System.out.println(change_4(amount, coin));
    }

    private static int change_4(int amount, int[] coins) {
        int coinNum = coins.length;

        int[] dp = new int[amount + 1];//代表金额为 mon 有多少银币组合(有序)
        dp[0] = 1;

/*        先遍历价值、后遍历金额，将会构成金额重复选取会构成不同的金额排列，而不是组合
            构成金额 4 时：
            1 可以和 3 进行组合，但是 3 中又含有 2， 2 中又含有 1
            1111、112、13
            2 可以和 2 进行组合，但是 2 中又含有 122、211

            构成金额 5 时：
            1 可以和 4 组合、、、、、
            所以 组合(有序) 不同于 排列(无序)*/

        for (int value = 1; value <= amount; ++value)
            for (int money = 0; money < coinNum; ++money)
                if (value >= coins[money])
                    dp[value] += dp[value - coins[money]];

                uu.print(dp);

        return dp[amount];
    }


    /**
     * 动规(一维)<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.96%的用户<br>
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了32.35%的用户
     */
    private static int change_3(int amount, int[] coins) {
        int coinNum = coins.length;

        int[] dp = new int[amount + 1];//代表金额为 mon 有多少银币组合(有序)
        dp[0] = 1;

        for (int money = 0; money < coinNum; ++money)
            for (int value = coins[money]; value <= amount; ++value)
                dp[value] += dp[value - coins[money]];

        return dp[amount];
    }


    /**
     * 动规(二维)<br>
     * 执行用时：10 ms, 在所有 Java 提交中击败了9.37%的用户<br>
     * 内存消耗：47.9 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    public static int change_2(int amount, int[] coins) {
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];

        for (int i = 1; i <= length; ++i)//金额为 0 均可以凑
            dp[i][0] = 1;

        //先遍历金额后遍历硬币
        for (int value = 1; value <= amount; ++value)
            for (int money = 1; money <= length; ++money) {

                dp[money][value] = dp[money - 1][value];//拷贝上一层

                if (value >= coins[money - 1])
                    dp[money][value] += dp[money][value - coins[money - 1]];//还需要累加自己能够凑的
            }

        return dp[length][amount];
    }

    /**
     * 动规(二维)<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了28.04%的用户<br>
     * 内存消耗：47.7 MB, 在所有 Java 提交中击败了9.70%的用户
     */
    public static int change_1(int amount, int[] coins) {
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];

        for (int i = 1; i <= length; ++i)//金额为 0 均可以凑
            dp[i][0] = 1;

        //先遍历硬币后遍历总金额
        for (int money = 1; money <= length; ++money)
            for (int value = 1; value <= amount; ++value) {
                dp[money][value] = dp[money - 1][value];//拷贝上一层

                if (value >= coins[money - 1])
                    dp[money][value] += dp[money][value - coins[money - 1]];//还需要累加自己能够凑的
            }

        return dp[length][amount];
    }
}
