package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/9 9:09
 */
//LCP_12-小张刷题计划
public class LCP_12 {
    public static void main(String[] args) {
/*        int[] time = {1, 2, 3, 3};
        int days = 2;*/

        int[] time = {999, 999, 999};
        int days = 4;
        System.out.println(minTime(time, days));
    }

    /**
     * 二分(极大化最小值)<br>
     * 执行用时：7 ms, 在所有 Java 提交中击败了78.82%的用户<br>
     * 内存消耗：49.3 MB, 在所有 Java 提交中击败了5.61%的用户<br>
     * 2022年06月09日  09:53:07
     */
    public static int minTime(int[] time, int days) {
        //可以花费时间为 0 (left = 0)
        int left = 0, right = 0;

        for (int val : time) right += val;//一天刷完

        while (left < right) {//最大化最小值
            int mid = left + (right - left) / 2;

            if (check(mid, days, time))
                right = mid;//ceiling(满足刷完题目的最小值)//缩减
            else
                left = mid + 1;
        }

        return left;//二分答案
    }

    private static boolean check(int mid, int days, int[] time) {
        int cnt = 0, sum = 0;//花费天数、一天刷题花费时间

        int maxReq = 0;//请求外援的时间(不会计算在一天时间之内)

        for (int val : time) {
            sum += val;

            maxReq = Math.max(maxReq, val);//一天使用花费时间最长来请求外援

            if (sum - maxReq > mid) {//请求外援后、已经超过了一天限制时间

                if (++cnt == days) return false;//更新天数

                //重置
                sum = val;
                maxReq = val;
            }
        }

        return true;
    }
}
