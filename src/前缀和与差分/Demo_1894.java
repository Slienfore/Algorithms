package 前缀和与差分;

import utils.uu;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/6/8 17:31
 */
//1894-找到需要补充粉笔的学生编号
public class Demo_1894 {
    public static void main(String[] args) {
        int[] chalk = {5, 1, 5};
        int all = 22;
        System.out.println(chalkReplacer_1(chalk, all));
        System.out.println(chalkReplacer_2(chalk, all));
        System.out.println(chalkReplacer_3(chalk, all));
    }

    /**
     * 前缀和+二分<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.36%的用户<br>
     * 内存消耗：53.9 MB, 在所有 Java 提交中击败了57.90%的用户<br>
     * 2022年06月08日  19:16:54
     */
    public static int chalkReplacer_3(int[] chalk, int all) {
        int length = chalk.length;

        if (chalk[0] > all) return 0;

        for (int cur = 1; cur < length; ++cur) {
            chalk[cur] += chalk[cur - 1];//构造前缀和数组

            if (chalk[cur] > all) return cur;
        }

        all %= chalk[length - 1];//获取最后一轮的粉笔数量

        int left = 0, right = length - 1;
        //优化第二遍搜索效率
        while (left < right) {//ceiling (搜索第一大)
            int mid = left + (right - left) / 2;

            if (chalk[mid] <= all)//说明之前的所有学生使用的粉笔总数不会超过最后一轮
                left = mid + 1;
            else right = mid;
        }

        return left;
    }

    /**
     * 取余<br>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.36%的用户<br>
     * 内存消耗：54 MB, 在所有 Java 提交中击败了54.47%的用户<br>
     * 2022年06月08日  19:03:24
     */
    public static int chalkReplacer_2(int[] chalk, int all) {
        long sum = 0;
        for (int val : chalk)
            sum += val;

        all %= sum;//每一轮学生消耗粉笔的数量是固定的(直接计算最后一轮的粉笔)

        for (int cur = 0; cur < chalk.length; ++cur) {
            if (chalk[cur] > all)
                return cur;

            all -= chalk[cur];//模拟消耗粉笔
        }

        return -1;
    }


    /**
     * 暴力<br>
     * 执行用时：2123 ms, 在所有 Java 提交中击败了5.03%的用户<br>
     * 内存消耗：54.4 MB, 在所有 Java 提交中击败了11.01%的用户<br>
     * 2022年06月08日  17:43:38
     */
    public static int chalkReplacer_1(int[] chalk, int all) {
        int length = chalk.length;

        for (int cur = 0; ; ++cur) {
            all -= chalk[cur];

//            System.out.println("编号 " + cur + " 的学生， 使用了 " + chalk[cur] + " 支粉笔，然后： " + all);

            if (all < 0) return cur;

            if (cur == length - 1) cur = -1;
        }
    }
}
