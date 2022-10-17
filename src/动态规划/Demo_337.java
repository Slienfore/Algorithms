package 动态规划;

import utils.uu;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/16 15:13
 */
//337-打家劫舍-III
public class Demo_337 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3) {{
            left = new TreeNode(2) {{
                left = null;
                right = new TreeNode(3) {{
                    left = null;
                    right = null;
                }};
            }};
            right = new TreeNode(3) {{
                left = null;
                right = new TreeNode(1) {{
                    left = null;
                    right = null;
                }};
            }};
        }};

        System.out.println(rob_1(root));
        System.out.println(rob_2(root));
        System.out.println(rob_3(root));
    }

    /**
     * 树形动规<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了40.32%的用户
     */
    public static int rob_3(TreeNode root) {
        int[] res = robHelp(root);

        return Math.max(res[0], res[1]);
    }

    private static int[] robHelp(TreeNode root) {
        if (root == null)
            return new int[2];//递归到最后一层了，初始化[0, 0]

        int[] left = robHelp(root.left);//左孩子
        int[] right = robHelp(root.right);//右孩子

        //偷
        int value_1 = root.val + left[0] + right[0];

        //不偷(<左右、孩子结点的值取决于对自己的孩子结点偷还是不偷的最大值>)
        int value_2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{value_2, value_1};
    }


    //记录已经计算过的结点
    static Map<TreeNode, Integer> memo = new HashMap<>();

    /**
     * 记忆化搜索<br>
     * 执行用时：2 ms, 在所有 Java 提交中击败了42.18%的用户<br>
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了37.86%的用户
     */
    public static int rob_2(TreeNode root) {
        if (root == null)
            return 0;

        if (memo.containsKey(root))//说明已经计算过该值了
            return memo.get(root);

        //偷父节点
        int value_1 = root.val;
        if (root.left != null)
            value_1 += rob_2(root.left.left) + rob_2(root.left.right);//计算左孙子结点

        if (root.right != null)
            value_1 += rob_2(root.right.left) + rob_2(root.right.right);//计算右重孙

        //不偷父节点
        int value_2 = rob_2(root.left) + rob_2(root.right);

        int max = Math.max(value_1, value_2);

        memo.put(root, max);//存放当前结点最大值

        return max;
    }


    /**
     * 超时
     */
    public static int rob_1(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)//左右孩子结点为空
            return root.val;

        //偷父节点
        int value_1 = root.val;
        if (root.left != null)
            value_1 += rob_1(root.left.left) + rob_1(root.left.right);//左子树的孙子结点

        if (root.right != null)
            value_1 += rob_1(root.right.left) + rob_1(root.right.right);//右子树的孙子结点

        //不偷父节点
        int value_2 = rob_1(root.left) + rob_1(root.right);

        //偷了根节点就不能偷左右子树结点
        return Math.max(value_1, value_2);
    }


}
