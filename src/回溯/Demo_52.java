package 回溯;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/14 10:19
 */
//52-N皇后-II
public class Demo_52 {
    public static void main(String[] args) {
        int num = 100;
        System.out.println(totalNQueens(num));
    }

    /**
    执行用时：2 ms, 在所有 Java 提交中击败了64.69%的用户<br>
    内存消耗：35.1 MB, 在所有 Java 提交中击败了77.25%的用户
    */
    public static int totalNQueens(int n) {
        //创建棋盘
        char[][] board = new char[n][n];
        //初始化棋盘
        for (char[] line : board)
            Arrays.fill(line, '.');

        help(0, board, n);
        return res;
    }

    private static int res = 0;

    private static void help(int row, char[][] board, int n) {
        if (row == n) {
            res++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(row, col, board, n)) {
                board[row][col] = 'Q';

                help(row + 1, board, n);

                board[row][col] = '.';
            }
        }
    }

    //判断放置位置是否合理
    private static boolean isValid(int row, int col, char[][] board, int n) {

        //判断同列
        for (int i = row; i >= 0; i--)
            if (board[i][col] == 'Q') return false;

        //判断45°角
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q') return false;

        //判断135°角
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 'Q') return false;

        return true;
    }

}
