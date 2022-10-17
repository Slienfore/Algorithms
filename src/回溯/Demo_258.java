package 回溯;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/3 7:22
 */
//258-各位相加
public class Demo_258 {
    public static void main(String[] args) {
        int num = 123;
        System.out.println(addDigits(num));
        System.out.println(mock(num));
        System.out.println(math(num));
    }

    public static int addDigits(int num) {
        recur(num);

        return res;
    }

    static int res;

    /**
     * 递归<br>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了31.44%的用户
     */
    private static boolean recur(int num) {
        if (num >= 0 && num < 10) {
            res = num;
            return true;
        }

        int nextVal = 0;
        while (num != 0) {//一次各位相加就是一层
            nextVal += num % 10;
            num /= 10;
        }

        return recur(nextVal);
    }


    /**
     * 模拟<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了42.79%的用户<br>
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了30.64%的用户
     */
    private static int mock(int num) {
        while (num > 9) {//当数字大于10的时候进行各位累加
            int sum = 0;
            while (num != 0) {//各位累加
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }


    /**数学<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：38.7 MB, 在所有 Java 提交中击败了19.62%的用户
    */
    private static int math(int num) {

        //能够被9整除的整数，各位上的数字加起来也必然能被9整除，所以，连续累加起来，最终必然就是9
        if (num > 0 && num % 9 == 0) return 9;

        //不能被9整除的整数，各位上的数字加起来，结果对9取模，和初始数对9取摸，是一样的，所以，连续累加起来，最终必然就是初始数对9取摸
        return num % 9;

    }
}
