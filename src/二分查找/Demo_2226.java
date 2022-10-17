package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/9 21:58
 */
//2226-每个小孩最多能分到多少糖果
public class Demo_2226 {
    public static void main(String[] args) {
/*        int[] candies = {5, 8, 6};
        long num = 3;*/

        int[] candies = {2, 5};
        long num = 11;

        System.out.println(maximumCandies(candies, num));
    }

    /**
     * 二分(极大化答案)<br>
     * 执行用时：39 ms, 在所有 Java 提交中击败了46.20%的用户<br>
     * 内存消耗：56.6 MB, 在所有 Java 提交中击败了81.59%的用户<br>
     * 2022年06月09日  22:10:11
     */
    public static int maximumCandies(int[] candies, long num) {
        long sum = 0, left = 0, right = 0;

        for (int val : candies) {
            right = Math.max(right, val);//上界就是最大堆糖果

            sum += val;
        }

        if (sum < num) return 0;//说明不能够满足所有孩子一人一颗

        while (left < right) {
            long mid = left + (right - left + 1) / 2;

            if (check(mid, num, candies))//说明还能够继续增大分配数量(扩大)
                left = mid;
            else
                right = mid - 1;//分配数量过多，收缩
        }

        return (int) left;
    }

    private static boolean check(long mid, long num, int[] candies) {
        long cnt = 0;

        for (int val : candies)
            cnt += val / mid;//下取整

        return cnt >= num;
    }

    /**
     * 二分(优化边界)<br>
     * 执行用时：18 ms, 在所有 Java 提交中击败了79.02%的用户<br>
     * 内存消耗：56.5 MB, 在所有 Java 提交中击败了90.78%的用户<br>
     * 2022年06月09日  22:18:11
     */
    public static int maximumCandies_1(int[] candies, long num) {
        long left = 0, right = 0;

        for (int val : candies)
            right += val;

        if (right < num) return 0;//说明不能够满足所有孩子一人一颗

        right /= num;//最大只能分配糖果总数一半

        while (left < right) {
            long mid = left + (right - left + 1) / 2;

            if (check(mid, num, candies))//说明还能够继续增大分配数量(扩大)
                left = mid;
            else
                right = mid - 1;//分配数量过多，收缩
        }

        return (int) left;
    }
}
