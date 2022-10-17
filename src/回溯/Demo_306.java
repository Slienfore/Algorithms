package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/1/10 10:14
 */
//306-累加数
public class Demo_306 {
    public static void main(String[] args) {
        String num = "111";
        System.out.println(isAdditiveNumber_1(num));
        System.out.println(isAdditiveNumber_2(num));

    }

    static String target;
    static int length;
    static List<List<Integer>> list = new ArrayList<>();

    /**
     * 回溯+高精度加法<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了48.53%的用户<br>
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了43.72%的用户
     */
    public static boolean isAdditiveNumber_1(String num) {
        target = num;
        length = num.length();
        return helper(0);
    }

    private static boolean helper(int pos) {//用来回溯查找满足条件的数字
        int size = list.size();//回溯的数字个数

        //递归出口(满足个数大于3的时候才会返回值为真)
        if (pos == length) return size >= 3;

        //递归范围(为 0 则递归下一位<不能包含前导 0>，非 0 则递归整个字符串)
        int maxArea = target.charAt(pos) == '0' ? (pos + 1) : length;

        List<Integer> curList = new ArrayList<>();

        for (int i = pos; i < maxArea; i++) {
            curList.add(0, target.charAt(i) - '0');//逆序存放数字(方便高精度运算)

            if (size < 2 || (check(list.get(size - 2), list.get(size - 1), curList))) {
                list.add(curList);

                if (helper(i + 1))
                    return true;

                list.remove(list.size() - 1);
            }

        }
        return false;
    }

    //高精度加法(逆序进行)
    private static boolean check(List<Integer> list_1, List<Integer> list_2, List<Integer> curList) {
        List<Integer> sumList = new ArrayList<>();
        int size_1 = list_1.size(), size_2 = list_2.size(), size_Cur = curList.size(), sum = 0;


        for (int i = 0; i < size_1 || i < size_2 || sum != 0; i++) {
            if (i < size_1) sum += list_1.get(i);
            if (i < size_2) sum += list_2.get(i);

            sumList.add(sum % 10);//取个位
            sum /= 10;//去除个位
        }

        boolean match = size_Cur == sumList.size();

        for (int i = 0; i < size_Cur && match; i++)
            if (!curList.get(i).equals(sumList.get(i))) return false;

        return match;
    }

    /**回溯<br>
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：36.2 MB, 在所有 Java 提交中击败了63.79%的用户
    */
    public static boolean isAdditiveNumber_2(String num) {
        return backtrack(num, 0, 0, 0, 0);
    }

    private static boolean backtrack(String num, int count, int index, long first, long second) {

        if (index >= num.length())//递归出口
            return count > 2;//判断已经处理的数是否有两个累加数

        long curVal = 0;
        for (int i = index; i < num.length(); i++) {

            if (num.charAt(index) == '0' && i > index)//如果首位为前导 0 ，则直接返回(如果是个位就是 0 的话，可以进行累加)
                return false;

            curVal = curVal * 10 + num.charAt(i) - '0';//进行累加

            if (count >= 2) {//如果统计的个数有两个的话需要进行判断是否满足累加和性质
                long sum = first + second;//前两个数进行累加

                //剪枝
                if (curVal > sum)//如果后面的数比前面累加的数"还大"的时候，则直接返回
                    return false;

                if (sum > curVal)//如果后面的数比前面累加的数"还小"的时候，则进行下一层的循环
                    continue;
            }

            if (backtrack(num, count + 1, i + 1, second, curVal))//进行下一层探索
                return true;
        }

        return false;
    }
}
