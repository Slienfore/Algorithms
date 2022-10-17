package 字符串;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/5 10:07
 */
//521-最长特殊子序列
public class Demo_521 {
    public static void main(String[] args) {
        String str_1 = "abc", str_2 = "bca";
        System.out.println(findLUSlength(str_1, str_2));
    }

    /**<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：39.3 MB, 在所有 Java 提交中击败了11.85%的用户
    */
    public static int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());//排序不同也是不同(只有完全相同的才是)
    }
}
