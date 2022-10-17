package 二分查找;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/9 23:00
 */
//1482-制作 n 束花所需的最少天数
public class Demo_1482 {
    public static void main(String[] args) {
/*        int[] nums = {1, 10, 3, 10, 2};
        int num = 3, adj = 1;*/
        int[] nums = {1, 10, 3, 10, 2};
        int num = 3, adj = 2;
/*        int[] nums = {7, 7, 7, 7, 12, 7, 7};
        int num = 2, adj = 3;*/
/*        int[] nums = {1, 10, 2, 9, 3, 8, 4, 7, 5, 6};
        int num = 4, adj = 2;*/

        System.out.println(minDays(nums, num, adj));
    }

    /**
     * 二分(极大化极小)<br>
     * 执行用时：20 ms, 在所有 Java 提交中击败了41.58%的用户<br>
     * 内存消耗：50 MB, 在所有 Java 提交中击败了14.89%的用户<br>
     * 2022年06月09日  23:27:38
     */
    public static int minDays(int[] bloomDay, int num, int adj) {
        if (num * adj > bloomDay.length) return -1;//花园的花不够

        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;

        for (int val : bloomDay) {
            left = Math.min(val, left);//下界-开花的最小日期

            right = Math.max(val, right);//上界-开花的最长日期
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (check(mid, num, adj, bloomDay))//说明当前天数恰好满足(收缩)
                right = mid;
            else
                left = mid + 1;//说明天数太少了
        }

        return left;
    }

    private static boolean check(int mid, int num, int adj, int[] bloomDays) {
        int length = bloomDays.length, cnt = 0, boom = 0;//制作的花数量、盛开的花数量

        for (int cur = 0; cur < length && cnt < num; ++cur) {
            int time = bloomDays[cur];//当前花束开花时间

            if (time > mid)//开花时间不同说明不相邻(重置)
                boom = 0;
            else if (++boom == adj) {//收集了相邻的 adj 束 花-制作一束花
                ++cnt;

                boom = 0;//重置
            }
        }

        return cnt >= num;//是否能够制作
    }
}
