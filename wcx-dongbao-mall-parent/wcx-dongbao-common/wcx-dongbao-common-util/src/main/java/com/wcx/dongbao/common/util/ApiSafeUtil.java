package com.wcx.dongbao.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ApiSafeUtil {
    final String apiSecret="abccc";
    public static String generateSign(Map<String,Object> map){
        TreeMap<String,Object> sortedMap=new TreeMap<>(map);
        StringBuilder sb=new StringBuilder();
        for (String k:sortedMap.keySet()){
            sb.append(k).append("=").append(sortedMap.get(k)).append("#");
        }
        return MD5Utils.MD5(sb.toString());
    }

    public static boolean checkSign(Map<String,Object> map){
        if (!map.containsKey("sign")) return false;
        String sign=map.get("sign").toString();
        map.remove("sign");
        System.out.println(map);
        String check=generateSign(map);
        System.out.println("sign为:"+check);
        return sign.equals(check);
    }

    public static void main(String[] args) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("name","123");
        map.put("age","18");
//        map.put("sign","123");
        System.out.println(generateSign(map));
    }
}
