package com.mvc.service.impl;

import com.mvc.dao.SpDao;
import com.mvc.dao.impl.SpDaoImpl;
import com.mvc.pojo.Bazy;
import com.mvc.service.SpService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpServiceImpl implements SpService {

    private SpDao spDao = new SpDaoImpl();

    @Override
    public String executeStoredProcedure(Integer zyh) {
        return spDao.executeStoredProcedure(zyh);
    }

    @Override
    public List<Bazy> executeStoredProcedure(String xm) {
        return spDao.executeStoredProcedure(xm);
    }

    @Override
    public List<Map<String,Object>> executeStoredProcedure(String procedureName, Object[] inParams, int[] outParamTypes) {
        return spDao.executeStoredProcedure(procedureName,inParams,outParamTypes);
    }
}
