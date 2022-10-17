package 周赛;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/12/19 10:49
 */
//5957-向字符串添加空格
public class Demo_5957 {
    public static void main(String[] args) {
/*
        String s = "LeetcodeHelpsMeLearn";
        int[] nums = {8, 13, 15};
*/
        String s = "spacing";
        int[] nums = {0, 1, 2, 3, 4, 5, 6};
        System.out.println(addSpaces(s, nums));
    }

    public static String addSpaces(String s, int[] spaces) {
        int str_Len = s.length(), sp_Len = spaces.length;
        if (str_Len == 0 || sp_Len == 0) return s;


        StringBuilder builder = new StringBuilder();
        int begin = 0;

        for (int i = 0; i < str_Len; i++) {
            if (begin < sp_Len && i == spaces[begin]) {
                begin++;
                builder.append(" ");
            }
            builder.append(s.charAt(i));
        }
        return builder.toString();
    }
}
