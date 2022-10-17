package 哈希表;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/19 15:55
 */
//242-有效的字母异位词
public class Demo_242 {
    public static void main(String[] args) {
        String s_1 = "anagram";
        String t_1 = "nagaram";
        System.out.println(isAnagram_1(s_1, t_1));

        String s_2 = "abc";
        String t_2 = "abd";
        System.out.println(isAnagram_2(s_2, t_2));

        String s_3 = "anagram";
        String t_3 = "nagaram";
        System.out.println(isAnagram_3(s_3, t_3));
    }

    /*定义一个26个字母的哈希表---数组<也是带索引的哈希表>
    执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.3 MB, 在所有 Java 提交中击败了91.29%的用户
     */
    public static boolean isAnagram_3(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] record = new int[26];//初始化26个字母的数组(下标即是各个字母的索引)
        for (char str : s.toCharArray()) {
            record[str - 'a'] += 1;//当出现相同的字符时，通过增加索引下的值来统计出现次数
        }

        for (char str : t.toCharArray()) {
            record[str - 'a'] -= 1;//当第第二个字符串出现相同的字符时，则访问其下标进行削减
        }

        for (int value : record) {
            if (value != 0) return false;//只有第二个数组的值全部相等的情况下才能全部删减为 零
        }
        return true;
    }


    /*哈希表
    执行用时：15 ms, 在所有 Java 提交中击败了20%的用户
    内存消耗：39.1 MB, 在所有 Java 提交中击败了17%的用户
    */
    public static boolean isAnagram_1(String s, String t) {
        //长度不相等直接返回
        if (s.length() != t.length()) return false;

        char[] str_1 = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (char value : str_1) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);//如果两个该值是是相同的情况的时候，统计value值出现的次数
            } else {
                map.put(value, 1);
            }
        }
        int index = 0;
        char[] str_2 = t.toCharArray();
        for (char value : str_2) {
            if (map.containsKey(value)) {//当哈希表中存在该值得时侯

                if (map.get(value) > 1) {//当其出现的次数大于 1 的时候则进行削减
                    map.put(value, map.get(value) - 1);
                } else {
                    map.remove(value);//反之将其移除
                }
                index++;

            }
        }
        return index == s.length();
    }

    /*比较排序
    执行用时：3 ms, 在所有 Java 提交中击败了74.66%的用户
    内存消耗：38.8 MB, 在所有 Java 提交中击败了31.12%的用户
    */
    public static boolean isAnagram_2(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] str_1 = s.toCharArray();
        char[] str_2 = t.toCharArray();
        //进行排序侯进行比较
        Arrays.sort(str_1);
        Arrays.sort(str_2);
        return Arrays.equals(str_1, str_2);
    }


}
