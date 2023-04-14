package com.mvc.test;

import com.mvc.utils.Ojdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OjdbcTest {
    public static void main(String[] args) {
        //初始化变量
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Ojdbc.getConnectionZlhis();
            String sql = "select 姓名 from 病案主页 where 病人id = ? and 主页id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"20771");
            ps.setString(2,"1");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Ojdbc.closePstmt(rs,ps,conn);
        }
    }
}
