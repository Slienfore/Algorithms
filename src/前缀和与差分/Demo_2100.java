package 前缀和与差分;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/8 21:14
 */
//2100-打劫银行
public class Demo_2100 {
    public static void main(String[] args) {
//        int[] nums = {5, 3, 3, 3, 5, 6, 2};
//        int[] nums = {1, 1, 1, 1, 1};
//        int[] nums = {1, 2, 3, 4, 5, 6};
//        int[] nums = {5, 3, 3, 3, 5, 6, 2};
        int[] nums = {1, 2, 5, 4, 1, 0, 2, 4, 5, 3, 1, 2, 4, 3, 2, 4, 8};
        int time = 2;

        System.out.println(goodDaysToRobBank_1(nums, time));
        System.out.println(goodDaysToRobBank_2(nums, time));
    }


    public static List<Integer> goodDaysToRobBank_2(int[] security, int time) {
        int length = security.length;
        int[] collect = new int[length];

        //统计相邻两天递增序列以及递减序列(相等可以忽略<前后都可以>)
        for (int i = 1; i < length; i++) {
            if (security[i] == security[i - 1])//若相等则忽略不计
                continue;

            collect[i] = security[i] > security[i - 1] ? 1 : -1;
        }

        int[] inc = new int[length], dec = new int[length];

        for (int i = 1; i < length; i++) {

            inc[i] += inc[i - 1] + (collect[i] == 1 ? 1 : 0);//前缀递增和
            dec[i] += dec[i - 1] + (collect[i] == -1 ? 1 : 0);//前缀递减和

        }

        List<Integer> res = new ArrayList<>();

        for (int i = time; i < length - time; i++) {//搜索区间[time, length - time]
            int incDays = inc[i] - inc[i - time];//不包含[i - time]之前的坐标的进行比较
            int decDays = dec[i + time] - dec[i];
            if (incDays == 0 && decDays == 0)
                res.add(i);
        }

        return res;
    }

    /**
     * 快慢指针<br>
     * 执行用时：4 ms, 在所有 Java 提交中击败了98.64%的用户<br>
     * 内存消耗：59.5 MB, 在所有 Java 提交中击败了70.07%的用户
     */
    public static List<Integer> goodDaysToRobBank_1(int[] security, int time) {
        List<Integer> res = new ArrayList<>();
        int slow = 0, fast = -1;//slow递减,fast递增

        int length = security.length;

        for (int pivot = 0; pivot < (length - time); pivot++) {
            if (pivot == 0 || security[pivot] > security[pivot - 1])//如果遇到比其大则重置其前一个结点
                slow = pivot;

            fast = Math.max(fast, pivot);//更新fast指针

            while (fast + 1 < length && security[fast + 1] >= security[fast])//fast指针沿着递增队列进行移动
                fast++;

            if (pivot - slow >= time && fast - pivot >= time)
                res.add(pivot);
        }

        return res;
    }


    /**
     * 前缀和<br>
     * 执行用时：7 ms, 在所有 Java 提交中击败了79.75%的用户<br>
     * 内存消耗：59.6 MB, 在所有 Java 提交中击败了67.56%的用户
     */
    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        int length = security.length;
        int[] collect = new int[length];
        for (int i = 1; i < length; i++) {
            if (security[i] == security[i - 1])
                continue;
            collect[i] = security[i] > security[i - 1] ? 1 : -1;//判断是否是递增还是递减
        }

        System.out.println(Arrays.toString(collect));

        int[] inc = new int[length + 1], dec = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            inc[i] = inc[i - 1] + (collect[i - 1] == 1 ? 1 : 0);//统计递增天数的前缀和
            dec[i] = dec[i - 1] + (collect[i - 1] == -1 ? 1 : 0);//统计递减天数的前缀和
        }

        System.out.println(Arrays.toString(inc));
        System.out.println(Arrays.toString(dec));

        List<Integer> res = new ArrayList<>();

        for (int i = time; i < length - time; i++) {
            int incDays = inc[i + 1] - inc[i + 1 - time], decDays = dec[i + 1 + time] - dec[i + 1];
            if (incDays == 0 && decDays == 0)
                res.add(i);
        }
        return res;
    }


}
