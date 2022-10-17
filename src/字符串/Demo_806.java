package 字符串;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/12 19:27
 */
public class Demo_806 {
    public static void main(String[] args) {
        int[] nums = {4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
//        String str = "abcdefghijklmnopqrstuvwxyz";
        String str = "bbbcccdddaaa";
        uu.print(numberOfLines(nums, str));
    }

    /**
     * 模拟<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了78.34%的用户<br>
     * 2022年04月12日  19:45:47
     */
    public static int[] numberOfLines(int[] widths, String s) {

        int row = 1, sur = 0;

        for (char val : s.toCharArray()) {
            int cur = val - 'a';
            if (sur + widths[cur] <= 100)
                sur += widths[cur];//说明不需要换行(累加当前的行数)
            else {//若宽度大于100，那么不能进行拆分，需要进行换行
                ++row;
                sur = widths[cur];//换行重置
            }
        }

        return new int[]{row, sur};
    }
}
