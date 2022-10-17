package 链表;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/23 17:36
 */

//链表基本操作
public class ListNode {
    int val;
    ListNode next;

    //设置双链表的前驱结点：
    ListNode pre;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    ListNode head = null;

    public void addNode(int value) {
        ListNode node = new ListNode(value);//实例化下一个结点

        if (head == null) {
            head = node;
            return;
        }

        ListNode tempNode = head;
        while (tempNode.next != null) tempNode = tempNode.next;
        tempNode.next = node;
    }

    public void displayList(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}
