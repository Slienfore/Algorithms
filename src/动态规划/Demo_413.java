package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/25 23:17
 */
//413-等差数列的划分
public class Demo_413 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(numberOfArithmeticSlices_1(nums));
        System.out.println(numberOfArithmeticSlices_2(nums));
    }

    /**双指针<br>
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     内存消耗：39.1 MB, 在所有 Java 提交中击败了52.01%的用户*/
    public static int numberOfArithmeticSlices_2(int[] nums) {
        int length = nums.length;
        if (length < 3)
            return 0;

        int res = 0;

        for (int cur = 0; cur < length - 2; ++cur) {//最少三个数
            int diff = nums[cur + 1] - nums[cur];//后一个数[cur + 1]

            for (int last = cur + 2; last < length; ++last) //后后一个数[cur + 2]
                if (nums[last] - nums[last - 1] == diff)
                    ++res;
                else
                    break;//必须连续
        }

        return res;
    }


    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了48.06%的用户
     */
    public static int numberOfArithmeticSlices_1(int[] nums) {
        int length = nums.length;
        if (length < 3)
            return 0;
        int[] dp = new int[length];//以当前字符结尾的、长度大于 "3" 形成的等差数列的个数
        int res = 0;

        for (int cur = 2; cur < length; ++cur) {
            if (nums[cur] - nums[cur - 1] == nums[cur - 1] - nums[cur - 2]) {//构成等差数列
                dp[cur] += dp[cur - 1] + 1;//在 " 以前面的数结尾 " 的等差数列再拼接上 " 当前数 "
            }

            res += dp[cur];//累加等差数列个数
        }

        return res;
    }
}
