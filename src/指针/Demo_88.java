package 指针;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/5 16:49
 */
//88-合并两个有序数组
public class Demo_88 {
//    static int[] nums1 = {1, 2, 3, 0, 0, 0}, nums2 = {2, 5, 6};
    static int[] nums1 = {0}, nums2 = {1};
//    static int[] nums1 = {2, 0}, nums2 = {1};


    public static void main(String[] args) {
//        int m = 3, n = 3;
        int m = 0, n = 1;
//        int m = 1, n = 1;

        merge(nums1, m, nums2, n);

        System.out.println(Arrays.toString(nums1));
    }


    /**
     * 逆序插入<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了18.47%的用户
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length - 1;//数组一中最后一个元素
        int point_1 = m - 1, point_2 = n - 1;//指向两个有效数值的指针

        while (point_2 >= 0) {//
            while (point_1 >= 0 && nums1[point_1] > nums2[point_2])
                nums1[index--] = nums1[point_1--];

            nums1[index--] = nums2[point_2--];//如果大数组中没有元素说明不用进行比较，直接插入
        }
    }

}