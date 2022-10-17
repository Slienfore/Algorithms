package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/11 22:34
 */
//410-分割数组的最大值
public class Demo_410 {
    public static void main(String[] args) {
/*        int[] nums = {7, 2, 5, 10, 8};
        int num = 2;*/
/*        int[] nums = {1, 2, 3, 4, 5};
        int num = 2;*/
        int[] nums = {1, 4, 4};
        int num = 3;
        System.out.println(splitArray(nums, num));
    }

    /**
     * 二分(极大化极小)<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了23.95%的用户<br>
     * 2022年06月11日  23:27:41
     */
    public static int splitArray(int[] nums, int num) {
        int left = 0, right = 0;//下界-最大(最大值可以容纳更多数)--上界-总和(构成一个子数组)
        for (int val : nums) {
            left = Math.max(val, left);

            right += val;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (check(mid, num, nums))//说明恰好能够分成(缩减总和)
                right = mid;
            else
                left = mid + 1;//子数组的和太小、导致分割子数组数量过多
        }

        return left;
    }

    private static boolean check(int mid, int num, int[] nums) {
        int cnt = 1, sum = 0;//分割形成的子数组的数量、区间总和

        for (int val : nums) {
            if (sum + val > mid) {//超出分割的和-组成一份子数组、重置
                ++cnt;

                sum = 0;
            }

            sum += val;
        }

        /*设置区间之和 (大) - 导致分割数量 过少
        设置区间之和 (小) - 导致分割数量 过多*/
        return cnt <= num;
    }
}
