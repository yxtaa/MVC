
package com.mvc.utils;

import java.sql.*;
import java.util.*;

public class Ojdbc {
    //数据库连接参数
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("resources.jdbc");
    private static final String DB_DRIVER = RESOURCE_BUNDLE.getString("driver");
    private static final String DB_URL_ZLHIS = RESOURCE_BUNDLE.getString("url");
    private static final String DB_USERNAME_ZLHIS = RESOURCE_BUNDLE.getString("username");
    private static final String DB_PASSWORD_ZLHIS = RESOURCE_BUNDLE.getString("password");
    private static final String DB_URL_ORCL = RESOURCE_BUNDLE.getString("urlOrcl");
    private static final String DB_USERNAM_ORCL = RESOURCE_BUNDLE.getString("usernameOrcl");
    private static final String DB_PASSWORD_ORCL = RESOURCE_BUNDLE.getString("passwordOrcl");

    //私有构造方法，不允许创建对象
    private Ojdbc() {
    }

    /**
     * 注册oracle数据库驱动！！！！！！！！！！！！！！！！！！！！！
     */
    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库连接 ZLHIS
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnectionZlhis() throws SQLException {
        return DriverManager.getConnection(DB_URL_ZLHIS, DB_USERNAME_ZLHIS, DB_PASSWORD_ZLHIS);
    }

    /**
     * 获取数据库连接 ORCL
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnectionOrcl() throws SQLException {
        return DriverManager.getConnection(DB_URL_ORCL, DB_USERNAM_ORCL, DB_PASSWORD_ORCL);
    }

    /**
     * 关闭数据库连接对象（关闭PreparedStatement对象）
     *
     * @param rs    ResultSet
     * @param pstmt PreparedStatement
     * @param conn  Connection
     */
    public static void closePstmt(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 关闭数据库连接（关闭CallableStatement对象）
     *
     * @param rs    Result
     * @param cstmt CallableStatement
     * @param conn  Connection
     */
    public static void closeCstmt(ResultSet rs, CallableStatement cstmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (cstmt != null) {
            try {
                cstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 执行查询并
     * @param pstmtSql PreparedStatement的sql字符串
     * @param params 数组
     * @param dbName 中联数据库传 ZLHIS,地纬数据库传 ORCL
     * @return 返回Map集合，如果没有数据返回空
     */
    public static List<Map<String,Object>> executeQuery(String pstmtSql, Object[] params,String dbName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> rvList = null;

        try {
            if (dbName == "ZLHIS"){//判断连接的数据库实例
                conn = getConnectionZlhis();
            }else {
                conn = getConnectionOrcl();
            }

            pstmt = conn.prepareStatement(pstmtSql);

            //循环赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }

            //执行查询，获取结果集对象
            rs = pstmt.executeQuery();

            //获取元数据对象
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            //检索结果集，并将每一行数据添加到Map中，最后将Map增加到List集合
            if (rs != null) {
                rvList = new ArrayList<>();
                while (rs.next()) {
                    //创建HashMap对象
                    Map<String,Object> rowMap = new HashMap<>();
                    for (int i = 0; i < columnCount; i++) {
                        String columnName = metaData.getColumnName(i+1);
                        Object columnValue = rs.getObject(i+1);
                        rowMap.put(columnName,columnValue);//添加
                    }
                    //添加到List集合
                    rvList.add(rowMap);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            closePstmt(rs, pstmt, conn);
        }
        return rvList;
    }

}
