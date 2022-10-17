package 动态规划;

import com.sun.jdi.IntegerType;
import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/15 18:25
 */
//279-完全平方数
public class Demo_279 {
    public static void main(String[] args) {
        int num = 999;
//        int num = 12;
        System.out.println(numSquares_1(num));
        System.out.println(numSquares_2(num));
    }

    /**
     * 动规(一维)<br>
     * 执行用时：35 ms, 在所有 Java 提交中击败了51.75%的用户<br>
     * 内存消耗：40 MB, 在所有 Java 提交中击败了57.04%的用户
     */
    public static int numSquares_2(int n) {

        int[] dp = new int[n + 1];//和为 val 的完全平方数最小数量

        for (int value = 1; value <= n; ++value) {
            dp[value] = value;//每一个数都可以由 完全平方数 1 组成

            for (int num = 2; (num * num) <= value; ++num) //与该数的完全平方数之和
                dp[value] = Math.min(dp[value], dp[value - (num * num)] + 1);

        }

        return dp[n];
    }


    /**
     * 动规(一维)<br>
     * 执行用时：42 ms, 在所有 Java 提交中击败了48.21%的用户<br>
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了38.52%的用户
     */
    public static int numSquares_1(int n) {

        int[] dp = new int[n + 1];//和为 val 的完全平方数最小数量

        for (int i = 1; i <= n; ++i)
            dp[i] = Integer.MAX_VALUE;

        for (int value = 1; value <= n; ++value)
            for (int num = 1; Math.pow(num, 2) <= value; ++num) //与该数的完全平方数之和
                dp[value] = Math.min(dp[value], dp[value - (int) Math.pow(num, 2)] + 1);

        return dp[n];
    }

}
