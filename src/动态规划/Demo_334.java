package 动态规划;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/1/12 18:25
 */
//334-递增的三元子序列
public class Demo_334 {
    public static void main(String[] args) {
//        int[] nums = {20, 100, 10, 12, 5, 13};
        int[] nums = {1, 2, 3, 4, 5};
//        int[] nums = {5, 4, 3, 2, 1};
//        int[] nums = {2, 1, 5, 0, 4, 6};
//        int[] nums = {0, 4, 2, 1, 0, -1, -3};

        System.out.println(increasingTriplet_1(nums));
        System.out.println(increasingTriplet_2(nums));
        System.out.println(increasingTriplet_3(nums));
    }

    /**
     * 动态维护第一、二大数<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：79.3 MB, 在所有 Java 提交中击败了5.08%的用户
     */
    public static boolean increasingTriplet_1(int[] nums) {
        int one = Integer.MAX_VALUE, two = Integer.MAX_VALUE;

        for (int val : nums)
            if (one >= val)//动态维护第一大
                one = val;
            else if (two >= val)//动态维护第二大
                two = val;
            else//如果都比第一、第二大的话直接返回
                return true;

        return false;
    }


    /**
     * 单调递增栈<br>
     * 执行用时：16 ms, 在所有 Java 提交中击败了5.05%的用户<br>
     * 内存消耗：82.1 MB, 在所有 Java 提交中击败了6.20%的用户
     */
    public static boolean increasingTriplet_2(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();

        int preMax = Integer.MAX_VALUE;//如果遇到一个更小的元素，那么之前递增的两个元素就会出栈

        for (int val : nums) {//单调递增栈(自底向上单调递增)yi
            if (val > preMax)
                return true;

            while (!stack.isEmpty() && stack.peekLast() >= val)
                stack.pollLast();//维护自己是区间最大，大的元素出栈

            stack.offerLast(val);

            if (stack.size() == 2)//防止存放完第二个的数字被全部出栈，需要记录一下
                preMax = Math.min(preMax, val);//优先选择最小元素
        }

        return false;
    }


    /**
     * 贪心+二分<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了40.54%的用户<br>
     * 内存消耗：82.2 MB, 在所有 Java 提交中击败了7.61%的用户
     */
    public static boolean increasingTriplet_3(int[] nums) {
        int length = nums.length;
        int[] tail = new int[length];
        tail[0] = nums[0];
        int point = 0;

        //使用更大的元素进行拼接，使用更小的元素替换掉第一大的元素(ceiling)
        for (int cur = 1; cur < length; ++cur) {
            int curVal = nums[cur];

            if (curVal > tail[point]) {//将大的进行拼接
                tail[++point] = curVal;
            } else if (curVal < tail[point]) {//选择小、进行替换
                int left = 0, right = point;

                while (left < right) {//Ceiling(每一次搜索的都是最小值进行替换)
                    int mid = left + (right - left) / 2;
                    if (curVal > tail[mid])
                        left = mid + 1;
                    else
                        right = mid;
                }
                tail[left] = curVal;
            }
            if (point == 2)
                return true;
        }

        return false;
    }
}
