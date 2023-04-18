package com.mvc.dao.impl;


import com.mvc.dao.SpDao;
import com.mvc.pojo.Bazy;
import com.mvc.utils.Ojdbc;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.*;


public class SpDaoImpl implements SpDao {

    /**
     * 调用 YXT_住院处(住院号 IN，执行结果 OUT);
     *
     * @param zyh：住院号
     * @return String
     */
    public String executeStoredProcedure(Integer zyh) {
        //初始化
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String outputValue = null;
        try {
            //获取连接对象
            conn = Ojdbc.getConnectionZlhis();

            //获取数据库操作对象-CallableStatement
            String sql = "{call YXT_TEST(?,?)}";
            cstmt = conn.prepareCall(sql);

            //设置输入参数
            cstmt.setInt(1, zyh);

            //注册输出参数
            cstmt.registerOutParameter(2, OracleTypes.VARCHAR);

            //执行存储过程
            cstmt.execute();

            //获取输出参数
            outputValue = cstmt.getString(2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Ojdbc.closeCstmt(rs, cstmt, conn);
        }
        return outputValue;
    }

    @Override
    public List<Bazy> executeStoredProcedure(String xm) {
        //初始化
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        //返回结果集参数
        String outputXm;
        Integer outputZyh;
        Integer outputZyid;
        ArrayList<Bazy> returnValue = new ArrayList<>();

        try {
            //获取连接对象
            conn = Ojdbc.getConnectionZlhis();

            //获取数据库操作对象-CallableStatement
            String sql = "call YXT_TEST(?,?)";
            cstmt = conn.prepareCall(sql);

            //设置输入参数
            cstmt.setString(1, xm);

            //注册输出参数
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            //执行存储过程
            boolean has = cstmt.execute();

            //获取结果集
            rs = (ResultSet) cstmt.getObject(2);
            //ResultSetMetaData metaData = rs.getMetaData();
            System.out.println(OracleTypes.CURSOR);
            //处理结果集
            while (rs.next()) {
                outputXm = rs.getString("姓名");
                outputZyh = rs.getInt("住院号");
                outputZyid = rs.getInt("主页id");
                //封装
                Bazy bazy = new Bazy();
                bazy.setXm(outputXm);
                bazy.setZyh(outputZyh);
                bazy.setZyid(outputZyid);
                returnValue.add(bazy);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Ojdbc.closeCstmt(rs, cstmt, conn);
        }
        return returnValue;
    }

    /**
     * 生成调用存储过程的SQL语句
     *
     * @param procedureName 存储过程名称
     * @param inParams      存储过程输入参数
     * @param oracleTypes   存储过程输出参数
     * @return String
     */
    public String generateSql(String procedureName, Object[] inParams, int[] oracleTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append("{call ");
        sb.append(procedureName);
        sb.append("(");
        int inParamCount = 0;
        int outParamCount = 0;
        for (int i = 0; i < inParams.length; i++) {
            if (inParams[i] != null) {
                if (inParamCount > 0) {
                    sb.append(",");
                }
                sb.append("?");
                inParamCount++;
            }
        }
        for (int i = 0; i < oracleTypes.length; i++) {
            if (oracleTypes[i] != Types.NULL) {
                if (inParamCount > 0 || outParamCount > 0) {
                    sb.append(",");
                }
                sb.append("?");
                outParamCount++;
            }
        }
        sb.append(")}");
        return sb.toString();
    }

    /**
     * @param procedureName 存储过程名称
     * @param inParams      存储过程输入参数
     * @param oracleTypes   存储过程输出参数
     * @return 以Map集合的形式返回存储过程输出参数
     */
    @Override
    public List<Map<String, Object>> executeStoredProcedure(String procedureName, Object[] inParams, int[] oracleTypes) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> rvList = new ArrayList();//返回输出参数

        try {
            conn = Ojdbc.getConnectionZlhis();//连接对象

            // 生成调用存储过程的SQL语句
            String sql = generateSql(procedureName, inParams, oracleTypes);
            //创建CallableStatement对象
            cstmt = conn.prepareCall(sql);

            // 设置输入参数
            for (int i = 0; i < inParams.length; i++) {
                cstmt.setObject(i + 1, inParams[i]);
            }

            // 注册输出参数
            for (int i = 0; i < oracleTypes.length; i++) {
                cstmt.registerOutParameter(i + 1 + inParams.length, oracleTypes[i]);
            }

            //执行过程
            cstmt.execute();

            //获取输出参数
            for (int i = 0; i < oracleTypes.length; i++) {
                switch (oracleTypes[i]) {//
                    case OracleTypes.VARCHAR:
                        // 处理字符串类型的输出参数
                        Object varValue = cstmt.getObject(inParams.length + i + 1);
                        Map<String, Object> varMap = new HashMap<>();
                        varMap.put("输出结果" + (i + 1), varValue);
                        rvList.add(varMap);
                        break;
                    case OracleTypes.NUMBER:
                        // 处理数字类型的输出参数
                        Object numValue = cstmt.getObject(inParams.length + i + 1);
                        Map<String, Object> numMap = new HashMap<>();
                        numMap.put("输出结果" + (i + 1), numValue);
                        rvList.add(numMap);
                        break;
                    case OracleTypes.DATE:
                        // 处理日期类型的输出参数
                        //...
                        Object valueDate = cstmt.getObject(inParams.length + i + 1);
                        Map<String, Object> dateMap = new HashMap<>();
                        dateMap.put("placeHolder" + (inParams.length + i + 1), valueDate);
                        rvList.add(dateMap);
                        break;
                    case OracleTypes.TIMESTAMP:
                        // 处理时间戳类型的输出参数
                        //...
                        break;
                    case OracleTypes.CURSOR:
                        // 处理结果集类型的输出参数
                        rs = (ResultSet) cstmt.getObject(inParams.length + i + 1);
                        if (rs != null) {
                            //获取结果集元数据
                            ResultSetMetaData rsMetaData = rs.getMetaData();
                            int columnCount = rsMetaData.getColumnCount();
                            while (rs.next()) {//检索结果集
                                Map<String, Object> rowMap = new HashMap<>();
                                for (int j = 1; j <= columnCount; j++) {
                                    String columnName = rsMetaData.getColumnName(j);
                                    Object columnValue = rs.getObject(j);
                                    rowMap.put(columnName, columnValue);
                                }
                                rvList.add(rowMap);
                            }
                        }
                        break;
                    // 可以根据需要添加其他输出参数类型
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            Ojdbc.closeCstmt(rs, cstmt, conn);
        }
        return rvList;//最后返回这个 List 集合
    }
}



