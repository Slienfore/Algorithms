package 动态规划;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//2100适合打劫银行的日子
public class Demo_2100 {
    public static void main(String[] args) {
        int[] nums = {5, 3, 3, 3, 5, 6, 2};
//        int[] nums = {1, 1, 1, 1, 1};
        int time = 0;
        System.out.println(goodDaysToRobBank_1(nums, time));
        System.out.println(goodDaysToRobBank_2(nums, time));

    }


    /**
     * 动规<br>
     * 执行用时：8 ms, 在所有 Java 提交中击败了36.68%的用户<br>
     * 内存消耗：60.6 MB, 在所有 Java 提交中击败了5.13%的用户
     */
    public static List<Integer> goodDaysToRobBank_1(int[] security, int time) {
        int length = security.length;
        int[] leftDp = new int[length], rightDp = new int[length];

        leftDp[0] = 0;
        rightDp[length - 1] = 0;

        for (int left = 1, right = length - 2; left < length && right >= 0; left++, right--) {
            leftDp[left] = security[left - 1] >= security[left] ? leftDp[left - 1] + 1 : 0;//若不为递减重置

            rightDp[right] = security[right] <= security[right + 1] ? rightDp[right + 1] + 1 : 0;//不为递增则重置
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < length; i++)
            if (leftDp[i] >= time && rightDp[i] >= time)
                res.add(i);

        return res;
    }


    /**
     * 维护天数区间<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：51.8 MB, 在所有 Java 提交中击败了99.22%的用户
     */
    public static List<Integer> goodDaysToRobBank_2(int[] security, int time) {
        int length = security.length;
        List<Integer> res = new ArrayList<>();

        if (time == 0) {
            while (time < length)
                res.add(time++);
            return res;
        }

        int num = 0;
        for (int i = 1; i < length - time; i++) {
            if (security[i - 1] >= security[i] && security[i + time] >= security[i + time - 1])
                num++;//第 i 天前后都符合条件的时候
            else num = 0;

            if (num >= time)
                res.add(i);
        }

        return res;
    }

}
