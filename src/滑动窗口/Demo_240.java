package 滑动窗口;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/3 11:23
 */
//搜索二维矩阵
public class Demo_240 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {18, 21, 23, 26, 30}};
        int target = 5;
        System.out.println(searchMatrix(matrix, target));
    }

    /**
     * 双指针<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了95.04%的用户<br>
     * 内存消耗：47.6 MB, 在所有 Java 提交中击败了5.02%的用户<br>
     * 2022年04月03日  11:54:33
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;

        for (int curRow = 0, curCol = col - 1; curRow < row && curCol >= 0; ) {
            if (matrix[curRow][curCol] == target)
                return true;

            else if (matrix[curRow][curCol] > target)//当前值较大，移动前一列寻找更小数
                --curCol;

            else if (matrix[curRow][curCol] < target)//当前值较小，移动下一行寻找更大数
                ++curRow;
        }

        return false;
    }
}
