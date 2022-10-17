package 哈希表;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/21 8:37
 */
//383-赎金信
public class Demo_383 {
    public static void main(String[] args) {
        String ransomNote = "a";
        String magazine = "aab";
        System.out.println(canConstruct_1(ransomNote, magazine));
        System.out.println(canConstruct_2(ransomNote, magazine));
        System.out.println(canConstruct_3(ransomNote, magazine));
    }

    /*哈希表：
    执行用时：11 ms, 在所有 Java 提交中击败了21.88%的用户
    内存消耗：38.9 MB, 在所有 Java 提交中击败了36.18%的用户
    */
    public static boolean canConstruct_1(String ransomNote, String magazine) {
        char[] str_2 = magazine.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char value : str_2) {
            if (!map.containsKey(value)) {
                map.put(value, 1);
            } else {
                map.put(value, map.get(value) + 1);//统计重复元素出现次数
            }
        }

        char[] str_1 = ransomNote.toCharArray();
        for (char value : str_1) {
            if (map.containsKey(value) && map.get(value) > 0) {//当存在相同的元素的时候，将统计信息递减
                map.put(value, map.get(value) - 1);
            } else return false;
        }
        return true;
    }

    /*数组哈希表
    执行用时：1 ms, 在所有 Java 提交中击败了99.87%的用户
    内存消耗：38.9 MB, 在所有 Java 提交中击败了32.21%的用户
     */
    public static boolean canConstruct_2(String ransomNote, String magazine) {
        if (ransomNote.isEmpty() || magazine.isEmpty()) return false;

        int[] record = new int[26];
        char[] str_2 = magazine.toCharArray();
        for (char value : str_2) {
            record[value - 'a']++;
        }

        char[] str_1 = ransomNote.toCharArray();
        for (char value : str_1) {
            if (record[value - 'a'] > 0) {//只有当该字母里面有值的时候可以继续及进行下去
                record[value - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }

    /*字符串容器：
    执行用时：3 ms, 在所有 Java 提交中击败了44.14%的用户
    内存消耗：39 MB, 在所有 Java 提交中击败了20.58%的用户
    */
    public static boolean canConstruct_3(String ransomNote, String magazine) {
        if (ransomNote.isEmpty() || magazine.isEmpty()) return false;

        StringBuilder builder = new StringBuilder(magazine);

        for (char value : ransomNote.toCharArray()) {

            int exist = builder.indexOf(String.valueOf(value));//获取指定的字符串中的位置
            if (exist >= 0) {
                builder.deleteCharAt(exist);//删除指定元素
            } else return false;

        }
        return true;
    }

    public boolean canConstruct_4(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null)
            return false;

        int[] hash = new int[26];
        char[] arr = magazine.toCharArray();
        for (char val : arr)
            hash[val - 'a']++;

        char[] array = ransomNote.toCharArray();
        for (char val : array)
            if (hash[val - 'a'] > 0) hash[val - 'a']--;
            else return false;

        return true;
    }
}