package 单调栈;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/19 21:21
 */
//456-132模式
public class Demo_456 {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4};//false
//        int[] nums = {3, 1, 4, 2};//true
//        int[] nums = {-1, 3, 2, 0};//true
//        int[] nums = {1, 0, -4, -3};//false
//        int[] nums = {3, 5, 0, 3, 4};//true
        int[] nums = {-2, 1, 2, -2, 1, 2};//true
//        int[] nums = {1, 2, 3, 4, -4, -3, -5, -1};//false
        System.out.println(find132pattern_1(nums));
        System.out.println(find132pattern_2(nums));

    }

    /**
     * 单调栈<br>
     * 执行用时：10 ms, 在所有 Java 提交中击败了92.78%的用户<br>
     * 内存消耗：60.1 MB, 在所有 Java 提交中击败了49.70%的用户
     */
    public static boolean find132pattern_2(int[] nums) {
        int length = nums.length;

        //单调递减栈(从后往前进行遍历)
        Deque<Integer> stack = new ArrayDeque<>();
        int three = Integer.MIN_VALUE;

        for (int cur = length - 1; cur >= 0; --cur) {
            int val = nums[cur];

            if (three > val)//一定会有 3 个元素
                return true;

            while (!stack.isEmpty() && stack.peekLast() < val)
                three = Math.max(stack.pollLast(), three);//第二个元素取得弹出的元素最大值，但是又不小于栈底元素

            stack.offerLast(val);

        }

        return false;
    }

    /**
     * 单调栈(逆序遍历)<br>
     * 执行用时：14 ms, 在所有 Java 提交中击败了39.13%的用户<br>
     * 内存消耗：59.2 MB, 在所有 Java 提交中击败了76.71%的用户
     */
    public static boolean find132pattern_1(int[] nums) {//第三个数比其前两
        int length = nums.length;

        //单调递减栈(从后往前进行遍历)
        Deque<Integer> stack = new ArrayDeque<>();
        int two = Integer.MIN_VALUE;

        for (int cur = length - 1; cur >= 0; --cur) {
            int val = nums[cur];

            while (!stack.isEmpty() && stack.peekLast() < val)
                two = Math.max(stack.pollLast(), two);//第二个元素取得弹出的元素最大值，但是又不小于栈底元素

            stack.offerLast(val);

            if (stack.size() >= 2 && two > stack.peekLast())//大于栈顶元素
                return true;
        }

        return false;
    }
}
