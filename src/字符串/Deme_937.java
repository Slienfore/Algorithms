package 字符串;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/5/3 18:42
 */
//937-重新排列日志文件
public class Deme_937 {
    public static void main(String[] args) {

    }

    public static String[] reorderLogFiles(String[] logs) {
        int length = logs.length;

        List<Log> list = new ArrayList<>();

        for (int cur = 0; cur < length; ++cur)
            list.add(new Log(logs[cur], cur));

        Collections.sort(list, (o1, o2) -> {
            if (o1.type != o2.type)//字母博客排在数字博客之前(升序排序)
                return o1.type - o2.type;

            if (o1.type == 0)//两者为字母(内容相同比较标识符，不相同比较内容)
                return o1.content.equals(o2.content) ? o1.sign.compareTo(o2.sign) : o1.content.compareTo(o2.content);

            //若两者为数字(保持数组原来的位置不变)
            return o1.index - o2.index;
        });


        String[] res = new String[length];
        for (int cur = 0; cur < length; ++cur)
            res[cur] = list.get(cur).str;

        return res;
    }

    static class Log {
        String str, sign, content;
        int index, type;

        Log(String str, int index) {
            this.index = index;
            this.str = str;

            int cur = 0;
            while (cur < str.length() && str.charAt(cur) != ' ')//搜索内容
                ++cur;
            //标识符、内容
            sign = str.substring(0, cur);
            content = str.substring(cur + 1);

            type = Character.isDigit(content.charAt(0)) ? 1 : 0;
        }
    }
}
