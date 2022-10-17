package 回溯;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/31 16:06
 */
//79-单词搜索
public class Demo_79 {
    public static void main(String[] args) {
        char[][] matrix = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
/*        char[][] matrix = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "SEE";*/
/*        char[][] matrix = {{'a'}};
        String word = "a";*/

        System.out.println(exist(matrix, word));
    }

    /**
     * 回溯<br>
     * 执行用时：117 ms, 在所有 Java 提交中击败了39.71%的用户<br>
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了52.98%的用户<br>
     * 2022年03月31日  18:23:13
     */
    public static boolean exist(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        visit = new boolean[row][col];
        len = word.length();

        for (int curRow = 0; curRow < row; ++curRow)
            for (int curCol = 0; curCol < col; ++curCol) //搜索每一个位置
                if (backTrack(curRow, curCol, 0, board, word))
                    return true;

        return false;
    }

    //移动方向(上、下、左、右)
    static final private int[][] DIRECTION = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    //行与列
    static int row, col, len;
    //重复访问
    static boolean[][] visit;

    private static boolean backTrack(int row, int col, int index, char[][] board, String tar) {
        if (board[row][col] != tar.charAt(index))//不相等
            return false;
        else if (index == len - 1)//长度相等时若字符匹配
            return true;

        visit[row][col] = true;//当前单词已经访问过了

        for (int[] cur : DIRECTION) {
            int nextRow = row + cur[0], nextCol = col + cur[1];
            if (!isValid(nextRow, nextCol) || visit[nextRow][nextCol])
                continue;

            if (backTrack(nextRow, nextCol, index + 1, board, tar))
                return true;
        }

        visit[row][col] = false;
        return false;
    }

    //判断是否越界
    private static boolean isValid(int tarX, int tarY) {
        return (tarX >= 0 && tarX < row && tarY >= 0 && tarY < col);
    }
}
