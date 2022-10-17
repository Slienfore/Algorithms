package 二分查找;

import utils.uu;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/2 11:06
 */
//1346-检查整数及其两倍数是否存在
public class Demo_1346 {
    public static void main(String[] args) {
//        int[] arr = {10, 2, 5, 3};
//        int[] arr = {-10, 12, -20, -8, 15};
//        int[] arr = {-20, 8, -6, -14, 0, -19, 14, 4};
        int[] arr = {-2, 0, 10, -19, 4, 6, -8};

        System.out.println(checkIfExist_1(arr));
        System.out.println(checkIfExist_2(arr));
    }

    /**
     * 哈希<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了21.39%的用户<br>
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了36.00%的用户<br>
     * 2022年04月02日  12:16:00
     */
    public static boolean checkIfExist_2(int[] arr) {
        HashSet<Integer> set = new HashSet<>();

        for (int val : arr) {
            if (val == 0 && set.contains(val))//0 的数量成对出现
                return true;

            set.add(val);
        }

        set.remove(0);// 0 不成对出现(0 的二倍依旧是 0)

        for (int val : set)
            if (set.contains(2 * val))
                return true;

        return false;
    }


    /**
     * 二分<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了21.39%的用户<br>
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了12.11%的用户<br>
     * 2022年04月02日  11:58:01
     */
    public static boolean checkIfExist_1(int[] arr) {
        int length = arr.length;
        Arrays.sort(arr);

        int zero = 0;
        for (int cur : arr) {
            if (cur == 0) {
                ++zero;
                if (zero == 2)
                    return true;
                continue;
            }

            int val = 2 * cur;

            for (int left = 0, right = length - 1; left <= right; ) {//包含负数，可能在左段
                int mid = left + (right - left) / 2;

                if (arr[mid] == val)//若为 0 可能会与其重复
                    return true;
                else if (val > arr[mid])//小了
                    left = mid + 1;
                else if (val < arr[mid])//大了
                    right = mid - 1;
            }

        }
        return false;
    }
}
