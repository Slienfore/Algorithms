package 周赛;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/20 10:46
 */
public class Demo_6012 {
    public static void main(String[] args) {
        System.out.println(countEven(30));
    }

    public static int countEven(int num) {
        int count = 0;

        for (int i = 1; i <= num; i++) {
            int temp = i, sum = 0;

            while (temp != 0) {
                sum += temp % 10;
                temp /= 10;
            }
            if (sum % 2 == 0) count++;
        }

        return count;
    }
}
