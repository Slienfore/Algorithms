package 数组;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/13 17:01
 */
//393-UTF-8 编码验证
public class Demo_393 {
    public static void main(String[] args) {
        int[] data = {197, 130, 1, 5};
//        int[] data = {235, 130, 4};
//        System.out.println(validUtf8_1(data));
        String str = "opdopsodsdpp";
        String[] pps = str.split("pp");

        int cn = 0;
        Matcher matcher = Pattern.compile("pp").matcher("oppdopsodsdpp");
        while (matcher.find())
            ++cn;

        System.out.println(cn);

    }


    public static boolean validUtf8_1(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; ) {
            int t = data[i], j = 7;

            while (j >= 0 && (((t >> j) & 1) == 1))
                j--;

            int cnt = 7 - j;

            if (cnt == 1 || cnt > 4)
                return false;

            if (i + cnt - 1 >= n)
                return false;
            for (int k = i + 1; k < i + cnt; k++) {

                if ((((data[k] >> 7) & 1) == 1) && (((data[k] >> 6) & 1) == 0)) continue;

                return false;
            }

            if (cnt == 0) i++;
            else i += cnt;
        }
        return true;
    }


}
