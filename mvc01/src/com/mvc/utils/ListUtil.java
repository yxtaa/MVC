package com.mvc.utils;

import com.alibaba.fastjson2.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * list集合常用方法，测试用。
 */

public class ListUtil {
    /**
     * 遍历 List(HashMap(<String,Object>)) 集合，并以建值对的形式输出到控制台
     * @param mapList
     */
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

    /**
     * 将List集合转化为 JSOM字符串
     * @param hashMapList List<HashMap<String,Object>>类型
     * @return JSON字符串
     */
    public static String ListToJsonStr(List<HashMap<String,Object>> hashMapList){
        return JSON.toJSONString(hashMapList);
    }



}
