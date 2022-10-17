package 二分查找;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/28 11:46
 */
//LCP-采购方案
public class LCP_28 {
    public static void main(String[] args) {
/*        int[] nums = {2, 3, 8, 5};
        int fee = 6;*/
        int[] nums = {40330, 31957, 63879, 13204, 47923, 56282, 75126, 3423, 98483};
        int fee = 60482;
        System.out.println(purchasePlans(nums, fee));
    }

    /**
     * 双指针<br>
     * 执行用时：31 ms, 在所有 Java 提交中击败了94.68%的用户<br>
     * 内存消耗：50 MB, 在所有 Java 提交中击败了49.54%的用户
     */
    public static int purchasePlans(int[] nums, int target) {
        int mod = 1_000_000_007;

        Arrays.sort(nums);
        int res = 0;

        for (int left = 0, right = nums.length - 1; left < right; ++left) {

            while (nums[left] + nums[right] > target && right > left)//超过预算了
                --right;

            //每一次取一块数(nums[left] + nums[right] <= tar)
            res += right - left;//因为是左边与右边部分的值进行匹配的数目，所以不包含 left

            res %= mod;//防爆
        }

        return res;
    }
}
