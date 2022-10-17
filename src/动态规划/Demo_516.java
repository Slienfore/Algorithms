package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 17:31
 */
//516-最长回文子序列
public class Demo_516 {
    public static void main(String[] args) {
//        String str = "bbbab";
//        String str = "aabaa";
//        String str = "cbbd";
//        String str = "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";
        String str = "cbbd";
        System.out.println(longestPalindromeSubseq_1(str));
        System.out.println(longestPalindromeSubseq_2(str));
    }

    /**区间DP<br>
     执行用时：41 ms, 在所有 Java 提交中击败了23.24%的用户<br>
     内存消耗：50.4 MB, 在所有 Java 提交中击败了41.55%的用户*/
    public static int longestPalindromeSubseq_2(String s) {
        int length = s.length();

        //区间 Dp->区间[left, right] 的最长回文子串长度
        int[][] dp = new int[length][length];

        for (int len = 1; len <= length; ++len)//区间长度
            for (int left = 0; (left + len - 1) < length; ++left) {//左边界
                int right = left + len - 1;//右边界

                if (len == 1) //区间长度为 1 时
                    dp[left][right] = 1;

                else if (len == 2) //区间长度(子串长度)为 2 时
                    dp[left][right] = (s.charAt(left) == s.charAt(right)) ? 2 : 1;//若不相等则该区间只存在长度为 1 的回文子串

                else
                    dp[left][right] = (s.charAt(left) == s.charAt(right)) ?
                            (dp[left + 1][right - 1] + 2) :
                            Math.max(dp[left + 1][right], dp[left][right - 1]);//比较去头、去尾
            }

        return dp[0][length - 1];
    }


    /**
     * 动规<br>
     * 执行用时：45 ms, 在所有 Java 提交中击败了14.86%的用户<br>
     * 内存消耗：50.8 MB, 在所有 Java 提交中击败了9.08%的用户
     */
    public static int longestPalindromeSubseq_1(String s) {
        int length = s.length();

        int[][] dp = new int[length][length];//字符串再[left,right]范围内构成的最长回文子序列长度

        for (int cur = 0; cur < length; ++cur)//每一个字符在自己范围内构成的回文子序列长度
            dp[cur][cur] = 1;

        for (int right = 1; right < length; ++right)//保证 Right - 1 是已经遍历的

            for (int left = right - 1; left >= 0; --left) //保证 Left + 1 是已经遍历过的
                if (s.charAt(left) == s.charAt(right)) {//头尾相同
                    dp[left][right] = dp[left + 1][right - 1] + 2;//加头、加尾
                } else
                    dp[left][right] = Math.max(dp[left + 1][right], dp[left][right - 1]);//去头或者去尾构成回文子串最大


        return dp[0][length - 1];//[ Left, Left ]越远,区间内 回文子串的长度越长
    }
}
