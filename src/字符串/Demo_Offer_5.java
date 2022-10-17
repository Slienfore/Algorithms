package 字符串;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/22 9:56
 */
//剑指Offer——05-替换空格
public class Demo_Offer_5 {
    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace_1(s));
        System.out.println(replaceSpace_2(s));
    }

    /*容器替换
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36.2 MB, 在所有 Java 提交中击败了71.43%的用户
    */
    public static String replaceSpace_1(String s) {
        char[] chars = s.toCharArray();
        StringBuilder result = new StringBuilder();//StringBuilder 单线程处理速度较快

        for (char str : chars) {
            if (str != ' ') {
                result.append(str);
            } else {
                result.append("%20");
            }
        }
        return result.toString();
    }

    /*库函数：
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36.5 MB, 在所有 Java 提交中击败了16.86%的用户
    */
    public static String replaceSpace_2(String s) {

        return s.replace(" ", "%20");

    }
}
