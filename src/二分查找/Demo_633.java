package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/3 10:20
 */
//633-平方数之和
public class Demo_633 {
    public static void main(String[] args) {
        int num = 1;
        System.out.println(judgeSquareSum(num));
    }

    /**
     * 双指针<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了91.67%的用户<br>
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了37.06%的用户<br>
     * 2022年04月03日  10:35:23
     */
    public static boolean judgeSquareSum(int c) {

        for (long left = 0, right = (long) Math.sqrt(c); left <= right; ) {
            long sum = left * left + right * right;
            if (sum == c)
                return true;
            else if (sum > c) {
                --right;

            } else if (sum < c)
                ++left;
        }

        return false;
    }
}
