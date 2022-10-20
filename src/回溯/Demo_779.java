package 回溯;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/20 8:20
 */
// 779-第 k 个语法符号
public class Demo_779 {

    public static void main(String[] args) {
        int n = 2, k = 2;
        // int n = 30, k = 434991989;
        System.out.println(kthGrammar2(n, k));

        System.out.println(kthGrammar1(n, k));
    }

    /**
     * 二叉树搜索<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了12.38%的用户<br>
     * 2022年10月20日  09:32:02
     */
    public static int kthGrammar2(int n, int k) {
        // n 层包含的数
        int all = (int) Math.pow(2, n - 1);
        return dfs(0, k, all);
    }

    private static int dfs(int cur, int k, int all) {
        // 达到子节点, 返回当前结点内容
        if (all == 1) return cur;

        // 递归树下是 左 0 右 1
        if (k <= all / 2) {// 如果搜索值在左结点
            return dfs(cur == 0 ? 0 : 1,
                    k, all / 2);
        } else {// 如果搜索值在右结点
            return dfs(cur == 0 ? 1 : 0,
                    k - all / 2, all / 2);
        }
    }

    // 超内存
    public static int kthGrammar1(int n, int k) {

        String str = "0";
        for (int idx = 1; idx < n; ++idx) {
            StringBuilder tmp = new StringBuilder();

            // 只取前几位就行
            for (int nth = 0; nth < k && nth < str.length(); ++nth) {
                tmp.append(str.charAt(nth) == '0' ? "01" : "10");
            }

            str = tmp.toString();
        }

        // 索引从 1 开始
        return str.charAt(k - 1) - '0';
    }
}
