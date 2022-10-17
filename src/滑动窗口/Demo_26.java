package 滑动窗口;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 21:02
 */
//26-删除有序数组中的重复项
public class Demo_26 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(removeDuplicates_1(nums));
        System.out.println(removeDuplicates_2(nums));
    }

    /**
     * 双指针<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了42.27%的用户<br>
     * 2022年04月02日  22:10:58
     */
    public static int removeDuplicates_2(int[] nums) {
        int length = nums.length;

        int left = 1, right = 1;

        while (right < length) {
            if (nums[left - 1] != nums[right])//允许重复元素出现一次
                nums[left++] = nums[right];

            ++right;
        }

        return left;
    }


    /**
     * 双指针<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：43.2 MB, 在所有 Java 提交中击败了9.58%的用户<br>
     * 2022年04月02日  21:26:20
     */
    public static int removeDuplicates_1(int[] nums) {

        int length = nums.length;
        int left = 0, right = 1;

        while (right < length) {//若遍历到最后
            if (nums[right] != nums[left])
                nums[++left] = nums[right];//使用下一个 "重复数" 来填充搜索到的 "不重复数"

            ++right;
        }

        return left + 1;
    }
}
