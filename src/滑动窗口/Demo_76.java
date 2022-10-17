package 滑动窗口;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/24 11:02
 */
//76-最小覆盖子串
public class Demo_76 {
    public static void main(String[] args) {
//        String str1 = "ADOBECODEBANC", str2 = "ABC";
//        String str1 = "ABBBC", str2 = "BC";
        String str1 = "", str2 = "a";
        System.out.println(minWindow_1(str1, str2));
        System.out.println(minWindow_2(str1, str2));
        System.out.println(minWindow_3(str1, str2));
    }

    /**滑动窗口<br>
     执行用时：3 ms, 在所有 Java 提交中击败了76.70%的用户<br>
     内存消耗：41.6 MB, 在所有 Java 提交中击败了57.62%的用户<br>
     2022年04月07日  22:47:41*/
    public static String minWindow_3(String str, String tar) {
        int length = str.length(), tarLen = tar.length();
        if (length < tarLen)
            return "";

        int[] hash = new int[128];

        for (char val : tar.toCharArray())
            ++hash[val];

        int sub = 0, min = Integer.MAX_VALUE;//截取位置最小长度

        int need = tarLen;//所需字符
        for (int left = 0, right = 0; right < length; ++right) {

            if (--hash[str.charAt(right)] >= 0)//若变成负数说明该值为多于或者重复的
                --need;

            while (need == 0) {//缩减窗口，减少长度

                if (right - left + 1 < min) {
                    min = Math.min(min, right - left + 1);//窗口进行缩减
                    sub = left;
                }


                if (hash[str.charAt(left)]++ >= 0)//目标字符吐出去后，缺失了
                    ++need;

                ++left;
            }
        }

        return min == Integer.MAX_VALUE ? "" : str.substring(sub, sub + min);
    }


    /**
     * 滑动窗口(加法)<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了97.46%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了60.01%的用户
     */
    public static String minWindow_2(String s, String t) {
        int length1 = s.length(), length2 = t.length();
        if (length1 < length2)
            return "";

        int[] hash = new int[128];

        for (char val : t.toCharArray())
            ++hash[val];

        int[] window = new int[128];//窗口中包含的字符
        int subLen = Integer.MAX_VALUE, sub = 0;

        for (int left = 0, right = 0, dis = 0; right < length1; ++right) {//缩小作用域
            char rVal = s.charAt(right);

            if (hash[rVal] == 0)//说明目标子串不包含该字符
                continue;

            if (window[rVal] < hash[rVal])//说明目标子串包含该元素
                ++dis;

            ++window[rVal];//窗口元素增加

            while (dis == length2) {//窗口中已经全部包含目标子串的字符了
                if (right - left < subLen) {
                    subLen = right - left + 1;
                    sub = left;
                }

                char lVal = s.charAt(left);
                if (hash[lVal] == 0) {//说明目标子串不包含该元素
                    ++left;
                    continue;
                }

                if (window[lVal] == hash[lVal])//左边界收缩,将不再包含该字符
                    --dis;

                --window[lVal];
                ++left;
            }
        }
        return subLen == Integer.MAX_VALUE ? "" : s.substring(sub, sub + subLen);
    }

    /**
     * 滑动窗口(减法)<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了97.46%的用户<br>
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了49.14%的用户
     */
    public static String minWindow_1(String s, String t) {
        int length1 = s.length(), length2 = t.length();
        if (length1 < length2)
            return "";

        int[] hash = new int[128];

        for (char val : t.toCharArray())//记录 T 子串出现的字符数
            ++hash[val];

        int need = length2;//所需元素个数
        int right = 0, left = 0;//左、右窗口边界

        int subLen = Integer.MAX_VALUE, sub = 0;//截取位置

        while (right < length1) {

            if (hash[s.charAt(right)] > 0)//遇到所需元素(只有指定子串的个数才会进行统计)
                --need;

            --hash[s.charAt(right)];//将扫描的字符装进窗口
            ++right;// "右窗口" 边界右移

            while (need == 0) {//已经遇到所有所需元素(进行缩小窗口)

                if (right - left < subLen) {
                    subLen = right - left;//更新最小长度
                    sub = left;
                }

                if (hash[s.charAt(left)] == 0)//更新窗口窗口中还差一个元素
                    ++need;

                ++hash[s.charAt(left)];
                ++left;// "左窗口" 边界右移
            }
        }

        return subLen == Integer.MAX_VALUE ? "" : s.substring(sub, sub + subLen);
    }
}
