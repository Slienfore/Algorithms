package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/14 18:43
 */
//1222-可以攻击国王的皇后
public class Demo_1222 {
    public static void main(String[] args) {
        int[][] queue = {{0, 0}, {1, 1}, {2, 2}, {3, 4}, {3, 5}, {4, 4}, {4, 5}};
        int[] king = {3, 3};

        System.out.println(queensAttackTheKing(queue, king));
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了86.91%的用户<br>
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了96.33%的用户
     */
    public static List<List<Integer>> queensAttackTheKing(int[][] queens, int[] king) {
        boolean[][] board = new boolean[8][8];
        for (int[] queen : queens)
            board[queen[0]][queen[1]] = true;

        List<List<Integer>> res = new ArrayList<>();

        /*方向：
        [东]->[西]->[北]->[南]->[东北]->[东南]->[西北]->[西南]*/
        int[] d_X = {0, 0, 1, -1, 1, -1, 1, -1};
        int[] d_Y = {1, -1, 0, 0, 1, 1, -1, -1};

        int king_X = king[0], king_Y = king[1];
        for (int i = 0; i < 8; i++) {
            int cur_X = king_X + d_X[i], cur_Y = king_Y + d_Y[i];

            while (cur_X >= 0 && cur_Y >= 0 && cur_X < 8 && cur_Y < 8) {

                if (board[cur_X][cur_Y]) {
                    res.add(Arrays.asList(cur_X, cur_Y));

                    break;//不能越子而行
                }

                cur_X += d_X[i];
                cur_Y += d_Y[i];
            }
        }
        return res;
    }
}
