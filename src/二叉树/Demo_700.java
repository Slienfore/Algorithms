package 二叉树;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/26 7:28
 */
//700-二叉搜索树的搜索
public class Demo_700 {
    public static void main(String[] args) {

    }

    /**递归<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：39.3 MB, 在所有 Java 提交中击败了52.28%的用户
    */
    public static TreeNode searchBST(TreeNode root, int val) {
            if (root == null)
                return null;
            if (root.val == val)
                return root;

            //二叉搜索树是带有有序性的(结点小的在左边，结点大的在右边)
            return searchBST(val < root.val ? root.left : root.right, val);
    }
}
