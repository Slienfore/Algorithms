package 栈_队列;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/30 19:18
 */
//239-滑动窗口最大数值
public class Demo_239 {
    public static void main(String[] args) {
//        int[] nums = {1, 3, 1, 2, 0, 5};
//        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] nums = {7, 2, 4};
//        int[] nums = {-7, -8, 7, 5, 7, 1, 6, 0};
        int[] nums = {9, 10, 9, -7, -4, -8, 2, -6};//5 [10,10,9,2]
        int k = 5;
        System.out.println(Arrays.toString(maxSlidingWindow_3(nums, k)));
        System.out.println(Arrays.toString(maxSlidingWindow_4(nums, k)));
    }

    /*超时*/
    public static int[] maxSlidingWindow_1(int[] nums, int k) {
        if (nums == null || k == 0) return nums;

        int length = nums.length - k + 1;
        int[] res = new int[length];
        Stack<Integer> stack = new Stack<>();
        int index = 0;

        for (int i = 0; i < length; i++) {
            for (int j = i; j < i + k; j++) {
                if (stack.empty() || nums[j] > stack.peek())
                    stack.push(nums[j]);
            }
            if (!stack.empty()) {
                res[index] = stack.peek();
                index++;
            }
            stack.clear();
        }
        return res;
    }

    /*单调队列(存放下标)
    执行用时：29 ms, 在所有 Java 提交中击败了82.76%的用户
    内存消耗：52.2 MB, 在所有 Java 提交中击败了91.52%的用户
    */
    public static int[] maxSlidingWindow_3(int[] nums, int k) {
        if (nums.length == 1 || k == 1) return nums;//当只有一个数的时候或者窗口只有为一的时候

        Deque<Integer> queue = new LinkedList<>();
        int length = nums.length, index = 0;
        int[] res = new int[length - k + 1];

        for (int right = 0; right < length; right++) {
            int val = nums[right];
            //先保证窗口的范围是否合理(保证队列的头结点在<peek, i>)
            while (!queue.isEmpty() && queue.peek() < right - k + 1) queue.poll();//移除队头元素

            //添加元素的时候，要保证添加进去的元素比 队尾 的元素的小
            while (!queue.isEmpty() && nums[queue.getLast()] < val) queue.removeLast();
            //将数组下标添加进去
            queue.add(right);

            //初始化队列的时候保证达到窗口的数量
            if (right + 1 >= k) res[index++] = nums[queue.peek()];
        }
        return res;
    }

    /*单调队列(存放值)
    执行用时：32 ms, 在所有 Java 提交中击败了49.66%的用户
    内存消耗：51.8 MB, 在所有 Java 提交中击败了95.45%的用户
     */
    public static int[] maxSlidingWindow_4(int[] nums, int k) {
        if (nums.length == 1 || k == 1) return nums;//当只有一个数的时候或者窗口只有为一的时候

        Deque<Integer> queue = new LinkedList<>();
        int length = nums.length, index = 0;
        int[] res = new int[length - k + 1];

        for (int i = 0; i < k; i++) {//先将队列初始化
            int val = nums[i];
            //当队列的不为空的时候且添加的元素比其队尾元素小的情况下
            while (!queue.isEmpty() && val > queue.getLast()) queue.removeLast();//将小值进行移除
            queue.add(val);
        }
        res[index++] = queue.peek();

        for (int right = k; right < length; right++) {
            int val = nums[right];
            //判断窗口的是否在其范围之内
            if (!queue.isEmpty() && nums[right - k] == queue.peek()) queue.poll();//若不在了移除队头元素

            while (!queue.isEmpty() && val > queue.getLast()) queue.removeLast();//将小值进行移除
            queue.add(val);

            res[index++] = queue.peek();
        }
        return res;
    }


    /*单调队列(单调递减队列，总是把最大值放到队头)
    执行用时：40 ms, 在所有 Java 提交中击败了28.38%的用户
    内存消耗：52.7 MB, 在所有 Java 提交中击败了69.17%的用户
    */
    public static int[] maxSlidingWindow_2(int[] nums, int k) {
        if (nums.length == 1 || k == 1) return nums;

        int length = nums.length;

        int[] res = new int[length - k + 1];
        int index = 0;

        MyQueue myQueue = new MyQueue();

        //先将初始值加入单调队列中
        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }

        res[index++] = myQueue.peek();

        for (int i = k; i < length; i++) {
            //移除最前面的元素，判断是否加入该队列
            myQueue.poll(nums[i - k]);//即划出窗口

            myQueue.add(nums[i]);

            res[index++] = myQueue.peek();
        }
        return res;
    }
}

//自定义单调递减队列(数组)
class MyQueue {
    Deque<Integer> deque = new LinkedList<>();

    //弹出元素时需要比较弹出的数值是否等于队列出口的数值，相等时则弹出1
    void poll(int val) {
        if (!deque.isEmpty() && val == deque.peek()) deque.poll();
    }

    //添加元素的时候若大于入口元素则将入口元素弹出(保证队列元素的单调递减)
    void add(int val) {
        while (!deque.isEmpty() && val > deque.getLast()) {
            deque.removeLast();
        }
        deque.add(val);
    }

    //返回队列的最大值
    int peek() {
        return deque.peek();
    }
}
