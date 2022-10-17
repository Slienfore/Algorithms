package 周赛;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/12/19 11:11
 */
//5958- 股票平滑下跌阶段的数目
public class Demo_5958 {
    public static void main(String[] args) {
        int[] prices = {0, 0, 0, 0};//68
        System.out.println(getDescentPeriods(prices));
    }

    public static long getDescentPeriods(int[] prices) {
        long res = prices.length;
        int length = prices.length, inc = 0;

        for (int i = 1; i < length; i++) {
            if (prices[i - 1] - 1 == prices[i]) {
                inc++;
                res += inc;
            } else {
                inc = 0;
            }
        }
        return res;
    }
}
