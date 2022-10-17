package 前缀和与差分;


import utils.uu;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/9 14:17
 */
//2055-蜡烛之间的盘子
public class Demo_2055 {


    public static void main(String[] args) {
//        String str = "***|**|*****|**||**|*";
//        String str = "**|**|***|";
//        String str = "***";
        String str = "***|**|*****|**||**|*";

        int[][] query =
                {
//                        {1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}
//                        {0, 1}, {5, 9}
//                        {2, 2}
                        {1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}
                };

        System.out.println(Arrays.toString(platesBetweenCandles_1(str, query)));
        System.out.println(Arrays.toString(platesBetweenCandles_2(str, query)));
        System.out.println(Arrays.toString(platesBetweenCandles_3(str, query)));

    }

    /**
     * 前缀和<br>
     * 执行用时：79 ms, 在所有 Java 提交中击败了13.18%的用户<br>
     * 内存消耗：102.5 MB, 在所有 Java 提交中击败了52.73%的用户
     */
    public static int[] platesBetweenCandles_1(String s, int[][] queries) {
        int length = s.length(), queLength = queries.length;

        int[] prefix = new int[length + 1];//统计盘子数量前缀和

        List<Integer> candle = new ArrayList<>();//蜡烛下标

        //收集盘子的数目
        for (int pos = 0; pos < length; pos++) {
            char val = s.charAt(pos);
            if (val == '|')
                candle.add(pos);

            prefix[pos + 1] += prefix[pos] + (val == '*' ? 1 : 0);
        }

        if (candle.size() == 0)//说明没有蜡烛
            return new int[queLength];

        int[] res = new int[queLength];
        int index = 0;

        for (int[] arr : queries) {
            //开始：搜索右边最靠近的蜡烛，结束：搜索左边最靠近的蜡烛
            int begin = searchRight(arr[0], candle), end = searchLeft(arr[1], candle);
            res[index++] = begin > end ? 0 : prefix[(end + 1)] - prefix[(begin + 1) - 1];
        }

        return res;
    }

    //搜索左边最近
    private static int searchLeft(int tar, List<Integer> list) {
        int left = 0, right = list.size() - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (tar < list.get(mid)) {//搜索的值大了(右边不可取)
                right = mid - 1;//搜索区间[left, mid - 1]
            } else if (list.get(mid) == tar) {
                left = mid;
            } else if (tar > list.get(mid)) {//搜索区间[mid, right]
                left = mid;
            }

        }

        return list.get(left);
    }

    //搜索右边最近
    private static int searchRight(int tar, List<Integer> list) {
        int left = 0, right = list.size() - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (tar > list.get(mid)) {//搜索的值小了(左边不可取)
                left = mid + 1;//搜索区间[mid + 1, right]
            } else if (list.get(mid) == tar) {
                right = mid;
            } else if (tar < list.get(mid)) {
                right = mid;
            }
        }

        return list.get(left);
    }


    public static int[] platesBetweenCandles_2(String s, int[][] queries) {
        int length = s.length(), queLength = queries.length;

        int[] preSum = new int[length + 1];//盘子数量前缀和
        for (int i = 0; i < length; i++)
            preSum[i + 1] += preSum[i] + (s.charAt(i) == '*' ? 1 : 0);

        int[] left = new int[length], right = new int[length];

        int pos_1 = -1, pos_2 = -1;
        for (int i = 0; i < length; i++) {//搜集最左边与最右边出现盘子的位置
            if (s.charAt(i) == '|')
                pos_1 = i;

            if (s.charAt((length - 1) - i) == '|')
                pos_2 = (length - 1) - i;

            left[i] = pos_1;
            right[(length - 1) - i] = pos_2;
        }

        if (pos_1 == -1 && pos_2 == -1)
            return new int[queLength];//说明没有蜡烛

        int[] res = new int[queLength];
        int index = 0;

        for (int[] arr : queries) {
            int begin = right[arr[0]], end = left[arr[1]];

            res[index++] = (begin == -1 || end == -1 || begin >= end) ? 0 : preSum[end + 1] - preSum[begin];
        }

        return res;
    }

    /**前缀和+红黑树<br>
    执行用时：166 ms, 在所有 Java 提交中击败了5.47%的用户<br>
    内存消耗：87.9 MB, 在所有 Java 提交中击败了75.12%的用户
    */
    public static int[] platesBetweenCandles_3(String s, int[][] queries) {
        int length = s.length(), queLength = queries.length;

        int[] preSum = new int[length + 1];//盘子数量前缀和

        TreeSet<Integer> candles = new TreeSet<>();

        candles.ceiling(0);
        candles.floor(0);


        for (int i = 0; i < length; i++) {
            int val = s.charAt(i);

            if (val == '|') candles.add(i);

            preSum[i + 1] += preSum[i] + (val == '*' ? 1 : 0);
        }

        if (candles.isEmpty())//说明没有蜡烛
            return new int[queLength];

        int[] res = new int[queLength];
        int index = 0;
        for (int[] arr : queries) {//红黑天地版
            if (candles.floor(arr[1]) != null && candles.ceiling(arr[0]) != null && candles.ceiling(arr[0]) < candles.floor(arr[1]))
                res[index] = preSum[candles.floor(arr[1]) + 1] - preSum[candles.ceiling(arr[0])];

            index++;
        }

        return res;
    }


}