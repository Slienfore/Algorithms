package 二分查找;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/6 18:25
 */
//153-寻找旋转排序数组中的最小值
public class Demo_153 {
    public static void main(String[] args) {
//        int[] nums = {3, 4, 5, 1, 2};
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        int[] nums = {11, 13, 15, 17};
        System.out.println(findMin_1(nums));
        System.out.println(findMin_2(nums));
        System.out.println(findMin_3(nums));
    }

    /**左侧区间找最大值<br>
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     内存消耗：41.1 MB, 在所有 Java 提交中击败了18.66%的用户
     */
    public static int findMin_3(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (nums[mid] >= nums[left]) {
                left = mid;//寻找最大值
            } else if (nums[mid] < nums[left]) {
                right = mid - 1;
            }
        }
        return nums[(left + 1) %  nums.length];//最大值移动一位就是最小值

    }

    /**
     * 二分(右边界)<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41 MB, 在所有 Java 提交中击败了21.59%的用户
     */
    public static int findMin_2(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            //右边界呈递增序列，那么右边界一定不是最小值
            if (nums[right] >= nums[mid]) {//搜索区间[left, mid]
                right = mid;
            } else if (nums[mid] > nums[right]) {//搜索区间[mid + 1， right]
                left = mid + 1;
            }
        }

        return nums[left];
    }


    /**
     * 线性搜索<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了37.83%的用户
     */
    public static int findMin_1(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int val : nums)
            if (val < min)
                min = val;

        return min;
    }
}
