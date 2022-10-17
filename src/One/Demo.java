package One;

import utils.uu;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/1 15:43
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println(5 ^ 2);
    }

    public int integerBreak(int n) {
        //拆分数字 N 的最大乘积
        int[] dp = new int[n + 1];//包含 N

        for (int cur = 2; cur <= n; ++cur) //0 与 1 不能拆分
            for (int pre = 1; pre <= (cur / 2); ++pre) {
                int tar = cur - pre;

                dp[cur] = Math.max(dp[cur], Math.max(tar * pre, dp[pre] * dp[tar]));//拆分后者不拆分的最大值
            }

        return dp[n];
    }
}
