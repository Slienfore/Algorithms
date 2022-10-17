package 单调栈;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/19 21:09
 */
//739-每日温度
public class Demo_739 {
    public static void main(String[] args) {
//        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] nums = {30, 40, 90};
        uu.print(dailyTemperatures(nums));

    }

    /**
     * 单调栈<br>
     * 执行用时：23 ms, 在所有 Java 提交中击败了81.51%的用户<br>
     * 内存消耗：55.2 MB, 在所有 Java 提交中击败了19.01%的用户
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        //寻找一个最高温度(单调递减栈)
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[length];

        for (int cur = 0; cur < length; ++cur) {
            res[cur] = 0;

            while (!stack.isEmpty() && temperatures[stack.peekLast()] < temperatures[cur]) {
                int orin = stack.pollLast();
                res[orin] = cur - orin;//相邻的天数
            }

            stack.offerLast(cur);
        }
        return res;
    }
}
