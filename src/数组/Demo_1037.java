package 数组;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/8 7:48
 */
//1037-有效的回旋镖
public class Demo_1037 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1}, {2, 3}, {3, 2}};

//        int[][] matrix = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println(isBoomerang(matrix));
    }

    public static boolean isBoomerang(int[][] points) {
        int[] ab = {points[1][0] - points[0][0], points[1][1] - points[0][1]};
        int[] ac = {points[2][0] - points[0][0], points[2][1] - points[0][1]};

        return ab[0] * ac[1] != ab[1] * ac[0];
    }
}
