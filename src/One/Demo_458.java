package One;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/25 8:32
 */
// 458-可怜的小猪
public class Demo_458 {
    public static void main(String[] args) {

    }

    /*抄答案
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：35.3 MB, 在所有 Java 提交中击败了29.32%的用户
     */
    public static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return (int) Math.ceil(Math.log(buckets) / Math.log((int) (minutesToTest / minutesToDie + 1)));
    }
}
