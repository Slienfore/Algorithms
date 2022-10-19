package 动态规划;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/19 10:45
 */
// 300-最长递增子序列
public class Demo_300 {
    public static void main(String[] args) {
        // int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        // int[] nums = {0, 1, 0, 3, 2, 3};
        // int[] nums = {7, 7, 7, 7, 7, 7, 7};
        int[] nums = {0};
        System.out.println(lengthOfLIS1(nums));
    }

    /**
     * 动规<br>
     * 执行用时：55 ms, 在所有 Java 提交中击败了72.50%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了39.22%的用户<br>
     * 2022年10月19日  11:03:19
     */
    public static int lengthOfLIS1(int[] nums) {
        int len = nums.length;

        // 以当前元素结尾的最长子序列
        int[] dp = new int[len];
        Arrays.fill(dp, 1);// 每一个元素能够贡献

        int max = 1;// 1 <= nums.length, 所以必定包含 一个 1
        for (int idx = 1; idx < len; ++idx) {
            int val = nums[idx];// 当前位置结尾的元素

            for (int pre = 0; pre < idx; ++pre) {
                if (nums[pre] < val) {// 严格递增
                    dp[idx] = Math.max(dp[idx], dp[pre] + 1);// 如果该值比其还小, 说明能够拼接到之前的元素
                }
            }

            max = Math.max(dp[idx], max);
        }

        return max;
    }
}
