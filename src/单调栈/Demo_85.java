package 单调栈;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/20 18:14
 */
//85-最大矩形
public class Demo_85 {
    public static void main(String[] args) {
/*        char[][] matrix =
                {
                        {'1', '0', '1', '0', '0' },
                        {'1', '0', '1', '1', '1' },
                        {'1', '1', '1', '1', '1' },
                        {'1', '0', '0', '1', '0' }
                };*/

//        char[][] matrix = {{'1', '1' }};
//        char[][] matrix = {{'1', '0' }};
        char[][] matrix = {{'0', '0', '1' }, {'1', '1', '1' }};

        System.out.println(maximalRectangle_1(matrix));
        System.out.println(maximalRectangle_2(matrix));
    }

    /**
     * 暴力(剪枝)<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.49%的用户<br>
     * 内存消耗：45.5 MB, 在所有 Java 提交中击败了23.10%的用户
     */
    public static int maximalRectangle_2(char[][] matrix) {
        int row = matrix.length;
        if (row == 0)
            return 0;
        int col = matrix[0].length;

        int[][] dp = new int[row + 1][col + 1];
        for (int curCol = 1; curCol <= col; ++curCol)
            for (int curRow = 1; curRow <= row; ++curRow) {//统计每一个点的高度
                if (matrix[curRow - 1][curCol - 1] == '0')
                    continue;

                dp[curRow][curCol] = dp[curRow - 1][curCol] + 1;
            }

        int max = 0;
        for (int curRow = 1; curRow <= row; ++curRow)
            for (int curCol = 1; curCol <= col; ++curCol) {
                int high = dp[curRow][curCol];

                if (high == 0 || high * col <= max)//剪枝(每一个元素的高度都给予最大宽度)
                    continue;

                int width = 1, left = curCol - 1, right = curCol + 1;
                while (left > 0 && dp[curRow][left] >= high) {//向左探寻可以延伸的宽度
                    ++width;
                    --left;
                }

                while (right <= col && dp[curRow][right] >= high) {//向右探寻可以延伸的宽度
                    ++width;
                    ++right;
                }

                max = Math.max(high * width, max);
            }

        return max;
    }


    /**
     * 单调栈+动规<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了92.57%的用户<br>
     * 内存消耗：44.9 MB, 在所有 Java 提交中击败了42.53%的用户
     */
    public static int maximalRectangle_1(char[][] matrix) {
        int row = matrix.length;
        if (matrix.length == 0)
            return 0;

        int col = matrix[0].length;


        int[][] dp = new int[row + 1][col + 1];//代表每一列矩形的最大高度

        for (int curCol = 1; curCol <= col; ++curCol)//以每一行为一个柱状图，即统计每一行的柱状图最大高度
            for (int curRow = 1; curRow <= row; ++curRow) {
                if (matrix[curRow - 1][curCol - 1] == '0')//遇到了 '0' 说明最大连续矩形的高度断了
                    continue;

                dp[curRow][curCol] = dp[curRow - 1][curCol] + 1;
            }

        int max = 0;
        for (int line = 0; line < row; ++line)
            max = Math.max(getMax(dp[line + 1]), max);

        return max;
    }

    private static int getMax(int[] height) {
        //单调递增栈(寻找第一小、维护矩形的边长得以继续扩张)
        Deque<Integer> stack = new ArrayDeque<>();
        int length = height.length, max = 0;

        for (int cur = 1; cur < length; ++cur) {//第一列无用
            int pos = cur, curHeight = height[cur];

            while (!stack.isEmpty() && height[stack.peekLast()] >= curHeight) {
                int orin = stack.pollLast();

                max = Math.max((cur - orin) * height[orin], max);//长 * 宽(面积)

                pos = orin;

            }

            height[pos] = curHeight;//修改当前矩形向右延伸的高度
            stack.offerLast(pos);
        }

        while (!stack.isEmpty()) {
            int orin = stack.pollLast();
            max = Math.max((length - orin) * height[orin], max);
        }

        return max;
    }
}
