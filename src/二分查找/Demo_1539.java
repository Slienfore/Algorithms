package 二分查找;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/31 10:52
 */
//1539-第 K 个缺失的数
public class Demo_1539 {
    public static void main(String[] args) {
/*        int[] arr = {2, 3, 4, 7, 11};
        int tar = 5;*/
        int[] arr = {2};
        int tar = 1;

        System.out.println(findKthPositive(arr, tar));

    }


    /**
     * 二分<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了67.47%的用户<br>
     * 2022年03月31日  12:01:27
     */
    public static int findKthPositive(int[] arr, int k) {
        if (arr[0] > k)
            return k;

        int left = 0, right = arr.length;

        while (left < right) {//ceiling
            int mid = left + (right - left) / 2, lost = arr[mid] - mid - 1;//缺失的数据项

            if (lost >= k)
                right = mid;

            else
                left = mid + 1;
        }

        int need = k - (arr[left - 1] - (left - 1) - 1);//还差多少个数

        return arr[left - 1] + need;
    }
}
