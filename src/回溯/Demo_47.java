package 回溯;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/12 18:49
 */
//47-全排列
public class  Demo_47 {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 0, 9};

        System.out.println(permuteUnique(nums));
    }

    static LinkedList<Integer> path = new LinkedList<>();
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
//        boolean[] visited = new boolean[nums.length];

        Arrays.sort(nums);//排列好方便树层去重

//        permuteHelp(nums, visited);

        for (int val : nums)
            path.add(val);
        recur_1(nums);

        return res;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.30%的用户<br>
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了59.70%的用户
     */
    private static void permuteHelp(int[] nums, boolean[] visited) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {//递归宽度
          /*树层去重
            就是本层的元素不能有重复元素，
            如果前面的元素(当其处于递归状态的时候为‘true’)
            递归完之后，指针移动到同层下一个结点
            如若其与其前面的元素相同，且其为false(递归完之后的状态) 则 ！visited[i - 1]为 真，则进行树层去重*/
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;

            /*树枝去重
            就是同一树枝下，不能使用过父节点使用过的元素
            子节点，是父节点进入递归后产生的
            那么父节点进入递归时的状态是 visited == true
            同时传入扫描字符串的位置是从首位元素开始的，那么将会包括使用过的父节点
            当扫描到父节点的时候 visited == true, 则 !visited ==  false
            所以不扫描父节点，只扫描那些还未使用过的结点*/
            if (!visited[i]) {
                visited[i] = true;
                path.add(nums[i]);

                permuteHelp(nums, visited);

                visited[i] = false;
                path.removeLast();
            }

        }
    }


    /**
     * 填充法<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了40.23%的用户<br>
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了11.35%的用户
     */
    private static void recur(int orin, int length) {
        if (orin == length) {
            res.add(new ArrayList<>(path));
            return;
        }

        HashSet<Integer> set = new HashSet<>();

        for (int i = orin; i < length; i++) {
            if (set.contains(path.get(i)))//进行树层去重(交换位置会打乱数组的位置)
                continue;

            set.add(path.get(i));

            Collections.swap(path, orin, i);

            //树枝去重

            recur(orin + 1, length);//从下一个填充位置开始

            Collections.swap(path, orin, i);

        }

    }

    static boolean[] branch = new boolean[8];//树枝去重

    /**
     * 数组哈希<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.52%的用户<br>
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了22.02%的用户
     */
    private static void recur_1(int[] nums) {
        if (path.size() == nums.length)
            res.add(new ArrayList<>(path));

        boolean[] layer = new boolean[20];//树层去重(使用数字哈希)

        for (int i = 0; i < nums.length; i++) {
            if (branch[i] || layer[nums[i] + 10])
                continue;

            branch[i] = true;
            layer[nums[i] + 10] = true;//因为是同一层来使用，不用进行回溯
            path.add(nums[i]);

            recur_1(nums);

            branch[i] = false;
            path.removeLast();

        }
    }
}
