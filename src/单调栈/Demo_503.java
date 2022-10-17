package 单调栈;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/19 20:07
 */
//503-下一个更大元素
public class Demo_503 {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 1};
        int[] nums = {1, 2, 3, 4, 3};
//        int[] nums = {1, 2, 3, 3};
//        int[] nums = {1, 2, 3, 2, 1};
        uu.print(nextGreaterElements_1(nums));
        uu.print(nextGreaterElements_2(nums));
    }

    /**
     * 单调栈<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了95.03%的用户<br>
     * 内存消耗：43.1 MB, 在所有 Java 提交中击败了10.27%的用户
     */
    public static int[] nextGreaterElements_2(int[] nums) {
        int length = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();

        int[] res = new int[length];
        Arrays.fill(res, -1);

        for (int cur = 0; cur < length * 2; ++cur) {//循环数组。两倍数组长度
            int val = nums[cur % length];

            while (!stack.isEmpty() && nums[stack.peekLast()] < val)
                res[stack.pollLast()] = val;

            stack.offerLast(cur % length);
        }

        return res;
    }


    /**
     * 单调栈<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了98.98%的用户<br>
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了51.97%的用户
     */
    public static int[] nextGreaterElements_1(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];

        Deque<Integer> stack = new ArrayDeque<>();//单调递减栈(寻找第一大)

        for (int cur = 0; cur < length; ++cur) {
            int val = nums[cur];

            res[cur] = -1;//初始化

            while (!stack.isEmpty() && nums[stack.peekLast()] < val) {
                int index = stack.pollLast();
                res[index] = val;
            }

            stack.offerLast(cur);
        }

        for (int cur = 0; cur < length && !stack.isEmpty(); ++cur) {
            int val = nums[cur];
            while (!stack.isEmpty() && nums[stack.peekLast()] < val)
                res[stack.pollLast()] = val;
        }

        return res;
    }
}
