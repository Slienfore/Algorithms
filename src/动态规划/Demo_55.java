package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 11:01
 */
//55-跳跃游戏
public class Demo_55 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(canJump_1(nums));
        System.out.println(canJump_2(nums));
        System.out.println(canJump_3(nums));
    }

    /**
     * 动规<br>
     * 执行用时：601 ms, 在所有 Java 提交中击败了6.50%的用户<br>
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了46.82%的用户
     */
    public static boolean canJump_3(int[] nums) {
        int length = nums.length;

        boolean[] dp = new boolean[length];//否跳跃到当前点
        dp[0] = true;//第一个台阶能够到达

        for (int cur = 1; cur < length; ++cur)
            for (int pre = 0; pre < cur; ++pre)
                if (dp[pre] && nums[pre] + pre >= cur) {//存在能够跳跃到之前的台阶是否能够跳跃到达当前点
                    dp[cur] = true;
                    break;
                }

        return dp[length - 1];//是否能够跳跃到达最后一点
    }

    /**
     * 动规<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了18.54%的用户<br>
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了36.19%的用户
     */
    public static boolean canJump_2(int[] nums) {
        int length = nums.length;

        int[] dp = new int[length];//当前点数能够跳跃达到最大的位置
        dp[0] = nums[0];

        for (int cur = 1; cur < length; ++cur) {//最后一个不用跳了
            if (dp[cur - 1] < cur)
                return false;

            dp[cur] = Math.max(dp[cur - 1], cur + nums[cur]);
        }

        return true;
    }

    /**
     * 动规<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了94.67%的用户<br>
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了41.98%的用户
     */
    public static boolean canJump_1(int[] nums) {
        int length = nums.length;

        int maxStep = nums[0];//第一个位置能够跳跃到达的位置

        for (int cur = 1; cur < length; ++cur) {
            if (maxStep < cur)//若之前能够跳跃不到该点
                return false;

            maxStep = Math.max(maxStep, nums[cur] + cur);//更新下一次跳跃到达的最大位置
        }

        return true;
    }
}
