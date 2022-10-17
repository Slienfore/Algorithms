package 链表;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/25 21:12
 */
//24-两两交换链表中的值
public class Demo_24 {

    public static void main(String[] args) {
        ListNode listNode_1 = new ListNode();
        listNode_1.addNode(1);
        listNode_1.addNode(2);
        listNode_1.addNode(3);
        listNode_1.addNode(4);
//        listNode_1.addNode(5);
        listNode_1.displayList(swapPairs(listNode_1.head));
    }
    /*
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：35.8 MB, 在所有 Java 提交中击败了89.76%的用户
    */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(0);//定义虚拟头结点
        dummyNode.next = head;
        ListNode pre = dummyNode;

        while (pre.next != null && pre.next.next != null) {
            ListNode temp = head.next.next;//保存没有进行交换的结点

            pre.next = head.next;//上一个结点 等于 “当前结点” 的 下一个结点

            head.next.next = head;//当前结点的 ”下一个结点“ 的下一个结点指向 ”当前结点“ 进行交换

            head.next = temp;//当前结点指向位交换的结点

            pre = head;//保存当前结点

            head = head.next;//当前结点移到下一个结点
        }
        return dummyNode.next;
    }
}
