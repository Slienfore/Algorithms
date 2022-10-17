package 栈_队列;

import java.util.Stack;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/28 21:49
 */
//232-用栈实现队列
public class Demo_232 {

    public static void main(String[] args) {
        Demo_232 myQueue = new Demo_232();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
    }

    /*
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36.1 MB, 在所有 Java 提交中击败了76.71%的用户
    */
    //封装两个栈
    private Stack<Integer> stack_1;
    private Stack<Integer> stack_2;

    public Demo_232() {//初始化栈
        this.stack_1 = new Stack<>();
        this.stack_2 = new Stack<>();
    }

    public void push(int x) {//入栈的时候都进到第一个栈中
        stack_1.push(x);
    }

    public int pop() {//出队列，删除元素
        if (empty()) return -1;
        //如果第二个”出栈“中 不为空则开始进栈
        if (this.stack_2.empty()) {
            while (!stack_1.empty()) {
                stack_2.push(stack_1.pop());//进栈队列将元素传进 “出栈队列”
            }
        }
        return this.stack_2.pop();
    }

    public int peek() {//获取队头元素但不删除
        if (empty()) return -1;
        //如果第二个”出栈“中 不为空则开始进栈
        if (this.stack_2.empty()) {
            while (!stack_1.empty()) {
                stack_2.push(stack_1.pop());//进栈队列将元素传进 “出栈队列”
            }
        }
        return this.stack_2.peek();
    }

    public boolean empty() {//判断队列是否为空
        return (stack_1.empty() && stack_2.empty());
    }
}
