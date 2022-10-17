package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/7 10:47
 */
//53-最大数组和
public class Demo_53 {
    public static void main(String[] args) {
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};//6
        int[] nums = {5, 4, -1, 7, 8};//23
        System.out.println(maxSubArray_1(nums));
        System.out.println(maxSubArray_2(nums));
    }

    /**
     * 动规<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：50.5 MB, 在所有 Java 提交中击败了33.98%的用户
     */
    public static int maxSubArray_2(int[] nums) {
        int length = nums.length;

        //maxDp:维护连续区间和最大值， max：结果最大值
        int maxDp = nums[0], max = nums[0];

        for (int cur = 1; cur < length; ++cur) {
            int val = nums[cur];
            maxDp = Math.max(maxDp + val, val);

            max = Math.max(maxDp, max);
        }

        return max;
    }


    /**
     * 动规<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：50.1 MB, 在所有 Java 提交中击败了42.71%的用户
     */
    public static int maxSubArray_1(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int max = nums[0], pre = 0;

        for (int val : nums) {
            pre = Math.max(pre + val, val);//维护连续区间最大值
            max = Math.max(pre, max);
        }

        return max;
    }
}
