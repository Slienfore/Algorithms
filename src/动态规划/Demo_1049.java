package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/11 21:59
 */
//1049-最后一块石头质量
public class Demo_1049 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 4, 1, 8, 1};
        System.out.println(lastStoneWeightII(nums));
    }

    /**
     * 动规<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了5.04%的用户<br>
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了39.05%的用户
     */
    public static int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();

        int target = sum / 2;//相撞尽可能小，说明分成两堆的石头尽可能重量相同

        int[] dp = new int[target + 1];

        for (int stone = 0; stone < stones.length; ++stone)
            for (int weight = target; weight >= stones[stone]; --weight)
                dp[weight] = Math.max(dp[weight], dp[weight - stones[stone]] + stones[stone]);


        return sum - 2 * dp[target];
    }

}
