package 回溯;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/31 19:00
 */
//130-被围绕的区域
public class Demo_130 {
    public static void main(String[] args) {
        char[][] matrix = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(matrix);
    }

    /**
     * DFS<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.89%的用户<br>
     * 内存消耗：43.6 MB, 在所有 Java 提交中击败了20.79%的用户<br>
     * 2022年03月31日  22:07:55
     */
    public static void solve(char[][] board) {
        row = board.length;
        col = board[0].length;

        for (int curRow = 0; curRow < row; ++curRow) {//对左右边界进行染色
            dfs(curRow, 0, board);//左边界

            dfs(curRow, col - 1, board);//右边界
        }

        for (int curCol = 0; curCol < col; ++curCol) {//对上下边界进行染色
            dfs(0, curCol, board);//上边界

            dfs(row - 1, curCol, board);//下边界
        }

        //边界染完色，中间的就是被包围的 'O'
        for (int curRow = 0; curRow < row; ++curRow)
            for (int curCol = 0; curCol < col; ++curCol)
                if (board[curRow][curCol] == 'O')//区域包围的
                    board[curRow][curCol] = 'X';
                else if (board[curRow][curCol] == '#')//边界的
                    board[curRow][curCol] = 'O';

    }

    private static int row, col;

    private static void dfs(int curRow, int curCol, char[][] matrix) {
        if (curRow < 0 || curRow >= row || curCol < 0 || curCol >= col)//越界
            return;

        if (matrix[curRow][curCol] == 'O') {//将边界的 'O' 进行染色
            matrix[curRow][curCol] = '#';
            dfs(curRow - 1, curCol, matrix);//上

            dfs(curRow + 1, curCol, matrix);//下

            dfs(curRow, curCol - 1, matrix);//左

            dfs(curRow, curCol + 1, matrix);//右
        }
    }

}
