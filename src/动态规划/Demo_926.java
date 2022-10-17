package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/11 23:40
 */
//926-将字符串翻转到单调递增
public class Demo_926 {
    public static void main(String[] args) {
//        String str = "00110";
        String str = "010110";

        System.out.println(minFlipsMonoIncr_1(str));
        System.out.println(minFlipsMonoIncr_2(str));
    }

    /**
     * 动规(滚动数组)<br>
     * 执行用时：8 ms, 在所有 Java 提交中击败了88.06%的用户<br>
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了66.67%的用户<br>
     * 2022年06月12日  08:52:47
     */
    public static int minFlipsMonoIncr_2(String str) {
        int zero = 0, one = 0;//代表着 当前字符串翻转成 0 || 1 递增最小翻转次数

        for (char val : str.toCharArray()) {
            //【1】前面可以是 0-1, 取最小值, 当前值为 1 时不需要进行翻转, 为 0 时需要进行翻转
            one = Math.min(one, zero) + (val == '1' ? 0 : 1);

            //【0】前面只能是 0, 不需要进行翻转, 为 1, 进行翻转
            zero = val == '0' ? zero : zero + 1;
        }

        return Math.min(one, zero);
    }


    /**
     * 动规<br>
     * 执行用时：36 ms, 在所有 Java 提交中击败了27.73%的用户<br>
     * 内存消耗：49.8 MB, 在所有 Java 提交中击败了5.01%的用户<br>
     * 2022年06月11日  23:48:00
     */
    public static int minFlipsMonoIncr_1(String str) {
        int length = str.length();
        int[][] dp = new int[length + 1][2];//表示以当前位置结尾的递增序列的 转换-0、1 最小次数

        for (int cur = 1; cur <= length; ++cur) {
            if (str.charAt(cur - 1) == '0') {
                //当前值为 0 -翻转可得- 1 -> 翻转后前面可以是 0、1
                dp[cur][1] = Math.min(dp[cur - 1][0], dp[cur - 1][1]) + 1;

                //为 0 不需要翻转
                dp[cur][0] = dp[cur - 1][0];
            } else {
                //当前值为 1 -翻转可得- 0 -> 翻转后前面只能是 0
                dp[cur][0] = dp[cur - 1][0] + 1;

                //当前值为 1 前面可以是 0、1 -> 取最小值
                dp[cur][1] = Math.min(dp[cur - 1][0], dp[cur - 1][1]);
            }
        }

        return Math.min(dp[length][0], dp[length][1]);
    }
}
