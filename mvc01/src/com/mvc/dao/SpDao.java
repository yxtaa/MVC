package com.mvc.dao;

import com.mvc.pojo.Bazy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SpDao {

    String executeStoredProcedure(Integer zyh);
    List<Bazy> executeStoredProcedure(String xm);
    //List<Object[]> executeStoredProcedure(String spSQL,Object[] inputParams,Object[] outputParams);

    /**
     *
     * @param procedureName 存储过程名称
     * @param inParams 存储过程输入参数
     * @param outParams 存储过程输出参数
     * @return 最后将存储过程的输出参数，以List<Map<String,Object>>返回。如果是CURSOR类型。
     * 返回格式placeHolder2，placeHolder3...数字代表存储过程占位符的索引，从1开始，2 3....
     */
    List<Map<String,Object>> executeStoredProcedure(String procedureName, Object[] inParams, int[] outParams);
    String generateSql(String procedureName, Object[] inParams, int[] outParamTypes);
}
