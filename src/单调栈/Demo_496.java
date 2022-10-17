package 单调栈;

import utils.uu;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/19 19:35
 */
//496-下一个更大的元素
public class Demo_496 {
    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};
//        int[] nums1 = {2, 4}, nums2 = {1, 2, 3, 4};
        uu.print(nextGreaterElement_1(nums1, nums2));
        uu.print(nextGreaterElement_2(nums1, nums2));

    }

    /**
     * 单调栈+哈希表<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了90.65%的用户<br>
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了20.60%的用户
     */
    public static int[] nextGreaterElement_1(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();//映射每一个第一大的值
        Deque<Integer> stack = new ArrayDeque<>();

        for (int curVal : nums2) {
            map.put(curVal, -1);//默认该值没有最大值

            //构造单调递减栈(寻找第一大)
            while (!stack.isEmpty() && stack.peekLast() < curVal) {
                int key = stack.pollLast();
                map.put(key, curVal);//下一个更大元素
            }

            stack.offerLast(curVal);
        }

        int len = nums1.length;
        int[] res = new int[len];

        for (int cur = 0; cur < len; ++cur)
            res[cur] = map.get(nums1[cur]);

        return res;
    }

    /**
     * 模拟<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了90.65%的用户<br>
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了17.40%的用户
     */
    public static int[] nextGreaterElement_2(int[] nums1, int[] nums2) {
        int length_1 = nums1.length, length_2 = nums2.length;
        int[] res = new int[length_1];

        for (int cur = 0; cur < length_1; ++cur) {
            int curVal = nums1[cur], point = 0;

            while (point < length_2 && nums2[point] != curVal)//搜索当前值在nums2中的位置
                ++point;

            while (point < length_2 && nums2[point] <= curVal)//搜索第一大的数
                ++point;

            res[cur] = point == length_2 ? -1 : nums2[point];//判断是否搜索到了
        }

        return res;
    }

}
