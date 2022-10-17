package 指针;


import java.util.HashMap;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/23 11:27
 */
//19-删除链表的倒数第N个结点
public class Demo_19 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        listNode.addNode(1);
        listNode.addNode(2);
        listNode.addNode(3);
        listNode.addNode(4);
        listNode.addNode(5);


        listNode.displayList(removeNthFromEnd_4(listNode.head, 3));
    }

    /*双指针
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36.1 MB, 在所有 Java 提交中击败了93.62%的用户
     */
    public static ListNode removeNthFromEnd_1(ListNode head, int n) {
        if (head == null) return null;//如果只有一个元素则直接进行删除

        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < n; i++) {//快指针走 n 步
            fast = fast.next;
        }
        //如果快指针走到了最后一个结点,那么就是删除第一个结点
        if (fast == null) return head.next;

        //使快指针比慢指针多走一步,就可以停留在删除结点之前的位置
        fast = fast.next;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }

    /*使用求取链表长度进行查找:
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36.3 MB, 在所有 Java 提交中击败了67.74%的用户
     */
    public static ListNode removeNthFromEnd_3(ListNode head, int n) {
        if (head == null) return null;//如果只有一个元素则直接进行删除

        int pos = listLength(head) - n;//获取删除结点的位置
        //删除的是第一个节点
        if (pos == 0) return head.next;

        ListNode preDel = head;
        //搜寻删除结点之前的位置
        for (int i = 0; i < pos - 1; i++) {
            preDel = preDel.next;
        }

        preDel.next = preDel.next.next;
        return head;
    }

    private static int listLength(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    /*递归:(逆序递归)
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：35.8 MB, 在所有 Java 提交中击败了99.90%的用户
     */
    public static ListNode removeNthFromEnd_4(ListNode head, int n) {
        int pos = length(head, n);
        //如果删除的是首结点的时候
        if (pos == n) return head.next;

        return head;
    }

    private static int length(ListNode node, int n) {
        if (node == null) return 0;//搜寻到最前面的元素的时候

        int pos = length(node.next, n) + 1;//递归逆序获取长度

        //如果获取到的位置是删除结点之前(一层递归完成后进行判断)
        if (pos == n + 1) node.next = node.next.next;

        return pos;
    }


    /*哈希表:
    执行用时：1 ms, 在所有 Java 提交中击败了8.13%的用户
    内存消耗：37.3 MB, 在所有 Java 提交中击败了8.07%的用户
     */
    public static ListNode removeNthFromEnd_2(ListNode head, int n) {
        if (head == null) return null;//如果只有一个元素则直接进行删除

        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode node = head;
        int index = 1;

        //用哈希表将链表的各个结点进行存储
        while (node != null) {
            map.put(index++, node);
            node = node.next;
        }
        int size = map.size();

        if (size == 1) return null;//只有一个元素的时候则

        else if (n == size) head = head.next;//删除的是第一个元素

        else if (n == 1) map.get(size - 1).next = null;//删除的是最后一个元素

        else {
            map.get(size - n).next = map.get(size - n + 2);
        }
        return head;
    }
}
