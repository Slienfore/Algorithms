package 二叉树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/5/1 21:16
 */
//1305-两棵二叉搜索树中的所有元素
public class Demo_1305 {
    public static void main(String[] args) {

    }

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();

        //直接对两棵子树遍历，然后对所有的值进行遍历
        dfs(root1, res);
        dfs(root2, res);

        Collections.sort(res);

        return res;
    }

    private static void dfs(TreeNode root, List<Integer> list) {
        if (root == null)
            return;

        dfs(root.left, list);

        list.add(root.val);

        dfs(root.right, list);
    }
}
