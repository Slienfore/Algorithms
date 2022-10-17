package 二叉树;

import utils.uu;

import java.util.HashSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/21 7:50
 */
//653-两数之和 IV
public class Demo_653 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2, null, null),
                        new TreeNode(4, null, null)),
                new TreeNode(6, null,
                        new TreeNode(7, null, null)));
        int tar = 9;
        System.out.println(findTarget(root, tar));

    }

    static HashSet<Integer> set = new HashSet<>();

    /**
     * 递归<br>
     * 执行用时：3 ms, 在所有 Java 提交中击败了48.29%的用户<br>
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了51.36%的用户
     */
    public static boolean findTarget(TreeNode root, int k) {
        if (root == null)
            return false;

        if (set.contains(root.val))
            return true;

        set.add(k - root.val);//(做差)存放进去

        return findTarget(root.left, k) | findTarget(root.right, k);//寻找左、右子树
    }
}
