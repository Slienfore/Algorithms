package 贪心算法;

import java.util.Arrays;

//135-分发糖果
public class Demo_135 {
    public static void main(String[] args) {
        int[] num = {1, 3, 2, 2, 1};
        System.out.println(candy_1(num));
        System.out.println(candy_2(num));

    }
    //Demo——1：
    public static int candy_1(int[] ratings) {
        int length = ratings.length;
        //创建数组存放糖果
        int[] candyBus = new int[length];
        //保证每一个小孩都有一个糖果
        Arrays.fill(candyBus, 1);
        //第一次为：比前面小孩高的分发糖果
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candyBus[i] = candyBus[i - 1] + 1;//总是比前面的小孩多一个糖果
            }
        }
        //第二次要为：比后面高的小孩分发糖果
        for (int i = length - 2; i >= 0; i--) {
            //当前一个大于后一个时候且还要判断其是否糖果少于它
            if (ratings[i] > ratings[i + 1] && candyBus[i] <= candyBus[i + 1]) {
                //等于后面一个的糖果个数加上一
                candyBus[i] = candyBus[i + 1] + 1;
            }
        }
        return Arrays.stream(candyBus).sum();
    }

    //Demo_2
    public static int candy_2(int[] ratings) {
        //初始化总糖果数为 1, 前序递增标记数量, 后序递减标记数量0, 增量为 1
        int total = 1, increment = 1, decrement = 0, increasing = 1;
        int length = ratings.length;
        for (int i = 1; i < length; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                //当前一个与后一个相等时，增量为一(多获得一个)
                increasing = ratings[i] == ratings[i - 1] ? 1 : increasing + 1;
                total += increasing;
                //标记此时的增量
                increment = increasing;
                //此时逆序已经消除了，后序递减标记数量将初始化
                decrement = 0;
            } else {
                //如果为递减情况的话，后序递减标记数量增加
                ++decrement;
                //当后序递减标记数量的值与递增增量标记相等时，此时应该增加递减增量，保持平衡
                if (decrement == increment)
                    ++decrement;
                total += decrement;
                //初始化增量
                increasing = 1;
            }
        }
        return total;
    }
}
