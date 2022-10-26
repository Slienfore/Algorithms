package 栈_队列;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/26 9:52
 */
// 862-和至少为 K 的最短子数组
public class Demo_862 {

    public static void main(String[] args) {
/*        int[] nums = {2, -1, 2};
        int tar = 3;*/
/*        int[] nums = {1};
        int tar = 1;*/
/*        int[] nums = {1, 2};
        int tar = 4;*/
        int[] nums = {84, -37, 32, 40, 95};
        int tar = 167;
        System.out.println(shortestSubarray1(nums, tar));
        System.out.println(shortestSubarray2(nums, tar));
    }

    /**
     * 双端队列<br>
     * 执行用时：29 ms, 在所有 Java 提交中击败了92.03%的用户<br>
     * 内存消耗：53.4 MB, 在所有 Java 提交中击败了24.52%的用户<br>
     * 2022年10月26日  16:36:46
     */
    public static int shortestSubarray2(int[] nums, int tar) {
        int len = nums.length;
        long[] pre = new long[len + 1];

        for (int idx = 0; idx < len; ++idx)
            pre[idx + 1] = pre[idx] + nums[idx];

        int min = len + 1;

        // 存放构成子数组 区间起点下标
        Deque<Integer> queue = new ArrayDeque<>();

        for (int idx = 0; idx <= len; ++idx) {

            long val = pre[idx];

            while (!queue.isEmpty() &&
                    val - pre[queue.peekFirst()] >= tar) {// 搜索最短子数组 => 使用之前区间将会更长
                min = Math.min(min, idx - queue.pollFirst());
            }

            // 若 pre[before] >= pre[last]
            // => after - pre[last] >= after - pre[before], 那么 组成的子数组会更短
            while (!queue.isEmpty() &&
                    pre[queue.peekLast()] >= val) {
                queue.pollLast();
            }

            queue.addLast(idx);
        }

        return min == len + 1 ? -1 : min;
    }

    // 暴力(超时)
    public static int shortestSubarray1(int[] nums, int tar) {
        int len = nums.length;

        int[] pre = new int[len + 1];

        // 初始化前缀和
        for (int cur = 1; cur <= len; ++cur)
            pre[cur] = pre[cur - 1] + nums[cur - 1];

        int min = len + 1;
        for (int rr = 1; rr <= len; ++rr) {
            // 搜索连续区间是否会存在最短子数组
            for (int ll = 0; ll < rr; ++ll) {
                if (pre[rr] - pre[ll] >= tar) {
                    min = Math.min(min, rr - ll);
                }
            }
        }

        return min == len + 1 ? -1 : min;
    }
}
