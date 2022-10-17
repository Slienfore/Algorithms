package 二叉树;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/18 9:16
 */
//563-二叉树的坡度
public class Demo_563 {
    public static void main(String[] args) {

    }
    private static int res = 0;

    public static int findTilt(TreeNode root) {
        findTiltHelper(root);

        return res;
    }

    /**
     * DFS递归<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了55.25%的用户
     */
    private static int findTiltHelper(TreeNode node) {
        if (node == null) return 0;

        //DFS获取左节点的和
        int leftSum = findTiltHelper(node.left);
        //DFS获取左节点的和
        int rightSum = findTiltHelper(node.right);

        res += Math.abs(leftSum - rightSum);

        return leftSum + rightSum + node.val;//返回当前层的所有结点之和给父节点
    }
}
