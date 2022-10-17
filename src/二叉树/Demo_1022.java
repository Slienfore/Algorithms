package 二叉树;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/5/30 10:55
 */
//1022-从根到叶的二进制数之和
public class Demo_1022 {
    public static void main(String[] args) {

    }

    /**
     * 深搜<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了48.10%的用户<br>
     * 2022年05月30日  11:03:48
     */
    public static int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int val) {
        if (root == null) return 0;

        val = (val << 1) | root.val;

        if (root.left == null && root.right == null) return val;

        return dfs(root.left, val) + dfs(root.right, val);//左、右结点
    }
}
