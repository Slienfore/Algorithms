package 回溯;

import utils.uu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/15 15:34
 */
//140-单词拆分-II
public class Demo_140 {
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>() {{
            add("cat");
            add("cats");
            add("and");
            add("sand");
            add("dog");
        }};

        System.out.println(wordBreak(s, wordDict));
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {


        HashSet<String> set = new HashSet<>(wordDict);



        backTrack(0, set, s);

        return res;

    }

    static List<String> res = new ArrayList<>();
    static LinkedList<String> path = new LinkedList<>();

    /**
     * 回溯<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了94.20%的用户<br>
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了30.78%的用户
     */
    private static void backTrack(int startIndex, HashSet<String> set, String str) {
        if (startIndex == str.length()) {
            res.add(String.join(" ", path));

            return;
        }

        for (int end = startIndex; end < str.length(); ++end) {//切割点
            String sub = str.substring(startIndex, end + 1);

            if (!set.contains(sub))
                continue;

            path.add(sub);

            backTrack(end + 1, set, str);

            path.removeLast();
        }
    }


}
