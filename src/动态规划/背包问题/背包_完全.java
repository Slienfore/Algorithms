package 动态规划.背包问题;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/13 19:12
 */
public class 背包_完全 {
    public static void main(String[] args) {
        completeBag_1();
        completeBag_2();
        completeBag_3();
        completeBag_4();

    }

    static int[] weight = {0, 1, 3, 4};
    static int[] price = {0, 15, 20, 30};
    static int maxVol = 4;
    static int proNum = weight.length;

    private static void completeBag_4() {
        int[] dp = new int[maxVol + 1];

        for (int vol = 1; vol <= maxVol; ++vol)
            for (int pro = 1; pro < proNum; ++pro)
                if (weight[pro] <= vol)
                    dp[vol] = Math.max(dp[vol], dp[vol - weight[pro]] + price[pro]);

        System.out.println(Arrays.toString(dp));

    }


    private static void completeBag_3() {
        int[] dp = new int[maxVol + 1];

        for (int pro = 1; pro < proNum; ++pro)
            for (int vol = weight[pro]; vol <= maxVol; ++vol)
                dp[vol] = Math.max(dp[vol], dp[vol - weight[pro]] + price[pro]);

        System.out.println(Arrays.toString(dp));

    }

    private static void completeBag_1() {
        int[][] dp = new int[proNum][maxVol + 1];

        //先遍历物品后遍历容量
        for (int pro = 1; pro < proNum; ++pro)
            for (int vol = 1; vol <= maxVol; ++vol) {
                if (weight[pro] <= vol)
                    dp[pro][vol] = Math.max(dp[pro - 1][vol], dp[pro][vol - weight[pro]] + price[pro]);
                else
                    dp[pro][vol] = dp[pro - 1][vol];
            }

        System.out.println(Arrays.deepToString(dp));

    }

    //先遍历容量后遍历物品
    private static void completeBag_2() {
        int[][] dp = new int[proNum][maxVol + 1];

        for (int vol = 1; vol <= maxVol; ++vol)
            for (int pro = 1; pro < proNum; ++pro) {
                if (weight[pro] <= vol)
                    dp[pro][vol] = Math.max(dp[pro - 1][vol], dp[pro][vol - weight[pro]] + price[pro]);
                else
                    dp[pro][vol] = dp[pro - 1][vol];
            }

        System.out.println(Arrays.deepToString(dp));

    }
}
