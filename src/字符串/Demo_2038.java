package 字符串;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/22 20:53
 */
//2038-如果相邻两个颜色均相同则删除当前颜色
public class Demo_2038 {
    public static void main(String[] args) {
        String str = "AAABABB";
        System.out.println(winnerOfGame(str));
    }

    /**
     * <br>
     * 执行用时：18 ms, 在所有 Java 提交中击败了22.64%的用户<br>
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了44.03%的用户
     */
    public static boolean winnerOfGame(String colors) {
        int length = colors.length();

        //明确删除任何一个字符都不会影响其另外一种颜色
        int count = 0;
        for (int cur = 1; cur < length - 1; ++cur)
            if (colors.charAt(cur - 1) == 'A' && colors.charAt(cur) == 'A' && colors.charAt(cur + 1) == 'A')
                ++count;
            else if (colors.charAt(cur - 1) == 'B' && colors.charAt(cur) == 'B' && colors.charAt(cur + 1) == 'B')
                --count;

        return count > 0;//判断Alice会否删除多个
    }
}
