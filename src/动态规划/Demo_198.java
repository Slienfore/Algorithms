package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/15 20:07
 */
//198-打家劫舍
public class Demo_198 {
    public static void main(String[] args) {
//        int[] nums = {2, 7, 9, 3, 1};
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(rob_1(nums));
        System.out.println(rob_2(nums));

    }

    /**
     * 动规(降维)<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了19.41%的用户
     */
    public static int rob_2(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return nums[0];

        int preNeigh = nums[0], neigh = Math.max(preNeigh, nums[1]);

        for (int hose = 2; hose < length; ++hose) {
            int temp = neigh;

            neigh = Math.max(neigh, nums[hose] + preNeigh);

            preNeigh = temp;

        }

        return neigh;
    }


    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39 MB, 在所有 Java 提交中击败了11.41%的用户
     */
    public static int rob_1(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return nums[0];

        int[] dp = new int[length + 1];//偷第 hose 间房囧的最高金额

        dp[1] = nums[0];//第一家只能偷自己
        dp[2] = Math.max(nums[0], nums[1]);//第二家比较两家最大值

        /*
        偷还是不偷？(相邻的一家不能偷)
        若偷这家：则偷 前、前 一家再加上自己的金额
        若不偷这家：则说明相邻一家金额比较大
        */
        for (int hose = 3; hose <= length; ++hose)
            dp[hose] = Math.max(dp[hose - 1], dp[hose - 2] + nums[hose - 1]);

        return dp[length];
    }

}
