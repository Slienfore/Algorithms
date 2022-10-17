package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/19 18:53
 */
//39-组合总和
public class Demo_39 {
    public static void main(String[] args) {
        int[] candidates = {2, 5, 3};
        int target = 8;
        List<List<Integer>> list = combinationSum(candidates, target);
        System.out.println(list);
    }

    static List<List<Integer>> res = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    /**
     * 回溯<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了56.31%的用户<br>
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了5.05%的用户
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);//排序，避免不必要的递归

//        backTrack(0, target, candidates);
        recur(0, target, candidates);

        return res;
    }

    /**
     * 排序剪枝后避免不必要的递归<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.05%的用户<br>
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了19.64%的用户
     */
    //limit: 一个数字被选取的<数量不一样>才能进行组合
    private static void backTrack(int startIndex, int sum, int[] arr) {
        if (sum == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < arr.length && sum - arr[i] >= 0; i++) {//避免进入下一层递归
            path.add(arr[i]);

            backTrack(i, sum - arr[i], arr);//注意此时的是 startIndex 是可以无限制被重复选取的，但又不包含前面的个数

            path.removeLast();
        }

    }

    /**逆序递归<br>
    执行用时：2 ms, 在所有 Java 提交中击败了98.05%的用户<br>
    内存消耗：41.5 MB, 在所有 Java 提交中击败了19.35%的用户
    */
    private static void recur(int startIndex, int sum, int[] arr) {
        if (sum == 0) {
            res.add(new ArrayList<>(path));
            return;
        } else if (startIndex >= arr.length)
            return;

        recur(startIndex + 1, sum, arr);//循环递归到最后一项

        if (sum - arr[startIndex] >= 0) {//剪枝：避免不必要的循环
            path.add(arr[startIndex]);

            recur(startIndex, sum - arr[startIndex], arr);

            path.removeLast();
        }
    }

}
