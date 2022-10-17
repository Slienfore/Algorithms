package 周赛;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/27 10:55
 */
//6009-使两字符串互为异位词的最小步骤数
public class Demo_6009 {
    public static void main(String[] args) {
//        String s = "leetcode", t = "coats";
//        String s = "night", t = "thing";
        String s = "cotxazilut", t = "nahrrmcchxwrieqqdwdpneitkxgnt";
        System.out.println(minSteps_1(s, t));
    }

    static char[] words = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static int minSteps(String s, String t) {
        int[] hash_1 = new int[26];
        for (char val : s.toCharArray())
            hash_1[val - 97]++;

        int[] hash_2 = new int[26];
        for (char val : t.toCharArray())
            hash_2[val - 97]++;


        int res = 0;
        for (int i = 0; i < 26; i++) {
            int val_1 = hash_1[i], val_2 = hash_2[i];

            if (val_1 == val_2)
                continue;

            res += Math.abs(val_1 - val_2);//计算两个差值(最终达到的长度是一样的，且字母个数也是相同的)

        }

        return res;
    }

    /**<br>
    执行用时：13 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：44.8 MB, 在所有 Java 提交中击败了100.00%的用户
    */
    private static int minSteps_1(String s, String t) {
        int[] hash = new int[26];

        for (char val : s.toCharArray())
            hash[val - 97]++;

        int res = s.length();//是字符串 s 的长度， 用来对比字符串 t 中《没有》的元素，如果共有就自减，否则需要自加

        for (char val : t.toCharArray())
            res += hash[val - 97]-- > 0 ? -1 : 1;

        return res;

    }

}
