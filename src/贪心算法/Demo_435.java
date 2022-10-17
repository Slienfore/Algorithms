package 贪心算法;

import java.util.Arrays;
import java.util.Comparator;

//435-无重叠区间
public class Demo_435 {
    public static void main(String[] args) {
        int[][] nums = {{1, 2}, {2, 3}};
        System.out.println(Arrays.deepToString(nums));
        Arrays.sort(nums, ((o1, o2) -> o1[0] - o2[0]));
        System.out.println(Arrays.deepToString(nums));
        System.out.println(eraseOverlapIntervals(nums));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        int length = intervals.length;
        int result = 0;
        //按照第一个进行排序(终点始终都大于第一位)
        Arrays.sort(intervals, ((o1, o2) -> o1[1] - o2[1]));
        for (int first = 1; first < length; first++) {//从第二层数组开始遍历
            //判断第二层是否在其区间上
            if (intervals[first][0] < intervals[first - 1][1]) {
                result++;
                //如果相同则将其抹去
                intervals[first] = intervals[first - 1];
            }
        }
        return result;
    }
}
