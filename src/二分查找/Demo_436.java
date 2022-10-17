package 二分查找;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/5 10:59
 */
public class Demo_436 {
    public static void main(String[] args) {
        int[][] intervals = {
                {3, 4},
                {2, 3},
                {1, 2}
/*                {1, 4},
                {2, 3},
                {3, 4}*/
        };

        System.out.println(Arrays.toString(findRightInterval_1(intervals)));
        System.out.println(Arrays.toString(findRightInterval_2(intervals)));

    }


    /**
     * 二分<br>
     * 执行用时：12 ms, 在所有 Java 提交中击败了73.49%的用户<br>
     * 内存消耗：46.1 MB, 在所有 Java 提交中击败了30.27%的用户
     */
    public static int[] findRightInterval_1(int[][] intervals) {
        int length = intervals.length;
        int[][] mapping = new int[length][2];//映射区间 start 以及下标，方便对start排序
        for (int i = 0; i < length; i++) {
            mapping[i][0] = intervals[i][0];//存放 start
            mapping[i][1] = i;//存放地址
        }

        Arrays.sort(mapping, Comparator.comparingInt(arr -> arr[0]));//对二维数组中的start 进行排序

        Arrays.sort(mapping, (o1, o2) -> o1[0] - o2[0]);

        int[] res = new int[length];

        for (int i = 0; i < length; i++) {//对每一个区间的end进行查找
            int end = intervals[i][1];

            int left = 0, right = length;//如果找不到则会越界
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (mapping[mid][0] < end) {
                    left = mid + 1;
                } else if (mapping[mid][0] == end) {
                    right = mid;
                } else if (mapping[mid][0] > end) {
                    right = mid;
                }

            }
            res[i] = left == length ? -1 : mapping[left][1];//找不到就会越界
        }
        return res;
    }

    /**
     * 红黑树<br>
     * 执行用时：18 ms, 在所有 Java 提交中击败了50.52%的用户<br>
     * 内存消耗：45.7 MB, 在所有 Java 提交中击败了42.59%的用户
     */
    public static int[] findRightInterval_2(int[][] intervals) {
        int length = intervals.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < length; i++)
            map.put(intervals[i][0], i);//进行地址映射且排序

        int[] res = new int[length];

        for (int i = 0; i < length; i++) {
            int end = intervals[i][1];
            Map.Entry<Integer, Integer> entry = map.ceilingEntry(end);//红黑树(返回大于等于给定键最小相关联的键值映射)

            res[i] = entry == null ? -1 : entry.getValue();//若不存在此键，则返回为 null
        }

        return res;
    }

}
