package 回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/12 9:46
 */
//93-复原IP地址
public class Demo_93 {
    public static void main(String[] args) {
        String s = "25525511135";
//        String s = "0000";
        System.out.println(restoreIpAddresses(s));
    }

    static List<String> res = new ArrayList<>();

    private static List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) return res;

        //第二个参数是用来记录 加点的
        backTracking(0, 0, s);
//        recur(0, s);
        return res;
    }
    static int num = 0;

    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了36.76%的用户<br>
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了28.19%的用户
     */
    private static void backTracking(int startIndex, int point, String str) {
        if (point == 3) {//当加的点数等于3时，字符串已经分开成4分的
            if (isValid(str, startIndex, str.length() - 1))//如果IP地址正确的话
                res.add(str);

            return;
        }

        for (int i = startIndex; i < str.length() && isValid(str, startIndex, i); i++) {//如果该串不是合理，就不必要递归下去了

            str = str.substring(0, i + 1) + "." + str.substring(i + 1);//进行加点
            point++;

            backTracking(i + 2, point, str);//加完逗点之后就多了一位

            point--;
            //回溯需要将该逗点去掉
            str = str.substring(0, i + 1) + str.substring(i + 2);
        }
    }

    //判断IP地址是否合理
    private static boolean isValid(String str, int left, int right) {
        if (left > right) return false;

        //左指针不等于右指针就是递归开始时的首位字符串
        if (str.charAt(left) == '0' && left != right) return false;

        int sum = 0;
        for (int i = left; i <= right; i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0') return false;

            sum = sum * 10 + (str.charAt(i) - '0');

            if (sum > 255) return false;//如果位数大于255
        }

        return true;
    }

    private static boolean valid(String str) {
        //不能包含前导 0
        if (str.length() > 1 && str.charAt(0) == '0') return false;

        if (str.length() > 3) return false;

        return Integer.parseInt(str) <= 255;

    }

    static LinkedList<String> path = new LinkedList<>();


    /**
    执行用时：2 ms, 在所有 Java 提交中击败了74.32%的用户<br>
    内存消耗：41.5 MB, 在所有 Java 提交中击败了11.08%的用户
    */
    private static void recur(int startIndex, String str) {
        if (path.size() == 4) {
            res.add(String.join(".", path));
            return;
        }

        for (int i = startIndex; i < str.length(); i++) {
            //假如自身至少拿一位，就是确定了这一部分了，那么剩下的各部分都拿3位，如果剩余位数大于其时就会进行溢出，不满足
            int part = 3 - path.size();//除去当前项还剩下 3 部分进行确定，查看剩下还未确定的部分

            int maxNums = 3 * part;//将剩下的还未确定的部分都赋予最大位数

            int residual = str.length() - 1 - i;//除去当前项确定的部分还剩下多少位

            if (residual > maxNums)//如果预留位置比预留位置的最大值还要大的时候，结束
                continue;

            if (!valid(str.substring(startIndex, i + 1)))
                continue;

            path.add(str.substring(startIndex, i + 1));//将这一部分进行截取

            recur(i + 1, str);//将后面这一部分进行截取

            path.removeLast();
        }

    }


}
