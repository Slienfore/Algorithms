package 回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/11 10:58
 */
//77-组合
public class Demo_77 {
    public static void main(String[] args) {
        int n = 4, k = 2;
        for (List<Integer> list : combine(n, k)) {
            System.out.println(list);
        }
    }


    private static List<List<Integer>> combine(int n, int k) {
        dfsRecur_3(1, n, k);
//        recur_4(1, k, n);
        return res;
    }

    static LinkedList<Integer> path = new LinkedList<>();
    static List<List<Integer>> res = new ArrayList<>();

    /**
     * 回溯<br>
     * 执行用时：14 ms, 在所有 Java 提交中击败了55.81%的用户<br>
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了41.09%的用户
     */
    private static void dfsRecur_1(int start, int num, int k) {
        if (path.size() == k) {//若达到了要求的数字
            res.add(new ArrayList<>(path));

            return;//返回上一层递归
        }

        for (int i = start; i <= num; i++) {//递归的宽度
            path.add(i);
            dfsRecur_1(i + 1, num, k);
            path.removeLast();//递归回溯
        }
    }

    /**
     * 回溯-剪枝<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.99%的用户<br>
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了5.05%的用户
     */
    private static void dfsRecur_3(int start, int num, int limit) {
        if (path.size() == limit) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= num; i++) {
            int need = limit - path.size();//还需要的个数
            int remain = num - need;//还剩下可以使用的个数

            if (i <= remain + 1) {//如果深度达到最大值，那么就只有本身了
                path.add(i);

                dfsRecur_3(i + 1, num, limit);

                path.removeLast();
            }
        }

    }


    /**
     * 回溯-剪枝<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户<br>
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了31.41%的用户
     */
    private static void dfsRecur_2(int start, int num, int k) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= num - (k - path.size()) + 1; i++) {//剪枝(总数 - 还需要的个数 + 1)当前位置
            path.add(i);
            dfsRecur_2(i + 1, num, k);
            path.removeLast();//递归回溯
        }
    }

    /**逆序递归<br>
    执行用时：1 ms, 在所有 Java 提交中击败了99.99%的用户<br>
    内存消耗：42.7 MB, 在所有 Java 提交中击败了9.48%的用户
     */
    private static void recur_4(int circle, int limit, int maxNum) {
        if (path.size() == limit) {
            res.add(new ArrayList<>(path));
            return;
        }
        //如果循环到达最大约束条件的时候停止，且将不符合深度的枝条减掉
        if (circle > maxNum || limit - path.size() > maxNum - circle + 1)
            return;

        recur_4(circle + 1, limit, maxNum);//逆序循环

        path.add(circle);
        recur_4(circle + 1, limit, maxNum);//往后面进行搜索符合条件的组合
        path.removeLast();

    }
}
