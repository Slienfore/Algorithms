package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/1 11:28
 */
//1351-统计有序矩阵的负数
public class Demo_1351 {
    public static void main(String[] args) {
        int[][] matrix = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
        System.out.println(countNegatives(matrix));
    }

    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了41.74%的用户<br>
     * 2022年04月01日  11:46:31
     */
    public static int countNegatives(int[][] grid) {
        int col = grid[0].length, res = 0;

        for (int[] cur : grid) {
            int left = 0, right = col;

            while (left < right) {//搜索第一个正数的
                int mid = left + (right - left) / 2;

                if (cur[mid] >= 0)//若为正数
                    left = mid + 1;
                else
                    right = mid;
            }

            res += col - right;
        }

        return res;
    }
}
