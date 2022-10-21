package 单调栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/21 8:51
 */
// 901-股票价格跨度
public class Demo_901 {

    public static void main(String[] args) {
        Demo_901 demo = new Demo_901();

        System.out.println(demo.next(100));
        System.out.println(demo.next(80));
        System.out.println(demo.next(60));
        System.out.println(demo.next(70));
        System.out.println(demo.next(60));
        System.out.println(demo.next(75));
        System.out.println(demo.next(85));
    }

    // stack[0] 当日股票 prices && stack[1] 比当日股票价格少的 days
    Deque<int[]> stack;

    public Demo_901() {
        stack = new ArrayDeque<>();
    }

    /**
     * 单调递减栈<br>
     * 执行用时：24 ms, 在所有 Java 提交中击败了80.50%的用户<br>
     * 内存消耗：49.2 MB, 在所有 Java 提交中击败了86.52%的用户<br>
     * 2022年10月21日  09:17:51
     */
    public int next(int price) {
        int days = 1;// 当日也算上

        // 单调递减栈 => 寻找左边第一大
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            days += stack.pop()[1];// 累加天数
        }

        // 将当日价格 && 比当日价格少的天数 入栈
        stack.push(new int[]{price, days});

        return days;
    }

}
