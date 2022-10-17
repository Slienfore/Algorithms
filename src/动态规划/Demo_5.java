package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 14:41
 */
//5-最长回文子串
public class Demo_5 {
    public static void main(String[] args) {
        String str = "babad";
//        String str = "cbbd";
//        String str = "bb";
        System.out.println(longestPalindrome_1(str));
        System.out.println(longestPalindrome_2(str));

    }

    /**
     * 动规<br>
     * 执行用时：262 ms, 在所有 Java 提交中击败了16.02%的用户<br>
     * 内存消耗：45.9 MB, 在所有 Java 提交中击败了7.39%的用户
     */
    public static String longestPalindrome_2(String s) {
        int length = s.length();
        if (length == 1)
            return s;

        boolean[][] dp = new boolean[length][length];//分别代表左右子串起始位置是否是回文子串

        for (int cur = 0; cur < length; ++cur)//单字符都是回文串
            dp[cur][cur] = true;

        int begin = 0, maxLen = 1;//一个字符是一个回文子串

        for (int right = 1; right < length; ++right)//右边界
            for (int left = 0; left < right; ++left) {//左边界

                if (s.charAt(left) == s.charAt(right)) {
                    if (right - left == 1)//左右两个字符都为一个且相等(BB)
                        dp[left][right] = true;

                    else if (right - left == 2)//左右字符相等中间夹一个字符(BAB)
                        dp[left][right] = true;

                    else if (dp[left + 1][right - 1])//去掉头尾依然是回文串的话(中间夹多个字符)
                        dp[left][right] = true;

                    else
                        dp[left][right] = false;
                } else
                    dp[left][right] = false;

                if (dp[left][right] && (right - left + 1) > maxLen) {//更新
                    begin = left;
                    maxLen = right - left + 1;
                }
            }

        return s.substring(begin, begin + maxLen);
    }


    /**
     * 中心扩散<br>
     * 执行用时：25 ms, 在所有 Java 提交中击败了82.97%的用户<br>
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了66.61%的用户
     */
    public static String longestPalindrome_1(String s) {
        int len = s.length();
        if (len == 1)
            return s;

        //中心扩散法
        int length = 2 * len - 1;//扩散中心点
        int begin = 0, maxLen = Integer.MIN_VALUE;//截取开始位置，截取长度设置不可到达

        for (int center = 0; center <= length; ++center) {
            int left = center / 2, right = center / 2 + center % 2;

            while ((left >= 0 && right < len) && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }

            if (right - (left + 1) > maxLen) {//最后指针停下的位置一定是不相等的
                begin = left + 1;
                maxLen = right - (left + 1);
            }

        }
        return maxLen == Integer.MIN_VALUE ? "" : s.substring(begin, begin + maxLen);
    }

}
