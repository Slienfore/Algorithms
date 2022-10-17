package One;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/12/21 9:29
 */
// 1154-一年中的第几天
public class Demo_1154 {
    public static void main(String[] args) {
        String date = "2019-02-10";
        System.out.println(dayOfYear(date));
    }

    public static int dayOfYear(String date) {
        //模拟每月天数
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        String[] split = date.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) days[2] = 29;

        int res = 0;
        for (int i = 1; i < month; i++)
            res += days[i];

        return res + day;
    }
}
