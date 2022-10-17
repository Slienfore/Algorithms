package 贪心算法;

import java.util.Arrays;
//455-分发饼干
public class Demo_455 {
    public static void main(String[] args) {
        int[] child = {1, 2, 3};
        int[] b = {1, 2};
        int num = findContentChildren(child, b);
        System.out.println(num);
    }

    public static int findContentChildren(int[] g, int[] s) {
        if (s.length == 0)
            return 0;
        //排好序后进行分配
        Arrays.sort(g);
        Arrays.sort(s);
        int childLength = g.length;
        int childNum = 0;
        for (int cookies : s) {
            if (childNum < childLength && g[childNum] <= cookies)
                childNum++;
        }
        return childNum;
    }


}

