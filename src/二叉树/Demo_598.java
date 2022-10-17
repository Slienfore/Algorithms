package 二叉树;

import utils.uu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/10 8:32
 */
//598-N叉树的前序遍历
public class Demo_598 {
    public static void main(String[] args) {
        Node node_5 = new Node(5, new ArrayList<>()), node_6 = new Node(6, new ArrayList<>()),
                node_3 = new Node(3,
                        new ArrayList<>() {{
                            add(node_5);
                            add(node_6);
                        }}
                ),
                node_2 = new Node(2, new ArrayList<>()),
                node_4 = new Node(4, new ArrayList<>()),
                root = new Node(1,
                        new ArrayList<>() {{
                            add(node_3);
                            add(node_2);
                            add(node_4);
                        }});

        uu.print(preorder(root));
    }

    static List<Integer> res = new ArrayList<>();


    /**
     * 迭代+栈<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了34.49%的用户<br>
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了34.92%的用户
     */
    public static List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        //利用栈依次从右向左添加孩子结点，那么访问的第一个就是前序序列了
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            ans.add(node.val);//前序遍历

            List<Node> child = node.children;//孩子结点

            for (int i = child.size() - 1; i >= 0; i--)
                stack.push(child.get(i));

        }

        return ans;
    }


    /**
     * DFS<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了10.61%的用户
     */
    private static void dfs(Node node) {
        if (node == null) //出口
            return;

        res.add(node.val);

        for (Node child : node.children)
            dfs(child);
    }
}
