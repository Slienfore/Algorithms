package 二分查找;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/9 22:28
 */
//2187-完成旅途的最少使用时间
public class Demo_2187 {
    public static void main(String[] args) {
        int[] time = {1, 2, 3};
        int all = 5;
        System.out.println(minimumTime(time, all));

    }

    /**
     * 二分(极大化最小值)<br>
     * 执行用时：91 ms, 在所有 Java 提交中击败了68.61%的用户<br>
     * 内存消耗：56 MB, 在所有 Java 提交中击败了5.00%的用户<br>
     * 2022年06月09日  22:42:30
     */
    public static long minimumTime(int[] time, int all) {
        Arrays.sort(time);

        long left = 0, right = (long) time[time.length - 1] * all;//上界就是花费时间最多的车跑完全程

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (check(mid, all, time))//若能够满足, 缩减
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    private static boolean check(long mid, int all, int[] time) {
        long cnt = 0;

        for (int val : time) {
            cnt += mid / val;//在当前时间下能够完成的旅途趟数

            if (val > mid || cnt >= all) break;//有序，后面时间都不会满足
        }

        return cnt >= all;//是否在有限时间内完成旅程
    }
}
