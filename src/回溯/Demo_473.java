package 回溯;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/11 21:04
 */
//473-火柴拼正方形
public class Demo_473 {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 2, 2, 2};
//        int[] nums = {3, 3, 3, 4};
//        int[] nums = {3, 3, 3, 3, 4};

        int[] nums = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
        System.out.println(makesquare(nums));
    }


    public static boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();

        if (sum % 4 != 0)
            return false;

        Arrays.sort(matchsticks);

        int target = sum / 4;//构成 4 条边的平均和
        if (matchsticks[matchsticks.length - 1] > target)
            return false;

        boolean[] branch = new boolean[matchsticks.length];

        return backTrack(0, 4, target, branch, matchsticks);
    }


    /**
     * 回溯<br>
     * 执行用时：1437 ms, 在所有 Java 提交中击败了5.01%的用户<br>
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了37.59%的用户
     */
    private static boolean backTrack(int curSum, int edge, int tar, boolean[] branch, int[] nums) {
        if (edge == 1)//剩下的一定可以组成
            return true;

        if (curSum == tar)//找到一条边后
            return backTrack(0, edge - 1, tar, branch, nums);

        for (int num = nums.length - 1; num >= 0; --num) {
            if (branch[num] || curSum + nums[num] > tar)//枝条去重
                continue;

            branch[num] = true;

            if (backTrack(curSum + nums[num], edge, tar, branch, nums))
                return true;

            branch[num] = false;

            while (num > 0 && nums[num] == nums[num - 1])//说明重复元素不可选
                --num;
        }

        return false;

    }


}
