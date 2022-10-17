package 回溯;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/17 15:15
 */
//318-最大的单词长度面积
public class Demo_318 {
    public static void main(String[] args) {
//        String[] str = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
//        String[] str = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
//        String[] str = {"a", "aa", "aaa", "aaaa"};
        String[] str = {"a", "ab", "abc", "abcd", "abcde", "abcdef", "abcdefg", "abcdefgh", "abcdefghi", "abcdefghij", "abcdefghijk", "abcdefghijkl", "abcdefghijklm", "nopqrstuvwxyz", "mnopqrstuvwxyz", "lmnopqrstuvwxyz", "klmnopqrstuvwxyz", "jklmnopqrstuvwxyz", "ijklmnopqrstuvwxyz", "hijklmnopqrstuvwxyz", "ghijklmnopqrstuvwxyz", "fghijklmnopqrstuvwxyz", "efghijklmnopqrstuvwxyz", "defghijklmnopqrstuvwxyz", "cdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyz"};
//        System.out.println(maxProduct_1(str));
        System.out.println(maxProduct_2(str));
    }

    /**位运算：<br>
    执行用时：24 ms, 在所有 Java 提交中击败了36.67%的用户<br>
    内存消耗：38.9 MB, 在所有 Java 提交中击败了8.08%的用户
    */
    public static int maxProduct_2(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();

        for (String word : words) {
            int val = 0, length = word.length();

            for (char chars : word.toCharArray())
                val |= (1 << chars - 'a');

            //如果哈希表中不存在该键，或者当前的长度大于之前的长度
            if (!map.containsKey(val) || map.get(val) < length) map.put(val, length);

        }

        int res = 0;
        for (int preKey : map.keySet())
            for (int curKey : map.keySet())
                if ((preKey & curKey) == 0)
                    res = Math.max(res, map.get(preKey) * map.get(curKey));

        return res;
    }


    /**
     * 穷举<br>
     * 执行用时：420 ms, 在所有 Java 提交中击败了13.50%的用户<br>
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了5.08%的用户
     */
    public static int maxProduct_1(String[] words) {
        int length = words.length;
        int res = 0;

        for (int i = 0; i < length; i++)
            for (int j = i + 1; j < length; j++)
                if (isValid(i, j, words)) {
/*                    int curLength = words[i].length() * words[j].length();

                    if (curLength < res) return res;
                    else res = curLength;*/
                    res = Math.max(res, words[i].length() * words[j].length());
                }

        return res;
    }

    private static boolean isValid(int pre, int cur, String[] words) {
        int[] hash = new int[26];
        String o1 = words[pre], o2 = words[cur];

        for (char val : o1.toCharArray())
            hash[val - 'a'] += 1;

        for (char val : o2.toCharArray())
            if (hash[val - 'a'] != 0) return false;

        return true;
    }


}
