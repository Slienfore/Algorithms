package 二分查找;

import utils.uu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 10:34
 */
//1337-矩阵中战斗力最弱的 K 行
public class Demo_1337 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}};
        int num = 3;
        uu.print(kWeakestRows(matrix, num));
    }

    /**
     * 二分<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了57.61%的用户<br>
     * 内存消耗：42.6 MB, 在所有 Java 提交中击败了21.13%的用户<br>
     * 2022年04月02日  10:52:19
     */
    public static int[] kWeakestRows(int[][] mat, int k) {
        int row = mat.length, col = mat[0].length;
        //map[][0]:映射下标 map[][1]:映射军人数量
        int[][] map = new int[row][2];

        int curRow = 0;
        for (int[] army : mat) {

            int left = 0, right = col;
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (army[mid] == 1)//军队
                    left = mid + 1;
                else
                    right = mid;
            }

            map[curRow][0] = curRow;//映射下标
            map[curRow++][1] = right;//隐射军人数量
        }

        Arrays.sort(map, (Comparator.comparingInt(o -> o[1])));//对军人数量进行排序

        int[] res = new int[k];
        for (int cur = 0; cur < k; ++cur)
            res[cur] = map[cur][0];

        return res;
    }
}
