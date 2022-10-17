package 动态规划;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/27 22:00
 */
//72-编辑距离
public class Demo_72 {
    public static void main(String[] args) {
        String str1 = "", str2 = "";
        System.out.println(minDistance(str1, str2));
    }

    public static int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();

        //表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int cur = 1; cur <= len1; ++cur)
            dp[cur][0] = cur;

        for (int cur = 1; cur <= len2; ++cur)
            dp[0][cur] = cur;

        for (int one = 1; one <= len1; ++one)
            for (int two = 1; two <= len2; ++two)
                if (word1.charAt(one - 1) == word2.charAt(two - 1))//相同则不需要进行编辑
                    dp[one][two] = dp[one - 1][two - 1];
                else
                    dp[one][two] = Math.min(Math.min(dp[one - 1][two - 1], dp[one][two - 1]), dp[one - 1][two]) + 1;

        return dp[len1][len2];
    }
}
