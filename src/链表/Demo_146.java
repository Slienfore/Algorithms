package 链表;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/16 9:57
 */
//146-LRU缓存
public class Demo_146 {
    public static void main(String[] args) {
        Demo_146 lRUCache = new Demo_146(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}

        System.out.println(lRUCache.get(1));// 返回 1

        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}

        System.out.println(lRUCache.get(2));// 返回 -1 (未找到)

        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}

        System.out.println(lRUCache.get(1));// 返回 -1 (未找到)

        System.out.println(lRUCache.get(3));// 返回 3

        System.out.println(lRUCache.get(4));// 返回 4

    }

    Node head, tail;//队头队尾
    int capacity;
    Map<Integer, Node> map;//使用哈希表快速定位结点

    public Demo_146(int capacity) {
        this.capacity = capacity;

        map = new HashMap<>();

        head = new Node(-1, -1);

        tail = new Node(-1, -1);

        head.right = tail;

        tail.left = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;


        Node node = map.get(key);

        refresh(node);//更新结点

        return node.value;
    }

    public void put(int key, int value) {
        Node node;

        if (!map.containsKey(key)) {//若不包含

            if (map.size() == capacity) {//判断缓存是否已经满了
                Node oldNode = tail.left;
                map.remove(oldNode.key);//移除结点
                delNode(oldNode);
            }

            node = new Node(key, value);
            map.put(key, node);

        } else {//包含则更新值
            node = map.get(key);
            node.value = value;
        }

        refresh(node);//刷新到队头

    }

    //更新结点，需将该节点删除后移到队列头部
    public void refresh(Node node) {//使用过后更新
        delNode(node);

        node.right = head.right;//结点右端与头节点右端建立联系

        node.left = head;//结点与队头建立联系

        head.right.left = node;//队头右端结点与结点建立联系

        head.right = node;//队头与结点建立联系

    }

    public void delNode(Node node) {
        if (node.left == null)
            return;

        Node leftNode = node.left;

        leftNode.right = node.right;//左节点与右节点建立联系

        node.right.left = leftNode;//有右结点与左节点建立联系
    }

    private class Node {//双端队列
        int key, value;

        Node left, right;//链表左右节点

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
