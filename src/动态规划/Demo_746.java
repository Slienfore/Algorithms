package 动态规划;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/4 21:56
 */
//746-使用最小花费爬楼梯
public class Demo_746 {

    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
//        int[] cost = {1, 2};

        System.out.println(minCostClimbingStairs_1(cost));
        System.out.println(minCostClimbingStairs_2(cost));
        System.out.println(minCostClimbingStairs_3(cost));
    }


    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：40.7 MB, 在所有 Java 提交中击败了37.71%的用户
     */
    public static int minCostClimbingStairs_2(int[] cost) {
        int[] dp = new int[cost.length + 1];
        //起始点是从 0 层开始的，最后终点是 length， 这两层均不用花费体力
        dp[0] = cost[0];//登上第一层花费的时间
        dp[1] = cost[1];//登上第二层花费的体力
        int length = dp.length;

        for (int layer = 2; layer < length; layer++) {//从第三层开始爬
            dp[layer] = Math.min(dp[layer - 1], dp[layer - 2]);//比较登上该层前花费最小的阶梯数

            dp[layer] += layer == length - 1 ? 0 : cost[layer];//若还还没到楼顶，那么说明还需要登上这一阶
        }

        return dp[length - 1];
    }

    /**动规<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：41.2 MB, 在所有 Java 提交中击败了7.77%的用户
    */
    public static int minCostClimbingStairs_3(int[] cost) {
        int length = cost.length + 1;//最后还有一层是楼顶

        int prePre = cost[0] + 0, pre = cost[1] + 0;//登上第一、二层花费体力

        for (int layer = 2; layer < length; layer++) {
            int min = Math.min(pre, prePre);//前两层花费的最最小体力

            prePre = pre;

            pre = layer == (length - 1) ? (min + 0) : (min + cost[layer]);//到达楼顶不需要花费体力
        }

        return pre;
    }


    /**
     * DP<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了92.41%的用户<br>
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了34.46%的用户
     */
    public static int minCostClimbingStairs_1(int[] cost) {
        if (cost.length == 0) return 0;
        else if (cost.length == 1) return cost[0];

        int stairs = cost.length;
        int[] dp = new int[stairs];//初始化DP数组

        //前两级楼梯需要花费体力
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < stairs; i++)
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];

        return Math.min(dp[stairs - 1], dp[stairs - 2]);//比较最后两级阶梯的值
    }

}
