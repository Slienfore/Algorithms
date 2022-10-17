package 链表;


/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/22 21:48
 */
//206-链表反转：
public class Demo_206 {
    public static void main(String[] args) {

    }


    /*双指针：
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.1 MB, 在所有 Java 提交中击败了68.14%的用户
    */
    public static ListNode reverseList_1(ListNode head) {
        ListNode pNode = head;
        ListNode pPrev = null;
        ListNode reversedHead = head;//保存头结点
        while (pNode != null) {
            ListNode pNext = pNode.next;

            if (pNext == null) {
                reversedHead = pNode;//如果是最后一个结点的话，则进行头结点保存
            }

            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        return reversedHead;
    }

    /*递归：(从前往后进行递归)
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.4 MB, 在所有 Java 提交中击败了11.72%的用户
     */
    public static ListNode reverseList_2(ListNode head) {
        return reverse(null, head);

    }

    private static ListNode reverse(ListNode pPrev, ListNode pNode) {
        if (pNode == null) return pPrev;//如果是最后一个结点的话，则返回头结点
        ListNode pNext = pNode.next;//保存下一个结点

        pNode.next = pPrev;
        pPrev = pNode;
        pNode = pNext;
        return reverse(pPrev, pNode);//递归
    }

    /*妖魔化递归：(从后往前进行递归)
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.4 MB, 在所有 Java 提交中击败了16.45%的用户
     */
    public static ListNode reverseList_3(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pNode = reverseList_3(head.next);
        head.next.next = head;//递归终止的时候，当前结点是其前一个结点(递归返回上一个结点)

        head.next = null;//断开循环指向
        return pNode;
    }
}
