package 滑动窗口;

import utils.uu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/28 9:41
 */
//438-找到字符串中所有字母异位词
public class Demo_438 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams_1(s, p));
        System.out.println(findAnagrams_2(s, p));
        System.out.println(findAnagrams_3(s, p));
        System.out.println(findAnagrams_4(s, p));
        uu.print("执行用时：\n" +
                "6 ms\n" +
                ", 在所有 Java 提交中击败了\n" +
                "89.95%\n" +
                "的用户\n" +
                "内存消耗：\n" +
                "42 MB\n" +
                ", 在所有 Java 提交中击败了\n" +
                "73.67%\n" +
                "的用户");
    }

    /**
     * 滑动窗口<br>
     * 执行用时：6 ms, 在所有 Java 提交中击败了89.95%的用户<br>
     * 内存消耗：42 MB, 在所有 Java 提交中击败了73.67%的用户<br>
     * 2022年04月07日  16:45:08
     */
    public static List<Integer> findAnagrams_4(String str, String tar) {
        int length = str.length(), width = tar.length();
        if (width > length)
            return new ArrayList<>();

        int[] hash = new int[26];
        for (char val : tar.toCharArray())
            ++hash[val - 'a'];

        int need = width;//统计窗口中的所需字符
        List<Integer> res = new ArrayList<>();

        for (int left = 0, right = 0; right < length; ++right) {
            int cur = str.charAt(right) - 'a';

            if (--hash[cur] >= 0)//说明是窗口中必须的元素
                --need;

            if (right - left + 1 == width) {
                int pre = str.charAt(left) - 'a';

                if (need == 0)
                    res.add(left);

                if (hash[pre] >= 0)//恢复窗口中的字符(若不存在重复多余的字符条件下)
                    ++need;

                ++hash[pre];

                ++left;//收缩窗口
            }
        }

        return res;
    }

    /**
     * 滑动窗口<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了93.95%的用户<br>
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了45.06%的用户
     */
    public static List<Integer> findAnagrams_3(String s, String p) {
        int length = s.length(), width = p.length();

        int[] hash_1 = new int[26], hash_2 = new int[26];

        for (char val : p.toCharArray())
            ++hash_2[val - 'a'];

        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0;//左右窗口

        while (right < length) {

            ++hash_1[s.charAt(right) - 'a'];//窗口移动

            if (right - left + 1 == width) {//到达了窗口大小
                if (isValid(hash_1, hash_2))//判断是否合理
                    res.add(left);

                --hash_1[s.charAt(left) - 'a'];
                ++left;//收缩窗口
            }

            right++;//移动右窗口
        }

        return res;
    }

    private static boolean isValid(int[] hash_1, int[] hash_2) {
        for (int cur = 0; cur < 26; ++cur)
            if (hash_1[cur] != hash_2[cur])
                return false;
        return true;
    }

    /**
     * 暴力<br>
     * 执行用时：512 ms, 在所有 Java 提交中击败了12.22%的用户<br>
     * 内存消耗：42 MB, 在所有 Java 提交中击败了60.56%的用户
     */
    public static List<Integer> findAnagrams_2(String s, String p) {
        int length_1 = s.length(), length_2 = p.length();

        if (length_1 < length_2)
            return new ArrayList<>();

        int[] hash = new int[26];
        for (int cur = 0; cur < length_2; ++cur)
            ++hash[p.charAt(cur) - 'a'];

        List<Integer> res = new ArrayList<>();
        for (int sub = 0; sub <= length_1 - length_2; ++sub) {
            String str = s.substring(sub, sub + length_2);
            int[] temp = new int[26];

            for (int cur = 0; cur < length_2; ++cur)
                ++temp[str.charAt(cur) - 'a'];

            if (isValid(temp, hash))
                res.add(sub);
        }

        return res;
    }


    /**
     * 滑动窗口<br>
     * 执行用时：7 ms, 在所有 Java 提交中击败了79.43%的用户<br>
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了49.99%的用户
     */
    public static List<Integer> findAnagrams_1(String s, String p) {
        int length_1 = s.length(), length_2 = p.length();

        if (length_1 < length_2) return new ArrayList<Integer>();

        List<Integer> res = new ArrayList<>();

        int[] nums_1 = new int[26], nums_2 = new int[26];

        for (int i = 0; i < length_2; i++) {//第一个窗口(多少个字母)
            ++nums_1[s.charAt(i) - 'a'];
            ++nums_2[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(nums_1, nums_2)) res.add(0);

        for (int i = 0; i < (length_1 - length_2); i++) {//总共有多少个窗口
            --nums_1[s.charAt(i) - 'a'];//释放左边界
            ++nums_1[s.charAt(i + length_2) - 'a'];//增加左边界

            if (Arrays.equals(nums_1, nums_2)) res.add(i + 1);
        }
        return res;
    }
}
