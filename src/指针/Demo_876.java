package 指针;

import java.util.ArrayList;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/23 16:26
 */
//876-链表的中间结点
public class Demo_876 {
    public static void main(String[] agrs) {
        ListNode listNode = new ListNode();
        listNode.addNode(1);
        listNode.addNode(2);
        listNode.addNode(3);
        listNode.addNode(4);
        listNode.addNode(5);
        listNode.addNode(6);

        listNode.displayList(middleNode_3(listNode.head));
    }

    /*快慢指针:
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36 MB, 在所有 Java 提交中击败了23.06%的用户
    */
    public static ListNode middleNode_1(ListNode head) {
        if (head.next == null) return head;
        if (head.next.next == null) return head.next;

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;//快指针每次走两步(快指针走完一圈就是慢指针走到半圈)
            slow = slow.next;
        }
        if (fast.next != null) slow = slow.next;//如果是"偶数"结点的时候快指针无法进行移动,则需要进行慢指针移动
        return slow;
    }

    /*直接获取链表的长度然后进行取中间位置:
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：35.5 MB, 在所有 Java 提交中击败了98.57%的用户
     */
    public static ListNode middleNode_2(ListNode head) {
        int length = listLength(head);
        int mid = length >>> 1;//获取中间结点

        for (int i = 0; i < mid; i++) head = head.next;
        return head;
    }

    //递归(求取链表的长度)
    private static int listLength(ListNode node) {
        if (node == null) return 0;
        return listLength(node.next) + 1;
    }

    /*集合进行存储链表
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：35.6 MB, 在所有 Java 提交中击败了93.09%的用户
     */
    public static ListNode middleNode_3(ListNode head) {
        if (head.next == null) return head;
        if (head.next.next == null) return head.next;

        ArrayList<ListNode> list = new ArrayList<>();
        ListNode node = head;

        while (node != null) {
            list.add(node);
            node = node.next;
        }

        return list.get(list.size() / 2);//直接获取集合中的中间元素
    }
}

