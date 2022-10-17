package 动态规划;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/14 20:25
 */
//377组合总和-IV
public class Demo_377 {
    public static void main(String[] args) {
/*        int[] nums = new int[]{1, 2, 3};
        int tar = 4;*/

        int[] nums = {9};
        int tar = 3;
        System.out.println(combinationSum4_1(nums, tar));
    }


    /**
     * 动规(一维)<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.73%的用户<br>
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了51.93%的用户
     */
    public static int combinationSum4_1(int[] nums, int target) {
        //排列数
        int[] dp = new int[target + 1];
        dp[0] = 1;//凑成 和 为 0 的方案总是为 1

        for (int sum = 1; sum <= target; ++sum)//先遍历价格，后遍历硬币
            for (int val : nums)
                if (val <= sum)
                    dp[sum] += dp[sum - val];

        return dp[target];
    }
}
