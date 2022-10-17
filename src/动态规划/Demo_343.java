package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/10 10:27
 */
//343-整数拆分
public class Demo_343 {
    public static void main(String[] args) {
        int num = 8;
        System.out.println(integerBreak_1(num));
        System.out.println(integerBreak_2(num));

    }

    /**
     * 动规<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了74.07%的用户<br>
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了5.89%的用户
     */
    public static int integerBreak_2(int n) {
        int[] dp = new int[n + 1];//整数 0 、 1不可拆分(初始化为 0)

        for (int num = 2; num < n + 1; num++)
            for (int cur = 1; cur <= (num / 2); cur++) {
                int tar = num - cur;
                dp[num] = Math.max(dp[num], Math.max(cur * (tar), cur * dp[tar]));
            }

        uu.print(dp);
        return dp[n];//数越大，拆分的数最大
    }


    /**
     * 动规<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了74.07%的用户<br>
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了43.96%的用户
     */
    public static int integerBreak_1(int n) {
        int[] dp = new int[n + 1];//代表着当前的数可以被拆分最大值

        for (int num = 2; num < dp.length; num++) //0 与 1 不能被拆分
            for (int j = 1; j < num; j++) {
                //num * (num - j) 是单纯的把整数拆分为两个数相乘，而j * dp[num - j]是拆分成两个以及两个以上的个数相乘
                int compo = Math.max(j * (num - j), j * dp[num - j]);
                dp[num] = Math.max(dp[num], compo);//比较当前数进行拆分大小
            }

        return dp[n];
    }
}
