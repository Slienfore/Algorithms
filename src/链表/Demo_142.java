package 链表;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/27 20:41
 */
//142-环形链表II(寻找环的起点)
public class Demo_142 {
    public static void main(String[] args) {
        ListNode node = new ListNode();
    }

    /*快慢指针
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.4 MB, 在所有 Java 提交中击败了66.08%的用户
    */
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {//说明有环

                slow = head;
                while (slow != fast) {//将慢指针置于头结点从新开始找环的入口
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
