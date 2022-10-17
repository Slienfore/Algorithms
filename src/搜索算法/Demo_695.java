package 搜索算法;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/2 21:46
 */
//695-岛屿的最大面积
public class Demo_695 {
    public static void main(String[] args) {
        int[][] grid =
                {
                        {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
                };
//        System.out.println(maxAreaOfIsland_1(grid));
//        System.out.println(maxAreaOfIsland_2(grid));
//        System.out.println(maxAreaOfIsland_3(grid));
        System.out.println(maxAreaOfIsland_4(grid));
    }


    /*DFS-递归
    执行用时：2 ms, 在所有 Java 提交中击败了99.99%的用户
    内存消耗：38.7 MB, 在所有 Java 提交中击败了81.58%的用户
    */
    private static int maxAreaOfIsland_1(int[][] grid) {
        int maxArea = 0;
        int length = grid.length, width = grid[0].length;
        for (int line = 0; line < length; line++) {//遍历每一行

            for (int row = 0; row < width; row++) {

                if (grid[line][row] == 1) {
                    int max = dfsTraverse(grid, line, row, length, width);
                    maxArea = Math.max(max, maxArea);//比较为 “ 1 ” 及其相邻的结点的岛屿面积
                }
            }
        }
        return maxArea;
    }

    private static int dfsTraverse(int[][] grid, int line, int row, int length, int width) {

        if (line < 0 || line >= length || row < 0 || row >= width || grid[line][row] == 0) return 0;

        //将属于岛屿面积的重置
        grid[line][row] = 0;
        //默认遍历了一次岛屿
        int sum = 1;

        sum += dfsTraverse(grid, line - 1, row, length, width);//上

        sum += dfsTraverse(grid, line + 1, row, length, width);//下

        sum += dfsTraverse(grid, line, row - 1, length, width);//左

        sum += dfsTraverse(grid, line, row + 1, length, width);//右

        return sum;
    }

    /*DFS-移动数组进行递归
    执行用时：2 ms, 在所有 Java 提交中击败了99.99%的用户
    内存消耗：39.1 MB, 在所有 Java 提交中击败了29.29%的用户
    */
    private static int maxAreaOfIsland_2(int[][] grid) {
        int max = 0;
        int length = grid.length, width = grid[0].length;

        for (int line = 0; line < length; line++) {//行
            for (int row = 0; row < width; row++) {//列

                if (grid[line][row] != 0) {
                    max = Math.max(max, dfsCoordinate(grid, line, row, length, width));
                }

            }
        }
        return max;
    }


    private static final int[] V_X = {-1, 1, 0, 0}, V_Y = {0, 0, 1, -1};//定义控制上下左右移动的坐标数组

    //通过坐标来进行移动递归
    private static int dfsCoordinate(int[][] grid, int v_x, int v_y, int length, int width) {
        int area = 1;
        grid[v_x][v_y] = 0;//访问过了

        for (int i = 0; i < 4; i++) {

            int x = v_x + V_X[i], y = v_y + V_Y[i];//坐标上下左右运动

            if (x >= 0 && x < length && y >= 0 && y < width && grid[x][y] != 0)
                area += dfsCoordinate(grid, x, y, length, width);
        }
        return area;
    }

    /*DFS-非递归(栈)
    执行用时：3 ms, 在所有 Java 提交中击败了50.47%的用户
    内存消耗：38.8 MB, 在所有 Java 提交中击败了75.62%的用户
    */
    private static final int[] Coordination = {-1, 0, 1, 0, -1};//进行”上->右->下->左“移动

    private static int maxAreaOfIsland_3(int[][] grid) {
        int length = grid.length, width = grid[0].length;

        int max = 0;
        //定义行与列坐标的栈
        Deque<Integer> stack_X = new LinkedList<>();
        Deque<Integer> stack_Y = new LinkedList<>();

        for (int line = 0; line < length; line++)
            for (int row = 0; row < width; row++)
                if (grid[line][row] != 0) {
                    int area = 1;//初始化该岛屿面积
                    grid[line][row] = 0;//当前岛屿访问过了

                    stack_X.push(line);
                    stack_Y.push(row);

                    while (!stack_X.isEmpty()) {
                        Integer v_X = stack_X.pop(), v_Y = stack_Y.pop();

                        for (int i = 0; i < 4; i++) {
                            int x = v_X + Coordination[i], y = v_Y + Coordination[i + 1];//进行”上->右->下->左“移动

                            if (x >= 0 && x < length && y >= 0 && y < width && grid[x][y] != 0) {
                                grid[x][y] = 0;//该结点已经访问过了
                                area++;

                                stack_X.push(x);
                                stack_Y.push(y);
                            }
                        }
                    }
                    max = Math.max(area, max);
                }
        return max;
    }

    /*BFS-队列
    执行用时：3 ms, 在所有 Java 提交中击败了50.47%的用户
    内存消耗：38.8 MB, 在所有 Java 提交中击败了71.63%的用户
     */
    private static int maxAreaOfIsland_4(int[][] grid) {
        int length = grid.length, width = grid[0].length;

        int max = 0;
        //存放的是坐标数组
        Queue<int[]> queue = new LinkedList<>();

        for (int line = 0; line != length; line++)
            for (int row = 0; row != width; row++)
                if (grid[line][row] != 0) {//若搜寻到的是岛屿
                    int area = 1;
                    grid[line][row] = 0;

                    queue.add(new int[]{line, row});//添加坐标
                    while (!queue.isEmpty()) {
                        int[] arr = queue.poll();

                        int v_X = arr[0], v_Y = arr[1];
                        for (int i = 0; i != 4; i++) {
                            int x = v_X + Coordination[i], y = v_Y + Coordination[i + 1];

                            if (x >= 0 && x < length && y >= 0 && y < width && grid[x][y] != 0) {
                                grid[x][y] = 0;
                                queue.add(new int[] {x, y});//加入链表尾
                                area++;
                            }
                        }
                    }
                    max = Math.max(area, max);
                }
        return max;
    }
}