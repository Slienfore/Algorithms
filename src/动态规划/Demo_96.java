package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/10 17:22
 */
//96-不同的二叉搜素树
public class Demo_96 {
    public static void main(String[] args) {
        int num = 10;

        System.out.println(numTrees_1(num));
        System.out.println(numTrees_2(num));

    }

    /**
     * 动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了28.34%的用户
     */
    public static int numTrees_2(int n) {
        int[] dp = new int[n + 1];//结点数量多少组成的搜索二叉树的数量

        dp[0] = 1;//结点为 0 组成的搜索二叉树为 1
        dp[1] = 1;//结点为 1 组成的搜索二叉树为 1

        for (int num = 2; num < n + 1; ++num) //结点个数
            for (int root = 1; root <= num; ++root) {//左、右孩子节点个数组成的二叉树数量

                int leftChild = dp[root - 1], rightChild = dp[num - root];

                dp[num] += leftChild * rightChild;
            }

        return dp[n];
    }


    /**
     * 递归<br>
     * 执行用时：2494 ms, 在所有 Java 提交中击败了5.01%的用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    public static int numTrees_1(int n) {
        if (n == 1 || n == 0)
            return 1;

        int res = 0;

        //左子树有(i - 1)有 N 种排列，剩下的右子树有(n - i) M 种排列，那么以该节点组成的二叉树就是 M * N
        for (int i = 1; i <= n; i++)
            res += numTrees_1(i - 1) * numTrees_1(n - i);

        return res;
    }

}
