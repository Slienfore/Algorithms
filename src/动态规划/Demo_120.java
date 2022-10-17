package 动态规划;

import utils.uu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/23 19:40
 */
//120-三角形最小路径和
public class Demo_120 {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>() {{
            add(Arrays.asList(2));
            add(Arrays.asList(3, 4));
            add(Arrays.asList(6, 5, 7));
            add(Arrays.asList(4, 1, 8, 3));
        }};

        System.out.println(minimumTotal_1(triangle));
        System.out.println(minimumTotal_2(triangle));
    }

    /**
     * 动规<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.27%的用户<br>
     * 内存消耗：41.3 MB, 在所有 Java 提交中击败了6.85%的用户
     */
    public static int minimumTotal_2(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[] dp = new int[triangle.get(row - 1).size()];//最后一行的最大值

        dp[0] = triangle.get(0).get(0);//第一个

        for (int curRow = 1; curRow < row; ++curRow) {
            //最后一个不需要比上边
            dp[curRow] += dp[curRow - 1] + triangle.get(curRow).get(curRow);

            for (int curCol = curRow - 1; curCol > 0; --curCol)
                dp[curCol] = Math.min(dp[curCol], dp[curCol - 1]) + triangle.get(curRow).get(curCol);

            //第一个不需要比左边
            dp[0] += triangle.get(curRow).get(0);
        }

        int min = Integer.MAX_VALUE;
        for (int val : dp)
            min = Math.min(min, val);

        return min;
    }


    /**
     * 动规<br>
     * 执行用时：8 ms, 在所有 Java 提交中击败了9.17%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了9.65%的用户
     */
    public static int minimumTotal_1(List<List<Integer>> triangle) {
        int row = triangle.size();

        for (int curRow = 1; curRow < row; ++curRow) {
            List<Integer> preList = triangle.get(curRow - 1);
            for (int curCol = 0; curCol < (triangle.get(curRow).size()); ++curCol) {
                int best;

                if (curCol == 0)//第一列不用判断左
                    best = preList.get(curCol);
                else if (curCol == (triangle.get(curRow).size()) - 1)//最后一列不用判断上
                    best = preList.get(curCol - 1);
                else
                    best = Math.min(preList.get(curCol - 1), preList.get(curCol));

                triangle.get(curRow).set(curCol, triangle.get(curRow).get(curCol) + best);
            }
        }

        int min = Integer.MAX_VALUE;
        List<Integer> list = triangle.get(row - 1);
        for (int val : list)
            min = Math.min(min, val);

        return min;
    }
}
