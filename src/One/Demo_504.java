package One;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/7 7:13
 */
// 504-七进制数
public class Demo_504 {
    public static void main(String[] args) {
//        int num = 90009;
//        int num = 0;
        int num = -87;
        System.out.println(convertToBase7_1(num));
        System.out.println(convertToBase7_2(num));

    }

    public static String convertToBase7_2(int num) {
        return Integer.toString(num, 7);
    }


    /**
     * 模拟<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了18.78%的用户
     */
    public static String convertToBase7_3(int num) {
        StringBuilder builder = new StringBuilder();

        boolean naga = num < 0;
        num = naga ? -num : num;

        do {//为 “0”，都要进行拼接一次
            builder.append(num % 7);
            num /= 7;
        } while (num != 0);

        return naga ? builder.append("-").reverse().toString() : builder.reverse().toString();
    }


    /**
     * 模拟<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了76.60%的用户<br>
     * 内存消耗：39 MB, 在所有 Java 提交中击败了12.49%的用户
     */
    public static String convertToBase7_1(int num) {
        if (num == 0)
            return "0";

        StringBuilder builder = new StringBuilder();
        boolean naga = num < 0;

        if (naga) {//负数
            builder.append("-");
            num = -num;
        }

        while (num != 0) {//结果需要逆序插入
            if (naga)
                builder.insert(1, num % 7);
            else
                builder.insert(0, num % 7);

            num /= 7;
        }

        return builder.toString();
    }


}
