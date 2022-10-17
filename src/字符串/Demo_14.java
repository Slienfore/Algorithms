package 字符串;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/27 13:56
 */
//14-最长公共前缀
public class Demo_14 {
    public static void main(String[] args) {
//        String[] strs = {"flower", "flow", "flight"};
        String[] strs = {"dog", "racecar", "car"};
//        String[] strs = {"asdsd"};

        System.out.println(longestCommonPrefix_1(strs));
        System.out.println(longestCommonPrefix_2(strs));

    }

    /**
     * 二分<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了68.78%的用户<br>
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了21.85%的用户
     */
    public static String longestCommonPrefix_2(String[] strs) {
        if (strs.length == 1)
            return strs[0];

        int minLen = Integer.MAX_VALUE;
        for (String val : strs)
            minLen = Math.min(minLen, val.length());//公共前缀一定包含于最短字串

        int left = 0, right = minLen;//搜索公共前缀的长度
        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (isPrefix(mid, strs))//若该长度包含公共前缀的话
                left = mid;
            else
                right = mid - 1;
        }

        return strs[0].substring(0, left);
    }

    private static boolean isPrefix(int prefixLen, String[] arr) {
        int length = arr.length;
        String base = arr[0];

        for (int cur = 1; cur < length; ++cur) {
            String curStr = arr[cur];
            for (int scan = 0; scan < prefixLen; ++scan)
                if (base.charAt(scan) != curStr.charAt(scan))
                    return false;
        }

        return true;
    }

    /**
     * 纵向扫描<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了68.78%的用户<br>
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了57.90%的用户
     */
    public static String longestCommonPrefix_1(String[] strs) {
        int length = strs.length;
        if (length == 1)//一个单词所有字符就是公共前缀
            return strs[0];

        //公共前缀则是所有字符串都有的
        StringBuilder res = new StringBuilder();

        String base = strs[0];
        for (int cur = 0; cur < base.length(); ++cur) {//以第一个字符为基准
            char tar = base.charAt(cur);
            int right = 1;//扫描的位置

            while (right < length && cur < strs[right].length() && strs[right].charAt(cur) == tar)
                ++right;

            if (right != length)//说明已经没有公共前缀了(不再连续)
                return res.toString();

            res.append(base.charAt(cur));//扫描到最后一个字符
        }

        return res.toString();
    }
}
