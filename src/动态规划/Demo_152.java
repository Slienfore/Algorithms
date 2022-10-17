package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/21 10:23
 */
//152-乘积最大子数组
public class Demo_152 {
    public static void main(String[] args) {
//        int[] nums = {2, 3, -2, 4};
//        int[] nums = {-2, 0, -1};
        int[] nums = {-2};
//        int[] nums = {-2, 3, -4};
        System.out.println(maxProduct_2(nums));
    }

    /**
     * 动规<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了73.08%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了62.99%的用户
     */
    public static int maxProduct_2(int[] nums) {
        int length = nums.length;

        //maxDp:维护连续区间最大值、minDp:维护连续区间最小值、max:结果最大值
        int maxDp = nums[0], minDp = nums[0], max = nums[0];

        for (int cur = 1; cur < length; ++cur) {
            int temp = maxDp, val = nums[cur];

            maxDp = Math.max(Math.max(maxDp * val, val), minDp * val);//负数也会成为最大值

            minDp = Math.min(Math.min(minDp * val, val), temp * val);

            max = Math.max(max, maxDp);
        }

        return max;
    }
}
