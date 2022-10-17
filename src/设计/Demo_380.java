package 设计;

import java.util.*;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/4/13 9:47
 */
//380-O(1) 时间插入、删除和获取随机元素
public class Demo_380 {
    public static void main(String[] args) {

    }

    List<Integer> list;//存储数值
    Map<Integer, Integer> map;//映射数值以及下标
    Random ram;//获取随机数

    public Demo_380() {
        list = new ArrayList<>();
        map = new HashMap<>();
        ram = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val))//不能插入重复数字
            return false;

        list.add(val);//添加元素
        map.put(val, list.size() - 1);//将当前元素以及其下标存放进去

        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val))//若不包含该值，说明不能将其进行移除
            return false;

        int pos = map.get(val);//将要移除元素的下标

        int temp = list.get(list.size() - 1);//使用一个中间变量填充进被移除的元素的位置
        list.set(pos, temp);
        map.put(temp, pos);

        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(ram.nextInt(list.size()));
    }
}
