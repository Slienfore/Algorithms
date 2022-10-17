package One;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/15 10:20
 */
public class Demo_319 {

    public static void main(String[] args) {
        int num = 99999;

        System.out.println(bulbSwitch_1(num));

        System.out.println(bulbSwitch_2(num));

    }

    /*
    求取他们的公约数，若为奇数则会电灯则会打开
    */
    private static int bulbSwitch_1(int num) {
        int light = 0;

        for (int i = 1; i <= num; i++) {
            int temp = i, operation = 0;

            for (int j = temp; j > 0; j--) //获取其的公约数
                if (temp % j == 0) operation++;

            if (operation % 2 != 0) light++;
        }

        return light;
    }

    private static int bulbSwitch_2(int num) {
        return (int) Math.sqrt(num);
    }
}