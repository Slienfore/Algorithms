package 二分查找;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/7 20:55
 */
//875-爱吃香蕉的珂珂
public class Demo_875 {
    public static void main(String[] args) {
/*        int[] nums = {3, 6, 7, 11};
        int hour = 8;*/
/*        int[] nums = {30, 11, 23, 4, 20};
        int hour = 5;*/
        int[] nums = {30, 11, 23, 4, 20};
        int hour = 6;

        System.out.println(minEatingSpeed(nums, hour));
    }

    /**
     * 二分<br>
     * 执行用时：6 ms, 在所有 Java 提交中击败了99.66%的用户<br>
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了27.03%的用户<br>
     * 2022年06月07日  21:43:45
     */
    public static int minEatingSpeed(int[] piles, int time) {
        int left = 1, right = 1;

        for (int val : piles)//最大值就是在有限时间内吃完香蕉的最快速度
            right = Math.max(right, val);

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (check(piles, mid, time))//合理继续缩小香蕉数
                right = mid;
            else left = mid + 1;
        }

        return left;
    }

    private static boolean check(int[] piles, int mid, int time) {
        int need = 0;

        for (int val : piles)
            need += (val + mid - 1) / mid;//向上取整

        return need <= time;
    }
}
