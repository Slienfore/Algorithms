package 前缀和与差分;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/9 21:31
 */
//995-得分最高的小论调
public class Demo_995 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 4, 0};
//        int[] nums = {1, 3, 0, 2, 4};
//        int[] nums = {6, 2, 8, 3, 5, 2, 4, 3, 7, 6};
        System.out.println(bestRotation(nums));
    }

    /**
     * 差分<br>
     * 执行用时：8 ms, 在所有 Java 提交中击败了20.00%的用户<br>
     * 内存消耗：53.1 MB, 在所有 Java 提交中击败了22.86%的用户
     */
    public static int bestRotation(int[] nums) {
        /*统计单区间值修改
        0 <= i - k <= length - 1 --> k => i - (length - 1)
        nums[i] <= i - k  --> k <= i - nums[i]
        i - (length - 1) <= k <= i - nums[i]
         */
        int length = nums.length;

        int[] dih = new int[length];

        for (int pivot = 0; pivot < length; pivot++) {//论调区间
            int begin = (pivot - (length - 1) + length) % length,
                    end = (pivot - nums[pivot] + length) % length;

            if (begin <= end) {
                dih[begin] += 1;

                if (end + 1 < length)
                    dih[end + 1] -= 1;
            } else {
                dih[0] += 1;//[0， length - 1 - end]
                dih[end + 1] -= 1;

                dih[begin] += 1;//[begin, length - 1]
            }
        }

        int pos = 0, max = dih[0];
        for (int i = 1; i < length; i++) {//搜素得分最高的 k
            dih[i] += dih[i - 1];
            if (dih[i] > max) {//若得分比之前大
                max = dih[i];
                pos = i;//返回坐标最小值
            }
        }
        return pos;
    }

}
