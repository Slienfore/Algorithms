package 二叉树;

import utils.uu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/8 21:22
 */
//429-
public class Demo_429 {
    public static void main(String[] args) {

    }

    /**
     * BFS<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了75.11%的用户<br>
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了27.98%的用户<br>
     * 2022年04月08日  21:41:51
     */
    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Node> queue = new ArrayDeque<>();

        if (root != null)
            queue.addLast(root);

        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();

            for (int size = queue.size(); size > 0; --size) {//当前层的节点数(将与其相连的孩子加入)
                Node cur = queue.pollFirst();

                for (Node child : cur.children)
                    queue.addLast(child);

                temp.add(cur.val);
            }
            res.add(temp);
        }

        return res;
    }
}
