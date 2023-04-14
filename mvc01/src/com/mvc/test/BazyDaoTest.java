package com.mvc.test;

import com.mvc.dao.impl.BazyDaoImpl;
import com.mvc.exceptions.NotFoundBazyException;
import com.mvc.pojo.Bazy;
import com.mvc.service.impl.BazyServiceImpl;

public class BazyDaoTest {
    public static void main(String[] args) throws NotFoundBazyException {

        //创建病案主页对象
/*        Bazy bazy = new Bazy();
        bazy.setBrid(20771);
        bazy.setZyid(4);
        bazy.setXm("李树千");
        bazy.setZyh(66669866);
        System.out.println(bazy);*/

        //创建BazyDao对象
        BazyDaoImpl bazyDao = new BazyDaoImpl();
        //bazyDao.intsert(bazy);
        //bazyDao.deleteById(20771,4);
/*        Bazy bazy1 = bazyDao.selectById(20771, 1);
        System.out.println(bazy1);
        List<Bazy> bazyList = bazyDao.selectALL();
        System.out.println(bazyList.size());
        for (Bazy b : bazyList) {
            System.out.println(b);
        }*/

        Bazy bazy = null;
           //   bazy =  bazyDao.selectById(20771, 1);

        Bazy bazy1 = bazyDao.selectById(20771, 2);
        BazyServiceImpl bazyService = new BazyServiceImpl();

    }
}
