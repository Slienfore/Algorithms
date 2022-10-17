package 滑动窗口;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 21:46
 */
//26-删除有序数组中的重复项-II
public class Demo_80 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};

        System.out.println(removeDuplicates(nums));

    }

    /**
     * 双指针<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了24.74%的用户<br>
     * 2022年04月02日  22:23:13
     */
    public static int removeDuplicates(int[] nums) {
        int length = nums.length;

        int left = 2, right = 2;//允许有两个重复项

        while (right < length) {
            if (nums[left - 2] != nums[right])//允许重复元素出现两次
                nums[left++] = nums[right];

            ++right;
        }

        return left;
    }
}
