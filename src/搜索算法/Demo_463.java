package 搜索算法;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/3 18:29
 */
//463-岛屿的周长
public class Demo_463 {


    public static void main(String[] args) {
        int[][] grid =
                {
                        {0, 1, 0, 0},
                        {1, 1, 1, 0},
                        {0, 1, 0, 0},
                        {1, 1, 0, 0},
                };
/*        int[][] grid =
                {
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                        {0, 1, 1, 0},
                };*/
/*        int[][] grid =
                {
                        {1, 1},
                        {1, 1},
                };*/
//        System.out.println(islandPerimeter_1(grid));
//        System.out.println(islandPerimeter_2(grid));
//        System.out.println(islandPerimeter_3(grid));
        System.out.println(islandPerimeter_4(grid));
    }

    private static int length, width;
    private static final int[] Coordination = {-1, 0, 1, 0, -1};

    /*BFS-队列
    执行用时：14 ms, 在所有 Java 提交中击败了6.80%的用户
    内存消耗：39.3 MB, 在所有 Java 提交中击败了91.83%的用户
    */
    public static int islandPerimeter_1(int[][] grid) {
        length = grid.length;
        width = grid[0].length;
        boolean[][] visit = new boolean[length][width];

        Queue<int[]> queue = new LinkedList<>();
        int perimeter = 0;

        for (int col = 0; col != length; col++)
            for (int row = 0; row != width; row++)
                if (grid[col][row] != 0) {
                    grid[col][row] = 0;//沉岛

                    queue.add(new int[]{col, row});
                    while (!queue.isEmpty()) {
                        perimeter += 4;

                        int[] arr = queue.poll();
                        int v_X = arr[0], v_Y = arr[1];
                        visit[v_X][v_Y] = true;//标记已经访问了

                        for (int i = 0; i != 4; i++) {
                            int x = v_X + Coordination[i], y = v_Y + Coordination[i + 1];

                            if (x >= 0 && x < length && y >= 0 && y < width && visit[x][y]) {
                                perimeter -= 2;
                            }

                            if (x >= 0 && x < length && y >= 0 && y < width && grid[x][y] != 0) {
                                queue.add(new int[]{x, y});
                                grid[x][y] = 0;//沉岛
                            }

                        }
                    }
                    return perimeter;

                }
        return perimeter;
    }

    /*迭代
    执行用时：8 ms, 在所有 Java 提交中击败了73.95%的用户
    内存消耗：39.3 MB, 在所有 Java 提交中击败了92.05%的用户
     */
    private static int islandPerimeter_2(int[][] grid) {
        length = grid.length;
        width = grid[0].length;
        int ans = 0;

        for (int col = 0; col < length; col++)
            for (int row = 0; row < width; row++)

                if (grid[col][row] == 1) {//找到小岛
                    int count = 0;
                    for (int i = 0; i != 4; i++) {

                        int x = col + Coordination[i], y = row + Coordination[i + 1];
                        if (x < 0 || x >= length || y < 0 || y >= width || grid[x][y] == 0) {//相邻结点为水域则增加周长
                            count++;
                        }
                    }
                    ans += count;
                }
        return ans;
    }

    /*DFS-栈非递归
    执行用时：12 ms, 在所有 Java 提交中击败了9.68%的用户
    内存消耗：39.4 MB, 在所有 Java 提交中击败了90.39%的用户
    */
    private static int islandPerimeter_3(int[][] grid) {
        length = grid.length;
        width = grid[0].length;
        int ans = 0;
        Stack<int[]> stack = new Stack<>();

        for (int col = 0; col != length; col++)
            for (int row = 0; row != width; row++)
                if (grid[col][row] == 1) {
                    stack.push(new int[]{col, row});
                    grid[col][row] = 2;

                    int count = 0;

                    while (!stack.isEmpty()) {
                        int[] arr = stack.pop();

                        int v_X = arr[0], v_Y = arr[1];
                        for (int i = 0; i != 4; i++) {
                            int x = v_X + Coordination[i], y = v_Y + Coordination[i + 1];

                            if (x < 0 || x >= length || y < 0 || y >= width || grid[x][y] == 0) {

                                count++;

                            } else if (grid[x][y] == 1) {//若不是湖泊或者还未访问到
                                stack.push(new int[]{x, y});
                                grid[x][y] = 2;
                            }
                        }
                    }
                    return ans + count;
                }
        return ans;
    }

    /*DFS-递归
    执行用时：7 ms, 在所有 Java 提交中击败了89.02%的用户
    内存消耗：40.1 MB, 在所有 Java 提交中击败了31.91%的用户
    */
    private static int islandPerimeter_4(int[][] grid) {
        length = grid.length;
        width = grid[0].length;

        for (int line = 0; line != length; line++)
            for (int row = 0; row != width; row++)
                if (grid[line][row] != 0) {
                    return dfsRecur(grid, line, row);
                }
        return 0;
    }

    //递归将相邻的岛沉下去
    private static int dfsRecur(int[][] grid, int v_X, int v_Y) {

        if (v_X < 0 || v_X >= length || v_Y < 0 || v_Y >= width) return 1;//越界时

        else if (grid[v_X][v_Y] == 0) return 1;//若没有越界，但是隔壁是湖泊

        else if (grid[v_X][v_Y] == 2) return 0;//若是访问过的岛屿

        grid[v_X][v_Y] = 2;

        return   dfsRecur(grid, v_X - 1, v_Y)

                + dfsRecur(grid, v_X + 1, v_Y)

                + dfsRecur(grid, v_X, v_Y - 1)

                + dfsRecur(grid, v_X, v_Y + 1);
    }
}
