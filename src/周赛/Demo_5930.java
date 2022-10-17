package 周赛;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/21 10:34
 */
//5930-两栋颜色不同且距离最远的房子
public class Demo_5930 {
    public static void main(String[] args) {
        int[] colors = {0, 1};
        System.out.println(maxDistance(colors));
    }

    public static int maxDistance(int[] colors) {
        int length = colors.length;

        int res = 0;
        for (int left = 0; left < length; left++)
            for (int right = length - 1; right > left; right--) {
                if (colors[left] != colors[right]) {
                    res = Math.max(res, right - left);
                    break;
                }
            }
        return res;
    }
}
