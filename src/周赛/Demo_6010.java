package 周赛;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/27 11:55
 */
//6010-完成旅程最小时间
public class Demo_6010 {
    public static void main(String[] args) {
/*        int[] time = {1, 2, 3};
        int step = 5;*//*
        int[] time = {2};
        int step = 1;*/
        int[] time = {5, 10, 10};
        int step = 9;
        System.out.println(minimumTime_1(time, step));
    }


    /**
     * 二分查找<br>
     * 执行用时：94 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：55.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static long minimumTime_1(int[] time, int totalTrips) {
        Arrays.sort(time);//寻找花费时间最小的公交车

        long right = (long) time[0] * totalTrips;//最大限定时间是独自走完全过程
        long left = 0;

        while (right > left) {
            int mid = (int) (left + right) >>> 1;

            long finish = 0;//搜索在此时间的车能完成的趟数
            for (int car : time) {
                if (car > mid)
                    break;
                finish += (int) mid / car;
            }

            if (finish < totalTrips) {//如果不能完成总行程说明mid与mid左边的元素均不可取
                left = mid + 1;//搜索区间[mid + 1, right]
            } else {
                right = mid;//搜索区间[left， mid]
            }
        }

        return left;
    }


    /**
     * 超时
     */
    public static long minimumTime(int[] time, int totalTrips) {
        Arrays.sort(time);//找出花费时间最小的车辆

        long maxTime = (long) time[0] * totalTrips;//花费时间最小的货车出发，走完全过程，如果有在这个时间段内完成的车辆就时间减少

        for (int mins = 1; mins <= maxTime; mins++) {//总共需要多长时间

            for (int val : time) {
                if (mins >= val && mins % val == 0) //如果有其他汽车在这个时间段内完成
                    totalTrips--;


                if (totalTrips == 0)
                    return mins;
            }
        }

        return maxTime;
    }
}
