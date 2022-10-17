package 单调栈;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/4 7:58
 */
//2104-子数组范围和
public class Demo_2104 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 3};
//        int[] nums = {1, 2, 3};
//        int[] nums = {4, -2, -3, 4, 1};
        System.out.println(subArrayRanges_1(nums));
        System.out.println(subArrayRanges_2(nums));
        System.out.println(subArrayRanges_3(nums));

    }

    /**单调栈(优化)<br>
     执行用时：11 ms, 在所有 Java 提交中击败了97.66%的用户<br>
     内存消耗：41.2 MB, 在所有 Java 提交中击败了27.13%的用户*/
    private static long subArrayRanges_3(int[] nums) {
        int length = nums.length;
        /*
        搜索最小值个数：单调 "递增" 栈(左边界已限定)
        搜索最大值个数：单调 "递减" 栈(左边界已限定)
        */
        Deque<Integer> incStack = new ArrayDeque<>(), decStack = new ArrayDeque<>();
        long[] max = new long[length], min = new long[length];

        for (int cur = 0; cur < length; ++cur) {
            int curVal = nums[cur];

            //搜索最大值个数：单调 "递减" 栈(左边元素均是比自己大)
            while (!decStack.isEmpty() && nums[decStack.peekLast()] <= curVal) {
                int pivot = decStack.pollLast();

                int left = decStack.isEmpty() ? -1 : decStack.peekLast();//左边元素比其大(左边界限定)

                max[pivot] = (long) (pivot - left) * (cur - pivot);//右边界

            }
            decStack.offerLast(cur);

            //搜索最小值个数：单调 "递增" 栈(左边元素均是比自己小)
            while (!incStack.isEmpty() && nums[incStack.peekLast()] >= curVal) {
                int pivot = incStack.pollLast();

                int left = incStack.isEmpty() ? -1 : incStack.peekLast();

                min[pivot] = (long) (pivot - left) * (cur - pivot);
            }
            incStack.offerLast(cur);
        }

        while (!decStack.isEmpty()) {//单减栈：左边均是比自己大的元素
            int pivot = decStack.pollLast();

            int left = decStack.isEmpty() ? -1 : decStack.peekLast();

            max[pivot] = (long) (pivot - left) * (length - pivot);
        }

        while (!incStack.isEmpty()) {//单增栈：左边均是比自己小的元素
            int pivot = incStack.pollLast();

            int left = incStack.isEmpty() ? -1 : incStack.peekLast();

            min[pivot] = (long) (pivot - left) * (length - pivot);
        }

        long res = 0;
        for (int cur = 0; cur < length; ++cur) {
            int val = nums[cur];

            res += (max[cur] - min[cur]) * val;
        }

        return res;
    }


    private static long subArrayRanges_1(int[] nums) {
        int length = nums.length;

        long[] minNums = getArr(true, nums), maxNums = getArr(false, nums);//获得每一个元素成为最大值最小值的次数

        long res = 0;
        for (int curIndex = 0; curIndex < length; curIndex++) {
            long curVal = nums[curIndex];

            res += (maxNums[curIndex] - minNums[curIndex]) * curVal;

        }

        return res;

    }

    private static long[] getArr(boolean min, int[] nums) {//获取每个元素成为最大或最小值个数
        int length = nums.length;

        long[] leftArr = new long[length], rightArr = new long[length];//分别是成为左区间或者右区间临界下标
        Deque<Integer> stack = new ArrayDeque<>();//获取单调栈

        //从左往右
        for (int curIndex = 0; curIndex < length; curIndex++) {
            int curVal = nums[curIndex];

            if (min) {//单调递增栈(成为区间最小值)
                while (!stack.isEmpty() && nums[stack.peekLast()] >= curVal)
                    stack.pollLast();//维护自己是最小值(将值大的元素进行出栈)

            } else {//单调递减栈(成为区间最大值)
                while (!stack.isEmpty() && curVal >= nums[stack.peekLast()])
                    stack.pollLast();//维护自己是最大值(将值小的元素出栈)
            }

            leftArr[curIndex] = stack.isEmpty() ? -1 : stack.peekLast();
            stack.offerLast(curIndex);//将当前下标加入

        }

        stack.clear();

        //从后往前
        for (int curIndex = length - 1; curIndex >= 0; curIndex--) {
            int curVal = nums[curIndex];

            if (min) {//单调递增栈
                while (!stack.isEmpty() && nums[stack.peekLast()] > curVal)//不包含等于、防止重复元素
                    stack.pollLast();//维护自己是区间最小值(将比自己大的元素出栈)

            } else {//单调递减栈
                while (!stack.isEmpty() && curVal > nums[stack.peekLast()])
                    stack.pollLast();//维护自己是区间最大值(将比自己小的元素出栈)
            }

            rightArr[curIndex] = stack.isEmpty() ? length : stack.peekLast();
            stack.offerLast(curIndex);
        }

        long[] arr = new long[length];
        //计算每一个区间的个数
        for (int curIndex = 0; curIndex < length; curIndex++) {

            long leftNums = curIndex - leftArr[curIndex], rightNums = rightArr[curIndex] - curIndex;//左侧区间的个数与右侧区间的个数

            arr[curIndex] = leftNums * rightNums;
        }

        return arr;
    }


    /**
     * 动规(维护区间最值)<br>
     * 执行用时：18 ms, 在所有 Java 提交中击败了82.43%的用户<br>
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了19.96%的用户
     */
    public static long subArrayRanges_2(int[] nums) {
        int length = nums.length;

        long res = 0;
        for (int i = 0; i < length; i++) {//分别以一个值与数组个元素之间的区间
            int maxVal = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE;
            for (int j = i; j >= 0; j--) {
                maxVal = Math.max(maxVal, nums[j]);//获取这个区间最大值

                minVal = Math.min(minVal, nums[j]);//获取这个区间得最小值

                res += maxVal - minVal;
            }
        }

        return res;
    }
}
