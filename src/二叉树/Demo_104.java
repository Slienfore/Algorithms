package 二叉树;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/21 17:50
 */
//104-二叉树的最大深度
public class Demo_104 {
    public static void main(String[] args) {

    }

    /**
     * 递归<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了75.60%的用户
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left) + 1;//统计右孩子结点

        int right = maxDepth(root.right) + 1;//统计右孩子结点

        return Math.max(left, right);
    }
}
