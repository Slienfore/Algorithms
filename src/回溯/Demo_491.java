package 回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/12 19:34
 */
//491-递增子序列
public class Demo_491 {
    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        System.out.println(findSubsequences(nums));
    }

    static LinkedList<Integer> path = new LinkedList<>();
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> findSubsequences(int[] nums) {

//        backTracking(0, nums);

        recur_1(0, Integer.MIN_VALUE, nums);
        return res;
    }

    /**
     * 执行用时：4 ms, 在所有 Java 提交中击败了83.38%的用户<br>
     * 内存消耗：44.1 MB, 在所有 Java 提交中击败了94.09%的用户
     */
    private static void backTracking(int startIndex, int[] nums) {
        //要求需要输出两个及以上的
        if (path.size() > 1) res.add(new ArrayList<>(path));

        HashSet<Integer> set = new HashSet<>();//仅仅是负责一层的结点

        for (int i = startIndex; i != nums.length; i++) {//递归的宽度
            /*判断了两个条件
            1: 当前素比队尾元素小的时候
            2: 如果再本层的哈希表中存在结点的时候(说明重复了)
            排除不必要的条件
            */
            if ((!path.isEmpty() && nums[i] < path.getLast()) || set.contains(nums[i])) continue;

            path.add(nums[i]);
            set.add(nums[i]);

            backTracking(i + 1, nums);

            path.removeLast();
        }
    }



    private static void recur(int startIndex, int[] nums) {
        if (path.size() > 1)
            res.add(new ArrayList<>(path));

        HashSet<Integer> set = new HashSet<>();//每一层建立一个去重Set

        for (int i = startIndex; i < nums.length; i++) {
            if (set.contains(nums[i]) || !path.isEmpty() && nums[i] < path.getLast())
                continue;

            set.add(nums[i]);

            path.add(nums[i]);

            recur(i + 1, nums);

            path.removeLast();

        }

    }

    /**回溯递归
     执行用时：3 ms, 在所有 Java 提交中击败了93.22%的用户<br>
     内存消耗：47.2 MB, 在所有 Java 提交中击败了22.83%的用户
     */
    private static void recur_1(int cur, int preVal, int[] nums) {
        if (cur == nums.length) {
            if (path.size() > 1)
                res.add(new ArrayList<>(path));
            return;
        }

        if (nums[cur] >= preVal) {//纵向遍历
            path.add(nums[cur]);

            recur_1(cur + 1, nums[cur], nums);

            path.removeLast();
        }
        /*如果当前元素与上层元素相同时，如果与上层元素相同，如果在《不选择当前元素》的情况下，继续递归下去，可能造成重复，
        当返回上一层的时候如果也《不选择当前元素》会造成重新搜索到该节点去，《两个结点相同》，本来撤销使用的，此时就《再一次启用》
        所以当结点与前结点相同的情况下，如果在不启用该结点的情况下，继续递归下去会造成重复返回上一层递归的时候再次启用该节点
        ---->上层启用 + 下层不启用 == 上层不启用 + 下层启用
        */
        if (nums[cur] != preVal)
            recur_1(cur + 1, preVal, nums);
    }


}
