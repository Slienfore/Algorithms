package 二分查找;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/26 21:46
 */
//1385-两个数组之间的距离值
public class Demo_1385 {
    public static void main(String[] args) {
        int[] arr1 = {4, 5, 8}, arr2 = {10, 9, 1, 8};
        int diff = 2;

/*        int[] arr1 = {1, 4, 2, 3}, arr2 = {-4, -3, 6, 10, 20, 30};
        int diff = 3;*/

/*        int[] arr1 = {4, -3, -7, 0, -10}, arr2 = {10};
        int diff = 69;*/
        System.out.println(findTheDistanceValue_1(arr1, arr2, diff));
        System.out.println(findTheDistanceValue_2(arr1, arr2, diff));

    }

    public static int findTheDistanceValue_2(int[] arr1, int[] arr2, int d) {
        int res = 0;

        for (int val : arr1) {
            boolean flag = true;
            for (int curVal : arr2)
                if (Math.abs(val - curVal) <= d) {//模拟
                    flag = false;
                    break;
                }

            if (flag)
                ++res;
        }
        return res;
    }

    /**
     * 二分<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了33.21%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了11.00%的用户
     */
    public static int findTheDistanceValue_1(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);

        int res = 0;

        //搜索范围是[val - diff, val + diff],第一个小于等于其的数(ceil)，第一个大于等于其的数(floor)
        for (int val : arr1) {
            int[] temp = binarySearch(val, d, arr2);//floor,ceil
            //(val - diff)小于其的数(ceil)，(val + diff)大于其的数(floor)
            if (Math.abs(val - temp[0]) > d && Math.abs(val - temp[1]) > d)
                ++res;

        }

        return res;
    }

    private static int[] binarySearch(int tar, int diff, int[] arr) {
        int[] res = new int[2];
        int length = arr.length;

        int left = 0, right = length - 1;
        while (left < right) {//floor
            int mid = left + (right - left + 1) / 2;

            if (arr[mid] > (tar + diff))
                right = mid - 1;
            else
                left = mid;
        }

        res[0] = arr[left];

        left = 0;right = length - 1;

        while (left < right) {//ceil
            int mid = left + (right - left) / 2;

            if (arr[mid] < (tar - diff))
                left = mid + 1;
            else
                right = mid;
        }
        res[1] = arr[left];

        return res;
    }
}
