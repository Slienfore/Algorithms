package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/23 14:53
 */
//1283-使结果不超过阈值的最小除数
public class Demo_1283 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 9};
        int threshold = 6;
/*        int[] nums = {44, 22, 33, 11, 1};
        int threshold = 5;*/

        System.out.println(smallestDivisor(nums, threshold));
    }

    /**<br>
     执行用时：6 ms, 在所有 Java 提交中击败了91.32%的用户<br>
     内存消耗：46.3 MB, 在所有 Java 提交中击败了5.28%的用户*/
    public static int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = threshold;//使用数组最大值充当被除数
        for (int val : nums)
            right = Math.max(val, right);

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (getSum(nums, mid) > threshold)//说明除数过小
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }
    private static int getSum(int[] nums, int div) {
            int sum = 0;

            for (int val : nums)
                sum += (val + div - 1) / div;//除不尽向上取整

            return sum;
    }
}
