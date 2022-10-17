package 动态规划;

import utils.uu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/15 11:03
 */
//139-单词拆分
public class Demo_139 {
    public static void main(String[] args) {
/*        String orin = "aaab";
        List<String> wordDict = new ArrayList<>() {{
            add("a");
            add("aa");
            add("aaa");
            add("aaaa");
            add("aaaaa");
            add("aaaaaa");
            add("aaaaaaa");
            add("aaaaaaaa");
            add("aaaaaaaaa");
            add("aaaaaaaaaa");
        }};*/

        String orin = "leetcode";
        List<String> wordDict = new ArrayList<>() {{
            add("leet");
            add("code");
        }};

/*        String orin = "applepenapple";
        List<String> wordDict = new ArrayList<>() {{
            add("apple");
            add("pen");
        }};*/

        System.out.println(wordBreak_2(orin, wordDict));
        System.out.println(wordBreak_3(orin, wordDict));

    }

    /**
     * 动规(一维 + 剪枝)<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.67%的用户<br>
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了53.87%的用户
     */
    public static boolean wordBreak_3(String s, List<String> wordDict) {
        int length = s.length();
        HashSet<String> set = new HashSet<>();

        int maxLength = Integer.MIN_VALUE;//查找单词最长，方便快速截取子串
        for (String str : wordDict) {
            set.add(str);
            maxLength = Math.max(str.length(), maxLength);
        }

        //单词-与切割点
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        for (int main = 1; main <= length; ++main)
            for (int sub = Math.max((main - maxLength), 0); sub < main; ++sub) {//若大于最长单词说明无效枚举
                String str = s.substring(sub, main);

                if (set.contains(str) && dp[sub]) {//标记的上一个子串是否切割-->需要连续
                    dp[main] = true;
                    break;
                }

            }

        return dp[length];
    }

    /**
     * 动规<br>
     * 执行用时：8 ms, 在所有 Java 提交中击败了33.42%的用户<br>
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了6.08%的用户
     */
    public static boolean wordBreak_2(String s, List<String> wordDict) {
        int length = s.length();

        HashSet<String> set = new HashSet<>(wordDict);

        //单词-与切割点
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        for (int main = 1; main <= length; ++main)//main 下标是从 1 开始的， 切割时不用 + 1
            for (int sub = 0; sub < main; ++sub) {//满足母串对子串的切割
                String str = s.substring(sub, main);

                if (set.contains(str) && dp[sub]) {//标记的上一个子串是否切割-->需要连续
                    dp[main] = true;
                    break;
                }

            }

        return dp[length];
    }

    /**
     * 记忆化搜索<br>
     * 执行用时：9 ms, 在所有 Java 提交中击败了20.51%的用户<br>
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了28.42%的用户
     */
    public static boolean wordBreak_1(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);//方便查找出现过的单词

        int[] memo = new int[s.length()];//记忆字符串种从指定位置截取是否会成功

        return backTrack(0, memo, set, s);

    }

    private static boolean backTrack(int startIndex, int[] memo, HashSet<String> set, String orin) {
        if (startIndex == orin.length())//说明已经拆分完了
            return true;

        if (memo[startIndex] != 0)
            return memo[startIndex] == 1;//标记该位置是否可以进行拼接

        for (int i = startIndex; i < orin.length(); ++i) {
            String str = orin.substring(startIndex, i + 1);//拆分单词

            if (set.contains(str) && backTrack(i + 1, memo, set, orin)) {//若可以有组成该字符串
                memo[startIndex] = 1;
                return true;
            }

        }

        memo[startIndex] = -1;//不能搜索
        return false;
    }
}
