package 单调栈;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/17 18:41
 */
//42-接雨水
public class Demo_42 {
    public static void main(String[] args) {
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println(trap_1(height));
        System.out.println(trap_2(height));
        System.out.println(trap_3(height));

    }

    /**
     * 单调栈<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了37.55%的用户<br>
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了32.04%的用户
     */
    private static int trap_3(int[] height) {
        int length = height.length;
        Deque<Integer> stack = new ArrayDeque<>();

        int res = 0;
        for (int cur = 0; cur < length; ++cur) {
            int curHeight = height[cur];

            //单调递减栈，寻找第一大
            while (!stack.isEmpty() && height[stack.peekLast()] < curHeight) {
                int pivot = stack.pollLast();

                int left = stack.isEmpty() ? pivot : stack.peekLast();//相邻柱子的高度

                //最短边的这一行的储水量
                res += (Math.min(height[left], curHeight) - height[pivot]) * (cur - left - 1);

            }

            stack.offerLast(cur);
        }

        return res;
    }


    /**
     * 动规(优化)<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了75.03%的用户<br>
     * 内存消耗：42 MB, 在所有 Java 提交中击败了18.34%的用户
     */
    private static int trap_2(int[] height) {
        int length = height.length;
        //表示当前柱子左、右两边最高柱子的高度
        int[] rightMax = new int[length];
        //初始化
        rightMax[length - 1] = height[length - 1];

        //从后往前
        for (int cur = length - 2; cur > 0; --cur)//最左边柱子不盛水
            rightMax[cur] = Math.max(height[cur], rightMax[cur + 1]);

        int res = 0, leftMax = height[0];//使用一个变量进行维护左边最大值

        //比较两边最小的柱子就是该条柱子的乘水量
        for (int cur = 1; cur < length - 1; ++cur) { //左右两边的柱子均不盛水
            leftMax = Math.max(height[cur], leftMax);
            res += Math.min(leftMax, rightMax[cur]) - height[cur];
        }

        return res;
    }

    /**
     * 动规<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了75.03%的用户<br>
     * 内存消耗：42 MB, 在所有 Java 提交中击败了18.62%的用户
     */
    public static int trap_1(int[] height) {
        int length = height.length;
        //表示当前柱子左、右两边最高柱子的高度
        int[] leftMax = new int[length], rightMax = new int[length];
        //初始化
        leftMax[0] = height[0];
        rightMax[length - 1] = height[length - 1];

        //从前往后
        for (int cur = 1; cur < length - 1; ++cur)//最右边柱子盛水
            leftMax[cur] = Math.max(height[cur], leftMax[cur - 1]);

        //从后往前
        for (int cur = length - 2; cur > 0; --cur)//最左边柱子不盛水
            rightMax[cur] = Math.max(height[cur], rightMax[cur + 1]);

        int res = 0;

        //比较两边最小的柱子就是该条柱子的乘水量
        for (int cur = 1; cur < length - 1; ++cur) //左右两边的柱子均不盛水
            res += Math.min(leftMax[cur], rightMax[cur]) - height[cur];

        return res;
    }
}
