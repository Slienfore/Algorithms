package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/28 22:25
 */
//673-最长递增子序列的个数
public class Demo_673 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
//        int[] nums = {2, 2, 2, 2, 2};
//        int[] nums = {1, 1, 1, 2, 2, 2, 3, 3, 3};//27
//        int[] nums = {100, 90, 80, 70, 60, 50, 60, 70, 80, 90, 100};
        System.out.println(findNumberOfLIS_1(nums));
        System.out.println(findNumberOfLIS_2(nums));
    }

    /**
     * 动规<br>
     * 执行用时：16 ms, 在所有 Java 提交中击败了91.99%的用户<br>
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了69.32%的用户<br>
     * 2022年04月01日  18:52:32
     */
    public static int findNumberOfLIS_2(int[] nums) {
        int length = nums.length;
        //dp:记录以当前数字结尾的最长递增子序列的长度，count:记录相同长度递增子序列的个数
        int[] dp = new int[length], count = new int[length];
        Arrays.fill(dp, 1);//每个数字默认最长长度为 1
        Arrays.fill(count, 1);//每个子序列的默认个数为 1

        int maxLen = 1;
        for (int cur = 1; cur < length; ++cur) {
            for (int pre = 0; pre < cur; ++pre) {
                if (nums[cur] <= nums[pre])//必须是严格递增
                    continue;

                if (dp[pre] + 1 > dp[cur]) {//拼接上以当前数字结尾子序列更长
                    dp[cur] = dp[pre] + 1;//拼接
                    count[cur] = count[pre];//更新最长长度
                } else if (dp[pre] + 1 == dp[cur])
                    count[cur] += count[pre];//相同长度的子序列

            }

            maxLen = Math.max(maxLen, dp[cur]);
        }

        int res = 0;
        for (int cur = 0; cur < length; ++cur)
            if (dp[cur] == maxLen)//统计相同长度的递增子序列个数
                res += count[cur];

        return res;
    }


    /**
     * 动规<br>
     * 执行用时：23 ms, 在所有 Java 提交中击败了24.20%的用户<br>
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了35.09%的用户
     */
    public static int findNumberOfLIS_1(int[] nums) {
        int length = nums.length;
        if (length == 1)
            return 1;

        int[][] dp = new int[length][2];//构建第二列用来存储相同长度子序列的个数
        for (int[] val : dp)
            Arrays.fill(val, 1);

        int maxLength = 1, res = 1;
        for (int i = 1; i < length; i++) {

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {//如果当前元素可以进行插入的时候

                    if (dp[j][0] + 1 > dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];//长度不相等进行重置
                    } else if (dp[j][0] + 1 == dp[i][0]) {//若长度相等时候则进行累加
                        dp[i][1] += dp[j][1];
                    }

                }
            }

            if (maxLength == dp[i][0]) {//如果长度不变，那么就是长度相同的子序列
                res += dp[i][1];
            } else if (dp[i][0] > maxLength) {//长度变长，需要进行重置
                maxLength = dp[i][0];
                res = dp[i][1];
            }

        }

        return res;
    }
}
