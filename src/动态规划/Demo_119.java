package 动态规划;

import utils.uu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/23 15:27
 */
//119-杨辉三角-II
public class Demo_119 {
    public static void main(String[] args) {
        int numRows = 0;
        System.out.println(generate(numRows));
    }

    /**动规(一维)<br>
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     内存消耗：39.1 MB, 在所有 Java 提交中击败了27.77%的用户*/
    public static List<Integer> generate(int rowIndex) {
        int[] dp = new int[rowIndex + 2];//rowIndex 从下标 0 开始
        dp[1] = 1;//第一行

        for (int row = 1; row <= rowIndex; ++row)
            for (int col = row + 1; col >= 1; --col)
                dp[col] += dp[col - 1];//等于上一行的同列与左上角

        List<Integer> temp = new ArrayList<>();

        for (int cur = 1; cur <= rowIndex + 1; ++cur)
            temp.add(dp[cur]);

        return temp;
    }
}