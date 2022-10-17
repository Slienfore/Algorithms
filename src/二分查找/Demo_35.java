package 二分查找;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

//35-搜索插入位置
public class Demo_35 {
    public static void main(String[] args) {
        int[] num = {1, 3, 5, 6};
        int target = 7;
        System.out.println(searchInsert_1(num, target));
        System.out.println(searchInsert_2(num, target));
        System.out.println(searchInsert_3(num, target));
        System.out.println(searchInsert_4(num, target));

    }

    //Demo_1
    public static int searchInsert_1(int[] nums, int target) {
        return Arrays.binarySearch(nums, target) >= 0 ? Arrays.binarySearch(nums, target) :
                (Math.abs(Arrays.binarySearch(nums, target)) - 1);
    }

    //Demo_2
    public static int searchInsert_2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = nums[mid];
            if (midVal < target) low = mid + 1;
            else if (midVal > target) high = mid - 1;
                //找到了
            else return mid;
        }
        //找不到的插入的位置
        return low;
    }

    /**
     * 搜索区间在数组之中
     */
    public static int searchInsert_3(int[] nums, int target) {
        int left = 0, right = nums.length - 1;//插入的区间在数组之中

        //若该元素比数组最后一个元素都大，那么说明插入的位置只能有末尾了
        if (nums[nums.length - 1] < target)
            return nums.length;

        while (left < right) {//只有当left以及right重叠的时候才会有解
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;//下一轮搜索区间[mid + 1, right]
            } else if (nums[mid] > target) {
                right = mid;//下一轮搜索区间[left, mid]， mid可能是插入位置
            }
        }

        return left;
    }

    /**
    搜索区间在数组外
    */
    public static int searchInsert_4(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (right > left) {
            int mid = (left + right) >>> 1;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        return left;
    }


}



