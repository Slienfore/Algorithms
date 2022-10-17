package 指针;


/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/22 16:40
 */
//151-反转字符串的中的单词
public class Demo_151 {
    public static void main(String[] args) {
        String s_1 = "the sky is blue";
        String s_2 = "  hello world  ";
        String s_3 = "Alice does not even like bob";
        System.out.println(reverseWords_1(s_1));
        System.out.println(reverseWords_1(s_2));
        System.out.println(reverseWords_1(s_3));
        System.out.println();
        System.out.println(reverseWords_2(s_1));
        System.out.println(reverseWords_2(s_2));
        System.out.println(reverseWords_2(s_3));
        System.out.println();
        System.out.println(reverseWords_3(s_1));
        System.out.println(reverseWords_3(s_2));
        System.out.println(reverseWords_3(s_3));
    }

    /*逆序拼接字符串：
    执行用时：5 ms, 在所有 Java 提交中击败了79.42%的用户
    内存消耗：38.6 MB, 在所有 Java 提交中击败了29.87%的用户
    */
    public static String reverseWords_1(String s) {
        if (s.isEmpty()) return s;

        StringBuilder result = new StringBuilder();
        String[] str = s.split(" ");
        int right = str.length - 1;
        boolean flag = false;

        while (right >= 0) {
            String value = str[right];
            if (!value.equals("")) {
                if (flag) {//第一个始终不会在其前面拼接空格
                    result.append(" ").append(value);
                } else {
                    result.append(value);
                    flag = true;
                }
            }
            right--;
        }
        return result.toString();
    }

    /*三指针：
    执行用时：3 ms, 在所有 Java 提交中击败了94.79%的用户
    内存消耗：38.1 MB, 在所有 Java 提交中击败了95.31%的用户
     */

    public static String reverseWords_2(String s) {
        StringBuilder builder = new StringBuilder();
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        //进行探寻第一个字符
        while (chars[left] == ' ') left++;
        while (chars[right] == ' ') right--;

        while (right >= left) {
            int point = right;

            while (point >= left && chars[point] != ' ') point--;//还未遇到空格的时候进行探路
            for (int i = point + 1; i <= right; i++) {
                builder.append(chars[i]);
            }

            if (point > left) builder.append(" ");//如果不是最后一个单词则没必要进行空格拼接

            while (point >= left && chars[point] == ' ') point--;//排除前面的空格进行非空格定位
            right = point;
        }
        return builder.toString();
    }

    /*三指针(分割好后的字符串)
    执行用时：5 ms, 在所有 Java 提交中击败了79.42%的用户
    内存消耗：38.6 MB, 在所有 Java 提交中击败了32.07%的用户
    */
    public static String reverseWords_3(String s) {
        String[] str = s.split(" ");//分割好空格
        StringBuilder builder = new StringBuilder();
        int right = str.length - 1;
        int left = 0;
        //开始搜寻第一个单词
        while (str[left].equals("")) left++;
        while (str[right].equals("")) right--;
        while (left <= right) {
            builder.append(str[right]);

            int point = right - 1;
            if (point >= left) builder.append(" ");

            while (point >= left && str[point].equals("")) point--;
            right = point;
        }
        return builder.toString();
    }


}
