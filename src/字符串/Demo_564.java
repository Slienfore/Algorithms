package 字符串;

import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/2 15:18
 */
//564-寻找最近的回文数
public class Demo_564 {
    public static void main(String[] args) {
        String num = "1";
        System.out.println(nearestPalindromic(num));
    }

    /**模拟<br>
    执行用时：2 ms, 在所有 Java 提交中击败了95.54%的用户<br>
    内存消耗：40 MB, 在所有 Java 提交中击败了15.29%的用户
    */
    public static String nearestPalindromic(String n) {
        long[] wait = new long[6];

        int length = n.length();
        boolean odd = length % 2 != 0;//判断是否为奇数

        //还要考虑剩下的更优解
        wait[0] = (long) (Math.pow(10, (length - 1)) - 1);//转换成999、99、9
        wait[1] = (long) (Math.pow(10, length) + 1);//转换成1001、101、11

        String preString = n.substring(0, (length + 1) / 2);
        long preVal = Long.parseLong(preString);//截取前半段数值

        long addVal = (preVal + 1);//中间位加一
        long midVal = preVal;//中间位不变
        long subVal = (preVal - 1);//中间位减一

        wait[2] = help(addVal, odd);
        wait[3] = help(midVal, odd);
        wait[4] = help(subVal, odd);

        long res = -1, num = Long.parseLong(n);

        for (long curVal : wait) {
            if (curVal == num) continue;

            if (res == -1) {
                res = curVal;
            } else if (Math.abs(num - res) > Math.abs(num - curVal)) {//如果当前相差值小于之前差值
                res = curVal;
            } else if (Math.abs(num - res) == Math.abs(num - curVal) && curVal < res) {//如果差值相等，选最小的一个
                res = curVal;
            }

        }

        return String.valueOf(res);

    }


    private static long help(long val, boolean odd) {//进行数字转换
        String str = String.valueOf(val);
        int length = str.length();

        if (odd) {
            str += new StringBuilder(str.substring(0, length - 1)).reverse();//奇数需要跳过中间数
        } else {
            str += new StringBuilder(str.substring(0, length)).reverse();//偶数直接拼接
        }

        return Long.parseLong(str);
    }


    private static boolean isPalindromic(String num) {
        return new StringBuilder(num).reverse().toString().equals(num);
    }

}
