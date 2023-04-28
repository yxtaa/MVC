
package com.mvc.test;

import com.alibaba.fastjson2.JSON;
import com.mvc.dao.SpDao;
import com.mvc.dao.impl.SpDaoImpl;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws SQLException {

        SpDao spDao = new SpDaoImpl();
        //List<Bazy> bazyList = spDao.executeStoredProcedure("吴连香");
        //System.out.println(bazyList.get(0).getXm());

        String name = "jsp_住院处";
        String[] inParams = {"23012"};
        int[] outParams = {OracleTypes.CURSOR,OracleTypes.VARCHAR};
        List<Map<String,Object>> mapList = spDao.executeStoredProcedure(name, inParams, outParams);
        String string = JSON.toJSONString(mapList);
        System.out.println(string);
        for ( Object obj: mapList){
            //判断该对象的类型，如果是Map类型，则将该对象转换为Map类型
            if (obj instanceof Map<?,?>){
                Map<String, Object> map = (Map<String, Object>) obj;
                //方法一：
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println(key + ":" + value);
                }

                //方法二：
            }else {
                System.out.println(obj.toString());
                System.out.println("年后");
            }
        }
        
    }
}