package One;

import java.util.Arrays;
import java.util.List;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/10/29 16:26
 */
// 1773-统计匹配检索规则的物品数量
public class Demo_1773 {

    public static void main(String[] args) {
        List<List<String>> list = List.of(Arrays.asList("phone", "blue", "pixel"),
                Arrays.asList("computer", "silver", "lenovo"),
                Arrays.asList("phone", "gold", "iphone"));

        String key = "color", val = "silver";
        System.out.println(countMatches(list, key, val));
    }

    // 模拟
    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        // 判断匹配类型 => items[i] = [type, color, name]
        int key = "type".equals(ruleKey) ? 0 :
                ("color".equals(ruleKey) ? 1 : 2);

        int res = 0;

        for (List<String> list : items)
            if (list.get(key).equals(ruleValue)) ++res;

        return res;
    }

}
