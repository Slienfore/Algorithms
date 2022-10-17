package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/15 17:08
 */
//540-有序数组中的单一元素
public class Demo_540 {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 2, 3, 3, 4, 4, 8, 8};
//        int[] nums = {3, 3, 7, 7, 10, 11, 11};
        int[] nums = {1, 1, 2};

        System.out.println(singleNonDuplicate_1(nums));
        System.out.println(singleNonDuplicate_2(nums));
        System.out.println(singleNonDuplicate_3(nums));

    }

    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了64.88%的用户
     */
    public static int singleNonDuplicate_3(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (mid % 2 == 0) {//偶数

                if (nums[mid] == nums[mid + 1]) {//偶数下标说明与右侧相等
                    left = mid + 1;
                } else
                    right = mid;

            } else {//奇数

                if (nums[mid] == nums[mid - 1]) {//奇数下下标说明与左侧相等
                    left = mid + 1;
                } else right = mid;

            }
        }

        return nums[left];
    }


    /**
     * 暴力(新颖)<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了34.94%的用户<br>
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了75.54%的用户
     */
    public static int singleNonDuplicate_2(int[] nums) {
        int length = nums.length;

        for (int i = 0; i <= (length - 2); i += 2)//成对出现，以步长为 2 进行判断，若前面不满足，就只剩下最后一项(length - 2)
            if (nums[i] != nums[i + 1])
                return nums[i];

        return nums[length - 1];
    }


    public static int singleNonDuplicate_1(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return nums[0];

        if (nums[0] != nums[1])//第一项
            return nums[0];

        for (int i = 1; i < length; ++i) {

            if (nums[i] != nums[i - 1] && ((i + 1 < length) && nums[i] != nums[i + 1]))
                return nums[i];
        }

        return nums[length - 1] == nums[length - 2] ? -1 : nums[length - 1];//最后一项
    }
}
