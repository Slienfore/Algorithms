package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 11:21
 */
//45-跳跃游戏-II
public class Demo_45 {
    public static void main(String[] args) {
//        int[] nums = {2, 3, 1, 1, 4};
        int[] nums = {2, 3, 0, 1, 4};
        System.out.println(jump_1(nums));
        System.out.println(jump_2(nums));
    }

    /**
     * 动规+双指针+贪心<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.16%的用户<br>
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了52.67%的用户
     */
    public static int jump_2(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];//跳跃到当前位置使用的最小次数

        for (int begin = 0, cur = 1; cur < length; ++cur) {

            while (begin + nums[begin] < cur)//选取刚好跳跃到达该点的最远位置
                ++begin;

            dp[cur] = dp[begin] + 1;//从起点达到当前位置花费的次数
        }

        return dp[length - 1];
    }


    /**
     * 动规<br>
     * 执行用时：28 ms, 在所有 Java 提交中击败了20.79%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了66.31%的用户
     */
    public static int jump_1(int[] nums) {
        int length = nums.length;

        int[] dp = new int[length];//跳到当前点最小次数

        for (int cur = 1; cur < length; ++cur)//不影响最小跳跃次数
            dp[cur] = Integer.MAX_VALUE;

        for (int cur = 0; cur < length; ++cur) {
            int step = nums[cur] + cur;//当前位置起跳到达最大位置

            int time = dp[cur] + 1;//跳跃到下一个位置的次数

            for (int next = cur + 1; next <= step && next < length; ++next)
                dp[next] = Math.min(dp[next], time);

        }

        return dp[length - 1];
    }
}
