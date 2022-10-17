package 动态规划;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/27 10:03
 */
//376-摆动序列
public class Demo_376 {
    public static void main(String[] args) {
//        int[] nums = {1, 7, 4, 9, 2, 5};
//        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        int[] nums = {33, 53, 12, 64, 50, 41, 45, 21, 97, 35, 47, 92, 39, 0, 93, 55, 40, 46, 69, 42, 6, 95, 51, 68, 72, 9, 32, 84, 34, 64, 6, 2, 26, 98, 3, 43, 30, 60, 3, 68, 82, 9, 97, 19, 27, 98, 99, 4, 30, 96, 37, 9, 78, 43, 64, 4, 65, 30, 84, 90, 87, 64, 18, 50, 60, 1, 40, 32, 48, 50, 76, 100, 57, 29, 63, 53, 46, 57, 93, 98, 42, 80, 82, 9, 41, 55, 69, 84, 82, 79, 30, 79, 18, 97, 67, 23, 52, 38, 74, 15};
        System.out.println(wiggleMaxLength_1(nums));
        System.out.println(wiggleMaxLength_2(nums));
    }

    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了39.12%的用户
     */
    public static int wiggleMaxLength_2(int[] nums) {
        int length = nums.length;

        //下降、上升最长子序列长度
        int[] negDp = new int[length], posDp = new int[length];
        negDp[0] = 1;
        posDp[0] = 1;

        for (int cur = 1; cur < length; ++cur) {
            negDp[cur] = negDp[cur - 1];//拷贝
            posDp[cur] = posDp[cur - 1];//拷贝

            if (nums[cur - 1] > nums[cur])//差值正数
                posDp[cur] = Math.max(posDp[cur], negDp[cur - 1] + 1);//查找其前一项的差值为负数最大值
            else if (nums[cur - 1] < nums[cur])//差值负数
                negDp[cur] = Math.max(negDp[cur], posDp[cur - 1] + 1);//查找其前一项的差值为负数最大值
        }

        return Math.max(negDp[length - 1], posDp[length - 1]);
    }


    /**
     * 动规<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了11.58%的用户<br>
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了41.23%的用户
     */
    public static int wiggleMaxLength_1(int[] nums) {
        int length = nums.length;

        //当前位置的序列的长度(dp[][0]: 差值为正数(下降)， dp[][1]: 差值为负数(上升))
        int[][] dp = new int[length][2];
        for (int[] arr : dp)
            Arrays.fill(arr, 1);

        for (int cur = 1; cur < length; ++cur) {
            int curVal = nums[cur];
            for (int pre = 0; pre < cur; ++pre) {
                int preVal = nums[pre];
                if (preVal > curVal) {//前者大于后者(差值为正数)
                    dp[cur][0] = Math.max(dp[cur][0], dp[pre][1] + 1);//拼接其  差值为负数  的序列

                } else if (preVal < curVal) {//前者小于后者(差值为负数)

                    dp[cur][1] = Math.max(dp[cur][1], dp[pre][0] + 1);//拼接其  差值为正数  的序列
                }
            }
        }
        return Math.max(dp[length - 1][0], dp[length - 1][1]);
    }
}
