package 周赛;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/3/13 14:04
 */
public class DemoTe {
    public static void main(String[] args) {

        HashSet<Integer> set = new HashSet<>() {{
            add(1);
            add(9);
            add(30);
            add(20);
            add(901);
        }};

        set.forEach(System.out::println);

    }
}
