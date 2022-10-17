package 周赛;

import utils.uu;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/13 10:52
 */
//6031-找出数组中所有 K 紧邻下标
public class Demo_6031 {
    public static void main(String[] args) {
/*        int[] nums = {1};
        int key = 1, k = 1;*/
        int[] nums = {3, 4, 9, 1, 3, 9, 5};
        int key = 9, k = 1;
/*        int[] nums = {2, 2, 2, 2, 3};
        int key = 2, k = 2;*/
/*        int[] nums = {222, 151, 842, 244, 103, 736, 219, 432, 565, 216, 36, 198, 10, 367, 778, 111, 307, 460, 92, 622, 750, 36, 559, 983, 782, 432, 312, 111, 676, 179, 44, 86, 766, 371, 746, 905, 850, 170, 892, 80, 449, 932, 295, 875, 259, 556, 730, 441, 153, 869};
        int key = 153, k = 19;*/


        System.out.println(findKDistantIndices_1(nums, key, k));
        System.out.println(findKDistantIndices_2(nums, key, k));
        System.out.println(findKDistantIndices_3(nums, key, k));
        System.out.println(findKDistantIndices_4(nums, key, k));

    }

    /**
     * 红黑树<br>
     * 执行用时：5 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static List<Integer> findKDistantIndices_4(int[] nums, int key, int k) {
        int length = nums.length;

        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < length; ++i)
            if (nums[i] == key)
                set.add(i);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < length; ++i) {

            Integer nearKey = set.floor(i + k);//搜索小于等于该值的最近一个结点

            if (nearKey != null && nearKey >= i - k)
                res.add(i);
        }

        return res;
    }


    /**
     * 二分查找<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：42 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static List<Integer> findKDistantIndices_3(int[] nums, int key, int k) {
        int length = nums.length;
        List<Integer> keyList = new ArrayList<>();

        for (int i = 0; i < length; ++i)
            if (nums[i] == key)//将关键字下标添加进入集合
                keyList.add(i);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            int nearKey = binarySearch(keyList, i + k);//搜索其其下一个 key 值

            if (Math.abs(nearKey - i) <= k)//与其下一个结点的区间值相差为 k
                res.add(i);

        }

        return res;

    }


    //二分查找最近的值
    private static int binarySearch(List<Integer> list, int tar) {
        int left = 0, right = list.size() - 1;

        while (left < right) {//搜索小于等于目标（收缩右边界）
            int mid = left + (right - left + 1) / 2;

            if (list.get(mid) > tar)
                right = mid - 1;//[left, mid - 1]
            else
                left = mid;//[mid, right]
        }
        return list.get(left);
    }

    /**
     * 暴力<br>
     * 执行用时：55 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static List<Integer> findKDistantIndices_2(int[] nums, int key, int k) {
        int length = nums.length;
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < length; ++i) {

            int next = i;
            if (nums[i] == key)
                for (int j = Math.max(i - k, 0); j < Math.min(((i + k) + 1), length); ++j) {//限定左右边界

                    if (!res.contains(j))
                        res.add(j);

                    if (nums[j] == key)
                        next = j;
                }
            i = Math.max(next - 1, i);
        }

        return res;
    }


    public static List<Integer> findKDistantIndices_1(int[] nums, int key, int k) {
        int length = nums.length;
        TreeSet<Integer> set = new TreeSet<>();//维护下标有序

        for (int cur = 0; cur < length; ++cur) {
            int next = cur;
            if (nums[cur] == key) {//等于给定的值

                int left = cur - 1, right = cur + 1;//左右开始遍历

                while (left >= 0 && left >= cur - k)
                    set.add(left--);

                set.add(cur);

                while (right < length && right <= cur + k) {
                    set.add(right);

                    if (nums[right] == key)//更新下一个下标
                        next = right;

                    ++right;
                }

            }

            cur = Math.max(next - 1, cur);//下一个位置起始点
        }

        return new ArrayList<>(set);
    }
}
