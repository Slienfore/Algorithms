package 前缀和与差分;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/9 20:38
 */
//1109-航班预定统计
public class Demo_1109 {
    public static void main(String[] args) {
        int[][] booking = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int num = 5;

        uu.print(corpFlightBookings(booking, num));
    }

    /**
     * 差分数组<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：55.4 MB, 在所有 Java 提交中击败了27.87%的用户
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        //差分数组
        int[] dih = new int[n];
        for (int[] arr : bookings) {
            int start = arr[0], end = arr[1], val = arr[2];

            dih[start - 1] += val;

            if (end != n)
                dih[end] -= val;//可能会越界
        }

        for (int i = 1; i < n; i++)
            dih[i] += dih[i - 1];//差分的前缀和就是原数组

        return dih;
    }

}
