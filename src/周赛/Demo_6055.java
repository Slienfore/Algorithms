package 周赛;

import utils.uu;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/3 10:57
 */
//6055-转换时间需要的最少操作数
public class Demo_6055 {
    public static void main(String[] args) {
        String cur = "02:30", right = "04:35";
//        String cur = "11:00", right = "11:01";
        System.out.println(convertTime_1(cur, right));
        System.out.println(convertTime_2(cur, right));
    }

    /**
     * 切割<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了100.00%的用户<br>
     * 2022年04月03日  13:24:57
     */
    public static int convertTime_2(String current, String correct) {
        int diff = getMinute(correct) - getMinute(current);//两个时间之差

        int[] op = {60, 15, 5, 1};
        int res = 0;
        for (int val : op)
            while (diff >= val) {
                res += diff / val;//当前时间转换需要多少次

                diff %= val;//转换完后还剩下的时间
            }

        return res;
    }

    private static int getMinute(String time) {
        String[] split = time.split(":");//将其分割成左右两部分
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);//时转分
    }

    public static int convertTime_1(String current, String correct) {
        long diff = getDiff(current, correct);

        int[] arr = {60, 15, 5, 1};//只能从这些数中进行取值
        int res = 0;

        for (int val : arr)
            while (diff >= val) {
                res += diff / val;//转换的次数

                diff %= val;//扣除完当前时间后
            }

        return res;
    }

    private static int getDiff(String cur, String tar) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        long diff = 0;
        try {
            diff = (sdf.parse(tar).getTime() - sdf.parse(cur).getTime()) / 1000 / 60;//转换成相差的分钟
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) diff;
    }
}
