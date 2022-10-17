package 二叉树;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/18 9:17
 */
//二叉树构建
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
