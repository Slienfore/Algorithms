package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 10:12
 */
//91-解码方法
public class Demo_91 {
    public static void main(String[] args) {
        String str = "11106";
        System.out.println(numDecodings(str));
    }

    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了20.90%的用户
     */
    public static int numDecodings(String s) {
        int length = s.length();

        int[] dp = new int[length + 1];//以当前字符结尾组成的字符串的方法

        dp[0] = 1;
        dp[1] = (s.charAt(0) - '0' >= 1 && s.charAt(0) - '0' <= 9) ? 1 : 0;

        for (int cur = 2; cur <= length; ++cur) {
            int curVal = s.charAt(cur - 1) - '0', compo = (s.charAt(cur - 2) - '0') * 10 + curVal;//组成的两位数

            if (curVal >= 1 && curVal <= 9)//自成一个数
                dp[cur] += dp[cur - 1];

            if (compo >= 10 && compo <= 26)//可以与前一个数组成两位数(即由[cur - 2]的数一步构成)
                dp[cur] += dp[cur - 2];
        }

        return dp[length];
    }
}
