package 哈希表;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//187-重复的DNA序列
public class Demo_187 {
    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(findRepeatedDnaSequences(s));
    }

    //双Set过滤
    public static List<String> findRepeatedDnaSequences(String s) {
        Set<String> judge = new HashSet<>();
        Set<String> save = new HashSet<>();
        int length = s.length();
        for (int i = 0; i < length - 9; i++) {
            String sub = s.substring(i, i + 10);
            //当其加不进第一个判断set，的时候则加入保存set
            if (!judge.add(sub)) save.add(sub);
        }
        return new ArrayList<>(save);
    }
}
