package 二叉树;

import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/21 17:29
 */
//N叉树构建
public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
