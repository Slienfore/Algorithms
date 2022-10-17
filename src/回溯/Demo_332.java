package 回溯;

import com.sun.source.tree.Tree;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2021/11/12 20:22
 */
//332-重新安排行程
public class Demo_332 {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add("MUC");
                add("LHR");
            }});
            add(new ArrayList<>() {{
                add("JFK");
                add("MUC");
            }});
            add(new ArrayList<>() {{
                add("SFO");
                add("SJC");
            }});
            add(new ArrayList<>() {{
                add("LHR");
                add("SFO");
            }});
        }};

/*        List<List<String>> tickets = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add("JFK");
                add("SFO");
            }});
            add(new ArrayList<>() {{
                add("JFK");
                add("ATL");
            }});
            add(new ArrayList<>() {{
                add("SFO");
                add("ATL");
            }});
            add(new ArrayList<>() {{
                add("ATL");
                add("JFK");
            }});
            add(new ArrayList<>() {{
                add("ATL");
                add("SFO");
            }});
        }};*/

        for (List<String> travel : tickets)
            System.out.println(travel);

        System.out.println("\n" + findItinerary(tickets));

    }


    static LinkedList<String> path = new LinkedList<>();
    //用来映射 (起始站 ----> (<终点站>, <其航班的数量>)
    static Map<String, Map<String, Integer>> map = new HashMap<>();

    public static List<String> findItinerary(List<List<String>> tickets) {


        for (List<String> list : tickets) {
            String begin = list.get(0), end = list.get(1);
            //目的地哈希表进行存放
            Map<String, Integer> desMap;

            if (map.containsKey(begin)) {//若映射中包含起点时
                //目的地
                desMap = map.get(begin);
                //存放到目的地的航班数量
                desMap.put(end, desMap.getOrDefault(end, 0) + 1);
            } else {
                //TreeMap 用来维护地点的有序性
                desMap = new TreeMap<>();
                desMap.put(end, 1);
            }

            map.put(begin, desMap);
        }
        //起始点: JFK
        path.add("JFK");

        backTracking(tickets.size());//JFK的所有的航线
        return path;
    }

    /**
     * 执行用时：13 ms, 在所有 Java 提交中击败了13.32%的用户<br>
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了96.40%的用户
     */
    private static boolean backTracking(int flight) {//航线的数量
        //飞完了 JFK 所有的航班的时候
        if (path.size() == flight + 1) return true;

        String cur = path.getLast();//获取当前航线的-->起始站

        if (map.containsKey(cur)) {//若存在该起始站，那么将进行 DFS 查询所有的目的地

            //获取当前起点的飞往其它地方的迭代器
            Set<Map.Entry<String, Integer>> line = map.get(cur).entrySet();
            for (Map.Entry<String, Integer> next : line) {
                Integer flightNum = next.getValue();//飞往下一个目的地的航线数量

                if (flightNum > 0) {//若下一个"目的地"有--飞往-->>其它地方的航班的时候

                    next.setValue(flightNum - 1);//已经飞过了

                    path.add(next.getKey());//进行回溯搜索

                    if (backTracking(flight)) return true;//飞完之后

                    //进行回溯
                    path.removeLast();
                    next.setValue(flightNum);
                }
            }
        }
        return false;
    }

}
