package 哈希表;

import utils.uu;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/17 7:54
 */
//720-词典中最长的单词
public class Demo_720 {
    public static void main(String[] args) {
        String[] nums = {"w", "wo", "wor", "worl", "world"};
//        String[] nums = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
//        String[] nums = {"yo", "ew", "fc", "zrc", "yodn", "fcm", "qm", "qmo", "fcmz", "z", "ewq", "yod", "ewqz", "y"};
        System.out.println(longestWord_1(nums));
        System.out.println(longestWord_2(nums));
    }

    /**
     * 哈希表(剪枝)<br>
     * 执行用时：6 ms, 在所有 Java 提交中击败了97.59%的用户<br>
     * 内存消耗：41 MB, 在所有 Java 提交中击败了48.08%的用户
     */
    public static String longestWord_2(String[] words) {
        if (words.length == 1)
            return "";
        Set<String> set = new HashSet<>() {{
            addAll(Arrays.asList(words));
        }};

        int max = Integer.MIN_VALUE;//获得的长度最大
        String res = "";

        for (String str : words) {
            int length = str.length();
            //长度相等字典序较大、长度较小
            if ((length == max && str.compareTo(res) > 0) || (length < max))
                continue;

            boolean flag = true;
            for (int split = 1; split < length; ++split)//切割
                if (!set.contains(str.substring(0, split))) {
                    flag = false;
                    break;
                }
            if (!flag)//说明不存在
                continue;

            if (length > max)//重置
                max = length;

            res = str;
        }

        return res;
    }


    /**
     * 暴力<br>
     * 执行用时：34 ms, 在所有 Java 提交中击败了10.98%的用户<br>
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了15.23%的用户
     */
    public static String longestWord_1(String[] words) {
        PriorityQueue<String> queue = new PriorityQueue<>();//维持字典序

        Set<String> set = Arrays.stream(words).collect(Collectors.toSet());

        int max = Integer.MIN_VALUE;

        for (String str : words) {
            int length = str.length();
            boolean flag = true;

            for (int i = 1; i <= length - 1; ++i)//该单词是逐步添加的
                if (!set.contains(str.substring(0, i))) {//不存在缺一个字符的单词
                    flag = false;
                    break;
                }

            if (flag && length == max) {//相等添加
                queue.add(str);
            } else if (flag && length > max) {//大于重置
                queue.clear();
                queue.add(str);
                max = length;
            }
        }

        return queue.isEmpty() ? "" : queue.poll();
    }
}
