package 哈希表;

import utils.uu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/12 20:52
 */
//890-查找和替换模式
public class Demo_890 {
    public static void main(String[] args) {
        String[] words = {"abc", "deq", "mee", "aqq", "dkd", "ccc"};
        String pat = "abb";


        findAndReplacePattern(words, pat).forEach(System.out::println);
    }

    /**
     * 字符双向映射<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：40.1 MB, 在所有 Java 提交中击败了92.31%的用户<br>
     * 2022年06月12日  21:00:43
     */
    public static List<String> findAndReplacePattern(String[] words, String pat) {
        List<String> res = new ArrayList<>();

        for (String val : words)
            if (check(val, pat))
                res.add(val);

        return res;
    }

    private static boolean check(String word, String pat) {
        if (pat.length() != word.length()) return false;

        for (int cur = 0; cur < pat.length(); ++cur)
            if (pat.indexOf(pat.charAt(cur)) != word.indexOf(word.charAt(cur)))
                return false;

        return true;
    }
}
