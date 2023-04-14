package com.mvc.dao.impl;

import com.mvc.dao.BazyDao;
import com.mvc.pojo.Bazy;
import com.mvc.utils.Ojdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BazyDaoImpl implements BazyDao {
    /**
     * 病案主页插入数据：病人id，主页id，姓名，住院号
     *
     * @param bazy 病案主页对象
     * @return 成功返回 true，失败返回 false
     */
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int count;

    public boolean intsert(Bazy bazy) {
        try {
            conn = Ojdbc.getConnectionZlhis();

            String sql = "insert into 病案主页(病人id,主页id,姓名,住院号) values (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bazy.getBrid());
            ps.setInt(2, bazy.getZyid());
            ps.setString(3, bazy.getXm());
            ps.setInt(4, bazy.getZyh());
            count = ps.executeUpdate();
            if (count != 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            Ojdbc.closePstmt(this.rs, ps, conn);
        }
        return false;
    }

    /**
     * 根据病人id和主页id，删除病案主页记录
     *
     * @param brid 病人id
     * @param zyid 主页id
     * @return 删除的记录数 int类型
     */
    public int deleteById(Integer brid, Integer zyid) {
        Connection conn = null;
        PreparedStatement ps = null;
        //ResultSet rs = null;

        try {
            conn = Ojdbc.getConnectionZlhis();
            String sql = "delete from 病案主页 where 病人id= ? and 主页id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, brid);
            ps.setInt(2, zyid);
            int i = ps.executeUpdate();
            if (i != 0) {
                return i;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Ojdbc.closePstmt(null, ps, conn);
        }
        return 0;
    }

    /**
     * 更新姓名
     *
     * @param bazy
     * @return 更新条数
     */
    public int updateById(Bazy bazy) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //获取连接对象
            conn = Ojdbc.getConnectionZlhis();

            //获取数据库操作对象-PrepareStatement
            String sql = "update 病案主页 set 姓名 = ? where 病人id= ? and 主页id = ?";
            pstmt = conn.prepareStatement(sql);

            //设置参数
            pstmt.setString(1, bazy.getXm());
            pstmt.setInt(2, bazy.getBrid());
            pstmt.setInt(3, bazy.getZyid());

            //执行更新操作
            int i = pstmt.executeUpdate();
            if (i != 0) {
                return i;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭资源
            Ojdbc.closePstmt(rs, pstmt, conn);
        }
        return 0;
    }

    /**
     * 更新费用和，模拟转账
     */
    public int updateFyhById(Bazy bazy) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Ojdbc.getConnectionZlhis();
            String sql = "update 病案主页 set 费用和 = ? where 病人id= ? and 主页id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, bazy.getFyh());
            ps.setInt(2, bazy.getBrid());
            ps.setInt(3, bazy.getZyid());
            int i = ps.executeUpdate();
            if (i != 0) {
                return i;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Ojdbc.closePstmt(rs, ps, conn);
        }
        return 0;

    }


    /**
     * 返回一条病案主页记录，并封装成对象
     *
     * @param brid 病人id
     * @param zyid 主页id
     * @return 返回病案主页对象。空返回null
     */
    public Bazy selectById(Integer brid, Integer zyid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Bazy bazy = null;

        try {
            conn = Ojdbc.getConnectionZlhis();
            //获取数据库操作对象-PrepareStatement
            String sql = "select 姓名,住院号,入院日期,出院日期,费用和 from 病案主页 where 病人id= ? and 主页id = ?";
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, brid);
            ps.setInt(2, zyid);
            //执行查询操作
            rs = ps.executeQuery();
            //处理查询结果
            while (rs.next()) {
                String xm = rs.getString(1);
                Integer zyh = rs.getInt(2);
                Date ryrq = rs.getDate(3);
                Date cyrq = rs.getDate(4);
                Double fyh = rs.getDouble(5);

                //将结果集封装成对象
                bazy = new Bazy(brid, zyid, xm, zyh, ryrq, cyrq, fyh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Ojdbc.closePstmt(rs, ps, conn);
        }
        return bazy;
    }

    public List<Bazy> selectALL() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Bazy bazy;
        List<Bazy> bazyList = new ArrayList<>();

        try {
            conn = Ojdbc.getConnectionZlhis();
            String sql = "select 姓名,住院号,入院日期,出院日期,病人id,主页id from 病案主页 where 险类=988";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xm = rs.getString(1);
                Integer zyh = rs.getInt(2);
                Date ryrq = rs.getDate(3);
                Date cyrq = rs.getDate(4);
                Integer brid = rs.getInt(5);
                Integer zyid = rs.getInt(6);

                //将结果集封装成对象
                bazy = new Bazy(brid, zyid, xm, zyh, ryrq, cyrq);
                bazyList.add(bazy);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            Ojdbc.closePstmt(rs, ps, conn);
        }
        return bazyList;
    }

}
