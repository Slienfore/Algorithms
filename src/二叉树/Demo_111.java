package 二叉树;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/21 17:59
 */
//111-二叉树的最小深度
public class Demo_111 {
    public static void main(String[] args) {

    }
    /**递归<br>
    执行用时：6 ms, 在所有 Java 提交中击败了52.93%的用户<br>
    内存消耗：58.8 MB, 在所有 Java 提交中击败了46.74%的用户
    */
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;

        //二叉树的左子树为空，右子树非空，不构成子树
        if (root.left == null && root.right != null)
            return minDepth(root.right) + 1;
        else if (root.right == null && root.left != null)
            return minDepth(root.left) + 1;

        return Math.min(minDepth(root.right), minDepth(root.left)) + 1;
    }
}
