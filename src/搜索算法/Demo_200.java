package 搜索算法;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/3 17:04
 */
//200-岛屿数量
public class Demo_200 {
    public static void main(String[] args) {
/*        char[][] grid =
                {
                        {'1', '1', '1', '1', '0'},
                        {'1', '1', '0', '1', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'}
                };*/
        char[][] grid =
                {
                        {'1', '1', '0', '0', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '1', '0', '0'},
                        {'0', '0', '0', '1', '1'}
                };
//        System.out.println(numIslands_1(grid));
//        System.out.println(numIslands_2(grid));
        System.out.println(numIslands_3(grid));
    }


    private static int length, width;
    private static final int[] Coordination = {-1, 0, 1, 0, -1};

    /*DFS-递归
    执行用时：2 ms, 在所有 Java 提交中击败了82.02%的用户
    内存消耗：46.8 MB, 在所有 Java 提交中击败了9.11%的用户
    */
    private static int numIslands_1(char[][] grid) {
        length = grid.length;
        width = grid[0].length;
        int num = 0;

        for (int row = 0; row != length; row++)
            for (int col = 0; col != width; col++)
                if (grid[row][col] != '0') {
                    dfsRecur(grid, row, col);
                    num++;
                }
        return num;
    }

    //递归将相邻的岛沉下去
    private static void dfsRecur(char[][] grid, int v_X, int v_Y) {
        if (v_X < 0 || v_X >= length || v_Y < 0 || v_Y >= width || grid[v_X][v_Y] == '0') return;

        grid[v_X][v_Y] = '0';

        dfsRecur(grid, v_X + 1, v_Y);

        dfsRecur(grid, v_X - 1, v_Y);

        dfsRecur(grid, v_X, v_Y + 1);

        dfsRecur(grid, v_X, v_Y - 1);
    }

    /*DFS-栈
    执行用时：6 ms, 在所有 Java 提交中击败了14.99%的用户
    内存消耗：46.8 MB, 在所有 Java 提交中击败了11.26%的用户
    */
    private static int numIslands_3(char[][] grid) {
        length = grid.length;
        width = grid[0].length;

        int num = 0;
        //定义行与列坐标的栈
        Deque<int[]> stack = new LinkedList<>();

        for (int row = 0; row < length; row++)
            for (int col = 0; col < width; col++)
                if (grid[row][col] != '0') {
                    grid[row][col] = 0;//当前岛屿访问过了
                    num++;

                    stack.push(new int[]{row, col});
                    while (!stack.isEmpty()) {
                        int[] arr = stack.pop();
                        int v_X = arr[0], v_Y = arr[1];

                        for (int i = 0; i != 4; i++) {
                            int x = v_X + Coordination[i], y = v_Y + Coordination[i + 1];//进行”上->右->下->左“移动

                            if (x >= 0 && x < length && y >= 0 && y < width && grid[x][y] != '0') {
                                grid[x][y] = '0';//该结点已经访问过了
                                stack.push(new int[]{x, y});
                            }
                        }
                    }
                }
        return num;
    }


    /*BFS—队列
    执行用时：6 ms, 在所有 Java 提交中击败了14.99%的用户
    内存消耗：46.8 MB, 在所有 Java 提交中击败了17.00%的用户
    */
    private static int numIslands_2(char[][] grid) {
        length = grid.length;
        width = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int num = 0;

        for (int row = 0; row != length; row++)
            for (int col = 0; col != width; col++)
                if (grid[row][col] != '0') {
                    num++;
                    grid[row][col] = '0';//沉岛
                    queue.add(new int[]{row, col});
                    while (!queue.isEmpty()) {
                        int[] arr = queue.poll();//队头出队
                        int v_X = arr[0], v_Y = arr[1];

                        for (int i = 0; i != 4; i++) {
                            int x = v_X + Coordination[i], y = v_Y + Coordination[i + 1];//上下左右进行移动

                            if (x >= 0 && x < length && y >= 0 && y < width && grid[x][y] != '0') {
                                grid[x][y] = '0';//沉岛
                                queue.add(new int[]{x, y});
                            }
                        }

                    }
                }
        return num;
    }
}
