package 周赛;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/21 10:48
 */
//5201-给植物浇水
public class Demo_5201 {

    public static void main(String[] args) {
        int[] plants = {2, 2, 3, 3};
        int capacity = 5;
        System.out.println(wateringPlants(plants, capacity));

    }

    public static int wateringPlants(int[] plants, int capacity) {
        int contain = capacity;
        int river = 0;
        int step = 0;
        for (int val : plants) {
            if (contain >= val) {
                contain -= val;//浇水
                step++;
                river++;
            } else {
                step += 2 * river + 1;
                river++;
                contain = capacity - val;//补水后还要浇水
            }
        }
        return step;
    }
}
