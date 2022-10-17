package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 18:03
 */
//647-回文子串
public class Demo_647 {
    public static void main(String[] args) {
        String str = "abc";
        System.out.println(countSubstrings(str));
    }

    /**
     * 动规<br>
     * 执行用时：9 ms, 在所有 Java 提交中击败了48.05%的用户<br>
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了24.53%的用户
     */
    public static int countSubstrings(String s) {
        int length = s.length();
        if (length == 1)
            return 1;

        boolean[][] dp = new boolean[length][length];

        int res = 0;//单个字符就是回文子串
        for (; res < length; ++res)
            dp[res][res] = true;

        for (int right = 1; right < length; ++right)
            for (int left = 0; left < right; ++left)
                if (s.charAt(left) == s.charAt(right)) {//左右字符相等
                    if (right - left < 3) {//BB、BAB
                        dp[left][right] = true;
                        ++res;
                    } else if (dp[left + 1][right - 1]) {//去头去尾
                        dp[left][right] = true;
                        ++res;
                    }
                }

        return res;
    }

}
