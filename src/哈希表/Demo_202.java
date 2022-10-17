package 哈希表;

import java.util.HashSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/20 15:49
 */
//202-快乐数
public class Demo_202 {
    public static void main(String[] args) {
        int n = 19;
        System.out.println(isHappy_1(n));
        System.out.println(isHappy_2(n));
        System.out.println(isHappy_3(n));
    }

    /*哈希表
    执行用时：2 ms, 在所有 Java 提交中击败了11.98%的用户
    内存消耗：35.6 MB, 在所有 Java 提交中击败了16.14%的用户
    */
    public static boolean isHappy_1(int n) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;

        while (!set.contains(sum)) {//如果包含该键的时候说明其重复了
            set.add(sum);
            sum = 0;//将 sum 重置

            while (n != 0) {
                sum += (int) Math.pow(n % 10, 2);
                n = n / 10;
            }
            if (sum == 1) return true;
            n = sum;
        }
        return false;
    }

    /*哈希表：
    执行用时：1 ms, 在所有 Java 提交中击败了96.67%的用户
    内存消耗：35.5 MB, 在所有 Java 提交中击败了44.07%的用户
    */
    public static boolean isHappy_2(int n) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;

        while (!set.contains(sum)) {//如果包含该键的时候说明其重复了
            set.add(sum);
            sum = 0;//将 sum 重置

            while (n != 0) {
                int temp = n % 10;
                sum += temp * temp;
                n = n / 10;
            }
            if (sum == 1) return true;
            n = sum;
        }
        return false;
    }

    /*快慢指针
    执行用时：1 ms, 在所有 Java 提交中击败了96.67%的用户
    内存消耗：35.2 MB, 在所有 Java 提交中击败了83.79%的用户
     */
    public static boolean isHappy_3(int n) {
        /*定义快慢指针，如果快指针与慢指针相遇，则说明成环，循环*/
        int slow = n;
        int fast = total(n);

        while (fast != 1 && slow != fast) {
            slow = total(slow);
            fast = total(total(fast));//快指针每次多走几步
        }
        return fast == 1;

    }

    public static int total(int value) {
        int sum = 0;
        while (value != 0) {
            int temp = value % 10;
            sum += temp * temp;
            value = value / 10;
        }
        return sum;
    }
}
