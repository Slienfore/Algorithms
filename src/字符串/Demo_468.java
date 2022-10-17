package 字符串;

import java.util.Arrays;

/**
 * @author Slienfore
 * @version 1.0
 * @date 2022/5/29 20:07
 */
//468-验证 IP 地址
public class Demo_468 {
    public static void main(String[] args) {
//        String ip = "172.16.254.1";
        String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334";
        System.out.println(validIPAddress(ip));
    }

    public static String validIPAddress(String queryIP) {

        if (queryIP.contains(".") && checkIPV4(queryIP))
            return "IPv4";

        if (queryIP.contains(":") && checkIPV6(queryIP))
            return "IPv6";

        return "Neither";
    }

    public static boolean checkIPV4(String queryIp) {
        //模式匹配最多限制 4 次
        String[] arr = queryIp.split("\\.", 4);

        if (arr.length != 4) return false;

        for (String ip : arr) {

            if (nonNum(ip, 10)) return false;

            int val = Integer.parseInt(ip);

            //验证 IP 取值范围， 且验证 IP 是否包含前导零
            if (val > 255 || (ip.length() > 1 && ip.startsWith("0"))) return false;
        }

        return true;
    }

    public static boolean checkIPV6(String queryIp) {
        String[] arr = queryIp.split("\\:", 8);

        if (arr.length != 8) return false;

        for (String ip : arr)
            if (ip.length() > 4 || nonNum(ip, 16)) return false;

        return true;
    }

    public static boolean nonNum(String ip, int radix) {
        try {
            Integer.parseInt(ip, radix);
            return false;
        } catch (Exception e) {//如果不能转换成为数字则说明会报错
            return true;
        }
    }
}
