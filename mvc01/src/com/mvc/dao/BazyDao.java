package com.mvc.dao;

import com.mvc.pojo.Bazy;

import java.util.List;

public interface BazyDao {
    boolean intsert(Bazy bazy);

    int deleteById(Integer brid, Integer zyid);

    int updateById(Bazy bazy);

    int updateFyhById(Bazy bazy);

    Bazy selectById(Integer brid, Integer zyid);

    List<Bazy> selectALL();
}
