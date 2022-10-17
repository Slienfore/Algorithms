package 哈希表;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/12 15:49
 */
//205-同构字符串
public class Demo_205 {
    public static void main(String[] args) {
//        String s = "egg", t = "add";
//        String s = "paper", t = "title";
//        String s = "badc", t = "baba";
        String s = "13", t = "42";
        System.out.println(isIsomorphic_1(s, t));
        System.out.println(isIsomorphic_2(s, t));
    }

    /**
     * 字符哈希<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了94.36%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了60.58%的用户<br>
     * 2022年06月12日  16:14:33
     */
    public static boolean isIsomorphic_1(String s, String t) {
        char[] hash1 = new char[128], hash2 = new char[128];

        for (int cur = 0; cur < s.length(); ++cur) {
            char val1 = s.charAt(cur), val2 = t.charAt(cur);

            if (hash1[val1] == '\u0000' && hash2[val2] == '\u0000') {//若两个字符均还未映射
                hash1[val1] = val2;//互相映射
                hash2[val2] = val1;//互相映射
            } else if (hash1[val1] != val2)//映射内容不相同
                return false;
        }

        return true;
    }

    /**
     * indexOf<br>
     * 执行用时：10 ms, 在所有 Java 提交中击败了75.78%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了55.81%的用户<br>
     * 2022年06月12日  16:23:08
     */
    public static boolean isIsomorphic_2(String str1, String str2) {
        for (int cur = 0; cur < str1.length(); ++cur)
            if (str1.indexOf(str1.charAt(cur)) != str2.indexOf(str2.charAt(cur)))//映射关系之前已经确立了
                return false;

        return true;
    }
}
