package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/28 10:59
 */
//441-排列硬币
public class Demo_441 {
    public static void main(String[] args) {
        int coin = 5;
        System.out.println(arrangeCoins(coin));
    }

    /**
     * 二分<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.82%的用户<br>
     * 内存消耗：39 MB, 在所有 Java 提交中击败了7.71%的用户
     */
    public static int arrangeCoins(int n) {
        long left = 1, right = n;//防止爆 int

        while (left < right) {
            long stair = left + (right - left + 1) / 2;

            if (stair * (1 + stair) / 2 <= n)//等差求和，到达该阶梯数所需要硬币
                left = stair;
            else
                right = stair - 1;
        }

        return (int) left;
    }
}
