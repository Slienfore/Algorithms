package 链表;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/25 21:43
 */
//203-移除链表中的元素
public class Demo_203 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        listNode.addNode(1);
        listNode.addNode(2);
        listNode.addNode(2);
        listNode.addNode(2);
        listNode.addNode(1);
//        ListNode node_1 = removeElements_1(listNode.head, 7);
//        listNode.displayList(node_1);

        ListNode node_2 = removeElements_2(listNode.head, 2);
        listNode.displayList(node_2);
    }

    /*虚拟头结点
    执行用时：1 ms, 在所有 Java 提交中击败了96.29%的用户
    内存消耗：39.3 MB, 在所有 Java 提交中击败了58.44%的用户
    */
    public static ListNode removeElements_1(ListNode head, int val) {
        //定义一个虚拟头结点
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else pre = cur;//如果不匹配则将前驱结点位置进行移动

            cur = cur.next;
        }
        return dummy.next;
    }

    /*不设置头结点
    执行用时：1 ms, 在所有 Java 提交中击败了96.28%的用户
    内存消耗：39.2 MB, 在所有 Java 提交中击败了71.49%的用户
     */
    public static ListNode removeElements_2(ListNode head, int val) {
        while (head != null && head.val == val) {//删除的位置在头结点则需要一个个将头结点位移
            head = head.next;
        }

        if (head == null) return head;

        ListNode pre = head;

        ListNode cur = head.next;//此时在头结点的位置的元素不必要进行删除了
        while (cur != null) {
            if (cur.val == val) pre.next = cur.next;

            else pre = cur;//(删除元素后)不必要进行保存当前结点

            cur = cur.next;
        }
        return head;
    }
}
