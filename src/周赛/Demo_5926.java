package 周赛;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/14 10:36
 */
public class Demo_5926 {

    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 1};
        int pos = 0;
        System.out.println(timeRequiredToBuy_1(nums, pos));
        System.out.println(timeRequiredToBuy_2(nums, pos));
    }

    /**队列<br>
    执行用时：11 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：37.7 MB, 在所有 Java 提交中击败了100.00%的用户
    */
    public static int timeRequiredToBuy_1(int[] tickets, int k) {
        int time = 0, size = tickets.length;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < size; i++)
            queue.add(i);

        while (!queue.isEmpty()) {
            time++;
            int temp = queue.peek();

            queue.poll();

            if (--tickets[temp] > 0) queue.add(temp);
            else if (temp == k) return time;//如果其其结果小于 0 的时候，则且 == 的时候返回
        }

        return time;
    }

    /**规律<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：35.7 MB, 在所有 Java 提交中击败了100.00%的用户
    */
    public static int timeRequiredToBuy_2(int[] tickets, int k) {
        int val = tickets[k], length = tickets.length;

        int res = val;

        for (int i = 0; i < length; i++) {
            if (i < k) {
                res += Math.min(val, tickets[i]);
            } else if (i > k) {
                res += Math.min(val - 1, tickets[i]);//后面的是要求其排完之后数据
            }
        }
        return res;
    }
}
