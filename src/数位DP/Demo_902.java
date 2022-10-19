package 数位DP;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/18 21:46
 */
// 902-最大为 N 的数字组合
public class Demo_902 {
    public static void main(String[] args) {
        String[] strs = {"1", "3", "5", "7"};
        int tar = 100;

        System.out.println(atMostNGivenDigitSet1(strs, tar));
    }

    private static String[] digits;
    private static char[] chars;
    private static int[] dp;

    /**
     * 数位DP+记忆化搜索<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了45.67%的用户<br>
     * 内存消耗：39 MB, 在所有 Java 提交中击败了51.05%的用户<br>
     * 2022年10月18日  22:43:05
     */
    public static int atMostNGivenDigitSet1(String[] strs, int tar) {
        digits = strs;
        chars = String.valueOf(tar).toCharArray();
        dp = new int[chars.length];

        // 初始化 dp
        Arrays.fill(dp, -1);

        return dfs(0, true, false);
    }

    private static int dfs(int idx, boolean limit, boolean isNum) {
        if (idx == chars.length) return isNum ? 1 : 0;

        if (isNum && !limit && dp[idx] >= 0) return dp[idx];

        int res = 0;

        if (!isNum) res = dfs(idx + 1, false, false);

        int ceiling = limit ? chars[idx] - '0' : 9;

        for (String val : digits) {// 重复使用
            if (Integer.parseInt(val) > ceiling) break;// 有序 => 大于最大值, 后面条件也不会满足

            res += dfs(idx + 1,
                    limit && Integer.parseInt(val) == ceiling,
                    true);
        }

        // 不受约束时候进行记忆
        if (!limit && isNum) dp[idx] = res;

        return res;
    }

}
