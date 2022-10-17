package 二分查找;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/31 15:35
 */
//1608-特殊数组的特殊值
public class Demo_1608 {
    public static void main(String[] args) {
//        int[] nums = {3, 5};
        int[] nums = {0, 0};
        System.out.println(specialArray(nums));
    }

    /**
     * 二分<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了16.70%的用户<br>
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了18.93%的用户<br>
     * 2022年03月31日  15:45:20
     */
    public static int specialArray(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length, res = -1;

        for (int tar = 1; tar <= length; ++tar) {
            int left = 0, right = length;//若不存在该数，说明越界

            while (left < right) {//ceiling
                int mid = left + (right - left) / 2;

                if (nums[mid] < tar)
                    left = mid + 1;
                else
                    right = mid;
            }

            if (length - left == tar)
                res = tar;
        }

        return res;
    }

}
