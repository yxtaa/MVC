package com.mvc.service;

import com.mvc.pojo.Bazy;

import java.util.List;
import java.util.Map;

public interface SpService {
   String executeStoredProcedure(Integer zyh);
   List<Bazy> executeStoredProcedure(String xm);
   List<Map<String,Object>> executeStoredProcedure(String procedureName, Object[] inParams, int[] outParamTypes);
}
