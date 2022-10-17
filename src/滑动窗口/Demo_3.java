package 滑动窗口;

import utils.uu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

// 3-无重复字符的最长子串
public class Demo_3 {
    public static void main(String[] args) {
        String s = "abcab/c]bb";
        System.out.println(lengthOfLongestSubstring_1(s));
        System.out.println(lengthOfLongestSubstring_2(s));
        System.out.println(lengthOfLongestSubstring_3(s));
        System.out.println(lengthOfLongestSubstring_5(s));

    }

    // Demo_1：哈希表：
    public static int lengthOfLongestSubstring_1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int point = 0, result = 0;
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(chars[i])) {//如果表中包含该键，则进行指针移动，不再进行计算
                //指针移动到其计算位(且指针不能回溯到比它小得值)
                point = Math.max(point, map.get(chars[i]) + 1);
            }
            map.put(chars[i], i);
            result = Math.max(result, i - point + 1);//计算最大值
        }
        return result;
    }

    //Demo_2: 滑动窗口
    public static int lengthOfLongestSubstring_2(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        if (length < 1) return length;

        int left = 0, right = 0, max = 1;
        //进行窗口滑动
        HashSet<Character> window = new HashSet<>();
        while (right < length) {
            char str = chars[right];

            while (window.contains(str)) {
                //将左边的元素进行剔除，知道没有位置(逐步缩小窗口)
                window.remove(chars[left]);
                left++;
            }

            //窗口缩减后不再重复，将元素添加进去
            max = Math.max(max, right - left + 1);
            window.add(str);
            right++;
        }
        return max;
    }

    /**
     * 滑动窗口<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了43.14%的用户<br>
     * 2022年04月07日  17:37:07
     */
    public static int lengthOfLongestSubstring_5(String str) {
        int length = str.length();

        int[] hash = new int[128];//包含所有字符
        Arrays.fill(hash, -1);

        int res = 0;
        for (int left = 0, right = 0; right < length; ++right) {
            int cur = str.charAt(right);

            left = Math.max(left, hash[cur] + 1);//比较上一个重复字符出现的位置是否会回流到重复位置

            res = Math.max(res, right - left + 1);//统计最长长度

            hash[cur] = right;//存放当前字符出现的位置
        }

        return res;
    }


    /**
     * 滑动窗口<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了30.41%的用户
     */
    public static int lengthOfLongestSubstring_3(String s) {
        int[] hash = new int[128];//定义全部字符的Ascii
        Arrays.fill(hash, -1);

        int left = 0, length = 0;
        for (int i = 0; i < s.length(); i++) {//维护两个相同元素的区间不出现重复元素

            int index = s.charAt(i);//获取当前哈希键值

            left = Math.max(left, hash[index] + 1);//重复的元素不能要，移动到下一位

            length = Math.max(length, i - left + 1);//获取当前不重复字符串的长度

            hash[index] = i;//将当前重复元素的下标填入
        }

        return length;
    }

    /**
     * 哈希<br>
     * 执行用时：86 ms, 在所有 Java 提交中击败了10.17%的用户<br>
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    private static int lengthOfLongestSubstring_4(String s) {
        if (s.length() == 0) return 0;

        HashSet<Character> set = new HashSet<>();//去重hash

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char val = s.charAt(i);//获取当前值

            if (!set.add(val)) {//如果添加不成功的时候说明出现了重复字符
                res = Math.max(res, set.size());//获取当前无重复字串的长度
                s = s.substring(s.indexOf(val) + 1);//从出现重复的字符下一位开始
                set.clear();//窗口更新
                i = -1;
            }

            res = Math.max(res, set.size());//如果全部添加成功
        }

        return res;
    }
}
