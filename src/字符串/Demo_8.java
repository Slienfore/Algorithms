package 字符串;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/22 21:21
 */
//8-字符串转换整数 (atoi)
public class Demo_8 {
    public static void main(String[] args) {
        String str = "42";
        System.out.println(myAtoi(str));
    }

    /**
     * <br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了17.47%的用户
     */
    public static int myAtoi(String s) {
        int length = s.length(), point = 0;

        //排除前导空格
        while (point < length && s.charAt(point) == ' ')
            ++point;

        //若全部均是空格
        if (point == length)
            return 0;

        int flag = 1;
        if (s.charAt(point) == '-') {//遇到负号
            flag = -flag;
            ++point;
        } else if (s.charAt(point) == '+')
            ++point;

        int res = 0;

        while (point < length && s.charAt(point) >= '0' && s.charAt(point) <= '9') {
            int digit = s.charAt(point) - '0';

            if (res > (Integer.MAX_VALUE - digit) / 10)//判断是否会溢出31位整数
                return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            ++point;
            res = res * 10 + digit;
        }

        return res * flag;
    }
}
