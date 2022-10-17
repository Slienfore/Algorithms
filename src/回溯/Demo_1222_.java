package 回溯;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/26 17:03
 */
public class Demo_1222_ {
    public static void main(String[] args) {
/*        int[][] queue = {{0, 0}, {1, 1}, {2, 2}, {3, 4}, {3, 5}, {4, 4}, {4, 5}};
        int[] king = {3, 3};*/
        int[][] queue = {{5, 6}, {7, 7}, {2, 1}, {0, 7}, {1, 6}, {5, 1}, {3, 7}, {0, 3}, {4, 0}, {1, 2}, {6, 3}, {5, 0}, {0, 4}, {2, 2}, {1, 1}, {6, 4}, {5, 4}, {0, 0}, {2, 6}, {4, 5}, {5, 2}, {1, 4}, {7, 5}, {2, 3}, {0, 5}, {4, 2}, {1, 0}, {2, 7}, {0, 1}, {4, 6}, {6, 1}, {0, 6}, {4, 3}, {1, 7}};
        int[] king = {3, 4};
/*        int[][] queue = {{0, 0}, {1, 1}, {2, 2}, {3, 4}, {3, 5}, {4, 4}, {4, 5}};
        int[] king = {3, 3};*/
        System.out.println("\n" + queensAttacktheKing(queue, king));

    }

    static List<List<Integer>> res = new ArrayList<>();
    static boolean[][] board = new boolean[8][8];

    public static List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {

        for (int[] queen : queens)
            board[queen[0]][queen[1]] = true;

        System.out.println(Arrays.deepToString(board));

        int xKing = king[0], yKing = king[1];

        for (int target = 0; target < 8; target++) //控制棋子往一个方向一直走，直到找到这个女王
            helper(target, xKing, yKing);

        return res;
    }

    //左、右、上、下、西北、东北、西南、东南
    static int[] xPos = {0, 0, 1, -1, -1, -1, 1, 1};
    static int[] yPos = {-1, 1, 0, 0, -1, 1, -1, 1};

    /**暴力<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：41.5 MB, 在所有 Java 提交中击败了8.39%的用户
    */
    private static void helper(int target, int xKing, int yKing) {//每一个方向只有一个女王

        for (int step = 0; step < xPos.length; step++) {//棋子在棋盘中最多只能走 7 步

            xKing += xPos[target];
            yKing += yPos[target];

            if (isValid(xKing, yKing) && board[xKing][yKing]) {
                List<Integer> list = new ArrayList<>();
                list.add(xKing);
                list.add(yKing);
                res.add(list);
                break;
            }
        }

    }

    //判断是否越界
    private static boolean isValid(int x, int y) {
        return ((x >= 0) && (x < 8) && (y >= 0) && (y < 8));
    }


}
