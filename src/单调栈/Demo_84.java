package 单调栈;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/17 22:04
 */
//84-柱状图中最大的矩形
public class Demo_84 {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
//        int[] heights = {4, 0, 1};
//        int[] heights = {5, 5, 1, 7, 1, 1, 5, 2, 7, 6};
        System.out.println(largestRectangleArea_1(heights));
        System.out.println(largestRectangleArea_2(heights));
        System.out.println(largestRectangleArea_3(heights));
    }

    /**
     * 单调递增栈<br>
     * 执行用时：21 ms, 在所有 Java 提交中击败了69.71%的用户<br>
     * 内存消耗：52.2 MB, 在所有 Java 提交中击败了57.86%的用户
     */
    public static int largestRectangleArea_3(int[] heights) {
        int length = heights.length, max = 0;
        Deque<Integer> stack = new ArrayDeque<>();//单调递减栈
        for (int cur = 0; cur < length; ++cur) {

            int curHeight = heights[cur];//当前矩形的高度

            if (stack.isEmpty() || curHeight >= heights[stack.peekLast()]) {//栈顶矩形的宽度向右扩散
                stack.offerLast(cur);

            } else if (heights[stack.peekLast()] > curHeight) {//栈顶矩形的宽扩散到最大

                int orin = cur;
                //栈顶矩形自顶向下单调递减
                while (!stack.isEmpty() && heights[stack.peekLast()] > curHeight) {//当前矩形向左扩散

                    orin = stack.pollLast();//栈顶矩形起始下标

                    max = Math.max((cur - orin) * heights[orin], max);

                    heights[orin] = curHeight;//向左扩张(也可以放出外面)

                }
                //加入向左扩张的下标
                stack.offerLast(orin);

            }
        }
        while (!stack.isEmpty()) {//将栈中的元素全部出栈
            int orin = stack.pollLast();
            max = Math.max((length - orin) * heights[orin], max);
        }

        return max;
    }


    /**
     * 单调递增栈(维护区间最大值)<br>
     * 执行用时：18 ms, 在所有 Java 提交中击败了86.88%的用户<br>
     * 内存消耗：50.7 MB, 在所有 Java 提交中击败了71.60%的用户
     */
    public static int largestRectangleArea_1(int[] heights) {
        int length = heights.length;
        if (length == 1) return heights[0];

        int maxArea = 0;
        Deque<Rectangle> stack = new ArrayDeque<>();//单调递增栈：维护最高矩形

        for (int width = 0; width < length; ++width) {
            int curHeight = heights[width];

            if (!stack.isEmpty()) {
                int pos = width;

                //维持单调增减
                while (!stack.isEmpty() && stack.peekLast().height >= curHeight) {//边长不能继续扩展了
                    Rectangle rect = stack.pollLast();//移除

                    int orin = rect.orin, height = rect.height;//获取到最高的长方形最大高度

                    maxArea = Math.max(maxArea, (width - orin) * height);//面积

                    pos = orin;//边长加长
                }

                stack.offerLast(new Rectangle(pos, curHeight));
            } else {
                stack.offerLast(new Rectangle(width, curHeight));//栈空进栈
            }
        }

        if (stack.isEmpty())
            return maxArea;

        while (!stack.isEmpty()) {//防止全部递增
            Rectangle rect = stack.pollLast();

            maxArea = Math.max(maxArea, (length - rect.orin) * rect.height);
        }

        return maxArea;
    }

    static class Rectangle {
        int orin, height;

        public Rectangle(int orin, int height) {//记录每一个正方形的起始位置和高度
            this.orin = orin;
            this.height = height;
        }
    }


    /**
     * 暴力
     */
    public static int largestRectangleArea_2(int[] heights) {
        int length = heights.length;
        if (length == 1)
            return heights[0];

        int maxArea = 0;

        //搜索每一条柱状图最大延伸边长
        for (int cur = 0; cur < length; ++cur) {
            int curHeight = heights[cur];

            int left = cur, right = cur;

            //搜索左边延伸的高度
            while ((left - 1) >= 0 && heights[left - 1] >= curHeight)
                --left;

            //向右边延伸的高度
            while ((right + 1) < length && heights[right + 1] >= curHeight)
                ++right;

            maxArea = Math.max(maxArea, ((right - left) + 1) * curHeight);
        }

        return maxArea;
    }
}