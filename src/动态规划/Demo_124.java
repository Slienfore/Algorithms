package 动态规划;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/22 9:37
 */
//124-二叉树的最大路径和
public class Demo_124 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10,
                new TreeNode(9, null, null),
                new TreeNode(20,
                        new TreeNode(15, null, null),
                        new TreeNode(7, null, null)));

        System.out.println(maxPathSum(root));

    }

    public static int maxPathSum(TreeNode root) {
        dfs(root);

        return max;
    }

    static int max = Integer.MIN_VALUE;//防止取负数影响

    /**
     * 动规(递归)<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了29.54%的用户<br>
     * 内存消耗：42.9 MB, 在所有 Java 提交中击败了32.57%的用户
     */
    private static int dfs(TreeNode root) {
        //路径：左中右、左节点出发回到根节点、右节点出发回到根节点
        if (root == null)
            return 0;

        //左分支(左孩子一直向下走)提供的最大路径和(左分支最大节点值为负数，则不选)
        int maxLeft = Math.max(dfs(root.left), 0);

        //右分支(右孩子一直向下走)提供的最大路径和(右分支最大节点值为负数，则不选)
        int maxRight = Math.max(dfs(root.right), 0);

        /*Math.max(maxLeft, maxRight) + root.val,取左右结点向下走的最大值,
        maxLeft + maxRight + root.val, 经过该结点的最大路径和
        以上将其综合Math.max(Math.max(maxLeft, maxRight) + root.val, maxLeft + maxRight + root.val)
         */
        int through = Math.max(maxLeft, maxRight) + root.val;//该节点的向左、右分支最大值(经过根节点)

        int noThrough = maxLeft + maxRight + root.val;//该节点的连通左中右结点的值(不经过该结点的父节点)

        int maxPath = Math.max(noThrough, through);

        max = Math.max(max, maxPath);//经过该节点获得的最大路径和

        return through;//当前 root 左、右分支给当前 root 的父节点的贡献
    }
}
