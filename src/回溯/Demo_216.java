package 回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/11 11:41
 */
//216-组合总和——III
public class Demo_216 {

    public static void main(String[] args) {
        int target = 7, k = 3;
        for (List<Integer> list : combinationSum3(k, target)) {
            System.out.println(list);
        }
    }

    static LinkedList<Integer> path = new LinkedList<>();
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combinationSum3(int k, int n) {
//        recur_2(1, n, k);
//        recur(1, 0, n, k);
        recur_3(1, n, k);

        return res;
    }

    /**
     * 回溯<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了56.53%的用户
     */
    private static void recur(int start, int sum, int target, int k) {
        //若找到的和大于目标值就直接返回
        if (sum > target) return;
        else if (path.size() == k) {//若符合其中之和
            if (sum == target) res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= 9 - (k - path.size()) + 1; i++) {//剪枝：除去不满足递归深度的组合
            path.add(i);
            sum += i;

            recur(i + 1, sum, target, k);

            //返回回溯条件
            path.removeLast();
            sum -= i;
        }
    }

    /**
     * 回溯隐藏<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了17.24%的用户<br>
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了5.06%的用户
     */
    private static void recur_1(int startIndex, int sum, int limit) {
        if (sum == 0 && path.size() == limit) {//如果sum的值最终相减为 0，且集合中的个数满足
            res.add(new ArrayList<>(path));
            return;
        } else if (sum < 0) return;


        for (int i = startIndex; i <= 9 - (limit - path.size()) + 1; i++) {
            path.add(i);

            recur_1(i + 1, sum - i, limit);//将所需要求得和隐藏在递归之中

            path.removeLast();
        }

    }

    /**
     * 回溯再次剪枝<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了5.97%的用户
     */
    private static void recur_2(int index, int sum, int limit) {
        if (sum == 0 && path.size() == limit) {
            res.add(new ArrayList<>(path));
            return;
        }

        int need = limit - path.size();//还需要多少个数

        //(9 - i)是剩下可供选择的个数，(9 - i) + 1 说明包含于自身，(i <= sum) 当前值不能大于总和，否则无意义
        for (int i = index; (need <= (9 - i) + 1) && (i <= sum); i++) {//使用 “ = ”，是包含剩余的数可能会与之相等相减为 0
            path.add(i);

            recur_2(i + 1, sum - i, limit);

            path.removeLast();
        }

    }

    /**逆向递归<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：38.9 MB, 在所有 Java 提交中击败了18.47%的用户
    */
    private static void recur_3(int circle, int sum, int limit) {
        if (sum == 0 && path.size() == limit) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (circle > sum || (limit - path.size()) > ((9 - circle) + 1))//递归出口
            return;

        recur_3(circle + 1, sum, limit);//相当于 for 循环，递归到最后一项，最后不符合就往前面推

        path.add(circle);

        recur_3(circle + 1, sum - circle, limit);//递归,往后面推

        path.removeLast();

    }
}
