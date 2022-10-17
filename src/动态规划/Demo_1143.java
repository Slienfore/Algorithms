package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/27 19:59
 */
//1143-最长公共子序列
public class Demo_1143 {
    public static void main(String[] args) {
//        String text1 = "abcde", text2 = "ace";
        String text1 = "abc", text2 = "def";
        System.out.println(longestCommonSubsequence_1(text1, text2));
        System.out.println(longestCommonSubsequence_2(text1, text2));
    }

    /**
     * 动规+滚动数组<br>
     * 执行用时：9 ms, 在所有 Java 提交中击败了74.33%的用户<br>
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了96.98%的用户
     */
    public static int longestCommonSubsequence_2(String text1, String text2) {
        //滚动数组：来源的值只会来源于其左、左上、上
        int len1 = text1.length(), len2 = text2.length();

        int[] dp = new int[len2 + 1];
        for (int one = 1; one <= len1; ++one) {
            int leftTop = dp[0];//左上的值(第一列就是 0)

            for (int two = 1; two <= len2; ++two) {
                int preLeftTop = dp[two];//防止上一层左上数组被覆盖

                if (text1.charAt(one - 1) == text2.charAt(two - 1))
                    dp[two] = leftTop + 1;
                else
                    dp[two] = Math.max(dp[two], dp[two - 1]);//再比较左和上

                leftTop = preLeftTop;//更新
            }
        }

        return dp[len2];
    }

    /**
     * 动规<br>
     * 执行用时：14 ms, 在所有 Java 提交中击败了12.92%的用户<br>
     * 内存消耗：44.8 MB, 在所有 Java 提交中击败了63.57%的用户
     */
    public static int longestCommonSubsequence_1(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        //表示在字符串一长度[0, cur]前 和  字符串二长度[0, cur]前 的区间中形成最长公共子序列
        int[][] dp = new int[len1 + 1][len2 + 1];

        /*两种状态，相等或者不相等
        text1[i] == text2[j],dp[i][j] = dp[i - 1][j - 1] + 1
        若text1[i] != text2[j],dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])*/

        for (int one = 1; one <= len1; ++one)
            for (int two = 1; two <= len2; ++two)
                if (text2.charAt(two - 1) == text1.charAt(one - 1))//相等
                    dp[one][two] = dp[one - 1][two - 1] + 1;
                else
                    dp[one][two] = Math.max(dp[one - 1][two], dp[one][two - 1]);//比较分别取one、two区间之前的最长公共子序列

        return dp[len1][len2];
    }
}
