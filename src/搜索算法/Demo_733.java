package 搜索算法;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/4 11:37
 */
//733-图像渲染
public class Demo_733 {
    public static void main(String[] args) {
        int[][] image =
                {
                        {0, 0, 1},
                        {0, 1, 1},
                        {0, 0, 1},
                };

        System.out.println(Arrays.deepToString(floodFill(image, 1, 1, 1)));
    }


    private static int length, width;
    /*DFS-递归
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：39.3 MB, 在所有 Java 提交中击败了37.42%的用户
    */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        length = image.length;
        width = image[0].length;
        //若新老颜色相同那么就不用比较了
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;

        dfsRecur(image, sr, sc, oldColor, newColor);

        return image;
    }

    private static void dfsRecur(int[][] image, int v_X, int v_Y, int oldColor, int newColor) {
        if (v_X < 0 || v_X >= length || v_Y < 0 || v_Y >= width || image[v_X][v_Y] != oldColor) return;

        image[v_X][v_Y] = newColor;//进行染色

        dfsRecur(image, v_X + 1, v_Y, oldColor, newColor);

        dfsRecur(image, v_X - 1, v_Y, oldColor, newColor);

        dfsRecur(image, v_X, v_Y + 1, oldColor, newColor);

        dfsRecur(image, v_X, v_Y - 1, oldColor, newColor);
    }
}
