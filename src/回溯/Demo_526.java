package 回溯;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/7 22:18
 */
//526-优美的排列
public class Demo_526 {
    public static void main(String[] args) {
//        System.out.println(countArrangement_1(15));
        System.out.println(countArrangement_2(15));
    }

    private static boolean[] vis;//标记在当前数组中已经使用过的数字
    private static List<Integer>[] match;//存放指定可以互相取余的 下标数组
    private static int res;

    /**
     * 回溯(预处理)<br>
     * 执行用时：41 ms, 在所有 Java 提交中击败了72.30%的用户<br>
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了5.06%的用户<br>
     * 2022年06月08日  07:35:30
     */
    public static int countArrangement_2(int n) {
        res = 0;
        vis = new boolean[n + 1];//标记使用过的数字
        match = new List[n + 1];//每一个一维数组代表着能够与其构成完美数组 List

        for (int cur = 0; cur < match.length; ++cur)//初始化
            match[cur] = new ArrayList<>();

        //预处理
        for (int pos = 1; pos <= n; ++pos)//下标
            for (int num = 1; num <= n; ++num)//数字
                if (pos % num == 0 || num % pos == 0)
                    match[pos].add(num);

        dfs(1);

        return res;
    }

    private static void dfs(int pos) {
        if (pos == match.length) {
            ++res;
            return;
        }

        for (int val : match[pos]) {//当前位置能够使用的数字
            if (vis[val]) continue;

            vis[val] = true;

            dfs(pos + 1);

            vis[val] = false;
        }
    }

    /**
     * 回溯<br>
     * 执行用时：58 ms, 在所有 Java 提交中击败了31.29%的用户<br>
     * 内存消耗：38 MB, 在所有 Java 提交中击败了92.33%的用户<br>
     * 2022年06月07日  23:29:47
     */
    public static int countArrangement_1(int n) {
        vis = new boolean[n + 1];

        return dfs_1(1);
    }

    private static int dfs_1(int pos) {
        if (pos == vis.length) return 1;//扫描了所有数字

        int num = 0;//一次扫描每一个数字开始作为的完美数组

        for (int cur = 1; cur < vis.length; ++cur) {//从左到右依次扫描数字
            if (vis[cur] || cur % pos != 0 && pos % cur != 0)
                continue;

            vis[cur] = true;

            num += dfs_1(pos + 1);

            vis[cur] = false;
        }

        return num;
    }
}
