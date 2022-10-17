package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/10 9:55
 */
//1011-在 D 天内送达包裹的能力
public class Demo_1011 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;

/*        int[] nums = {1, 2, 3, 1, 1};
        int days = 4;*/

        System.out.println(shipWithinDays(nums, days));
    }

    /**
     * 二分(极大化极小)<br>
     * 执行用时：11 ms, 在所有 Java 提交中击败了62.40%的用户<br>
     * 内存消耗：45.1 MB, 在所有 Java 提交中击败了13.96%的用户<br>
     * 2022年06月10日  10:28:54
     */
    public static int shipWithinDays(int[] weights, int days) {
        int left = 1, right = 0;

        for (int val : weights)
            right += val;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (check(mid, days, weights))//说明能够满足,缩减
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    private static boolean check(int mid, int days, int[] weights) {
        int sum = 0, cnt = 1;//默认从第一天开始

        for (int val : weights) {

            if (val > mid) return false;//一天装不下

            sum += val;

            if (sum > mid) {//超出了载量
                sum = val;//重置

                ++cnt;
            }
        }

        return cnt <= days;
    }
}
