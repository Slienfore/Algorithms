package 滑动窗口;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/8 12:37
 */
//242-替换后的最长重复字符
public class Demo_242 {
    public static void main(String[] args) {
        String str = "ABAB";
        int op = 2;
/*        String str = "AABABBA";
        int op = 1;*/
/*        String str = "AABCABBB";
        int op = 2;*/
        System.out.println(characterReplacement(str, op));
    }

    /**
     * 滑动窗口<br>
     * 执行用时：9 ms, 在所有 Java 提交中击败了28.92%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了47.58%的用户<br>
     * 2022年04月08日  16:19:20
     */
    public static int characterReplacement(String str, int op) {
        int length = str.length(), res = 0;

        int[] freq = new int[26];//记录各个字符出现的频率

        for (int left = 0, right = 0; right < length; ++right) {

            ++freq[str.charAt(right) - 'A'];//统计(当前字符)出现的频率

            while (!isValid(freq, op)) {//判断该区间中是否可以由 "出现次数最多" 的字符 "替换" 形成
                --freq[str.charAt(left) - 'A'];//收缩左边窗口
                ++left;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    private static boolean isValid(int[] freq, int limit) {
        int max = 0, total = 0;

        for (int val : freq) {
            max = Math.max(max, val);//统计出现最多次的字符
            total += val;//统计该区间中的所有字符数目
        }

        return total - max <= limit;//该区间中是否可以由出现次数最多的字符替换形成
    }
}
