package 动态规划;

import utils.uu;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/25 14:23
 */
//264-丑数-II
public class Demo_264 {
    public static void main(String[] args) {
        int num = 16;
        System.out.println(nthUglyNumber_1(num));
        System.out.println(nthUglyNumber_2(num));
        System.out.println(nthUglyNumber_3(num));
    }

    /**
     * 动规(三指针)<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.22%的用户<br>
     * 内存消耗：40.3 MB, 在所有 Java 提交中击败了30.74%的用户
     */
    public static int nthUglyNumber_3(int n) {
        int[] dp = new int[n];//表示第 N 个丑数的值
        dp[0] = 1;//第一个丑数

        int pos_2 = 0, pos_3 = 0, pos_5 = 0;//指向每一个丑数的位置，代表着分别与2、3、5相乘

        for (int cur = 1; cur < n; ++cur) {

            dp[cur] = Math.min(Math.min(dp[pos_2] * 2, dp[pos_3] * 3), dp[pos_5] * 5);//选取每一个丑数与2、3、5乘积最小数

            //分别判断是为了去重(可能每一个丑数 2、3、5的倍数相同，失去了与各个倍数机会)
            if (dp[cur] == dp[pos_2] * 2)//pos_2位置的丑数失去了与 2 相乘的资格
                ++pos_2;

            if (dp[cur] == dp[pos_3] * 3)//pos_3位置的丑数失去了与 2 相乘的资格
                ++pos_3;

            if (dp[cur] == dp[pos_5] * 5)//pos_5位置的丑数失去了与 2 相乘的资格
                ++pos_5;
        }

        return dp[n - 1];
    }


    /**
     * 小顶堆+哈希<br>
     * 执行用时：48 ms, 在所有 Java 提交中击败了29.80%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了11.25%的用户
     */
    public static int nthUglyNumber_2(int n) {
        int[] arr = {2, 3, 5};
        PriorityQueue<Long> heap = new PriorityQueue<>();//使用小顶堆来维护出队的第 N 个丑数
        HashSet<Long> set = new HashSet<>();//进行丑数去重
        long res = 1;
        heap.offer(res);
        set.add(res);

        for (int nth = 1; nth <= n; ++nth) {
            res = heap.poll();

            for (int val : arr)
                if (set.add(res * val))//2、3、5的倍数(去重)
                    heap.offer(res * val);
        }

        //丑数是 2、3、5的倍数
        return (int) res;
    }


    /**
     * 超时
     */
    public static int nthUglyNumber_1(int n) {
        int val = 1;

        for (int nth = 0, num = 1; nth != n; ++num)
            if (isUgly(num)) {
                ++nth;
                val = num;
            }

        return val;
    }

    private static boolean isUgly(int num) {
        int[] arr = {2, 3, 5};
        for (int val : arr)
            while (num % val == 0)
                num /= val;

        return num == 1;
    }
}
