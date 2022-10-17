package 字符串;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/5/29 21:04
 */
//1021-删除最外层的括号
public class Demo_1021 {
    public static void main(String[] args) {
        String str = "(()())(())";
//        String str = "(()())(())(()(()))";
        System.out.println(removeOuterParentheses(str));
    }

    public static String removeOuterParentheses(String str) {
        char[] arr = str.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (char cur : arr) {
            if (cur == ')') stack.pollLast();

            //删除最外面一层括号(保证栈不为空)
            if (!stack.isEmpty()) sb.append(cur);

            //只有左括号时候才入栈
            if (cur == '(') stack.offerLast(')');
        }

        return sb.toString();
    }
}
