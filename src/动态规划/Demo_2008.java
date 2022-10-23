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
        int n = 5;
        int[][] rides = {{2, 5, 4}, {1, 5, 1}};
/*        int n = 20;
        int[][] rides = {{1, 6, 1}, {3, 10, 2}, {10, 12, 3}, {11, 12, 2}, {12, 15, 2}, {13, 18, 1}};*/
        System.out.println(maxTaxiEarnings1(n, rides));
        System.out.println(maxTaxiEarnings2(n, rides));
    }

    /**
     * 动规+二分
     * <br>
     * 执行用时：87 ms, 在所有 Java 提交中击败了23.95%的用户<br>
     * 内存消耗：65.2 MB, 在所有 Java 提交中击败了11.34%的用户<br>
     * 2022年10月23日  11:33:32
     */
    public static long maxTaxiEarnings2(int n, int[][] rides) {
        int len = rides.length;

        // 终点由近及远
        Arrays.sort(rides, Comparator.comparingInt(o -> o[1]));

        // 送该乘客是否会获得的最大利润
        long[] dp = new long[len + 1];

        for (int cur = 1; cur <= len; ++cur) {// 遍历所有乘客
            // 当前乘客信息
            int orin = rides[cur - 1][0],
                    end = rides[cur - 1][1], tip = rides[cur - 1][2];

            // 搜索 当前乘客出发点 之前 送达 (最近) 终点乘客 => 连续送客
            int ll = 0, rr = cur - 1;

            while (ll < rr) {
                int mid = ll + (rr - ll + 1) / 2;

                if (rides[mid - 1][1] <= orin)// 搜寻最近送达乘客
                    ll = mid;
                else
                    rr = mid - 1;// 上个乘客到达位置 超过 当前乘客的起点 => 收缩
            }

            // 送上个乘客 与 送当前乘客 获得的利润是否最大
            dp[cur] = Math.max(dp[cur - 1],// 如果利润小, 就不送当前乘客
                    dp[ll] + ((end - orin) + tip));
        }

        return dp[len];
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
