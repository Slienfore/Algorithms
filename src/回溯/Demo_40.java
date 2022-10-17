package 回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/11 22:12
 */
//40-组合总和II
public class Demo_40 {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
//        int[] candidates = {2, 5, 2, 1, 2};
//        int[] candidates = {2, 2, 2, 2};
//        int[] candidates = {14, 6, 25, 9, 30, 20, 33, 34, 28, 30, 16, 12, 31, 9, 9, 12, 34, 16, 25, 32, 8, 7, 30, 12, 33, 20, 21, 29, 24, 17, 27, 34, 11, 17, 30, 6, 32, 21, 27, 17, 16, 8, 24, 12, 12, 28, 11, 33, 10, 32, 22, 13, 34, 18, 12};
        int target = 8;

//        System.out.println(combinationSum2(candidates, target));
        System.out.println(combinationSum2_1(candidates, target));

    }

    static LinkedList<Integer> path = new LinkedList<>();
    static List<List<Integer>> res = new ArrayList<>();
    static boolean[] visited;
    private static Integer sum = 0;

    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //进行排序，将相等的数字排到一起
        Arrays.sort(candidates);

        visited = new boolean[candidates.length];//初始化访问数组

        backTrack(0, candidates, target);

        return res;
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.44%的用户<br>
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了59.48%的用户
     */
    private static void backTracking(int startIndex, int[] candidates, int target) {

        if (sum == target) {//当其等于目标值的时候进行添加
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length && candidates[i] + sum <= target; i++) {//进行剪枝
            int val = candidates[i];

            //若是树层去重
            if (i > 0 && val == candidates[i - 1] && !visited[i - 1]) continue;

            sum += val;
            path.add(val);
            visited[i] = true;//判断是否访问

            backTracking(i + 1, candidates, target);//每一个数字只能用一次

            //回溯
            visited[i] = false;
            sum -= val;
            path.removeLast();
        }
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.42%的用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了70.12%的用户
     */
    private static void backTrack(int startIndex, int[] nums, int target) {
        if (target == 0) {//如果target值变成了零就是目标值
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < nums.length && nums[i] <= target; i++) {
            //当当前位置不是起点的时候，且当前层的不同
            if (i > startIndex && nums[i] == nums[i - 1]) continue;

            path.add(nums[i]);

            backTrack(i + 1, nums, target - nums[i]);//不用sum进行判断，如果target值变成了零就是目标值

            path.removeLast();
        }
    }

    private static List<List<Integer>> combinationSum2_1(int[] candidates, int target) {

        Arrays.sort(candidates);//排序进行同层去重

        boolean[] lock = new boolean[candidates.length];//解锁状态，同层枝条不能用

        recur(0, target, candidates, lock);

        return res;
    }

    /**
     * 回溯剪枝<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了97.73%的用户<br>
     * 内存消耗：41.8 MB, 在所有 Java 提交中击败了8.56%的用户
     */
    private static void recur(int index, int sum_1, int[] candidates, boolean[] lock) {//当出现重复元素的时候，如果处在同一层的时候则会有相同的枝条，则需要进行同层去重
        if (sum_1 == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < candidates.length && (sum_1 - candidates[i] >= 0); i++) {

            //同一条树枝也会出现<<前者与后者>>相同的元素,所以需要定义isUsed布尔数组
            if (i > 0 && candidates[i] == candidates[i - 1] && !lock[i - 1])//如果该元素与前面的元素相同的时候，则进行去重
                continue;

            path.add(candidates[i]);
            lock[i] = true;

            recur(i + 1, sum_1 - candidates[i], candidates, lock);

            lock[i] = false;//该层使用完之后进行释放

            path.removeLast();

        }

    }

}
