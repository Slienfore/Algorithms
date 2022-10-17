package 回溯;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/2/25 16:30
 */
public class Demo_332__ {
    public static void main(String[] args) {
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

    static LinkedList<String> res = new LinkedList<>();
    static Map<String, PriorityQueue<String>> adjTable = new HashMap<>();//优先队列保持字典序

    public static List<String> findItinerary(List<List<String>> tickets) {
        tickets.forEach(list -> {
            String start = list.get(0), end = list.get(1);//获取初始还有结束结点

            adjTable.computeIfAbsent(start, o -> new PriorityQueue<>()).add(end);

        });

        euler("JFK");

        return res;
    }


    /**欧拉图解法<br>
    执行用时：4 ms, 在所有 Java 提交中击败了100.00%的用户<br>
    内存消耗：41.8 MB, 在所有 Java 提交中击败了27.56%的用户
    */
    private static void euler(String orin) {
        PriorityQueue<String> queue = adjTable.get(orin);//获取目的地

        while (queue != null && !queue.isEmpty())
            euler(queue.poll());//dfs下去到最后一个无连通的叶子结点

        res.addFirst(orin);//逆序插入叶子结点
    }


}
