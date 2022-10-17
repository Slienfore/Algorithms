package 二叉树;

import utils.uu;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/12 8:02
 */
//590-N叉树的后序遍历
public class Demo_590 {
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

        uu.print(postorder_1(root));

        System.out.println(postorder_2(root));
    }

    /**
     * 迭代<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了8.10%的用户<br>
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了45.12%的用户
     */
    public static List<Integer> postorder_2(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Deque<Node> stack = new ArrayDeque<>();
        Set<Node> set = new HashSet<>();


        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.peek();//获取栈顶元素

            //说明已经到达了最左边的结点，如果弹出栈顶元素的时候到了根节点，添加set来判断是否访问过(否则会死循环)
            if (node.children.size() == 0 || set.contains(node)) {
                stack.pop();
                ans.add(node.val);
                continue;
            }

            List<Node> children = node.children;
            for (int end = children.size() - 1; end >= 0; --end)
                stack.push(children.get(end));//逆序插入

            set.add(node);//说明该结点已经访问过了
        }

        return ans;
    }


    public static List<Integer> postorder_1(Node root) {

        dfs(root);
        return res;
    }

    static List<Integer> res = new ArrayList<>();


    /**
     * 深度优先<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：42.5 MB, 在所有 Java 提交中击败了5.16%的用户
     */
    private static void dfs(Node root) {
        if (root == null)
            return;


        for (Node child : root.children) //即相当与深度优先遍历
            dfs(child);

        res.add(root.val);
    }
}
