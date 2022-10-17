package 滑动窗口;

import utils.uu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/25 10:42
 */
// 567-字符串的排序
public class Demo_567 {
    public static void main(String[] args) {
//        String s_1 = "abc", s_2 = "rubca";
        String s_1 = "hello", s_2 = "ooolleoooleh";

        System.out.println(checkInclusion_1(s_1, s_2));
        System.out.println(checkInclusion_2(s_1, s_2));
        System.out.println(checkInclusion_3(s_1, s_2));
    }

    /**
     * 滑动窗口<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了96.92%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了41.88%的用户<br>
     * 2022年04月08日  11:57:56
     */
    public static boolean checkInclusion_3(String tar, String str) {
        int width = tar.length(), length = str.length();
        if (width > length)//母串长度小，则不可能为目标串的子集
            return false;

        int[] hash = new int[26];
        for (char val : tar.toCharArray())
            ++hash[val - 'a'];

        for (int need = width, left = 0, right = 0; right < length; ++right) {
            if (--hash[str.charAt(right) - 'a'] >= 0)
                --need;

            if (right - left + 1 == width) {//达到窗口限制长度
                if (need == 0)
                    return true;

                if (hash[str.charAt(left) - 'a']++ >= 0)//吐出指定字符了
                    ++need;

                ++left;//收缩窗口
            }
        }

        return false;
    }


    /**
     * 字符哈希：<br>
     * 执行用时：46 ms, 在所有 Java 提交中击败了15.44%的用户<br>
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了37.30%的用户
     */
    public static boolean checkInclusion_1(String s1, String s2) {
        char[] char_1 = s1.toCharArray(), char_2 = s2.toCharArray();
        //创建字符哈希
        int[] pre = new int[26];
        for (char value : char_1) {
            pre[value - 'a'] += 1;//统计字符出现的次数
        }
        int preLength = s1.length();
        int length = s2.length();

        for (int i = 0; i < length; i++) {
            int[] temp = new int[26];
            if (i + preLength <= length) {

                for (int j = (i + preLength - 1); j >= i; j--) {
                    temp[char_2[j] - 'a'] += 1;
                }

                int index = 0;
                for (int o = 0; o < 26; o++) {
                    if (pre[o] == temp[o]) index++;
                }
                if (index == 26) return true;//如果全部和数组一相等的时候则会直接返回

            } else break;
        }
        return false;
    }


    /**
     * 滑动窗口<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.99%的用户<br>
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了94.20%的用户
     */
    public static boolean checkInclusion_2(String s1, String s2) {
        int length_1 = s1.length(), length_2 = s2.length();
        if (length_1 > length_2) return false;

        char[] str_1 = s1.toCharArray();
        char[] str_2 = s2.toCharArray();

        int[] total = new int[26];//字符哈希
        for (char value : str_1) {//进行存储子串
            total[value - 97]++;
        }

        int left = 0, right = 0;//左右窗口边界

        //当窗口边界不超过的时候
        while (left <= (length_2 - length_1)) {

            while (right < (left + length_1) && total[str_2[right] - 97] > 0) {//只有里边有值右边界才能进行移动
                total[str_2[right] - 97]--;//右边界将位置元素的值清除进行移动
                right++;
            }

            if (right == (left + length_1)) return true;

            total[str_2[left] - 97]++;//将新元素添加进如总数组中，方便右边界移动
            left++;
        }
        return false;
    }
}