package 前缀和与差分;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/20 15:00
 */
//304-二维区域和检索 - 矩阵不可变
public class Demo_304 {
    public static void main(String[] args) {
        int[][] nums = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};

        Demo_304 numMatrix = new Demo_304(nums);

        System.out.println(numMatrix.sumRegion(2, 1, 4, 3)); // return 8 (红色矩形框的元素总和)
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2)); // return 11 (绿色矩形框的元素总和)
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4)); // return 12 (蓝色矩形框的元素总和)

    }

    int[][] matrix;

    /**
     * 前缀和(二维)<br>
     * 执行用时：108 ms, 在所有 Java 提交中击败了49.80%的用户<br>
     * 内存消耗：63.3 MB, 在所有 Java 提交中击败了67.91%的用户
     */
    public Demo_304(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        this.matrix = new int[row + 1][col + 1];

        //构造二维前缀和
        for (int i = 1; i <= row; ++i)
            for (int j = 1; j <= col; ++j)
                this.matrix[i][j] = this.matrix[i][j - 1] + this.matrix[i - 1][j] - this.matrix[i - 1][j - 1] + matrix[i - 1][j - 1];
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return matrix[row2 + 1][col2 + 1] - matrix[row2 + 1][(col1 + 1) - 1] - matrix[(row1 + 1) - 1][col2 + 1] + matrix[(row1 + 1) - 1][(col1 + 1) - 1];
    }
}
