package 链表;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 8:13
 */
//82-删除排序链表中的重复元素 II
public class Demo_82 {
    public static void main(String[] args) {
    }

    /**
     * <br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了72.24%的用户<br>
     * 2022年04月02日  08:26:16
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;

        ListNode dummy = new ListNode(-1);//增加一个头结点
        ListNode pre = dummy;
        pre.next = head;

        while (head != null && head.next != null) {
            if (head.val == head.next.val) {//结点相同
                int val = head.val;
                while (head != null && head.val == val)//删除重复节点
                    head = head.next;
                pre.next = head;
            } else {
                pre = head;
                head = head.next;
            }
        }

        return dummy.next;
    }
}