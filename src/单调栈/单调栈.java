package 单调栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/18 22:15
 */
public class 单调栈 {
    public static void main(String[] args) {

        int value = 100;
        Deque<Integer> stack = new ArrayDeque<>() {{
            offerLast(value);
        }};

        //单调递减
        while (!stack.isEmpty() && value > stack.peekLast()) {
            stack.pollLast();
        }

        //单调递增
        while (!stack.isEmpty() && stack.peekLast() > value) {
            stack.pollLast();
        }


    }
}
