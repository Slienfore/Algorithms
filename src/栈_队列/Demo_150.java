package 栈_队列;

import java.util.Stack;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/30 17:13
 */
//150-逆波兰表达式求值
public class Demo_150 {
    public static void main(String[] args) {
        String[] strings = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN_1(strings));
        System.out.println(evalRPN_2(strings));
    }

    /*栈：
    执行用时：6 ms, 在所有 Java 提交中击败了53.63%的用户
    内存消耗：38.2 MB, 在所有 Java 提交中击败了41.71%的用户
    */
    public static int evalRPN_1(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String value : tokens) {
            if (value.equals("*")) {
                Integer pop_1 = stack.pop();
                Integer pop_2 = stack.pop();
                stack.push(pop_1 * pop_2);
            } else if (value.equals("/")) {
                Integer pop_1 = stack.pop();
                Integer pop_2 = stack.pop();
                stack.push(pop_2 / pop_1);
            } else if (value.equals("-")) {
                Integer pop_1 = stack.pop();
                Integer pop_2 = stack.pop();
                stack.push(pop_2 - pop_1);
            } else if (value.equals("+")) {
                Integer pop_1 = stack.pop();
                Integer pop_2 = stack.pop();
                stack.push(pop_1 + pop_2);
            } else {
                stack.push(Integer.parseInt(value));
            }
        }
        return stack.pop();
    }

    /*数组模拟栈
    执行用时：2 ms, 在所有 Java 提交中击败了99.75%的用户
    内存消耗：38.4 MB, 在所有 Java 提交中击败了7.91%的用户
     */
    public static int evalRPN_2(String[] tokens) {
        int[] stack = new int[tokens.length / 2 + 1];//操作数 一定会比 操作符的数目多

        int pos = -1;//初始化“栈顶指针”为负数
        for (String str : tokens) {
            switch (str) {
                case "+" -> {
                    stack[pos - 1] += stack[pos];
                    pos--;
                }
                case "-" -> {
                    stack[pos - 1] -= stack[pos];
                    pos--;
                }
                case "*" -> {
                    stack[pos - 1] *= stack[pos];
                    pos--;
                }
                case "/" -> {
                    stack[pos - 1] /= stack[pos];
                    pos--;
                }
                default -> stack[++pos] = Integer.parseInt(str);//数字入栈，栈顶指针移动
            }
        }
        return stack[pos];
    }

}
