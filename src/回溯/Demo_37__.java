package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/26 15:25
 */
public class Demo_37__ {
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

    static boolean[][] rowCheck;
    static boolean[][] colCheck;
    static boolean[][][] areaCheck;
    static LinkedList<int[]> posList = new LinkedList<>();//用来存放棋子放置下标

    public static void solveSudoku(char[][] board) {
        rowCheck = new boolean[9][9];//row:表示位于第几行，col:进行数字排重
        colCheck = new boolean[9][9];//row:表示位于第几列，col:进行数字排重

        /*将九宫格同时划分为 9 个区域， 分别为(区域内有 9 个数字)
        [ 00, 01, 02
          10, 11, 12
          20, 21, 22 ]*/
        areaCheck = new boolean[3][3][9];

        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    posList.add(new int[]{row, col});
                    continue;
                }

                int val = board[row][col] - '0' - 1;

                rowCheck[row][val] = true;
                colCheck[col][val] = true;
                areaCheck[row / 3][col / 3][val] = true;

            }

        recur(board);

        System.out.println(Arrays.deepToString(board));

    }

    /**数字哈希去重<br>
    执行用时：3 ms, 在所有 Java 提交中击败了58.73%的用户<br>
    内存消耗：40.9 MB, 在所有 Java 提交中击败了5.04%的用户
    */
    private static boolean recur(char[][] board) {
        if (posList.isEmpty())
            return true;//说明已经将所有的棋子放置好了

        int[] pos = posList.pop();
        int row = pos[0], col = pos[1];


        for (char num = 49; num < 58; num++) {
            int val = num - '0' - 1;
            if (rowCheck[row][val] || colCheck[col][val] || areaCheck[row / 3][col / 3][val])//如果不满足行、列、块
                continue;

            board[row][col] = num;
            rowCheck[row][val] = colCheck[col][val] = areaCheck[row / 3][col / 3][val] = true;

            if (recur(board))
                return true;

            board[row][col] = '.';
            rowCheck[row][val] = colCheck[col][val] = areaCheck[row / 3][col / 3][val] = false;

        }

        posList.addFirst(new int[]{row, col});//当不满足的时候回退还需将该坐标加进来
        return false;//棋子放置都不满足则回退

    }


}
