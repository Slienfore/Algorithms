package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/25 14:02
 */
//263-丑数
public class Demo_263 {
    public static void main(String[] args) {
        int num = 6;
        System.out.println(isUgly(num));
    }
    /**数学<br>
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     内存消耗：38.5 MB, 在所有 Java 提交中击败了44.61%的用户*/
    public static boolean isUgly(int n) {
        if (n <= 0)//丑数是非负数
            return false;

        // N == 2^a x 3^b x 5^c(丑数由：2、3、5的倍数)
        int[] arr = {2, 3, 5};
        for (int val : arr)
            while (n % val == 0)//反复除以2、3、5，直到最后不包括这三个质因数
                n /= val;

        return n == 1;
    }
}
