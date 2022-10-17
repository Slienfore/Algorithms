package One;


/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/10/19 15:01
 */
// 矩形切割
public class Rectangle {
    public static void main(String[] args) {
        int length = 2019;
        int width = 324;
        int result = 324;
        while (length > 1 && width > 1) {
            if (length > width) {
                length -= width;
            } else {
                width -= length;
            }
            result++;
        }
        System.out.println(result);
    }
}
