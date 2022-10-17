package 数组;

import utils.uu;

import java.util.PriorityQueue;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/11 21:45
 */
//1046-最后一块石头的重量
public class Demo_1046 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 4, 1, 8, 1};
        System.out.println(lastStoneWeight(nums));
    }

    /**
     * 优先队列<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了81.01%的用户<br>
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了21.75%的用户
     */
    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);//维护最大值

        for (int weight : stones)
            queue.add(weight);

        while (queue.size() > 1) {
            int big = queue.poll(), small = queue.poll();
            if (big > small)
                queue.offer(big - small);//若两个石头质量相同，将会完全粉碎
        }
        return queue.isEmpty() ? 0 : queue.poll();
    }
}
