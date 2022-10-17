package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/25 20:56
 */
//918-环形子数组的最大和
public class Demo_918 {
    public static void main(String[] args) {
//        int[] nums = {1, -2, 3, -2};
        int[] nums = {5, -3, 5};
        System.out.println(maxSubarraySumCircular(nums));
    }

    /**
     * 动规<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了89.66%的用户<br>
     * 内存消耗：46.4 MB, 在所有 Java 提交中击败了41.07%的用户
     */
    public static int maxSubarraySumCircular(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return nums[0];

        //一、不使用环
        int sum = nums[0], max = nums[0];

        for (int cur = 1, maxDp = max; cur < length; ++cur) {//求取最大值(不成环)
            int val = nums[cur];
            sum += val;

            maxDp = Math.max(maxDp + val, val);
            max = Math.max(max, maxDp);
        }

        int min = 0;//连续最小值
        //二、使用环-->那么最小值必定存在于[1, length - 1]段,必定经过头、尾
        for (int cur = 1, minDp = min; cur < length - 1; ++cur) {//成环则说明必定经过头和尾
            int val = nums[cur];
            minDp = Math.min(minDp + val, val);
            min = Math.min(min, minDp);
        }

        return Math.max(max, (sum - min));//总和减去连续中间递减段
    }
}
