package 动态规划;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/4 21:05
 */
//509-斐波那契数
public class Demo_509 {

    public static void main(String[] args) {
        int n = 5;
/*        System.out.println(fib_1(n));
        System.out.println(fib_2(n));
        System.out.println(fib_3(n));
        System.out.println(fib_4(n));*/
    }

    /**
     * 基础DP<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了31.06%的用户
     */
    public static int fib_1(int n) {
        if (n == 0) return 0;

        int[] dp = new int[n + 1];//因为数组中新开了一个dp[0] = 0,所以需要 + 1
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {//如果 给 n = 1时 i > 1所以能够返回dp [n]
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 递归<br>
     * 执行用时：9 ms, 在所有 Java 提交中击败了10.84%的用户<br>
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了19.87%的用户
     */
    public static int fib_4(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        return fib_4(n - 1) + fib_4(n - 2);
    }


    /**
     * 双指针<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了21.71%的用户
     */
    public static int fib_3(int n) {
        if (n == 0)
            return 0;

        int pre = 0, cur = 1;
        for (int i = 2; i <= n; i++) {
            int temp = cur;
            cur += pre;
            pre = temp;
        }
        return cur;
    }

    /**
     * 优化空间<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了19.31%的用户
     */
    public static int fib_2(int n) {
        if (n == 0) return 0;

        int a = 0, b = 1;//初始化前两个数

        for (int i = 2; i <= n; i++) {
            int sum = a + b;//前两个数之和
            a = b;
            b = sum;
        }

        return b;
    }
}