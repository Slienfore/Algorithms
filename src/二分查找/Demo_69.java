package 二分查找;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/27 21:18
 */
//69-x 的算术平方根
public class Demo_69 {
    public static void main(String[] args) {
        int num = 8;
        System.out.println(mySqrt(num));
    }

    /**
     * 二分(区间向右收缩)<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了95.76%的用户<br>
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了10.44%的用户
     */
    public static int mySqrt(int x) {
        if (x == 0 || x == 1)
            return x;

        int left = 1, right = x / 2;//以上排除了 1， 但是当 x = 2 的时候， 就需要进行向下取整

        while (left < right) {
            int mid = left + (right - left + 1) / 2;//向右收缩区间

            long sqrt = (long) mid * mid;
            if (sqrt == x) {
                return mid;//可以直接返回，也可以收缩右边界(left = mid)
            } else if (sqrt > x) {
                right = mid - 1;//搜索区间[left, mid - 1]
            } else if (sqrt < x) {
                left = mid;//搜索区间[mid, right]
            }
        }

        return left;
    }

}
