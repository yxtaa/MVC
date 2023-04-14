package com.mvc.service;

import com.mvc.exceptions.NotFoundBazyException;

public interface BazyService {
     void zhuanZhang(Integer brid, Integer zyid, Double fyh, Integer brid1, Integer zyid1)
            throws NotFoundBazyException;
}
