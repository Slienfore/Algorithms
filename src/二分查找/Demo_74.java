package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/16 14:46
 */
//74-搜索二维矩阵
public class Demo_74 {
    public static void main(String[] args) {
        int[][] nums = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int tar = 13;
        System.out.println(searchMatrix(nums, tar));
    }

    /**
     * 二分查找<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41 MB, 在所有 Java 提交中击败了28.26%的用户
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        //搜索初始行
        int begin = 0, end = matrix.length - 1;

        while (begin < end) {//floor
            int mid = begin + (end - begin + 1) / 2, midVal = matrix[mid][0];

            if (midVal > target) {//floor(说明右边不可用)
                end = mid - 1;
            } else
                begin = mid;
        }

        //搜索行内元素
        int left = 0, right = matrix[0].length - 1;

        while (left < right) {//ceiling
            int mid = left + (right - left) / 2, midVal = matrix[begin][mid];

            if (midVal < target) {//说明左边不可用
                left = mid + 1;
            } else
                right = mid;
        }

        return matrix[begin][left] == target;
    }
}
