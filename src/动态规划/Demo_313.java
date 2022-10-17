package 动态规划;

import utils.uu;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/25 15:50
 */
//313-超级丑数
public class Demo_313 {
    public static void main(String[] args) {
        int[] primes = {2, 7, 13, 19};
        int nth = 5;
        System.out.println(nthSuperUglyNumber_1(nth, primes));
        System.out.println(nthSuperUglyNumber_2(nth, primes));

    }

    /**
     * 动规(多指针)<br>
     * 执行用时：296 ms, 在所有 Java 提交中击败了83.26%的用户<br>
     * 内存消耗：43.4 MB, 在所有 Java 提交中击败了41.90%的用户
     */
    public static int nthSuperUglyNumber_2(int n, int[] primes) {
        int length = primes.length;
        int[] pos = new int[length];//多指针(只与其中的质数有关)

        int[] dp = new int[n];
        dp[0] = 1;//第一个丑数

        for (int nth = 1; nth < n; ++nth) {
            int minVal = Integer.MAX_VALUE;
            for (int cur = 0; cur < length; ++cur)
                minVal = Math.min(minVal, dp[pos[cur]] * primes[cur]);

            dp[nth] = minVal;

            for (int cur = 0; cur < length; ++cur)
                if (dp[pos[cur]] * primes[cur] == minVal)//指针移动
                    ++pos[cur];
        }

        return dp[n - 1];
    }


    /**
     * 超时
     */
    public static int nthSuperUglyNumber_1(int n, int[] primes) {

        PriorityQueue<Long> heap = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        long res = 1;
        heap.offer(res);
        set.add(res);

        for (int nth = 1; nth <= n; ++nth) {
            res = heap.poll();

            for (int val : primes)//只包含规定 prime 中的质因数有关
                if (set.add(val * res))
                    heap.offer(val * res);
        }

        return (int) res;
    }
}

