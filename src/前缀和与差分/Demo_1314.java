package 前缀和与差分;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/20 9:12
 */
//1314-矩阵区域和
public class Demo_1314 {
    public static void main(String[] args) {
        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int k = 2;

        uu.print(matrixBlockSum(nums, k));
    }

    /**
     * 前缀和<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了47.35%的用户<br>
     * 内存消耗：42.1 MB, 在所有 Java 提交中击败了29.57%的用户
     */
    public static int[][] matrixBlockSum(int[][] mat, int k) {
        int row = mat.length, col = mat[0].length;

        int[][] preSum = new int[row + 1][col + 1];

        for (int i = 1; i <= row; ++i)//累加前缀和
            for (int j = 1; j <= col; ++j)
                preSum[i][j] += preSum[i][j - 1] + mat[i - 1][j - 1];

        int[][] res = new int[row][col];

        for (int curRow = 0; curRow < row; ++curRow)
            for (int curCol = 0; curCol < col; ++curCol) {

                int endRow = Math.min((curRow + 1) + k, row),//控制边界
                        right = Math.min((curCol + 1) + k, col),
                        left = Math.max(((curCol + 1) - k) - 1, 0);

                for (int begin = Math.max((curRow + 1) - k, 0); begin <= endRow; ++begin)
                    res[curRow][curCol] += preSum[begin][right] - preSum[begin][left];
            }

        return res;
    }
}
