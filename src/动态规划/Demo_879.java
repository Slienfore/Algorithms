package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/12 17:04
 */
//879-盈利计划
public class Demo_879 {
    public static void main(String[] args) {
        int person = 5, minProfit = 3;

        int[] group = {2, 3};//完成任务所需要的人数
        int[] profit = {2, 3};//完成任务所获得的利益

        System.out.println(profitableSchemes_1(person, minProfit, group, profit));
//        System.out.println(profitableSchemes_2(person, minProfit, group, profit));

    }

    /**
     * 动规(二维)<br>
     * 执行用时：16 ms, 在所有 Java 提交中击败了90.96%的用户<br>
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了80.42%的用户
     */
    public static int profitableSchemes_2(int n, int minProfit, int[] group, int[] profit) {
        int total = group.length;//任务总数

        //DP[ peo ][ minProfit ]: 人数  不超过(至少是)  peo  时   (至少)获利为 minProfit 的方案数
        int[][] dp = new int[n + 1][minProfit + 1];

        for (int i = 0; i <= n; ++i)//无论是多少个人总能够提供一种方案
            dp[i][0] = 1;

        for (int tsk = 0; tsk < total; ++tsk) {
            int minPeo = group[tsk], ins = profit[tsk];//完成该项任务所需要的人数以及所获得利润

            for (int peo = n; peo >= minPeo; --peo)//满足最低人数限制
                for (int prc = minProfit; prc >= 0; --prc) {//各个人数至少为 peo 人时 获得各种利润的方案

                    int surIns = Math.max(prc - ins, 0);
                    dp[peo][prc] += dp[peo - minPeo][surIns];

                    if (dp[peo][prc] > mod)
                        dp[peo][prc] -= mod;
                }
        }

        return dp[n][minProfit];
    }


    static int mod = (int) 1e9 + 7;//防止数过大

    /**
     * 动规(三维)<br>
     * 执行用时：44 ms, 在所有 Java 提交中击败了20.48%的用户<br>
     * 内存消耗：57.2 MB, 在所有 Java 提交中击败了5.12%的用户
     */
    public static int profitableSchemes_1(int n, int minProfit, int[] group, int[] profit) {
        int total = group.length;

        //每一个任务 使用 ？ 多少个人 获利至少为 minProfit 的方案数
        long[][][] dp = new long[total + 1][n + 1][minProfit + 1];//获取大于minProfit的都可以看作是minProfit

        for (int i = 0; i <= n; ++i)//无论人数为多少，至少都可以提供一种方案数
            dp[0][i][0] = 1;

        for (int tsk = 1; tsk <= total; ++tsk) {//完成的任务
            int minPeo = group[tsk - 1], ins = profit[tsk - 1];//获利以及所需要的人数

            for (int peo = 0; peo <= n; ++peo) //分配的人数

                for (int prc = 0; prc <= minProfit; ++prc) {
                    //若不满足人数，还是需要将上一层数据复制到这一层来
                    dp[tsk][peo][prc] = dp[tsk - 1][peo][prc];

                    if (peo >= minPeo) {
                        int surIns = Math.max(prc - ins, 0);//若获利比最低利润大(那么也算进minProfit)
                        dp[tsk][peo][prc] += dp[tsk - 1][peo - minPeo][surIns];

                        if (dp[tsk][peo][prc] > mod)//数值过大
                            dp[tsk][peo][prc] -= mod;
                    }
                }
        }
        return (int) dp[total][n][minProfit];
    }
}
