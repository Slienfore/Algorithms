package 回溯;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/12 9:18
 */
//131-分割回文串
public class Demo_131 {

    public static void main(String[] args) {

        String str = "aaa";
        System.out.println(partition(str));
    }

    static List<List<String>> res = new ArrayList<>();
    static LinkedList<String> path = new LinkedList<>();

    private static List<List<String>> partition(String s) {
        backTracking(0, s);
        recur(0, s);
        return result;
    }

    /**
     * 执行用时：11 ms, 在所有 Java 提交中击败了18.49%的用户<br>
     * 内存消耗：53 MB, 在所有 Java 提交中击败了18.13%的用户
     */
    private static void backTracking(int startIndex, String str) {

        //结束条件是：当其超过字符串的长度(因为是没有规定指定数量)
        if (startIndex >= str.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < str.length(); i++) {

            //如果是回文字符串的时候，需要进行拼接
            if (isPalindrome(str, startIndex, i)) {

                String strings = str.substring(startIndex, i + 1);
                path.add(strings);//拼接回文部分
            } else continue;

            backTracking(i + 1, str);

            path.removeLast();
        }
    }

    //判断回文字符串
    private static boolean isPalindrome(String str, int left, int right) {
        char[] chars = str.toCharArray();
        while (left < right) {
            if (chars[left] != chars[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    static List<List<String>> result = new ArrayList<>();
    static LinkedList<String> list = new LinkedList<>();

    /**
     * 执行用时：8 ms, 在所有 Java 提交中击败了61.53%的用户<br>
     * 内存消耗：54.1 MB, 在所有 Java 提交中击败了20.37%的用户
     */
    private static void recur(int startIndex, String str) {
        if (startIndex == str.length()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = startIndex; i < str.length(); i++) {
            String temp = str.substring(startIndex, i + 1);

            if (check(temp))
                list.add(temp);
            else continue;

            recur(i + 1, str);

            list.removeLast();
        }
    }

    private static boolean check(String str) {
        if (str.equals("")) return false;

        for (int left = 0, right = str.length() - 1; left <= right; left++, right--)
            if (str.charAt(left) != str.charAt(right))
                return false;

        return true;
    }


/*
    static Map<Integer, Set<String>> mapping = new HashMap<>();//记录的是回文子串的起始位置


    private static List<List<String>> partition_1(String s) {
        //采用中心扩展法进行搜寻该子串的所有回文子串
        int length = s.length();
        for (int center = 0; center < 2 * length - 1; center++) {
            int left = center / 2, right = center / 2 + center % 2;

            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                String temp = s.substring(left, right + 1);
                System.out.println(temp);

                mapping.put(left, temp);//将所有的回文子串使用扩散中心的下标存储起来

                left--;//向左扩散
                right++;//向右扩散
            }
        }
        back(0, length);

        return res;
    }


    private static void back(int collect, int length) {
        if (collect == length) {//当指针指向到字符串末尾时，说明收集完一个集合(记录收集的字符长度)
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < mapping.get(collect).length; i++) {//横向遍历该节点的所有回文子串起始点
            path.add(mapping.get(collect)[i]);

            int occupy = mapping.get(collect)[i].length();//当前该中心点回文子串的字符大小

            back(i + occupy, length);//该回文子串占据多少个字符就从起点出发，指针移动多少位

            path.removeLast();
        }

    }
*/

}
