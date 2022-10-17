package 哈希表;

import java.util.*;
//1436-旅行终点站
public class Demo_1436 {
    public static void main(String[] args) {
        //创建一个数组装书数据
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("London", "New York"));
        list.add(Arrays.asList("New York", "Lima"));
        list.add(Arrays.asList("Lima", "Sao Paulo"));
        System.out.println(list.get(0));
        System.out.println(destCity(list));
    }
    //Demo_1：哈希表
    public static String destCity(List<List<String>> paths) {
        HashSet<String> map = new HashSet<>();
        for (List<String> start : paths) {
            //将起始地点放入到哈希表
            map.add(start.get(0));
        }
        for (List<String> end : paths) {
            String destination = end.get(1);
            //检索目的地，如果之中有不作为起始点去往别的地方
            if (!map.contains(destination))
                return destination;
        }
        //找不到直接为空目的地
        return "";
    }
}
