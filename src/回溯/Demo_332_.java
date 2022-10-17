package 回溯;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/24 19:47
 */
public class Demo_332_ {
    public static void main(String[] args) {
/*        List<List<String>> tickets = new ArrayList<>() {{
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
        }};*/
/*        List<List<String>> tickets = new ArrayList<>() {{
            add(new ArrayList<>() {{
                add("JFK");
                add("KUL");
            }});
            add(new ArrayList<>() {{
                add("JFK");
                add("NRT");
            }});
            add(new ArrayList<>() {{
                add("NRT");
                add("JFK");
            }});
        }};*/
        List<List<String>> tickets = new ArrayList<>() {{
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
        }};

        for (List<String> travel : tickets)
            System.out.println(travel);

        System.out.println("\n" + findItinerary(tickets));

    }

    /*      //遍历机的散列表
            Set<Map.Entry<String, TreeMap<String, Integer>>> entrySet = mapping.entrySet();
            for (Map.Entry<String, TreeMap<String, Integer>> mapEntry : entrySet) {
                System.out.println("起始站:  " + mapEntry.getKey());

                TreeMap<String, Integer> endPos = mapEntry.getValue();
                for (Map.Entry<String, Integer> end : endPos.entrySet())
                    System.out.println("终点站:   " + end.getKey() + "     票数" + end.getValue());


                System.out.println();
            }*/

    //映射首站以及终点站
    static Map<String, TreeMap<String, Integer>> mapping = new HashMap<>();
    static LinkedList<String> res = new LinkedList<>();


    public static List<String> findItinerary(List<List<String>> tickets) {

        for (List<String> list : tickets) {
            String start = list.get(0), end = list.get(1);//获取每一张票的起点与终点

/*            TreeMap<String, Integer> treeMap = mapping.getOrDefault(start, new TreeMap<String, Integer>());

            treeMap.put(end, treeMap.getOrDefault(end, 0) + 1);

            mapping.put(start, treeMap);*/

            TreeMap<String, Integer> absent = mapping.computeIfAbsent(start, map -> new TreeMap<String, Integer>());

            absent.put(end, absent.getOrDefault(end, 0) + 1);

        }



        res.add("JFK");//添加起始结点

        dfsRecur(tickets.size());

        return res;
    }


    /**
     * 回溯<br>
     * 执行用时：9 ms, 在所有 Java 提交中击败了57.50%的用户<br>
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了9.28%的用户
     */
    private static boolean dfsRecur(int total) {//只需要搜索一条枝条(不用搜索子集)
        if (res.size() == total + 1)
            return true;

        String orin = res.getLast();//获得起始地点

        if (!mapping.containsKey(orin))//防止起始点没有飞往其他机场的航班
            return false;

        for (Map.Entry<String, Integer> endPos : mapping.get(orin).entrySet()) {//获取到每一个终点站
            if (endPos.getValue() < 1)
                continue;

            TreeMap<String, Integer> map = mapping.get(orin);//获得当前机场航班的散列表

            res.add(endPos.getKey());

            map.put(endPos.getKey(), endPos.getValue() - 1);//将要起飞
            mapping.put(orin, map);

            if (dfsRecur(total))
                return true;//如果找到的话一步步回退

            map.put(endPos.getKey(), endPos.getValue() + 1);//航班取消
            mapping.put(orin, map);

            res.removeLast();
        }
        return false;//遍历所有结点的时候找不到就返回false

    }
}