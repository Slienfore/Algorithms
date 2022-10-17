package 二分查找;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/1 20:26
 */
//34-在排序数组中查找元素的第一个和最后一个位置
public class Demo_34 {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
//        int[] nums = {3, 3, 8, 9, 10, 20};
//        int[] nums = {};
//        int[] nums = {2, 5, 6, 7, 9, 10};
//        int[] nums = {1};
//        int[] nums = {3, 3, 3};
        int target = 8;
        System.out.println(Arrays.toString(searchRange_1(nums, target)));

    }

    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：43.7 MB, 在所有 Java 提交中击败了36.84%的用户
     */
    public static int[] searchRange_1(int[] nums, int target) {
        if (nums.length == 0)
            return new int[]{-1, -1};

        if (searchFirst(target, nums) == -1)//如果第一次找不到
            return new int[]{-1, -1};

        return new int[]{searchFirst(target, nums), searchLast(target, nums)};
    }


    private static int searchFirst(int target, int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {//说明 mid 左边的元素一定不是第一次出现target(那么有机会搜寻到第一次)
                left = mid + 1;//搜索区间[mid + 1, right]
            } else {
                right = mid;//搜索区间[left, mid](当nums[mid] == target时，mid可能就是第一次出现的位置)
            }
        }

        if (nums[left] != target)
            return -1;
        else return left;

    }

    private static int searchLast(int target, int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;//注意向上取整

            if (nums[mid] > target) {
                right = mid - 1;//搜索区间[left, mid - 1]//说明mid的取值一定不是target
            } else {
                left = mid;//搜索区间[mid, right]如果nums[mid] == target, 说明mid可能是最后一次取值
            }
        }
        return left;
    }


    /**
     * 二分搜寻第二个出现的位置<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：44.7 MB, 在所有 Java 提交中击败了9.09%的用户
     */
    public static int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) return new int[]{-1, -1};

        int left = 0, right = length - 1;//数组内进行查找

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (nums[mid] > target) {//如果mid绝对大于其值，说明mid的值一定不是要找的位置
                right = mid - 1;//搜索范围[left, mid - 1]
            } else {
                left = mid;//搜索范围[mid, right]
            }

        }

        if (nums[left] != target)
            return new int[]{-1, -1};

        //寻找第一个出现的位置
        for (int i = 0; i < right; i++)
            if (nums[i] == target) {
                left = i;
                break;
            }

        return new int[]{left, right};
    }
}
