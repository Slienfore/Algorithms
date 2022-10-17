package 栈_队列;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/29 21:31
 */
//1047-删除字符串中所有相邻重复项
public class Demo_1047 {
    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates_1(s));
        System.out.println(removeDuplicates_2(s));
        System.out.println(removeDuplicates_3(s));
    }

    /*单栈再反转
    执行用时：25 ms, 在所有 Java 提交中击败了41.55%的用户
    内存消耗：39.1 MB, 在所有 Java 提交中击败了56.01%的用户
    */
    public static String removeDuplicates_1(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for (char value : chars) {
            if (stack.empty() || stack.peek() != value) {//当栈为空的时候，或者栈顶元素与对应值不相等时
                stack.push(value);

            } else {
                stack.pop();
            }
        }
        StringBuilder builder = new StringBuilder();

        while (!stack.empty()) {
            builder.append(stack.pop());
        }

        return builder.reverse().toString();
    }

    /*双向队列
    执行用时：149 ms, 在所有 Java 提交中击败了20.07%的用户
    内存消耗：40.6 MB, 在所有 Java 提交中击败了10.82%的用户
     */
    public static String removeDuplicates_2(String s) {
        //ArrayDeque比LinkedList在两端进行迭代和添加/删除操作效率更高
        ArrayDeque<Character> deque = new ArrayDeque<>();

        char[] chars = s.toCharArray();
        for (char value : chars) {
            if (deque.isEmpty() || deque.peek() != value) {
                deque.push(value);//将元素压进队列中(即就是”后进先出“与栈一样)
            } else {
                deque.pop();//出栈
            }
        }
        String result = "";
        //此时还是栈模式--因此需要进行从"栈底"即"队尾"进行出队
        while (!deque.isEmpty()) {
            result += deque.removeLast();
        }
        return result;
    }


    /*字符串做栈
    执行用时：9 ms, 在所有 Java 提交中击败了86.69%的用户
    内存消耗：39.2 MB, 在所有 Java 提交中击败了42.61%的用户
     */
    public static String removeDuplicates_3(String s) {
        StringBuilder buffer = new StringBuilder();
        int position = -1;
        char[] chars = s.toCharArray();

        for (char value : chars) {
            if (position < 0 || buffer.charAt(position) != value) {//当栈为空的时候，或者栈顶元素不相等时进行拼接
                buffer.append(value);
                position++;
            } else {
                buffer.deleteCharAt(position);
                position--;
            }
        }
        return buffer.toString();
    }
}
