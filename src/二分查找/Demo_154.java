package 二分查找;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/6 20:11
 */
//153-寻找旋转排序数组中的最小值II
public class Demo_154 {
    public static void main(String[] args) {
//        int[] nums = {2, 2, 2, 0, 1};
        int[] nums = {1, 1, 1};
//        int[] nums = {1, 1, 3, 5};
        System.out.println(findMin_1(nums));
        System.out.println(findMin_2(nums));
    }

    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41 MB, 在所有 Java 提交中击败了31.05%的用户
     */
    public static int findMin_2(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[right] > nums[mid]) {
                right = mid;
            } else if (nums[right] < nums[mid]) {
                left = mid + 1;
            } else if (nums[right] == nums[mid]) {//说明与中间元素相同(收缩右边界)
                right--;
            }
        }

        return nums[left];
    }


    /**
     * 线性搜索<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了19.13%的用户
     */
    public static int findMin_1(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int val : nums)
            min = Math.min(val, min);

        return min;
    }
}
