package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/1 17:40
 */
//162-寻找峰值
public class Demo_162 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(findPeakElement_1(nums));
        System.out.println(findPeakElement_2(nums));
    }

    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了42.68%的用户<br>
     * 2022年04月01日  18:03:30
     */
    public static int findPeakElement_2(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;//(向下取整，所以不会越界)

            if (nums[mid] > nums[mid + 1])//严格大于其右边元素说明 mid 可能是解
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }


    public static int findPeakElement_1(int[] nums) {
        int length = nums.length, res = 0;
        if (length == 1)
            return 0;

        for (int cur = 1; cur < length; ++cur)
            if (nums[cur] > nums[cur - 1])//只需要与前面一项进行比较就行
                res = cur;

        return res;
    }
}
