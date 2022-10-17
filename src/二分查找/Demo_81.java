package 二分查找;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/6 16:44
 */
//81-搜索旋转排序数组-II
public class Demo_81 {
    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {1, 0, 1, 1, 1};
//        int[] nums = {2, 2, 2, 3, 2, 2, 2};
        int target = 0;
        System.out.println(search_1(nums, target));
        System.out.println(search_2(nums, target));
        System.out.println(search_3(nums, target));


    }

    /**
     * 二分(右边界)<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41 MB, 在所有 Java 提交中击败了28.62%的用户
     */
    public static boolean search_2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (nums[mid] < nums[right]) {//区间[mid, right]
                //[9, 1, 2, 3, 4]右边一定是目标
                if (nums[mid] <= target && nums[right] >= target) {
                    left = mid;
                } else
                    right = mid - 1;//收缩右边界[left, mid - 1]

            } else if (nums[mid] > nums[right]) {
                //若判断 mid >= target 那么将会忽视 mid 的值， 若为 mid - 1 还会回到 mid 中，
                if (target <= nums[mid - 1] && nums[left] <= target) {//区间[left, mid - 1]
                    right = mid - 1;

                } else left = mid;
                //[1, 0, 1, 1, 1]不知那边是有序还是无序
            } else if (nums[right] == nums[mid]) {//遇到重复元素
                if (nums[mid] == target)
                    return true;

                right--;//元素重复，将进行从右向左进行收缩
            }
        }
        return nums[left] == target;
    }

    /**二分(左边界)<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：40.7 MB, 在所有 Java 提交中击败了40.14%的用户
    */
    public static boolean search_3(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid;//区间[left, mid]

                } else left = mid + 1;//区间[mid + 1, right]

            } else if (nums[left] > nums[mid]) {
                if (target >= nums[mid + 1] && target <= nums[right]) {//(mid + 1), mid 还会回到 mid这个位置
                    left = mid + 1;

                } else right = mid;

            } else if (nums[left] == nums[mid]) {
                if (nums[left] == target)
                    return true;

                left++;//移除左边重复元素
            }
        }
        return nums[left] == target;
    }


    /**
     * 线性搜索<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了16.34%的用户
     */
    public static boolean search_1(int[] nums, int target) {
        for (int val : nums)
            if (val == target)
                return true;

        return false;
    }
}
