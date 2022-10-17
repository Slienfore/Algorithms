package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/12 9:25
 */
//494-目标和
public class Demo_494 {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 1, 1, 1};
        int[] nums = {1, 2, 3, 4, 5};
        int target = 3;
        System.out.println(findTargetSumWays_1(nums, target));
        System.out.println(findTargetSumWays_2(nums, target));
    }


    /**
     * 动规(一维)<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了94.20%的用户<br>
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了48.08%的用户
     */
    public static int findTargetSumWays_2(int[] arr, int target) {
        int sum = 0;
        for (int val : arr)
            sum += val;

        int diff = sum - target;

        if (diff % 2 != 0 || diff < 0)
            return 0;

        int neg = diff / 2;

        int[] dp = new int[neg + 1];//组成该数有多少种方法
        dp[0] = 1;//组成后续不用进行赋值

        for (int num = 0; num < arr.length; ++num)
            for (int vol = neg; vol >= arr[num]; --vol)//从后向前遍历防止数据覆盖
                dp[vol] += dp[vol - arr[num]];

        return dp[neg];
    }


    /**
     * 动规<br>
     * 执行用时：6 ms, 在所有 Java 提交中击败了51.19%的用户<br>
     * 内存消耗：41 MB, 在所有 Java 提交中击败了13.13%的用户
     */
    public static int findTargetSumWays_1(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();

/*      数组总和: sum
        用来充当负数的正数之和: neg
        用来充当正数的正数之和: sum - neg
        则 (sum - neg) - neg = target
        --->>  neg = (sum - target) / 2*/

        int neg = sum - target;//负数之和确定了，那么整数之和也就确定了
        if (neg % 2 != 0 || neg < 0)
            return 0;

        int length = nums.length, maxVol = neg / 2;

        int[][] dp = new int[length + 1][maxVol + 1];//DP[num][vol]代表着组成 vol 这个数的所有组合总数
        dp[0][0] = 1;// 0 就是 0， 组成该数数量就是 1

        for (int num = 1; num <= length; ++num)//先遍历数组中的数
            for (int vol = 0; vol <= maxVol; ++vol) {
                if (vol >= nums[num - 1])//组成该数的组合总数
                    dp[num][vol] = dp[num - 1][vol] + dp[num - 1][vol - nums[num - 1]];
                else
                    dp[num][vol] = dp[num - 1][vol];
            }

        return dp[length][maxVol];
    }
}
