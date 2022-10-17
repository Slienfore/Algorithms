package 搜索算法;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/3 21:52
 */
//827-最大人工岛
public class Demo_827 {
    public static void main(String[] args) {
        int[][] grid =
                {
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                };
        System.out.println(largestIsland(grid));
    }

    private static int length, width;
    private static final int[] Coordination = {-1, 0, 1, 0, -1};

    /*DFS
    执行用时：71 ms, 在所有 Java 提交中击败了91.02%的用户
    内存消耗：78.6 MB, 在所有 Java 提交中击败了34.45%的用户
    */
    public static int largestIsland(int[][] grid) {
        length = grid.length;
        width = grid[0].length;
        int maxArea = 0, color = 2;//标记的每一块陆地

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int col = 0; col != length; col++)
            for (int row = 0; row != width; row++)
                if (grid[col][row] == 1)//遍历每一个岛屿
                {//先将每一一块陆地进行染色

                    int area = dfsRur(grid, col, row, color);

                    maxArea = Math.max(maxArea, area);//统计最大的岛屿面积

                    map.put(color, area);//将其颜色存放进其中

                    color++;//颜色增加
                }

        if (maxArea == 0) return 1;//若找不到一块陆地，那么就填一块海

        //开始填海
        for (int col = 0; col != length; col++)
            for (int row = 0; row != width; row++)
                if (grid[col][row] == 0) {

                    HashSet<Integer> sea = sea(grid, col, row);
                    if (sea.isEmpty()) continue;//岛屿周围没有陆地

                    int sea_Island = 1;//填海
                    for (Integer land : sea) {
                        sea_Island += map.get(land);//获取该陆地的面积
                    }
                    maxArea = Math.max(sea_Island, maxArea);
                }

        return maxArea;
    }

    /**
     * 统计每一块海域中相邻的岛屿
     * @param grid 图
     * @param v_X 横坐标
     * @param v_Y 纵坐标
     */
    private static HashSet<Integer> sea(int[][] grid, int v_X, int v_Y) {
        HashSet<Integer> set = new HashSet<>();

        if (isArea(v_X + 1, v_Y) && grid[v_X + 1][v_Y] != 0)//判断上边是否为岛屿
            set.add(grid[v_X + 1][v_Y]);

        if (isArea(v_X - 1, v_Y) && grid[v_X - 1][v_Y] != 0)//判断下边是否为岛屿
            set.add(grid[v_X - 1][v_Y]);

        if (isArea(v_X, v_Y - 1) && grid[v_X][v_Y - 1] != 0)//判断左边是否为岛屿
            set.add(grid[v_X][v_Y - 1]);

        if (isArea(v_X, v_Y + 1) && grid[v_X][v_Y + 1] != 0)//判断右边是否为岛屿
            set.add(grid[v_X][v_Y + 1]);

        return set;
    }

    /**
     * 统计各个岛屿的最大面积数据
     *
     * @param color 岛屿的颜色
     * @param grid  遍历的图
     * @param v_X   X坐标
     * @param v_Y   Y坐标
     */
    private static int dfsRur(int[][] grid, int v_X, int v_Y, int color) {

        if (!isArea(v_X, v_Y) || grid[v_X][v_Y] != 1) return 0;//若不处于边界之中，或者不属于陆地那么就面积为0

        grid[v_X][v_Y] = color;

        return 1
                + dfsRur(grid, v_X + 1, v_Y, color)

                + dfsRur(grid, v_X - 1, v_Y, color)

                + dfsRur(grid, v_X, v_Y + 1, color)

                + dfsRur(grid, v_X, v_Y - 1, color);
    }

    /**
     * 判断是否处于边界之中
     * @param v_X 横坐标
     * @param v_Y 纵坐标*/
    private static boolean isArea(int v_X, int v_Y) {
        return (v_X >= 0 && v_X < length && v_Y >= 0 && v_Y < width);
    }
}
