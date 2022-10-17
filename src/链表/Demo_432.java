package 链表;

import utils.uu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/16 9:13
 */
//432-全 O(1) 的数据类型
public class Demo_432 {

    /**
     * 哈希表+双端队列<br>
     * 执行用时：49 ms, 在所有 Java 提交中击败了64.98%的用户<br>
     * 内存消耗：57.5 MB, 在所有 Java 提交中击败了13.23%的用户
     */
    public static void main(String[] args) {
        Demo_432 ob = new Demo_432();
        ob.inc("hello");
        ob.inc("hello");
        System.out.println(ob.getMaxKey());
        System.out.println(ob.getMinKey());
        ob.inc("leet");
        System.out.println(ob.getMaxKey());
        System.out.println(ob.getMinKey());

    }

    Node head, tail;//头尾结点
    Map<String, Node> map;//映射每一个 key 位于的计数结点


    public Demo_432() {
        map = new HashMap<>();

        head = new Node(-100);
        tail = new Node(-100);
        head.right = tail;
        tail.left = head;
    }

    public void inc(String key) {
        Node node;

        if (!map.containsKey(key)) {//不包含该键时

            if (head.right.count == 1) {//若有 计数为 1 的结点(直接加入 key )
                node = head.right;

            } else {
                node = new Node(1);
                node.right = head.right;
                node.left = head;
                head.right.left = node;
                head.right = node;

            }
            node.set.add(key);
            map.put(key, node);

        } else {
            node = map.get(key);
            node.set.remove(key);//增加计数了，要从该计数结点移除

            int count = node.count;

            Node nextNode;

            if (node.right.count == count + 1) {//若右边有计数 增加 1 的结点
                nextNode = node.right;

            } else {
                nextNode = new Node(count + 1);

                nextNode.right = node.right;
                nextNode.left = node;
                node.right.left = nextNode;
                node.right = nextNode;

            }
            nextNode.set.add(key);
            map.put(key, nextNode);

            clear(node);//释放结点
        }
    }

    public void dec(String key) {
        Node node = map.get(key);

        node.set.remove(key);//移除该元素

        int count = node.count;

        if (count == 1) {
            map.remove(key);

        } else {

            Node preNode;
            if (node.left.count == count - 1) {//若左边为计数减一的结点
                preNode = node.left;

            } else {//否则新创结点
                preNode = new Node(count - 1);//即保存该计数结点，将新创的结点迁移到前面

                preNode.right = node;
                preNode.left = node.left;
                node.left.right = preNode;
                node.left = preNode;

            }
            preNode.set.add(key);
            map.put(key, preNode);

        }

        clear(node);
    }

    public String getMaxKey() {
        Node node = tail.left;//最大值位于尾结点左边

        for (String tar : node.set)
            return tar;

        return "";
    }

    public String getMinKey() {
        Node node = head.right;//最小结点位于头节点右端

        for (String tar : node.set)
            return tar;

        return "";
    }

    public void clear(Node node) {//若计数结点的 set 中没有存放 key 值
        if (node.set.size() != 0)
            return;

        node.left.right = node.right;
        node.right.left = node.left;
    }

    private class Node {//计数结点(每一个结点代表着数量的多少)，使用 set 来存放 key 值
        int count;

        Set<String> set = new HashSet<>();

        Node left, right;

        Node(int count) {
            this.count = count;
        }

    }
}
