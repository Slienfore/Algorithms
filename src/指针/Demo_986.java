package 指针;

import utils.uu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 16:59
 */
//986-区间列表的交集
public class Demo_986 {
    public static void main(String[] args) {
        int[][] matrix1 = {{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                matrix2 = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};

        uu.print(intervalIntersection(matrix1, matrix2));
    }

    /**
     * 双指针<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了92.67%的用户<br>
     * 内存消耗：42.1 MB, 在所有 Java 提交中击败了37.35%的用户<br>
     * 2022年04月02日  17:49:59
     */
    public static int[][] intervalIntersection(int[][] first, int[][] second) {
        int row1 = first.length, row2 = second.length;
        if (row1 == 0 || row2 == 0)
            return new int[][]{};

        List<int[]> path = new ArrayList<>();

        for (int one = 0, two = 0; one < row1 && two < row2; ) {
            int left1 = first[one][0], right1 = first[one][1];//第一个列表的左、右区间
            int left2 = second[two][0], right2 = second[two][1];//第二个列表的左、右区间

            //左边取最大，右边取最小
            int start = Math.max(left1, left2), end = Math.min(right1, right2);

            if (start <= end)
                path.add(new int[]{start, end});//添加交集(左、右区间不能)

            if (right2 > right1)//若其中一个 "较长" 的时候，则将其保留不动
                ++one;
            else
                ++two;
        }

        return path.toArray(new int[0][]);
    }
}