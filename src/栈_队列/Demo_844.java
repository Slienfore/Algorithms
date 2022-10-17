package 栈_队列;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 8:39
 */
//844-比较含退格的字符串
public class Demo_844 {
    public static void main(String[] args) {
        String str1 = "ab#c", str2 = "ab#c";
        System.out.println(backspaceCompare_1(str1, str2));
        System.out.println(backspaceCompare_2(str1, str2));

    }

    public static boolean backspaceCompare_2(String s, String t) {
        Deque<Character> stk1 = new ArrayDeque<>(), stk2 = new ArrayDeque<>();

        for (Character val : s.toCharArray())
            if (val != '#')
                stk1.offerLast(val);
            else if (!stk1.isEmpty())//遇到退格符进行退格
                stk1.pollLast();

        for (Character val : t.toCharArray())
            if (val != '#')
                stk2.offerLast(val);
            else if (!stk2.isEmpty())//遇到退格符进行退格
                stk2.pollLast();

        while (!stk1.isEmpty() && !stk2.isEmpty())
            if (stk1.pollLast() != stk2.pollLast())
                return false;

        return stk1.isEmpty() && stk2.isEmpty();
    }


    public static boolean backspaceCompare_1(String s, String t) {
        return reBuild(s).equals(reBuild(t));
    }

    private static String reBuild(String orin) {
        StringBuilder sb = new StringBuilder();

        int length = orin.length();
        for (int cur = 0; cur < length; ++cur) {
            if (orin.charAt(cur) != '#')//若没有遇到 '退格'
                sb.append(orin.charAt(cur));
            else {
                if (sb.length() > 0)
                    sb.deleteCharAt(sb.length() - 1);//删除最后一项
            }
        }

        return sb.toString();
    }
}
