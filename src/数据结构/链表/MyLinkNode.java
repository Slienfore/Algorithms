package 数据结构.链表;

/**
 * 单链表的实现
 *
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/23 7:11
 */
public class MyLinkNode {
    class Node {
        Node next = null;
        int data;

        public Node(int data) {//方法重写带参构造
            this.data = data;
        }
    }

    Node head = null;//定义头结点

    /**
     * 功能：返回结点的长度
     */
    private int length() {
        int length = 0;
        Node node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    /**
     * 功能：向链表尾部插入数据
     *
     * @param value
     */
    public void addNode(int value) {
        Node node = new Node(value);//实例化下一个结点

        if (head == null) {
            head = node;
            return;
        }
        Node tempNode = head;
        while (tempNode.next != null) tempNode = tempNode.next;
        tempNode.next = node;
    }


    /**
     * 功能：删除第index个结点
     */
    public boolean delNode(int index) {
        if (index == 0) {
            head = head.next;
            return true;
        }

        if (index == 1) {
            head.next = head.next.next;
            return true;
        }
        int i = 1;

        Node preNode = head;
        Node curNode = preNode.next;

        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return false;
    }

    /**
     * 功能：在不知道头指针的情况下删除指定结点
     */
    public boolean deleteSpecifiedNode(Node node) {
        if (node == null || node.next == null) return false;

        int temp = node.data;
        node.data = node.next.data;

        node.next.data = temp;
        node.next = node.next.next;
        return true;
    }

    /**
     * 功能：链表遍历
     */
    public void displayList() {
        Node node = head;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        MyLinkNode list = new MyLinkNode();
        list.addNode(5);
        list.addNode(3);
        list.addNode(1);
        list.addNode(2);
        list.addNode(55);
        list.addNode(36);

        System.out.println("linkLength:" + list.length());
        System.out.println("head.data:" + list.head.data);
        list.displayList();

        list.delNode(4);
        System.out.println("After deleteNode(4):");

        list.displayList();
    }

    /**
     * 链表反转
     *
     * @param head
     * @return
     */
    public Node ReverseIteratively(Node head) {
        Node pReversedHead = head;
        Node pNode = head;
        Node pPrev = null;

        while (pNode != null) {
            Node pNext = pNode.next;

            if (pNext == null) {
                pReversedHead = pNode;
            }

            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        this.head = pReversedHead;
        return this.head;
    }

    /**
     * 查找单链表的中间节点
     *
     * @param head
     * @return
     */
    public Node SearchMid(Node head) {
        Node fast = this.head, slow = this.head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        System.out.println("Mid:" + slow.data);
        return slow;
    }

    /**
     * 查找倒数 第k个元素
     *
     * @param head
     * @param k
     * @return
     */
    public Node findElem(Node head, int k) {
        if (k < 1 || k > this.length()) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        for (int i = 0; i < k; i++)// 前移k步
            fast = fast.next;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 对链表进行排序
     *
     * @return
     */
    public Node sortList() {
        Node nextNode = null;
        int tmp = 0;
        Node curNode = head;

        while (curNode.next != null) {
            nextNode = curNode.next;

            while (nextNode != null) {
                if (curNode.data > nextNode.data) {
                    tmp = curNode.data;
                    curNode.data = nextNode.data;
                    nextNode.data = tmp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }
        return head;
    }

    /**
     * 删除重复节点
     */
    public void deleteDuplicate(Node head) {
        Node node = head;

        while (node != null) {
            Node pNode = node;

            while (pNode.next != null) {
                if (node.data == pNode.next.data) {//重复的话，则将该节点进行替换
                    pNode.next = pNode.next.next;
                } else
                    pNode = pNode.next;
            }
            node = node.next;
        }
    }

    /**
     * 逆序--输出单链表，采用递归方式实现
     *
     * @param node
     */
    public void printListReversely(Node node) {

        if (node != null) {
            printListReversely(node.next);
            System.out.println("printListReversely:" + node.data);
        }
    }

    /**
     * 判断链表是否有环，单向链表有环时，尾节点相同
     *
     * @param head
     * @return
     */
    public boolean IsLoop(Node head) {
        Node fast = head, slow = head;
        if (fast == null) {
            return false;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                System.out.println("该链表有环");
                return true;
            }
        }
        return !(fast == null || fast.next == null);
    }

    /**
     * 找出链表环的入口
     *
     * @param head
     * @return
     */
    public Node FindLoopPort(Node head) {
        Node fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)//先找出两个指针相遇的时候快指针的位置
                break;
        }

        if (fast == null || fast.next == null) return null;

        slow = head;
        while (slow != fast) {//将慢指针归于起始点，当慢指针追上了快指针的时候则找到了环的入口
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
