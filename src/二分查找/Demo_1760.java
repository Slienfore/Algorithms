package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/8 20:48
 */
//1760-袋子里最小数目的球
public class Demo_1760 {
    public static void main(String[] args) {
/*        int[] nums = {9};
        int maxOpt = 2;*/
        int[] nums = {2, 4, 8, 2};
        int maxOpt = 4;

        System.out.println(minimumSize(nums, maxOpt));

    }

    /**
     * 二分(最小化最大)<br>
     * 执行用时：49 ms, 在所有 Java 提交中击败了53.56%的用户<br>
     * 内存消耗：52.5 MB, 在所有 Java 提交中击败了75.07%的用户<br>
     * 2022年06月08日  21:02:36
     */
    public static int minimumSize(int[] nums, int maxOpt) {
        int left = 1, right = Integer.MIN_VALUE;

        for (int val : nums)//搜寻划分最大值(需要在有限操作内，最小化(操作)->最大值(球的数量))
            right = Math.max(right, val);

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (check(mid, maxOpt, nums))//说明能够划分(最小化)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    private static boolean check(int mid, int maxOpt, int[] nums) {

        for (int opt = 0, cur = 0; cur < nums.length; ++cur) {

            if (nums[cur] % mid == 0)
                opt += nums[cur] / mid - 1;//如果能够被整除，说明能够少一次被拆分

            else opt += nums[cur] / mid;

            if (opt > maxOpt) return false;//袋子装的球太少了，划分次数过多
        }

        return true;
    }
}
