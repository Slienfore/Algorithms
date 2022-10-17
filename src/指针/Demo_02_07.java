package 指针;

import java.util.HashSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/23 18:15
 */
//面试题:02.07 链表相交
public class Demo_02_07 {
    public static void main(String[] args) {
        ListNode listNode_1 = new ListNode();
        ListNode listNode_2 = new ListNode();
        listNode_1.addNode(4);
        listNode_1.addNode(1);
        listNode_1.addNode(8);
        listNode_1.addNode(4);
        listNode_1.addNode(5);

        listNode_2.addNode(5);
        listNode_2.addNode(0);
        listNode_2.addNode(1);
        listNode_2.addNode(8);
        listNode_2.addNode(4);
        listNode_2.addNode(5);

        ListNode node = new ListNode();

        node.displayList(getIntersectionNode_1(listNode_1, listNode_2));
    }

    /*双指针:
    执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：41 MB, 在所有 Java 提交中击败了71.66%的用户
    */
    public static ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        ListNode point_1 = headA, point_2 = headB;

        while (point_1 != point_2) {
            //当一个指针走完了全过程的时候就走另外一个链表,始终会相遇,若没有交集则都会走到尽头
            point_1 = point_1 == null ? headB : point_1.next;
            /* a + b + c = b + a + c*/
            point_2 = point_2 == null ? headA : point_2.next;
        }
        return point_1;
    }

    /*哈希表:
    执行用时：7 ms, 在所有 Java 提交中击败了10.48%的用户
    内存消耗：41.7 MB, 在所有 Java 提交中击败了6.68%的用户
     */
    public static ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (set.contains(headB)) return headB;//如果链表A中包含有链表B中的结点

            headB = headB.next;
        }
        return null;
    }

    /*长度相等终究会相遇
    执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：41.6 MB, 在所有 Java 提交中击败了7.09%的用户
     */
    public static ListNode getIntersectionNode_3(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);

        while (lenA != lenB) {//如果两个链表长度不相等,则将它们移到相同的起点进行一个个比较
            if (lenA > lenB) {
                headA = headA.next;
                lenA--;
            } else {
                headB = headB.next;
                lenB--;
            }
        }

        while (headA != headB) {//此时两个链表的相对起点相等
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private static int length(ListNode node) {
        if (node == null) return 0;
        return length(node.next) + 1;
    }
}
