package 回溯;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/31 22:42
 */
//1020-飞地的数量
public class Demo_1020 {
    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        System.out.println(numEnclaves(matrix));
    }

    /**
     * DFS<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了97.40%的用户<br>
     * 内存消耗：49.1 MB, 在所有 Java 提交中击败了25.65%的用户<br>
     * 2022年03月31日  22:54:25
     */
    public static int numEnclaves(int[][] grid) {
        int row = grid.length, col = grid[0].length;

        for (int curRow = 0; curRow < row; ++curRow) {//左右边界进行染色
            dfs(curRow, 0, grid);
            dfs(curRow, col - 1, grid);
        }

        for (int curCol = 0; curCol < col; ++curCol) {
            dfs(0, curCol, grid);
            dfs(row - 1, curCol, grid);
        }

        int res = 0;
        for (int curRow = 1; curRow <= row - 1; ++curRow)
            for (int curCol = 1; curCol <= col - 1; ++curCol)
                if (grid[curRow][curCol] == 1)//统计被海洋包围的陆地个数
                    ++res;

        return res;
    }

    private static void dfs(int row, int col, int[][] matrix) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length)
            return;


        if (matrix[row][col] == 1) {//遇到海洋
            matrix[row][col] = 0;//沉岛

            dfs(row - 1, col, matrix);//上

            dfs(row + 1, col, matrix);//下

            dfs(row, col - 1, matrix);//左

            dfs(row, col + 1, matrix);//右
        }
    }
}
