package 回溯;

import utils.uu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/17 17:52
 */
//22-括号生成
public class Demo_22 {
    public static void main(String[] args) {
        int num = 8;
//        System.out.println(generateParenthesis_1(num));
        System.out.println(generateParenthesis_2(num));
    }

    public static List<String> generateParenthesis_2(int n) {
        backTrack("", 0, 0, n);
        return res;
    }

    /**
     * 回溯<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了76.28%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了62.98%的用户<br>
     * 2022年03月31日  18:42:08
     */
    private static void backTrack(String path, int left, int right, int limit) {
        if (left == limit && left == right) {//左\右括号的数量相等
            res.add(path);
            return;
        }

        if (left < limit)
            backTrack(path + "(", left + 1, right, limit);//拼接左括号

        if (right < left)
            backTrack(path + ")", left, right + 1, limit);//拼接右括号
    }

    static List<String> res = new ArrayList<>();

    /**
     * 回溯<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了75.72%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了42.90%的用户
     */
    public static List<String> generateParenthesis_1(int n) {
        dfs("", 0, 0, n);

        return res;
    }

    private static void dfs(String str, int leftBra, int rightBra, int num) {//使用String则不会加入缓存中，回退的时候直接清除
        if (leftBra == num && rightBra == num) {//左右括号已经生成了
            res.add(str);
            return;
        } else if (leftBra > num || rightBra > num)//出口
            return;

        dfs(str + "(", leftBra + 1, rightBra, num);//加上左括号

        if (leftBra > rightBra)//只有左括号的数量大于右括号的数量才可以进行拼接右括号
            dfs(str + ")", leftBra, rightBra + 1, num);//加上右括号
    }

}
