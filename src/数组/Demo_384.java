package 数组;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/22 8:35
 */
//384-打乱数组
public class Demo_384 {
    public static void main(String[] args) {
        Demo_384 solution = new Demo_384(new int[]{1, 2});

        // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
        System.out.println(Arrays.toString(solution.shuffle()));

        // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
        System.out.println(Arrays.toString(solution.reset()));

        // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
        System.out.println(Arrays.toString(solution.shuffle()));
    }

    private int[] origin;
    private int[] random;

    /**暴力<br>
    执行用时：87 ms, 在所有 Java 提交中击败了37.93%的用户<br>
    内存消耗：46.8 MB, 在所有 Java 提交中击败了26.97%的用户
    */
    public Demo_384(int[] nums) {
        this.random = nums;
        //注意引用
        this.origin = new int[nums.length];
        System.arraycopy(nums, 0, origin, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(origin, 0, random, 0, origin.length);
        return random;
    }

    public int[] shuffle() {
        List<Integer> list = new ArrayList<>();
        for (int val : random)
            list.add(val);

        Random rand = new Random();
        for (int i = 0; i < random.length; i++)
            random[i] = list.remove(rand.nextInt(list.size()));
        return random;

    }
}
