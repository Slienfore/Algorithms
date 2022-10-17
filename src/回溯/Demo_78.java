package 回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/12 15:21
 */
//78-子集
public class Demo_78 {

    public static void main(String[] args) {
        int[] nums = {0};
        System.out.println(subsets(nums));
    }

    static List<List<Integer>> res = new ArrayList<>();
    static LinkedList<Integer> path = new LinkedList<>();

    public static List<List<Integer>> subsets(int[] nums) {

        if (nums.length == 0) {//若为空则不必要进行
            res.add(new ArrayList<>(path));
            return res;
        }

        subsetsHelp(0, nums);
        return res;
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了86.87%的用户
     */
    private static void subsetsHelp(int startIndex, int[] nums) {
        res.add(new ArrayList<>(path));//遍历所有结点

        if (startIndex >= nums.length) return;//也可以不加，因为本身就是遍历到叶子结点后进行返回上一层

        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);

            subsetsHelp(i + 1, nums);

            //回溯
            path.removeLast();
        }
    }


    private static void recur(int startIndex, int[] nums) {
        res.add(new ArrayList<>(path));

        for (int i = startIndex; i < nums.length; i++) {

            path.add(nums[i]);

            recur(i + 1, nums);

            path.removeLast();

        }

    }
}
