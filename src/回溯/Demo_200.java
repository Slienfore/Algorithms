package 回溯;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/31 22:20
 */
//200-岛屿的数量
public class Demo_200 {
    public static void main(String[] args) {
        char[][] matrix = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(numIslands(matrix));
    }

    /**
     * DFS<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了67.03%的用户<br>
     * 内存消耗：49.7 MB, 在所有 Java 提交中击败了32.95%的用户<br>
     * 2022年03月31日  22:32:43
     */
    public static int numIslands(char[][] grid) {
        int row = grid.length, col = grid[0].length, res = 0;

        for (int curRow = 0; curRow < row; ++curRow)
            for (int curCol = 0; curCol < col; ++curCol)
                if (grid[curRow][curCol] == '1') {//将遇到的陆地及其相邻的岛屿进行染色(构成一块岛屿)
                    dfs(curRow, curCol, grid);
                    ++res;
                }

        return res;
    }

    private static void dfs(int row, int col, char[][] matrix) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length)//越界
            return;

        if (matrix[row][col] == '1') {//遇到陆地
            matrix[row][col] = '0';//沉岛

            dfs(row - 1, col, matrix);//上

            dfs(row + 1, col, matrix);//下

            dfs(row, col - 1, matrix);//左

            dfs(row, col + 1, matrix);//右
        }
    }
}
