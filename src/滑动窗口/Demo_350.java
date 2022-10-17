package 滑动窗口;

import utils.uu;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/3 9:37
 */
//350-两个数组中的交集-II
public class Demo_350 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1}, nums2 = {2, 2};
        System.out.println(Arrays.toString(intersect_1(nums1, nums2)));
        System.out.println(Arrays.toString(intersect_2(nums1, nums2)));
    }

    /**
     * 双指针<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了91.71%的用户<br>
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了33.15%的用户<br>
     * 2022年04月03日  10:02:18
     */
    public static int[] intersect_2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> path = new ArrayList<>();

        for (int one = 0, two = 0; one < nums1.length && two < nums2.length; ) {//双指针
            if (nums1[one] == nums2[two]) {//相等
                path.add(nums1[one]);
                ++one;
                ++two;
            } else if (nums1[one] > nums2[two])//第一个大了
                ++two;
            else if (nums1[one] < nums2[two])//第一个小了
                ++one;
        }

        int[] res = new int[path.size()];
        for (int cur = 0; cur < path.size(); ++cur)
            res[cur] = path.get(cur);

        return res;
    }


    /**
     * 哈希<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了39.32%的用户<br>
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了27.45%的用户<br>
     * 2022年04月03日  10:03:37
     */
    public static int[] intersect_1(int[] nums1, int[] nums2) {
        //映射 数值以及出现的频率
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int val : nums1)
            map.put(val, map.getOrDefault(val, 0) + 1);

        List<Integer> res = new ArrayList<>();

        for (int val : nums2)
            if (map.getOrDefault(val, 0) > 0) {
                res.add(val);
                map.put(val, map.get(val) - 1);
            }

        return res.stream().mapToInt(Integer::valueOf).toArray();
    }
}
