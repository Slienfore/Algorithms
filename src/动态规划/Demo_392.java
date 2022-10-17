package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/25 11:41
 */
//392-判断子序列
public class Demo_392 {
    public static void main(String[] args) {
        String str1 = "abc", str2 = "ahbgdc";
        System.out.println(isSubsequence_1(str1, str2));
        System.out.println(isSubsequence_2(str1, str2));
        System.out.println(isSubsequence_3(str1, str2));
    }

    /**
     * 动规<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了30.49%的用户<br>
     * 内存消耗：41 MB, 在所有 Java 提交中击败了15.79%的用户
     */
    public static boolean isSubsequence_3(String s, String t) {
        int len1 = s.length(), len2 = t.length();

        // 表示以下标i-1为结尾的字符串s，和以下标j-1为结尾的字符串t，相同子序列的长度为dp[i][j]
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int one = 1; one <= len1; ++one)
            for (int two = 1; two <= len2; ++two)
                if (s.charAt(one - 1) == t.charAt(two - 1))
                    dp[one][two] = dp[one - 1][two - 1] + 1;//若是子序列则会会加上其前面的长度
                else
                    dp[one][two] = dp[one][two - 1];//删除 T 字符串的序列的长度

        return dp[len1][len2] == len1;
    }


    /**
     * 动规<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了47.63%的用户<br>
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    public static boolean isSubsequence_2(String s, String t) {
        int length = t.length();
        //每一个位置往后中各个字符第一次出现的位置
        int[][] dp = new int[length + 1][26];

        Arrays.fill(dp[length], length);//最后一行的位置是不存在的

        for (int cur = length - 1; cur >= 0; --cur) {

            for (int last = 0; last <= 25; ++last)
                dp[cur][last] = dp[cur + 1][last];//若该位置以后没有出现该字符则填入最大长度

            dp[cur][t.charAt(cur) - 'a'] = cur;//当前字符出现的位置
        }

        int jump = 0;//母串字符中进行跳转的第一个比对字符
        for (char val : s.toCharArray()) {
            if (dp[jump][val - 'a'] == length)
                return false;//该跳转位置后续不存在该字符

            jump = dp[jump][val - 'a'] + 1;//说明当前位置以后存在该字符，继续跳转
        }

        return true;
    }


    /**
     * 双指针<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了88.46%的用户<br>
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了62.12%的用户
     */
    public static boolean isSubsequence_1(String s, String t) {
        int length1 = s.length(), length2 = t.length();
        int left = 0;

        for (int right = 0; right < length2 && left < length1; ++right)
            if (t.charAt(right) == s.charAt(left))//目标子串的字符
                ++left;

        return left == length1;//若目标子串的个数已经全部包含了
    }

}
