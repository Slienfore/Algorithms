package 哈希表;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/19 20:04
 */
//Demo——349：两个数组的交集
public class Demo_349 {
    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(intersection_1(nums1, nums2)));

        System.out.println(Arrays.toString(intersection_2(nums1, nums2)));

        System.out.println(Arrays.toString(intersection_3(nums1, nums2)));

        System.out.println(Arrays.toString(intersection_4(nums1, nums2)));
    }

    /*哈希表：
    执行用时：4 ms, 在所有 Java 提交中击败了21.89%的用户
    内存消耗：38.8 MB, 在所有 Java 提交中击败了9.55%的用户
    */

    public static int[] intersection_1(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        for (int value : nums1) {
            set.add(value);
        }

        for (int value : nums2) {
            if (set.contains(value)) {//当哈希表中存在交集的时候则进行存放进集合中
                list.add(value);
                set.remove(value);
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();//将List<Integer> 转换成 int[]
    }

    /*哈希表(不调用库函数)
    执行用时：2 ms, 在所有 Java 提交中击败了90.29%的用户
    内存消耗：38.7 MB, 在所有 Java 提交中击败了20.63%的用户
    */
    public static int[] intersection_3(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<>();

        HashSet<Integer> set = new HashSet<>();
        for (int value : nums1) {
            set.add(value);
        }

        for (int value : nums2) {
            if (set.contains(value)) {//当哈希表中存在交集的时候则进行存放进集合中
                list.add(value);
                set.remove(value);
            }
        }
        //直接通过遍历集合存放进数组之中
        int[] result = new int[list.size()];
        int index = 0;
        for (int value : list) {
            result[index] = value;
            index++;
        }
        return result;
    }


    /*
    哈希表：
    执行用时：3 ms, 在所有 Java 提交中击败了42.39%的用户
    内存消耗：38.5 MB, 在所有 Java 提交中击败了76.01%的用户
    */
    public static int[] intersection_2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        HashSet<Integer> set_1 = new HashSet<>();
        HashSet<Integer> set_2 = new HashSet<>();

        for (int value : nums1) {
            set_1.add(value);
        }
        for (int value : nums2) {
            set_2.add(value);
        }

        set_1.retainAll(set_2);//求出两个集合中的交集

        int[] result = new int[set_1.size()];
        int index = 0;
        for (int value : set_1) {
            result[index] = value;
            index++;
        }
        return result;
    }

    /*哈希表继续优化
    执行用时：2 ms, 在所有 Java 提交中击败了90.29%的用户
    内存消耗：38.7 MB, 在所有 Java 提交中击败了39.20%的用户
     */
    public static int[] intersection_4(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        HashSet<Integer> set_1 = new HashSet<>();
        HashSet<Integer> set_2 = new HashSet<>();

        for (int value : nums1) {
            set_1.add(value);
        }
        for (int value : nums2) {
            if (set_1.contains(value)) {//当仅有set_1中存在元素的时候，才添加进set_2中
                set_2.add(value);
            }
        }
        int[] result = new int[set_2.size()];
        int index = 0;
        for (int value : set_2) {
            result[index] = value;
            index++;
        }
        return result;
    }


}
