package 位运算;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/5 8:24
 */
//762-二进制表示中质数个计算置位
public class Demo_762 {
    public static void main(String[] args) {
        int left = 6, right = 10;
//        int left = 10, right = 15;
//        int left = 842, right = 888;
        System.out.println(countPrimeSetBits(left, right));
    }

    /**
     * 模拟<br>
     * 执行用时：81 ms, 在所有 Java 提交中击败了11.28%的用户<br>
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了40.05%的用户<br>
     * 2022年04月05日  08:54:35
     */
    public static int countPrimeSetBits(int left, int right) {
        int res = 0;
        while (left <= right) {
            if (check(left)) {
                ++res;
                System.out.println(left);
            }
            ++left;
        }

        return res;
    }

    private static boolean check(int num) {

        int cnt = 0;
        while (num != 0) {
            if (num % 2 == 1)
                ++cnt;
            num /= 2;
        }

        return isPrime(cnt);
    }

    private static boolean isPrime(int num) {
        if (num <= 3)//在 3 以内，只有 2， 3是质数
            return num > 1;

        if (num % 6 != 1 && num % 6 != 5)//质数只与 6 的倍数相邻
            return false;

        for (int cur = 5; cur <= Math.sqrt(num); cur += 6) //判断与 6倍数 相邻的数是否会被其奇数整除
            if (num % cur == 0 || num % (cur + 2) == 0)
                return false;

        return true;
    }
}
