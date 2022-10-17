package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 16:55
 */
//674-最长连续递增序列
public class Demo_674 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
//        int[] nums = {2, 2, 2, 2, 2};
        System.out.println(findLengthOfLCIS_1(nums));
        System.out.println(findLengthOfLCIS_2(nums));
    }

    /**
     * 动规<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了33.88%的用户<br>
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了67.65%的用户
     */
    private static int findLengthOfLCIS_2(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return 1;

        int[] dp = new int[length];//以当前点结尾连续递增序列最大值
        dp[0] = 1;
        int maxLen = dp[0];

        for (int cur = 1; cur < length; ++cur) {
            if (nums[cur] > nums[cur - 1])//拼接上当前点是否会严格递增
                dp[cur] = dp[cur - 1] + 1;
            else
                dp[cur] = 1;

            maxLen = Math.max(maxLen, dp[cur]);
        }

        return maxLen;
    }

    /**
     * 滑动窗口<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.78%的用户<br>
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了62.82%的用户
     */
    public static int findLengthOfLCIS_1(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return 1;

        int maxLen = 1;
        for (int left = 0; left < length; ++left) {
            int right = left + 1;

            while (right < length && nums[right] > nums[right - 1])//维护严格递增窗口
                ++right;

            maxLen = Math.max(maxLen, right - left);

            left = right - 1;//该段严格递增了
        }

        return maxLen;
    }
}
