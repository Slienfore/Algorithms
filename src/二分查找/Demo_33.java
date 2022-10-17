package 二分查找;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/6 10:11
 */
//33-搜索旋转排序数组
public class Demo_33 {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2, 2};
//        int[] nums = {1, 0, 1, 1, 1};
        int target = 0;
        System.out.println(search_2(nums, target));

    }


    /**
     * 二分(非有序数组)<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了31.75%的用户
     */
    public static int search_2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (nums[mid] < nums[right]) {

                if (target >= nums[mid] && target <= nums[right]) {//区间[mid, right]
                    left = mid;
                } else if (target > nums[right] || target < nums[mid]) {//区间[left, mid -1]
                    right = mid - 1;
                }

            } else if (nums[mid] >= nums[right]) {

                //nums[mid - 1] 需要与以上区间转移相一致
                if (target <= nums[mid - 1] && nums[left] <= target) {//区间[left, mid - 1]
                    right = mid - 1;
                } else if (target > nums[left] || nums[mid - 1] > target) {//区间[mid, right]
                    left = mid;
                }

            }
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * 线性查找<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了9.68%的用户
     */
    public static int search_1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == target)
                return i;

        return -1;

    }


}
