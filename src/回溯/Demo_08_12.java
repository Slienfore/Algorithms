package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/15 20:42
 */
//面试题-08.12-八皇后
public class Demo_08_12 {

    public static void main(String[] args) {
        System.out.println(solveNQueens(5));
    }

    static List<List<String>> res = new ArrayList<>();
    private static char[][] board;


    /**
    执行用时：2 ms, 在所有 Java 提交中击败了89.42%的用户<br>
    内存消耗：38.6 MB, 在所有 Java 提交中击败了71.55%的用户*/
    public static List<List<String>> solveNQueens(int n) {
        board = new char[n][n];

        for (char[] row : board) //初始化(将每一行铺满点)
            Arrays.fill(row, '.');

        layout(0, n);

        return res;
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了91.81%的用户<br>
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了90.79%的用户
     */
    private static void layout(int row, int num) {

        if (row == num) { //如果搜索到了最底层
            res.add(path(board));
            return;
        }

        for (int col = 0; col < num; col++) {//每一次都从新的以行开始出发

            if (isValid(row, col, num)) {
                board[row][col] = 'Q';//放置皇后

                layout(row + 1, num);//进入下一行

                board[row][col] = '.';//回溯
            }
        }
    }

    private static List<String> path(char[][] chess) {
        List<String> list = new ArrayList<>();

        for (char[] chars : chess)//将棋盘的每一行进行存放进list集合之中
            list.add(String.valueOf(chars));

        return list;
    }

    /**
     * 判断棋盘的拜访位置是否合理<br>
     * 不能同行<br>
     * 不能同列<br>
     * 不能同斜线 （45度和135度角）
     */
    private static boolean isValid(int row, int col, int num) {

        //检查同列
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q') return false;


        //检查左斜对角线(45°)是否有皇后(向左边遍历棋盘)
        for (int i = row - 1, left = col - 1; i >= 0 && left >= 0; i--, left--)
            if (board[i][left] == 'Q') return false;


        //检查右斜对角线(135°)是否有皇后(向右边遍历棋盘)
        for (int i = row - 1, right = col + 1; i >= 0 && right < num; i--, right++)
            if (board[i][right] == 'Q') return false;

        return true;
    }
}
