package 二叉树;

import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/21 17:31
 */
//559-N叉树的深度
public class Demo_559 {
    public static void main(String[] args) {
    }
    /**递归<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：38.7 MB, 在所有 Java 提交中击败了14.19%的用户
    */
    public static int maxDepth(Node root) {
        if (root == null) return 0;

        int maxDepth = 0;

        List<Node> child = root.children;//获取孩子结点

        for (Node node : child)
            maxDepth = Math.max(maxDepth, maxDepth(node));//遍历一层结点然后取最大值

        return maxDepth + 1;//最后还要加上根节点
    }
}
