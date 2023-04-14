package com.mvc.service.impl;

import com.mvc.dao.BazyDao;
import com.mvc.dao.impl.BazyDaoImpl;
import com.mvc.exceptions.NotFoundBazyException;
import com.mvc.pojo.Bazy;
import com.mvc.service.BazyService;

/**
 * 专门处理病案主页表相关的业务
 * 特点：就是创建dao对象，
 */
public class BazyServiceImpl implements BazyService {
    //初始化bazyDao对象

    private BazyDao bazyDao = new BazyDaoImpl();//多态

    /**
     * 两个费用和模拟转账
     */
    public void zhuanZhang(Integer brid, Integer zyid, Double fyh, Integer brid1, Integer zyid1) throws NotFoundBazyException {

        Bazy fromBazy = bazyDao.selectById(brid, zyid);
        Bazy toBazy = bazyDao.selectById(brid1, zyid);

        if (fromBazy == null | toBazy == null) {
            throw new NotFoundBazyException("病人信息不存在");
        }

        //修改内存
        fromBazy.setFyh(fromBazy.getFyh() - fyh);
        toBazy.setFyh(toBazy.getFyh() + fyh);

        //更新数据库
        bazyDao.updateFyhById(fromBazy);
        bazyDao.updateFyhById(toBazy);

    }
}
