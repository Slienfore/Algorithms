package 动态规划;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/22 9:32
 */
// 2008-出租车的最大盈利
public class Demo_2008 {
    public static void main(String[] args) {
/*        int n = 5;
        int[][] rides = {{2, 5, 4}, {1, 5, 1}};*/
        int n = 20;
        int[][] rides = {{1, 6, 1}, {3, 10, 2}, {10, 12, 3}, {11, 12, 2}, {12, 15, 2}, {13, 18, 1}};
        System.out.println(maxTaxiEarnings1(n, rides));
    }

    /**
     * 动规<br>
     * 执行用时：62 ms, 在所有 Java 提交中击败了50.21%的用户<br>
     * 内存消耗：62.2 MB, 在所有 Java 提交中击败了52.32%的用户<br>
     * 2022年10月22日  10:03:54
     */
    public static long maxTaxiEarnings1(int n, int[][] rides) {
        // 接客顺序由近及远(起点排序)
        Arrays.sort(rides, Comparator.comparingInt(o -> o[0]));

        // 到达终点 pos 的点获得的最大利润为多少
        long[] dp = new long[n + 1];

        int pos = 0;// 当前乘客

        for (int cur = 1; cur <= n; ++cur) {// 出租车经过所有的点

            dp[cur] = Math.max(dp[cur], dp[cur - 1]);// 以该点为终点获取利润是否最大

            while (pos < rides.length && rides[pos][0] == cur) {// 是否有乘客从当前点出发
                // 从当前出发达到的终点, 收获的利润是否最大
                dp[rides[pos][1]] = Math.max(dp[rides[pos][1]],
                        dp[cur] + (rides[pos][1] - rides[pos][0] + rides[pos][2]));

                ++pos;
            }

        }

        return dp[n];
    }
}
