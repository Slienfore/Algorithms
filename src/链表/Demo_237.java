package 链表;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/2 21:11
 */
//237-删除链表中的结点
public class Demo_237 {
    public static void main(String[] args) {


    }


    public static void deleteNode(ListNode node) {
        if (node == null || node.next == null) return;

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
