package 指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//6- "Z"字型转换
public class Demo_6 {
    public static void main(String[] args) {
        int rows = 4;
        String s = "PAYPALISHIRING";
//        String s = "AB";
/*      P     A     H     N
        A  P  L  S  I  I  G
        Y     I     R
*/
//        System.out.println(myConvert(s, rows));
        System.out.println(convert_1(s, rows));
        System.out.println(convert_2(s, rows));
        System.out.println(convert_3(s, rows));
    }

    private static String convert_3(String s, int numRows) {
        if (numRows < 2)
            return s;

        String[] rowVal = new String[numRows];//分别用来拼接每一行字母
        Arrays.fill(rowVal, "");

        int row = 0;
        boolean isEnd = false;
        for (char val : s.toCharArray()) {
            if (row == numRows - 1) {
                isEnd = true;
            } else if (row == 0) {
                isEnd = false;
            }

            rowVal[row] += (val);//每一行用来拼接字符

            if (isEnd) {
                row--;
            } else {
                row++;
            }

        }

        StringBuilder res = new StringBuilder();
        for (String val : rowVal)
            res.append(val);

        return res.toString();
    }


    /**模拟<br>
    执行用时：5 ms, 在所有 Java 提交中击败了71.17%的用户<br>
    内存消耗：42.1 MB, 在所有 Java 提交中击败了20.42%的用户
    */
    public static String convert_2(String s, int numRows) {
        if (numRows < 2) return s;//若为 1 就是原字符串

        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++)
            rows.add(new StringBuilder());

        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1)
                flag = -flag;//如果是最后或者开头则进行反转增量指针

            i += flag;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows)
            res.append(row);

        return res.toString();
    }

    //Demo_2
    public static String convert_1(String s, int numRows) {
        //当行数为 1 的时候
        if (numRows == 1) {
            return s;
        }
        //创建一个字符串数组
        String[] array = new String[numRows];
        //字符串数组初始化
        Arrays.fill(array, "");
        //将字符串转换成char数组
        char[] chars = s.toCharArray();
        //获取规律周期
        int period = 2 * numRows - 2;
        for (int i = 0; i < chars.length; i++) {
            int index = i % period;//取模，获取位置
            //当其位置小于行数时，直接存放进字符串数组
            if (index < numRows) {
                array[index] += chars[i];
            } else {//中间
                array[period - index] += chars[i];
            }
        }
        //字符串拼接
        StringBuilder builder = new StringBuilder();
        for (String a : array) {
            builder.append(a);
        }
        return builder.toString();
    }


    //Demo_1(纪念)：
    public static String myConvert(String s, int numRows) {
        String result = "";
        int begin = 0, end = 0, point = 0;//设立三个标记(记录起点、终点、移动点)
        int length = s.length();
        ArrayList<String> container = new ArrayList<String>();//设立一个大容器
        if (s.length() > numRows) {//起始将第  end标记 定位于末端
            end = numRows - 1;
        } else {//当字符串的长度小于或者等于行数时，则直接返回，不用比较
            return s;
        }

        //进行遍历的是  point 标记
        while (point < length) {
            ArrayList<String> s1 = new ArrayList<String>();//设立-列容器
            ArrayList<String> s2 = new ArrayList<String>();//设立-中间字符容器
            // 中间容器起点与端点不能存储值
            s2.add(0, "");
            while (point <= end) { //进行 列 遍历
                s1.add(String.valueOf(s.charAt(point)));
                point++;
                if (point == s.length()) {//当标记触碰到末尾时退出循环
                    break;
                }
            }
            int index = 1;//记录中间中添加的元素个数
            StringBuilder builder = new StringBuilder();
            if (point < length) {//当标记为触碰到列时
                while (point > end && index < (numRows - 1) && point < length) {
                    builder.append(s.charAt(point));
                    point++;
                    index++;
                }
            }
            if (index == 2) {//只存了一次的话就不用反转了
                s2.add(String.valueOf(builder));
            } else {
                String reverse = String.valueOf(builder.reverse());//反转
                for (int m = 0; m < index - 1; m++) {
                    s2.add(String.valueOf(reverse.charAt(m)));
                }
            }
            for (int i = 0; i < numRows; i++) {//添加进大容器
                String middle = "", line = "";
                if (i <= s2.size() - 1) {
                    middle = s2.get(i);
                }
                if (i < s1.size()) {
                    line = s1.get(i) + middle;
                }
                if (end == (numRows - 1)) {//当end的还在第一列的时候就直接添加就行了
                    container.add(line);
                } else {
                    String whole = container.get(i) + line;//将之前的字符进行拼接，再放入
                    container.set(i, whole);//修改相应的元素
                }
            }
            if (point == ((2 * end) - begin)) {
                begin = point;
                int next = begin + (numRows - 1);//确定end标记下一次移动位置
                if (next < s.length()) {
                    end = next;
                } else {//如果end指针的值是超过了字符串长，则等于字符串长
                    end = s.length() - 1;
                }
            }
        }
        for (String traverse : container) {//遍历字符串拼接
            result += traverse;
        }
        return result;
    }
}
