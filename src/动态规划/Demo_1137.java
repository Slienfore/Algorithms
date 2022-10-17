package 动态规划;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/8 11:39
 */
//1137-第 N 个泰波那契数
public class Demo_1137 {
    public static void main(String[] args) {
        int num = 35;
        System.out.println(tribonacci_1(num));
        System.out.println(tribonacci_2(num));
        System.out.println(tribonacci_3(num));

    }

    /**
    递归(超时)
    */
    public static int tribonacci_3(int n) {
        if (n == 0 || n == 1)
            return n;
        else if (n == 2) {
            return 1;
        }

        //寻求前三个数进行累加，那么需要进行查找前三个数
        return tribonacci_3(n - 1) + tribonacci_3(n - 2) + tribonacci_3(n - 3);
    }


    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了11.26%的用户
     */
    public static int tribonacci_2(int n) {
        if (n == 0 || n == 1)
            return n;

        int[] dp = new int[n + 1];
        dp[0] = 0;//T0
        dp[1] = 1;//T1

        for (int i = 2; i <= n; i++)
            for (int j = i - 1; j >= 0 && (j + 3 >= i); j--) //等于其前面 3 项累加
                dp[i] += dp[j];


        return dp[n];
    }

    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了36.92%的用户
     */
    public static int tribonacci_1(int n) {
        if (n == 0 || n == 1)
            return n;

        //第 N 个数都是等于其前三项之和
        int[] dp = new int[n + 1];

        dp[0] = 0;//T0
        dp[1] = 1;//T1
        dp[2] = 1;//T2

        for (int i = 3; i <= n; i++)  //从第四个数开始推
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];


        return dp[n];
    }
}
