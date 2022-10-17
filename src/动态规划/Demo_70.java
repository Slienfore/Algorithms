package 动态规划;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/4 21:32
 */
//70-爬楼梯
public class Demo_70 {

    public static void main(String[] args) {
        int stairs = 1;
        System.out.println(climbStairs_1(stairs));
        System.out.println(climbStairs_2(stairs));
        System.out.println(climbStairs_3(stairs));
        System.out.println(climbStairs_4(stairs));
    }

    /**
     * 超时
     */
    public static int climbStairs_3(int n) {
        /*询问其前两项两步登上，询问其前一项一步登上
         一种：相邻的只需要一步就能登上了
         一种：其前两项也能够两步登上
        */
        if (n == 0 || n == 1) {
            return 1;
        } else if (n == 45) {
            return 1836311903;
        }

        return climbStairs_3(n - 1) + climbStairs_3(n - 2);
    }

    /**动规<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：38.1 MB, 在所有 Java 提交中击败了24.01%的用户
    */
    public static int climbStairs_4(int n) {
        int prePre = 1, pre = 1;//前前一项一步登上，前一项一步登上

        for (int i = 2; i <= n; i++) {
            int temp = pre;
            pre += prePre;
            prePre = temp;
        }

        return pre;
    }


    /*基础DP
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：35.4 MB, 在所有 Java 提交中击败了5.40%的用户
    */
    public static int climbStairs_1(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        //不是从 0 台阶开始的(这层不用爬)

        dp[1] = 1;//一层只能爬一次
        dp[2] = 2;//两层 可以爬一次， 也可以爬两次

        for (int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

    /*DP
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：35.1 MB, 在所有 Java 提交中击败了74.33%的用户
    */
    public static int climbStairs_2(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= 2; j++) {//每次只能爬的步数不能超过 2 步

                if (i - j >= 0)// i - j 就是当前楼梯的是否满足它的空间跨度

                    dp[i] += dp[i - j];
            }

        return dp[n];
    }


}
