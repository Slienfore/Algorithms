package 回溯;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/1 20:32
 */
//1706-球会落在何处
public class Demo_1706 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}};
        uu.print(findBall_1(matrix));
        uu.print(findBall_2(matrix));
    }

    /**
     * 模拟<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了61.69%的用户<br>
     * 内存消耗：42.4 MB, 在所有 Java 提交中击败了81.50%的用户<br>
     * 2022年04月01日  20:44:52
     */
    public static int[] findBall_1(int[][] grid) {
        int balls = grid[0].length;//球的数量
        int[] res = new int[balls];

        for (int cur = 0; cur < balls; ++cur) {
            int pos = cur;//球落在的位置

            for (int[] arr : grid) {
                int tar = arr[pos];//网格值为(1)向右走，为(-1)向左走

                pos += tar;//(1)向右走，(-1)向左走

                if (pos < 0 || pos == balls || arr[pos] != tar) {//若移动位置为 " V " 型，说明将球卡住了，无法移动
                    pos = -1;
                    break;
                }

            }
            res[cur] = pos;
        }

        return res;
    }

    private static int row, col;
    /**
     * DFS<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：42.5 MB, 在所有 Java 提交中击败了57.29%的用户<br>
     * 2022年04月01日  21:43:25
     */
    public static int[] findBall_2(int[][] grid) {
        row = grid.length;
        col = grid[0].length;

        int[] res = new int[col];
        for (int cur = 0; cur < col; ++cur)
            res[cur] = dfs(0, cur, grid);

        return res;
    }
    private static int dfs(int curRow, int pos, int[][] matrix) {
        if (curRow == row)//到达了底端
            return pos;
        if ((pos == 0 && matrix[curRow][pos] == -1))//遇到左边界
            return -1;

        else if ((pos == col - 1 && matrix[curRow][pos] == 1))//遇到右边界
            return -1;

        else if (matrix[curRow][pos] == -1 && matrix[curRow][pos - 1] == 1)//遇到 "V"型槽
            return -1;

        else if (matrix[curRow][pos] == 1 && matrix[curRow][pos + 1] == -1)//遇到 "V"型槽
            return -1;

        return dfs(curRow + 1, pos + matrix[curRow][pos], matrix);
    }

}
