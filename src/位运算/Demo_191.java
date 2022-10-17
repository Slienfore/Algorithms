package 位运算;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/5 8:57
 */
//191-位 1 的个数
public class Demo_191 {
    public static void main(String[] args) {
        int val = 000000001111;
        System.out.println(hammingWeight(val));
    }

    public static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

}
