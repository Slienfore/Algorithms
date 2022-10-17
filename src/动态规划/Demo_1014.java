package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/25 17:56
 */
//1014-最佳观光组合
public class Demo_1014 {
    public static void main(String[] args) {
//        int[] values = {8, 1, 5, 2, 6};
        int[] values = {1, 2};
        System.out.println(maxScoreSightseeingPair_1(values));
        System.out.println(maxScoreSightseeingPair_2(values));
        System.out.println(maxScoreSightseeingPair_3(values));
    }

    /**
     * 动规(拆分公式)<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了95.33%的用户<br>
     * 内存消耗：49.6 MB, 在所有 Java 提交中击败了49.71%的用户
     */
    public static int maxScoreSightseeingPair_3(int[] values) {
        /*(values[pre] + values[cur]) - (cur - pre)
       == (values[pre] + pre) + (values[cur] - cur)
       values[cur] - cur 固定不动，则只需要维护 (values[pre] + pre) 最大值
         */

        int length = values.length, pre = values[0] + 0, res = 0;

        for (int cur = 1; cur < length; ++cur) {
            res = Math.max(res, pre + values[cur] - cur);//(values[pre] + pre) + (values[cur] - cur)

            pre = Math.max(pre, values[cur] + cur);
        }

        return res;
    }


    /**
     * 动规<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了7.71%的用户<br>
     * 内存消耗：48.8 MB, 在所有 Java 提交中击败了59.89%的用户
     */
    public static int maxScoreSightseeingPair_2(int[] values) {
        int length = values.length;
        int[] dp = new int[length];//表示组成得分最高的前一个景点下标
        int max = values[0];

        for (int cur = 1; cur < length; ++cur) {
            int val = values[cur], prePos = dp[cur - 1];//之前的组成最高分的景点下标

            if ((val + values[cur - 1] - 1) > (values[prePos] + val - (cur - prePos))) {//相邻点的值较大
                dp[cur] = cur - 1;//记录相邻点

                max = Math.max((val + values[cur - 1] - 1), max);
            } else {
                dp[cur] = prePos;//继承之前下标
                max = Math.max(max, values[prePos] + val - (cur - prePos));
            }

        }

        return max;
    }


    /**
     * 暴力
     */
    public static int maxScoreSightseeingPair_1(int[] values) {
        int length = values.length, max = values[0];

        for (int cur = 1; cur < length; ++cur) {
            int curVal = values[cur];
            for (int pre = cur - 1; pre >= 0; --pre)
                max = Math.max(max, curVal + values[pre] - (cur - pre));
        }

        return max;
    }
}
