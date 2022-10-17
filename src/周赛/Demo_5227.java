package 周赛;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/13 11:29
 */
//5227- k 次操作后最大顶端元素
public class Demo_5227 {
    public static void main(String[] args) {
/*        int[] nums = {5, 2, 2, 4, 0, 6};
        int k = 4;*/
/*        int[] nums = {2};a
        int k = 1;*/
        int[] nums = {99, 95, 68, 18};
        int k = 69;
/*        int[] nums = {91, 98, 17, 79, 15, 55, 47, 86, 4, 5, 17, 79, 68, 60, 60, 31, 72, 85, 25, 77, 8, 78, 40, 96, 76, 69, 95, 2, 42, 87, 48, 72, 45, 25, 40, 60, 21, 91, 32, 79, 2, 87, 80, 97, 82, 94, 69, 43, 18, 19, 21, 36, 44, 81, 99};
        int k = 2;*/
        System.out.println(maximumTop(nums, k));
    }

    /**
     * 边界<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：56.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int maximumTop(int[] nums, int k) {
        int length = nums.length;

        if (length == (k % 2))//若长度为 1 ， 且操作次数为 奇数，栈顶没有元素
            return -1;

        int max = Integer.MIN_VALUE;

        //第 K 次操作的结点是不会出栈的， 找出前 k - 2 最大值， k - 1 就是 最后一次操作， 应该选择 第 K 项与 前 k - 2比较
        for (int i = 0; i < (k - 1) && i < length; ++i)
            max = Math.max(max, nums[i]);

        if (k < length)//若操作次数小于数组长度， 只能比较前 k 次操作与这一次操作的最大值了
            max = Math.max(max, nums[k]);

        return max;
    }
}
