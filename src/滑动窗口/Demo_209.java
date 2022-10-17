package 滑动窗口;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/8 12:08
 */
//209-长度最小的子数组
public class Demo_209 {
    public static void main(String[] args) {
/*        int[] nums = {2, 3, 1, 2, 4, 3};
        int tar = 7;*/
        int[] nums = {1, 4, 4};
        int tar = 4;
        System.out.println(minSubArrayLen(tar, nums));
    }

    /**
     * 滑动窗口<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了40.83%的用户<br>
     * 2022年04月08日  12:19:29
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int length = nums.length, min = Integer.MAX_VALUE;

        for (int left = 0, right = 0; right < length; ++right) {
            target -= nums[right];

            while (target <= 0) {//达到所需的值(收缩窗口大小)
                min = Math.min(min, right - left + 1);

                target += nums[left++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
