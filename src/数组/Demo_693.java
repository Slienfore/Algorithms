package 数组;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/28 10:03
 */
//693-交替位二进制数
public class Demo_693 {
    public static void main(String[] args) {
//        int num = 5;
        int num = 1;
        System.out.println(hasAlternatingBits(num));
    }

    /**模拟<br>
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     内存消耗：38 MB, 在所有 Java 提交中击败了52.94%的用户*/
    public static boolean hasAlternatingBits(int n) {
        int pre = Integer.MAX_VALUE;

        while (n != 0) {
            int compo = n % 2;//取余
            if (compo == pre)//若与前一个数相同说明不能交替进行
                return false;

            pre = compo;
            n /= 2;
        }

        return true;
    }
}
