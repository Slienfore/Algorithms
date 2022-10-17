package 二分查找;

import utils.uu;

import java.util.Arrays;

//704-二分查找
public class Demo_704 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 0;
        System.out.println(search_1(nums, target));
        System.out.println(search_2(nums, target));
        System.out.println(search_3(nums, target));
        uu.print("执行用时：\n" +
                "0 ms\n" +
                ", 在所有 Java 提交中击败了\n" +
                "100.00%\n" +
                "的用户\n" +
                "内存消耗：\n" +
                "42.3 MB\n" +
                ", 在所有 Java 提交中击败了\n" +
                "5.35%\n" +
                "的用户");
    }

    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了5.35%的用户
     */
    public static int search_3(int[] nums, int target) {
        int left = 0;

        for (int right = nums.length - 1; left < right; ) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }

        return nums[left] == target ? left : -1;
    }


    //Demo_1：二分查找
    public static int search_1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            //中间
            int mid = (right + left) / 2;
            if (nums[mid] == target) return mid;
                //当要查找的值小于中间值的时候，在左边进行查找
            else if (target < nums[mid]) right = mid - 1;
                //当要查找的值大于中间值的时候，在右边进行查找
            else left = mid + 1;
        }
        return -1;
    }

    //Demo_2
    public static int search_2(int[] nums, int target) {
        return Arrays.binarySearch(nums, target) >= 0 ? Arrays.binarySearch(nums, target) : -1;
    }
}
