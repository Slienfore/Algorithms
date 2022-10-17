package 二叉树;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/3 17:17
 */
//572-另一棵树的子树
public class Demo_572 {
    public static void main(String[] args) {
    }

    /**
     * DFS<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了78.10%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了62.20%的用户<br>
     * 2022年04月03日  18:28:37
     */
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null)//目标子树为空说明一定相等
            return true;

        if (root == null)//当前树为空说明一定不相等
            return false;

        //以当前结点为父节形成的子树是否相等
        return check(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean check(TreeNode root, TreeNode subRoot) {
        if (subRoot == null && root == null)//两棵树均为空
            return true;

        //两棵树结点不相同
        if (root == null || subRoot == null || root.val != subRoot.val)
            return false;

        //两棵树对称移动结点
        return check(root.left, subRoot.left) && check(root.right, subRoot.right);
    }

}
