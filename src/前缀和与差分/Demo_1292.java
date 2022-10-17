package 前缀和与差分;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/20 10:17
 */
public class Demo_1292 {
    public static void main(String[] args) {
//        int[][] nums = {{1, 1, 3, 2, 4, 3, 2}, {1, 1, 3, 2, 4, 3, 2}, {1, 1, 3, 2, 4, 3, 2}};
//        int[][] nums = {{2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}, {2, 2, 2, 2, 2}};
        int[][] nums = {{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        int threshold = 3;

        System.out.println(maxSideLength_2(nums, threshold));
        System.out.println(maxSideLength_3(nums, threshold));
    }

    /**
     * 前缀和(二维)<br>
     * 执行用时：122 ms, 在所有 Java 提交中击败了17.16%的用户<br>
     * 内存消耗：49 MB, 在所有 Java 提交中击败了30.55%的用户
     */
    public static int maxSideLength_3(int[][] mat, int threshold) {
        int row = mat.length, col = mat[0].length;
        int[][] preSum = new int[row + 1][col + 1];
        for (int curRow = 1; curRow <= row; ++curRow)
            for (int curCol = 1; curCol <= col; ++curCol)
                //左、上、左上
                preSum[curRow][curCol] = preSum[curRow][curCol - 1] + preSum[curRow - 1][curCol] - preSum[curRow - 1][curCol - 1] + mat[curRow - 1][curCol - 1];

        int maxEdge = 0;
        for (int width = 1; width <= Math.min(row, col); ++width)//正方形的边长(两边取最短边)
            for (int curRow = 1; curRow <= row; ++curRow)
                for (int curCol = 1; curCol <= col; ++curCol) {
                    if (curRow - width < 0 || curCol - width < 0)//防止越界
                        continue;

                    //右下、左、右上、左上
                    int sum = preSum[curRow][curCol] - preSum[curRow][curCol - width] - preSum[curRow - width][curCol] + preSum[curRow - width][curCol - width];

                    if (sum <= threshold)
                        maxEdge = Math.max(width, maxEdge);
                }

        return maxEdge;
    }

    //最后两个通不过
    public static int maxSideLength_2(int[][] mat, int threshold) {
        int row = mat.length, col = mat[0].length;
        int[][] preSum = new int[row + 1][col + 1];
        for (int curRow = 1; curRow <= row; ++curRow)
            for (int curCol = 1; curCol <= col; ++curCol)
                //左、上、左上
                preSum[curRow][curCol] = preSum[curRow][curCol - 1] + preSum[curRow - 1][curCol] - preSum[curRow - 1][curCol - 1] + mat[curRow - 1][curCol - 1];

        int maxEdge = 0;

        for (int curRow = 1; curRow <= row; ++curRow) {//优化两层for
            for (int curCol = 1; curCol <= col; ++curCol) {
                int width = Math.min(curRow, curCol);//正方形的边长(两边取最短边)
                //右下、左、右上、左上
                int sum = preSum[curRow][curCol] - preSum[curRow][curCol - width] - preSum[curRow - width][curCol] + preSum[curRow - width][curCol - width];

                if (sum <= threshold)
                    maxEdge = Math.max(width, maxEdge);
            }
        }
        return maxEdge;
    }
}
