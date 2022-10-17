package 回溯;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/25 20:44
 */
public class Demo_37_ {
    public static void main(String[] args) {
        char[][] board = new char[][]
                {
                        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };

        solveSudoku(board);

    }


    public static void solveSudoku(char[][] board) {

        recur(0, 0, board);

        System.out.println(Arrays.deepToString(board));

    }

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了48.28%的用户<br>
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了5.04%的用户
     */
    private static boolean recur(int rowOrin, int colOrin, char[][] board) {
        int[] nextPos = next(rowOrin, board);

        if (nextPos.length == 0)//当其找不到下一个节点时候就已经填完了
            return true;

        int fillRow = nextPos[0], fillCol = nextPos[1];//获取下一个棋子放置位置

        for (char num = 49; num <= 57; num++) {//放置棋子
            if (check(num, fillRow, fillCol, board)) {//检查该坐标是否合适
                board[fillRow][fillCol] = num;

                if (recur(fillRow, fillCol, board))
                    return true;

                board[fillRow][fillCol] = '.';

            }
        }

        return false;//每一层递归负责一个棋子放置的位置

    }

    private static int[] next(int rowOrin, char[][] board) {
        for (int row = rowOrin; row < 9; row++)
            for (int col = 0; col < 9; col++)
                if (board[row][col] == '.')
                    return new int[]{row, col};


        return new int[0];
    }


    private static boolean check(char val, int row, int col, char[][] board) {

        //检查同行(已经填入部分数字)
        for (int col_ = 0; col_ < 9; col_++)
            if (board[row][col_] == val)
                return false;

        //检查同列(已经填入部分数字)
        for (int row_ = 0; row_ < 9; row_++)
            if (board[row_][col] == val)
                return false;

        //检查3*3宫格
        int rowOrin = row / 3 * 3, colOrin = col / 3 * 3;//分别定位于每一块区域, 然后求出其起始地点
        int rowEnd = rowOrin + 3, colEnd = colOrin + 3;

        for (int row_ = rowOrin; row_ < rowEnd; row_++)
            for (int col_ = colOrin; col_ < colEnd; col_++)
                if (board[row_][col_] == val)
                    return false;

        return true;
    }


}
