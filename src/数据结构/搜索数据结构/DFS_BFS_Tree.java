package 数据结构.搜索数据结构;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/1 15:04
 */
//树的--DFS与BFS算法
public class DFS_BFS_Tree {

    private static class tree {//创建一棵树
        public int val;
        public tree left;
        public tree right;

        public tree(int val, tree left, tree right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //递归实现DFS(层数过深的时候会出现栈溢出)
    private void dfs(tree node) {
        if (node == null) return;
        //其实就是前序遍历
        process(node);
        dfs(node.left);
        dfs(node.right);
    }

    //非递归使用栈来实现DFS
    private void dfsStack(tree root) {
        if (root == null) return;
        //创建一个栈来进行存放各个遍历结点
        Stack<tree> stack = new Stack<>();

        stack.push(root);//将根节点压进栈中
        while (!stack.isEmpty()) {
            tree node = stack.pop();//将栈顶元素弹出来

            process(node);

            if (node.right != null) stack.push(node.right);//右节点总是最后一个输出，所以是先进栈

            if (node.left != null) stack.push(node.left);
        }
    }

    //非递归使用队列实现BFS
    private void bfsQueue(tree root) {
        if (root == null) return;

        LinkedList<tree> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            tree node = queue.poll();

            process(node);

            if (node.left != null) queue.add(node.left);

            if (node.right != null) queue.add(node.right);
        }
    }


    private void process(tree node) {
        System.out.println(node.val);
    }
}
