package com.mvc.utils;

import java.util.List;
import java.util.Map;

public class ListUtil {
    public static void getHashMap(List mapList){
        for (Object obj : mapList) {
            //判断该对象的类型，如果是Map类型，则将该对象转换为Map类型
            if (obj instanceof Map<?,?>){
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println(key + ":" + value);
                }
            }else {
                System.out.println(obj.toString());
            }
        }
    }
}
