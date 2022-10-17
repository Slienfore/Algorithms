package 回溯;

import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/16 13:56
 */
//494-目标和
public class Demo_494 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1};
//        int[] nums = {1, 1, 1, 1, 1};
        int target = 0;
        System.out.println(findTargetSumWays(nums, target));
    }

    static int res = 0;

    public static int findTargetSumWays(int[] nums, int target) {

//        helper(0, nums, target, nums.length);
//        recur(0, nums, target);
        return recur_1(0, 0, nums, target);
//        return res;
    }

    /**
     * 抽象成一棵带有”正负号“的数<br>
     * 执行用时：856 ms, 在所有 Java 提交中击败了5.01%的用户<br>
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了91.46%的用户
     */
    private static void helper(int index, int[] nums, int target, int length) {
        if (index == length) {//当达到了最后一位数字就需要返回
            if (target == 0) res++;

            return;
        }

        for (int i = 0; i != 2; i++) { //只有两个符号
            if (i == 0) {
                helper(index + 1, nums, target - nums[index], length);
            } else helper(index + 1, nums, target - (-nums[index]), length);
        }

    }

    /**
     * 正负反转<br>
     * 执行用时：854 ms, 在所有 Java 提交中击败了5.03%的用户<br>
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了23.90%的用户
     */
    private static void recur(int index, int[] nums, int target) {
        if (index == nums.length) {
            if (target == 0)
                res++;
            return;
        }

        for (int i = 0; i < 2; i++) {
            nums[index] = -nums[index];//第一个为 - 第二个为 +

            recur(index + 1, nums, target - nums[index]);

        }

    }

    /**DFS爆搜<br>
    执行用时：540 ms, 在所有 Java 提交中击败了12.00%的用户<br>
   内存消耗：39 MB, 在所有 Java 提交中击败了30.78%的用户
    */
    private static int recur_1(int depth, int cur, int[] nums, int target) {
        if (depth == nums.length)
            return cur == target ? 1 : 0;

        int left = recur_1(depth + 1, cur + nums[depth], nums, target);
        int right = recur_1(depth + 1, cur - nums[depth], nums, target);

        return left + right;

    }

}
