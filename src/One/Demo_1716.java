package One;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/1/15 19:43
 */
public class Demo_1716 {
    public static void main(String[] args) {
        System.out.println(totalMoney(10));
    }


    /**
     * 模拟<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了46.54%的用户
     */
    public static int totalMoney(int n) {
        int weeks = n / 7;
        int days = n % 7;
        int money = 28;//一周存的钱
        int res = 0;
        int monday = 1;//周一存的钱(递增)

        for (int i = 0; i < weeks; i++) {
            res += money;
            money += 7;//一个星期多存7块钱
            monday++;
        }
        for (int i = 0; i < days; i++)
            res += monday++;

        return res;
    }
}
