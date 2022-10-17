package 字符串;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/12/1 0:10
 */
public class Demo_1446 {

    /**
    执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：36.9 MB, 在所有 Java 提交中击败了76.19%的用户
    */
    public int maxPower(String s) {
        int length = s.length(), res = 1, count = 1;
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
                res = Math.max(res, count);
            } else count = 1;
        }
        return res;
    }
}
