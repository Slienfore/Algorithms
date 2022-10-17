package 栈_队列;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/31 21:08
 */
//225-队列实现栈
public class Demo_225 {
    public static void main(String[] agrs) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.top(); // 返回 2
        myStack.pop(); // 返回 2
        myStack.empty(); // 返回 False
    }

    /*封装两个队列
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36.2 MB, 在所有 Java 提交中击败了45.18%的用户
    * */
    Queue<Integer> queue_1;//与栈中保持一致的队列
    Queue<Integer> queue_2;//辅助队列

    //初始化栈
    public Demo_225() {
        queue_1 = new LinkedList<>();
        queue_2 = new LinkedList<>();
    }

    /*
    辅助队列用来装进新的元素,随后主队列的元素添加进辅助队列的队尾,这时再复制到主栈
    */
    public void push(int x) {
        queue_2.add(x);
        while (!queue_1.isEmpty()) {
            queue_2.add(queue_1.poll());//将主队列中的元素添加进辅助队列中(出队)
        }
        Queue<Integer> tempQueue;
        tempQueue = queue_1;//此时主队列已经将所有的元素清空了
        queue_1 = queue_2;
        queue_2 = tempQueue;
    }

    //出栈
    public int pop() {
        return queue_1.poll();
    }

    public int top() {
        return queue_1.peek();
    }

    public boolean empty() {
        return queue_1.isEmpty();
    }
}

/*单Deque
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：36 MB, 在所有 Java 提交中击败了81.53%的用户
 */
class MyStack {
    Deque<Integer> deque;

    public MyStack() {
        deque = new ArrayDeque<>();
    }

    //入栈则添加队尾
    public void push(int x) {
        deque.add(x);
    }

    //出栈
    public int pop() {
        int size = deque.size();
        size--;
        while (size-- > 0) {//保留一个元素不变
            deque.add(deque.peek());//将队头元素添加入队尾元素
            deque.poll();//队头出队
        }
        return deque.poll();
    }

    //栈顶元素(即队尾元素)
    public int top() {
        return deque.peekLast();
    }

    public boolean empty() {
        return deque.isEmpty();
    }
}