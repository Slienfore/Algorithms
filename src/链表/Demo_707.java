package 链表;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/26 16:11
 */
//707-设计链表
public class Demo_707 {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.disPlayList(linkedList);

        linkedList.addAtTail(3);
        linkedList.disPlayList(linkedList);

        linkedList.addAtIndex(1, 2);   //链表变为 1-> 2-> 3
        linkedList.disPlayList(linkedList);

        int value = linkedList.get(1);//返回 2
        System.out.println("获取指定值: " + value);

        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        linkedList.disPlayList(linkedList);

        int end = linkedList.get(1);//返回3
        System.out.println("获取指定值: " + end);

    }
}

/*单链表
    执行用时：12 ms, 在所有 Java 提交中击败了12.70%的用户
    内存消耗：38.5 MB, 在所有 Java 提交中击败了98.15%的用户
*/
class MyLinkedList {
    int size;//记录链表元素存储的个数
    ListNode head;//定义虚拟头结点

    public MyLinkedList() {//初始化链表
        size = 0;
        head = new ListNode(0);
    }

    //获取第index个结点的数值
    public int get(int index) {
        //判断index是否合法
        if (index < 0 || index >= size) return -1;

        ListNode cur = head;

        for (int i = 0; i <= index; i++) {//包含一个虚拟头结点，所以是 <=
            cur = cur.next;
        }
        return cur.val;
    }

    //在链表前插入一个结点
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    //链表后插入一个结点
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /*
    在第index个结点之前插入一个新结点
    eg_1:若index为0，则新插入的结点为链表的头结点
    eg_2:若index等于链表的长度，则说明新插入的结点为链表的尾结点
    eg_3:若插入的结点大于链表的长度，则返回空
    */
    public void addAtIndex(int index, int val) {
        if (index > size) return;

        if (index < 0) index = 0;
        size++;
        //寻找插入新结点的前驱
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        ListNode node = new ListNode(val);//因为定义的是虚拟头结点，所以不需要判断在哪儿添加
        node.next = cur.next;
        cur.next = node;
    }

    //删除第index个结点
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        size--;
        ListNode cur = head;
        for (int i = 0; i < index; i++) {//寻找前驱结点
            cur = cur.next;
        }
        cur.next = cur.next.next;
    }

    //遍历链表
    public void disPlayList(MyLinkedList node) {
        for (int i = 0; i < size; i++) {
            System.out.print(node.get(i) + " - ");
        }
        System.out.println();
    }
}

/*
双链表：
执行用时：8 ms, 在所有 Java 提交中击败了86.49%的用户
内存消耗：39.1 MB, 在所有 Java 提交中击败了48.54%的用户
*/
class DoubleMyLinkedList {
    int size;
    ListNode head, tail;//设置虚拟头结点还有尾结点

    public DoubleMyLinkedList() {//初始化双链表
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.pre = head;
    }


    //头结点进行添加元素
    public void addAtHead(int val) {
        ListNode cur = head;
        ListNode node = new ListNode(val);

        node.next = cur.next;
        cur.next.pre = node;
        cur.next = node;
        node.pre = cur;
        size++;
    }

    //添加结点到尾结尾
    public void addAtTail(int val) {
        ListNode cur = tail;
        ListNode node = new ListNode(val);

        node.next = tail;//最后指向的是尾结点
        node.pre = cur.pre;
        cur.pre.next = node;
        cur.pre = node;
        size++;
    }

    //获取指定位置上的结点
    public int get(int index) {
        if (index < 0 || index >= size) return -1;

        ListNode cur;
        //为提高搜索效率，判断从头结点还是从为节点进行遍历
        if (index < ((size - 1) / 2)) {
            cur = head;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;//搜索后驱结点
            }
        } else {
            cur = tail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.pre;//搜索前驱结点
            }
        }
        return cur.val;
    }

    //指定位置添加元素
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        else if (index < 0) index = 0;

        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        ListNode node = new ListNode(val);
        node.next = cur.next;
        cur.next.pre = node;
        node.pre = cur;
        cur.next = node;
        size++;
    }

    //指定位置删除元素
    public void deleteAtIndex(int index) {
        if (index >= size || index < 0) return;
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.next.next.pre = cur;//修改前驱指向
        cur.next = cur.next.next;
        size--;
    }
}

