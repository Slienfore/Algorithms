package 回溯;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/13 9:56
 */
//37-解数独
public class Demo_37 {
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
        dfsRecur(0, 0, board);
        System.out.println(Arrays.deepToString(board));
    }

    /**
     * 执行用时：10 ms, 在所有 Java 提交中击败了21.65%的用户<br></>
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了99.39%的用户
     */
    private static boolean helper(char[][] board) {

        for (int row = 0; row < 9; row++) //遍历棋盘的行
            for (int col = 0; col < 9; col++) {//遍历棋盘的列
                if (board[row][col] != '.') continue;

                for (char val = '1'; val <= '9'; val++) {//填入数字
                    if (isValid(val, row, col, board)) {
                        board[row][col] = val;

                        if (helper(board)) return true;
                        //进行回溯
                        board[row][col] = '.';

                    }
                }
                //若 9 个数字试玩都不行，要返回false
                return false;
            }

        return true;
    }

    /**
     * 判断放置得位置是否合理<br>
     * 同行是否重复<br>
     * 同列是否重复<br>
     * 所处在的九宫格是否重复<br>
     */
    private static boolean isValid(char val, int row, int col, char[][] board) {
        //搜索同行
        for (int i = 0; i < 9; i++)
            if (board[row][i] == val) return false;

        //搜索同列
        for (int j = 0; j < 9; j++)
            if (board[j][col] == val) return false;

        int startRow = (row / 3) * 3;//初始行
        int startCol = (col / 3) * 3;//初始列
        for (int i = startRow; i < startRow + 3; i++)//九宫格判重
            for (int j = startCol; j < startCol + 3; j++)
                if (board[i][j] == val) return false;

        return true;
    }


    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了46.85%的用户<br>
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了88.29%的用户
     */
    private static boolean dfsRecur(int row, int col, char[][] board) {
        //已经搜索到最底层了
        if (row == 8 && col == 9) return true;

        else if (col == 9) {
            row++;
            col = 0;
        }

        if (board[row][col] == '.') {
            for (char val = '1'; val <= '9'; val++) {
                if (isValid(val, row, col, board)) {
                    board[row][col] = val;

                    if (dfsRecur(row, col + 1, board)) return true;

                    board[row][col] = '.';
                }
            }
            return false;

        } else return dfsRecur(row, col + 1, board);
    }
}
