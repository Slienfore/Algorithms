package 栈_队列;

import java.util.Stack;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/28 22:07
 */
//155-实现一个最小栈(常数时间内检索最小值)
public class Demo_155 {
    public static void main(String[] args) {
        Demo_155 minStack = new Demo_155();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   //--> 返回 -3.
        minStack.pop();
        int top = minStack.top();//--> 返回 0.
        System.out.println(top);
        minStack.getMin();   //--> 返回 -2.
    }

    /*
    执行用时：4 ms, 在所有 Java 提交中击败了99.80%的用户
    内存消耗：40.3 MB, 在所有 Java 提交中击败了22.64%的用户
     */
    //封装两个栈
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public Demo_155() {//初始化栈
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int val) {//若"最小栈"为空不用进行比较，直接入栈
        stack.push(val);

        if (minStack.empty()) {
            minStack.push(val);
        } else {//若非空与栈顶元素比较
            int value = minStack.peek();

            if (val <= value) minStack.push(val);//也可以包含相等的元素
        }
    }

    public void pop() {//删除栈顶元素
        if (this.stack.empty()) return;

        //只有当最小栈和栈的元素相等的时候才会进行出栈
        if (stack.pop().equals(this.minStack.peek())) minStack.pop();
    }

    //获取栈顶元素
    public int top() {
        if (stack.empty()) return -1;

        return stack.peek();
    }

    //获取最小值(不用删除)
    public int getMin() {
        if (minStack.empty()) return -1;

        return minStack.peek();
    }
}
