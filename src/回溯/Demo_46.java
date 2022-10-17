package 回溯;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/12 16:04
 */
//46-全排列
public class Demo_46 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
//        int[] nums = {0, 1};
        System.out.println(permute(nums));
    }

    static LinkedList<Integer> path = new LinkedList<>();
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];

//        permuteHelp(nums, visited);
        for (int val : nums)
            path.add(val);

        recur_2(0, nums);
//        recur_2_(0, 0, nums);

//        recur_1(0, visited, nums);
        return res;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了82.86%的用户<br>
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了62.21%的用户
     */
    private static void permuteHelp(int[] nums, boolean[] visited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }


        for (int i = 0; i < nums.length; i++) {//递归宽度

            if (visited[i]) continue;//树枝去重(一条树枝不能被使用重复)

            visited[i] = true;
            path.add(nums[i]);
            permuteHelp(nums, visited);

            visited[i] = false;
            path.removeLast();
        }
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了80.09%的用户<br>
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了19.67%的用户
     */
    private static void recur(boolean[] branch, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }


        for (int i = 0; i < nums.length; i++) {//从左往右递归到最后一个
            if (branch[i])//如果枝条相同
                continue;

            path.add(nums[i]);
            branch[i] = true;//当前结点已经使用

            recur(branch, nums);

            path.removeLast();
            branch[i] = false;
        }

    }

    /**
     * 逆序递归<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了80.09%的用户<br>
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了5.13%的用户
     */
    private static void recur_1(int startIndex, boolean[] branch, int[] nums) {
        if (startIndex == nums.length) {
            if (path.size() == nums.length)
                res.add(new ArrayList<>(path));

            return;
        }

        recur_1(startIndex + 1, branch, nums);//递归到最后一项

        if (!branch[startIndex]) {//跳过已经重复的结点
            path.add(nums[startIndex]);
            branch[startIndex] = true;

            recur_1(0, branch, nums);//深入下一层结点

            branch[startIndex] = false;
            path.removeLast();
        }
    }

    /**
     * 填充法<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了80.09%的用户<br>
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了5.13%的用户
     */
    private static void recur_2(int fillPosition, int[] nums) {
        if (fillPosition == nums.length) {//当填入最后一个数字的时候
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = fillPosition; i < nums.length; i++) {
            Collections.swap(path, fillPosition, i);//寻找剩余的同层结点(即横向遍历所有同层结点)进行当前位置的两两交换

            recur_2(fillPosition + 1, nums);//已经填入了一个数字

            Collections.swap(path, fillPosition, i);//填完之后回退
        }

    }

    /**逆序递归填充法<br>
    执行用时：1 ms, 在所有 Java 提交中击败了80.09%的用户<br>
    内存消耗：41.5 MB, 在所有 Java 提交中击败了16.99%的用户
    */
    private static void recur_2_(int level, int pos, int[] nums) {
        if (pos == nums.length) {
            if (level == nums.length)
                res.add(new ArrayList<>(path));
            return;
        }

        recur_2_(level, pos + 1, nums);//从左向右遍历到最后一个

        Collections.swap(path, level, pos);//将当前的值填入到 level 起始位置

        recur_2_(level + 1, level + 1, nums);//递归到那一层就从那一层的位置开始搜寻后面的数字进行交换

        Collections.swap(path, level, pos);//撤销填入位置

    }
}
