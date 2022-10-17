package 滑动窗口;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/3 16:19
 */
//1855-下标对中的最大距离
public class Demo_1855 {
    public static void main(String[] args) {
//        int[] nums1 = {55, 30, 5, 4, 2}, nums2 = {100, 20, 10, 10, 5};
        int[] nums1 = {30, 29, 19, 5}, nums2 = {25, 25, 25, 25, 25};
        System.out.println(maxDistance_1(nums1, nums2));
        System.out.println(maxDistance_2(nums1, nums2));
    }

    /**
     * 快慢指针<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了86.26%的用户<br>
     * 内存消耗：54.7 MB, 在所有 Java 提交中击败了5.22%的用户<br>
     * 2022年04月03日  16:52:53
     */
    public static int maxDistance_2(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;

        int slow = 0, fast = 0;

        int res = 0;

        while (slow < length1) {
            fast = Math.max(fast, slow);//快指针能够保证最低满足条件的最远距离

            while (fast < length2 && nums2[fast] >= nums1[slow])//移动右指针寻找最小能够满足最大值的
                ++fast;

            res = Math.max(res, fast - slow - 1);

            ++slow;
        }

        return res;
    }

    /**
     * 二分<br>
     * 执行用时：28 ms, 在所有 Java 提交中击败了8.06%的用户<br>
     * 内存消耗：54.2 MB, 在所有 Java 提交中击败了36.97%的用户<br>
     * 2022年04月03日  16:35:12
     */
    public static int maxDistance_1(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;

        int res = 0;
        for (int cur = 0; cur < length1; ++cur) {
            int tar = nums1[cur];

            //向上取整，不会越界
            int left = cur - 1, right = length2 - 1;

            while (left < right) {//数组单调递减
                int mid = left + (right - left + 1) / 2;

                if (nums2[mid] < tar)//若比较小(左移变大)
                    right = mid - 1;
                else
                    left = mid;
            }

            if (left >= cur)
                res = Math.max(res, (left - cur));
        }

        return res;
    }
}
