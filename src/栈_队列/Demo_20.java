package 栈_队列;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/28 11:37
 */
//20-有效的括号
public class Demo_20 {

    public static void main(String[] args) {
        String s = "{({})[{}]}";
        System.out.println(isValid_1(s));
        System.out.println(isValid_2(s));
    }

    /*栈：
    执行用时：1 ms, 在所有 Java 提交中击败了98.94%的用户
    内存消耗：36.5 MB、, 在所有 Java 提交中击败了47.79%的用户
    */
    public static boolean isValid_1(String s) {
        Deque<Character> stack = new LinkedList<>();

        char[] chars = s.toCharArray();

        for (char value : chars) {
            if (value == '(') stack.push(')');
            else if (value == '{') stack.push('}');
            else if (value == '[') stack.push(']');
            else if (stack.isEmpty() || stack.peek() != value) return false;//若是都是右括号不进栈一定不匹配
            else stack.pop();//若是右括号进行判断“栈顶元素”是否匹配，若匹配则进行出栈
        }
        return stack.isEmpty();//如果括号都匹配了，就说明栈已经空了
    }

    /*替换：
    执行用时：32 ms, 在所有 Java 提交中击败了5.07%的用户
    内存消耗：38.5 MB, 在所有 Java 提交中击败了6.91%的用户
     */
    public static boolean isValid_2(String s) {
        int length = s.length() / 2;
        for (int i = 0; i < length; i++) {
            s = s.replace("()", "").replace("{}", "").replace("[]", "");//替换成对括号
        }
        return s.length() == 0;
    }
}