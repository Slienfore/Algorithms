package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/11 16:22
 */
//416-分割等和子集
public class Demo_416 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
//        int[] nums = {1, 2, 3, 5};
//        int[] nums = {4, 4, 4, 4, 4, 4, 4, 4, 8, 8, 8, 8, 8, 8, 8, 8, 12, 12, 12, 12, 12, 12, 12, 12, 16, 16, 16, 16, 16, 16, 16, 16, 20, 20, 20, 20, 20, 20, 20, 20, 24, 24, 24, 24, 24, 24, 24, 24, 28, 28, 28, 28, 28, 28, 28, 28, 32, 32, 32, 32, 32, 32, 32, 32, 36, 36, 36, 36, 36, 36, 36, 36, 40, 40, 40, 40, 40, 40, 40, 40, 44, 44, 44, 44, 44, 44, 44, 44, 48, 48, 48, 48, 48, 48, 48, 48, 52, 52, 52, 52, 52, 52, 52, 52, 56, 56, 56, 56, 56, 56, 56, 56, 60, 60, 60, 60, 60, 60, 60, 60, 64, 64, 64, 64, 64, 64, 64, 64, 68, 68, 68, 68, 68, 68, 68, 68, 72, 72, 72, 72, 72, 72, 72, 72, 76, 76, 76, 76, 76, 76, 76, 76, 80, 80, 80, 80, 80, 80, 80, 80, 84, 84, 84, 84, 84, 84, 84, 84, 88, 88, 88, 88, 88, 88, 88, 88, 92, 92, 92, 92, 92, 92, 92, 92, 96, 96, 96, 96, 96, 96, 96, 96, 97, 99};
        System.out.println(canPartition_2(nums));
    }

    /**
     * 01-背包<br>
     * 执行用时：21 ms, 在所有 Java 提交中击败了66.45%的用户<br>
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了37.81%的用户
     */
    public static boolean canPartition_2(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        if (sum % 2 != 0)
            return false;//说明不能够进行划分相同的两部分

        int maxVol = sum / 2;//各数总和--->最大背包容量

        int[] dp = new int[maxVol + 1];//当总和为 vol 时，最大凑成子集总和

        for (int pro = 0; pro < nums.length; ++pro)
            for (int vol = maxVol; vol >= nums[pro]; --vol)//重量是nums[pro], 价格也是nums[pro]
                dp[vol] = Math.max(dp[vol], dp[vol - nums[pro]] + nums[pro]);


        return dp[maxVol] == maxVol;
    }


    public static boolean canPartition_1(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        if (sum % 2 != 0)
            return false;

        int tar = sum / 2;
        Arrays.sort(nums);

        if (nums[nums.length - 1] > tar)
            return false;

        boolean[] branch = new boolean[nums.length];

        return backTrack(0, tar, nums, branch);

    }


    private static boolean backTrack(int curSum, int tar, int[] nums, boolean[] branch) {
        if (curSum == tar)//sum - (N - 1)*tar = tar
            return true;

        for (int val = nums.length - 1; val >= 0; --val) {
            if (branch[val] || curSum + nums[val] > tar)//树枝去重、避免值过大
                continue;

            branch[val] = true;

            if (backTrack(curSum + nums[val], tar, nums, branch))
                return true;

            branch[val] = false;

            while (val > 0 && nums[val] == nums[val - 1])//说明重复元素不可用
                val--;
        }

        return false;
    }
}
