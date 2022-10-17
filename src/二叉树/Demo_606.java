package 二叉树;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/19 13:56
 */
//606-根据二叉树创建字符串
public class Demo_606 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2,
                new TreeNode(4, null, null), null
        ), new TreeNode(3, null, null));

        System.out.println(tree2str(root));
    }

    static StringBuilder builder = new StringBuilder();

    public static String tree2str(TreeNode root) {
        dfs(root);
        return builder.deleteCharAt(builder.length() - 1).toString();

    }

    /**深搜<br>
     执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     内存消耗：41.3 MB, 在所有 Java 提交中击败了27.37%的用户*/
    private static void dfs(TreeNode root) {

        builder.append(root.val);

        if (root.left != null) {
            builder.append("(");
            dfs(root.left);
        } else if (root.right != null)//左子树为空、右子树不为空
            builder.append("()");

        if (root.right != null) {//左右子树起始均需要拼接" ( "
            builder.append("(");
            dfs(root.right);
        }

        builder.append(")");
    }
}
