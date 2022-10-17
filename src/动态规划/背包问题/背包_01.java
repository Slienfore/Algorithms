package 动态规划.背包问题;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/11 7:21
 */
public class 背包_01 {
    static int maxVol = 4;
    static int[] weight = {0, 1, 3, 4};
    static int[] price = {0, 15, 20, 30};
    static int maxPrice = 0;

    public static void main(String[] args) {
//        backTrack(0, 0, maxVol);
        dynamicProgram();
        System.out.println(maxPrice);
    }

    private static void dynamicProgram() {
        int proNum = weight.length;

        /*
        在满足当前物品的容量下，(将剩余的背包容量)分配给上一件物品
        相同容量下:
        上一件物品的最大价值: dp[cur - 1][vol]
        剩余可分配的容量: vol - weight[cur]
        将剩余容量分配给上一层: dp[cur - 1][vol - weight[cur]]
        当前物品的最大价值: dp[cur - 1][vol - weight[cur]] + price[cur]
        dp[cur][vol] = Math.max(dp[cur - 1][vol], dp[cur - 1][vol - weight[cur]] + price[cur])
        */

        int[][] dp = new int[proNum][maxVol + 1];

        for (int vol = 1; vol <= maxVol; ++vol)
            if (vol >= weight[1])//若当前物品符合当前背包的容量
                dp[1][vol] = Math.max(dp[0][vol], dp[0][vol - weight[1]] + price[1]);


        for (int vol = maxVol; vol >= weight[0]; --vol)//从后向左满足背包容量的情况下添加
            dp[1][vol] = dp[1][vol] + price[1];

        /*[[0, 0, 0, 0, 0],
         [0, 15, 15, 15, 15],
         [0, 15, 15, 20, 35],
         [0, 15, 15, 20, 35]]*/
/*
        //先遍历物品、后遍历背包容量
        for (int cur = 2; cur < proNum; ++cur)//遍历每一件物品
            for (int vol = 1; vol <= maxVol; ++vol) {//每一种背包容量下的商品存放的最大价值

                if (vol >= weight[cur])//当前容量放得下时

                    dp[cur][vol] = Math.max(dp[cur - 1][vol], dp[cur - 1][vol - weight[cur]] + price[cur]);

                else//背包放不下就是上层最大值

                    dp[cur][vol] = dp[cur - 1][vol];
            }


        //先遍历背包容量、后遍历物品
        for (int vol = 1; vol <= maxVol; ++vol)//遍历每一个背包容量
            for (int cur = 2; cur < proNum; ++cur) {//遍历每一件物品
                if (vol >= weight[cur])
                    dp[cur][vol] = Math.max(dp[cur - 1][vol], dp[cur - 1][vol - weight[cur]] + price[cur]);

                else
                    dp[cur][vol] = dp[cur - 1][vol];
            }*/


        int[] Dp = new int[maxVol + 1];//记录的是上一层物品的最大价值
/*        //滚动数组
        for (int cur = 1; cur < proNum; ++cur)
            for (int vol = maxVol; vol >= weight[cur]; --vol)
                Dp[vol] = Math.max(Dp[vol], Dp[vol - weight[cur]] + price[cur]);*/

        for (int vol = maxVol; vol >= 0; --vol)
            for (int cur = 1; cur < proNum; ++cur) {
                if (vol >= weight[cur])
                Dp[vol] = Math.max(Dp[vol], Dp[vol - weight[cur]] + price[cur]);
            }

        uu.print(Dp);
        uu.print(dp);
    }


    private static void backTrack(int pro, int curPrice, int maxVol) {
        if (maxVol == 0 || pro == weight.length) {//出口：当容量花光或者物品已经选完之后
            maxPrice = Math.max(maxPrice, curPrice);
            return;
        }

        for (int num = 1; num >= 0; num--) {
            if (weight[pro] * num <= maxVol)//选与不选
                backTrack(pro + 1, curPrice + (price[pro] * num), maxVol - (weight[pro] * num));
        }
    }


}
