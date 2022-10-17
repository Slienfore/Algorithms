package 数据结构;

public class ListNode {
    int val;//该类的值
    ListNode next;//该类的下一个结点

    //迭代访问
    void traverse_1(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {

        }
    }

    //递归访问
    void traverse_2(ListNode head) {
        traverse_2(head.next);
    }
}

class TreeNode {
    int val;
    TreeNode left, right;

    //二叉树遍历
    void traverse(TreeNode root) {
        traverse(root.left);
        traverse(root.right);
    }
}

class NTreeNode {
    int val;
    NTreeNode[] children;
    // n 叉树遍历
    void traverse(NTreeNode root) {
        for (NTreeNode child : root.children) {
            traverse(child);
        }
    }
}
