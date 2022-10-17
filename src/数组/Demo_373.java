package 数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/1/14 18:55
 */
//373-寻找 和 最小的 k 对数字
public class Demo_373 {
    public static void main(String[] args) {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;
        System.out.println(kSmallestPairs(nums1, nums2, k));
    }

    /**求和排序<br>
    执行用时：905 ms, 在所有 Java 提交中击败了5.07%的用户<br>
    内存消耗：222 MB, 在所有 Java 提交中击败了5.05%的用户
    */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int length_1 = Math.min(nums1.length, k), length_2 = Math.min(nums2.length, k);
        for (int i = 0; i < length_1; i++)
            for (int j = 0; j < length_2; j++)
                res.add(Arrays.asList(nums1[i], nums2[j]));

        res.sort(Comparator.comparing(o -> o.get(0) + o.get(1)));//对其和进行排序

        return res.subList(0, Math.min(res.size(), k));
    }
}
