package 数组;

import utils.uu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/27 8:32
 */
//2028-找出缺失的观测数据
public class Demo_2028 {
    public static void main(String[] args) {
/*        int[] rolls = {3, 2, 4, 3};
        int mean = 4, sur = 2;*/
/*        int[] rolls = {1, 5, 6};
        int mean = 3, sur = 4;*/
/*        int[] rolls = {1};
        int mean = 3, sur = 1;*/
/*        int[] rolls = {6, 3, 4, 3, 5, 3};
        int mean = 1, sur = 6;*/
        int[] rolls = {4, 2, 2, 5, 4, 5, 4, 5, 3, 3, 6, 1, 2, 4, 2, 1, 6, 5, 4, 2, 3, 4, 2, 3, 3, 5, 4, 1, 4, 4, 5, 3, 6, 1, 5, 2, 3, 3, 6, 1, 6, 4, 1, 3};
        int mean = 2, sur = 53;

        uu.print(missingRolls(rolls, mean, sur));

    }

    /**
     * 模拟<br>
     * 执行用时：9 ms, 在所有 Java 提交中击败了20.72%的用户<br>
     * 内存消耗：57.4 MB, 在所有 Java 提交中击败了37.84%的用户
     */
    public static int[] missingRolls(int[] rolls, int mean, int n) {
        int length = rolls.length, sum = Arrays.stream(rolls).sum();

        //最小值向上取整
        if ((sum + n * 6) / (n + length) < mean || (sum + n * 1 + n + length - 1) / (n + length) > mean)//后面几项全选最大值或最小值都不符合
            return new int[]{};

        //(sum + need) / (n + length) == mean(平均值)
        int need = mean * (n + length) - sum;//需要组成的数
        int[] res = new int[n];

        int param = need / n;
        for (int cur = 0; cur < n; ++cur)//记录能够均分的值
            res[cur] = param;

        if (need % n != 0) {//若不能够被整除说明不能够均分
            int sur = need % n;
            for (int cur = 1; cur <= sur; ++cur)
                res[cur] += 1;
        }

        return res;
    }
}
