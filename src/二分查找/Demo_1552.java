package 二分查找;

import utils.uu;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/9 10:31
 */
//1552-两球之间的磁力
public class Demo_1552 {
    public static void main(String[] args) {
        int[] pos = {1, 2, 3, 4, 7};
        int all = 3;

        System.out.println(maxDistance(pos, all));
    }

    /**
     * 二分(极大化最小值)<br>
     * 执行用时：47 ms, 在所有 Java 提交中击败了84.09%的用户<br>
     * 内存消耗：55.7 MB, 在所有 Java 提交中击败了59.32%的用户<br>
     * 2022年06月09日  10:56:51
     */
    public static int maxDistance(int[] pos, int all) {
        Arrays.sort(pos);//单调性--间距从小到大排列

        int length = pos.length,
                left = 1, right = pos[length - 1] - pos[0] + 1;//[起始,开头]就是最大磁力

        while (left < right) {//(极大化最小值->极大化磁力)
            int mid = left + (right - left + 1) / 2;

            if (check(mid, all, pos))//恰好满足->增大(最小)磁力
                left = mid;
            else
                right = mid - 1;//磁力过大, 球放不下, 缩小
        }

        return left;//恰好放置所有球(极大化)的磁力(最小值)
    }

    private static boolean check(int mid, int all, int[] pos) {
        int pre = pos[0], cnt = 1;//第一个球放置在起点

        for (int cur = 1; cur < pos.length; ++cur)
            if (pos[cur] - pre >= mid) {//超出 || 恰好满足 -> 限制磁力大小
                pre = pos[cur];

                ++cnt;
            }

        return cnt >= all;//当前磁力下能否放得下全部小球
    }
}
