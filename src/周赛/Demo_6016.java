package 周赛;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/6 10:34
 */
//6016-
public class Demo_6016 {
    public static void main(String[] args) {
//        String str = "K1:L2";
        String str = "A1:F1";
        System.out.println(cellsInRange(str));
    }

    public static List<String> cellsInRange(String s) {
        char colBegin = s.charAt(0), colEnd = s.charAt(3);//行与列
        char rowBegin = (char) Math.min(s.charAt(1), s.charAt(4)), rowEnd = (char) Math.max(s.charAt(1), s.charAt(4));

        List<String> res = new ArrayList<>();

        for (char col = colBegin; col <= colEnd; col++)
            for (char row = rowBegin; row <= rowEnd; row++) {
                res.add(String.valueOf(col) + (row - '0'));
            }

        return res;
    }
}
