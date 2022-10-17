package 哈希表;

import utils.uu;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/12 16:32
 */
//290-单词规律
public class Demo_290 {
    public static void main(String[] args) {
//        String pat = "abba", str = "dog cat cat dog";//true
//        String pat = "aaa", str = "aa aa aa aa";//false
        String pat = "he", str = "unit";//false
        System.out.println(wordPattern_1(pat, str));

        System.out.println(wordPattern_2(pat, str));
    }

    /**
     * 哈希<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了46.75%的用户<br>
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了56.07%的用户<br>
     * 2022年06月12日  20:45:46
     */
    public static boolean wordPattern_2(String pat, String str) {
        Map<Character, String> map1 = new HashMap<>();//映射模式字符串
        Map<String, Character> map2 = new HashMap<>();//映射子串

        String[] word = str.split(" ");//直接替换空格

        if (pat.length() != word.length) return false;//说明对应单词数量不匹配, 无法一一对应

        for (int cur = 0; cur < pat.length(); ++cur) {
            char val = pat.charAt(cur);
            String strVal = word[cur];

            if (map1.containsKey(val) && !map1.get(val).equals(strVal) ||
                    map2.containsKey(strVal) && map2.get(strVal) != val)
                return false;

            map1.put(val, strVal);
            map2.put(strVal, val);
        }

        return true;
    }


    /**
     * 字符哈希<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了46.75%的用户<br>
     * 内存消耗：39 MB, 在所有 Java 提交中击败了91.83%的用户<br>
     * 2022年06月12日  17:21:45
     */
    public static boolean wordPattern_1(String pat, String str) {
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();

        int pos = 0;

        for (int cur = 0; cur < pat.length(); ++cur) {//字符映射字符串
            if (pos == str.length()) return false;//模式串过长，不能满足匹配

            char val1 = pat.charAt(cur);

            int pace = pos;
            while (pace < str.length() && str.charAt(pace) != ' ') ++pace;

            String val2 = str.substring(pos, pace);//遇到空格停下来的

            //只有进行映射之后才能进行比较
            if (map1.containsKey(val1) && !map1.get(val1).equals(val2) ||
                    map2.containsKey(val2) && map2.get(val2) != val1)
                return false;

            map1.put(val1, val2);//互相映射
            map2.put(val2, val1);//互相映射

            pos = Math.min(str.length(), pace + 1);//从空格后开始 && 不能越界
        }

        return pos == str.length();//判断是否扫描完成
    }
}
