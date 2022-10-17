package 反转;

//9-回文数
public class Demo_9 {
    public static void main(String[] args) {
        System.out.println(isPalindrome_1(223));
        System.out.println(isPalindrome_2(222));
        System.out.println(isPalindrome_3(0));
    }

    //Demo_1
    public static boolean isPalindrome_1(int x) {
        if (x < 0) {//如果是负数的话就不是
            return false;
        }
        int[] array = new int[10];
        int index = 0;
        while (x != 0) {
            array[index] = x % 10;
            x = x / 10;
            index++;
        }
        if (index <= 1)
            return true;
        int mid = (index - 1) / 2;
        for (int i : array) {
            index--;
            if (i != array[index]) {//当其值不相等时
                return false;
            }
            if (index <= mid) {//当index小于中位数时不用比较
                break;
            }
        }
        return true;
    }

    //Demo_2(回文数反转后相等)
    private static boolean isPalindrome_2(int x) {
        if (x < 0) {
            return false;
        }
        int result = 0, origin = x;
        while (x > 0) {
            //整数反转
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result == origin;
    }

    //Demo_3(字符串反转)
    private static boolean isPalindrome_3(int i) {
        if (i < 0) {
            return false;
        }
        String str = "" + i;
        String builder = new StringBuilder("" + i).reverse().toString();
        return str.equals(builder);
    }
}
