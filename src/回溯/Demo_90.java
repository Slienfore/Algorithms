package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/12 15:41
 */
//90-子集——II
public class Demo_90 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(subsetsWithDup(nums));
    }

    static LinkedList<Integer> path = new LinkedList<>();
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            res.add(new ArrayList<>(path));
            return res;
        }

        //因为集合中包含重复元素，所以需要排序方便树层去重
        Arrays.sort(nums);

        backTracking(0, nums);
        return res;
    }

    /**
     * 树层去重<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.44%的用户<br>
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了63.86%的用户
     */
    private static void backTracking(int startIndex, int[] nums) {
        res.add(new ArrayList<>(path));

        for (int i = startIndex; i < nums.length; i++) {
            //进行树层去重
            if (i > startIndex && nums[i] == nums[i - 1]) continue;//去重的是下一个结点而不是 0

            path.add(nums[i]);

            backTracking(i + 1, nums);//i + 1 进行树枝去重

            path.removeLast();
        }
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.45%的用户<br>
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了21.67%的用户
     */
    private static void recur(int startIndex, int[] nums) {
        res.add(new ArrayList<>(path));//子集就是没有任何条件限制，直接遍历到叶子结点

        for (int i = startIndex; i < nums.length; i++) {

            if (i > startIndex && nums[i] == nums[i - 1])//i++是横向递归，而index是纵向递归
                continue;

            path.add(nums[i]);

            recur(i + 1, nums);

            path.removeLast();

        }

    }

    /**
     * 逆序递归<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.45%的用户<br>
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了15.52%的用户
     */
    private static void recur_1(boolean choose, int startIndex, int[] nums) {
        if (startIndex == nums.length) {//结束条件是递归到末尾
            res.add(new ArrayList<>(path));
            return;
        }

        recur_1(false, startIndex + 1, nums);//横向遍历每一层到最后一个结点

        if (!choose && startIndex > 0 && nums[startIndex] == nums[startIndex - 1])
            return;

        path.add(nums[startIndex]);

        //枝条递归-->每一个枝条递归下去
        recur_1(true, startIndex + 1, nums);//纵向遍历到下一层

        path.removeLast();
    }

}
