package com.mvc.test;

import com.mvc.utils.Ojdbc;
import oracle.jdbc.OracleTypes;

import java.sql.*;

public class ProcTest {
    public static void main(String[] args) throws SQLException {
        //测试元数据对象 MetaData
        Connection conn = Ojdbc.getConnectionZlhis();
        //CallableStatement cs = conn.prepareCall("{call your_stored_procedure(?, ?, ?)}");
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet rs = metaData.getProcedureColumns(null, null, "yxt_test", null);
        while (rs.next()) {
            String columnName = rs.getString("COLUMN_NAME");
            int columnType = rs.getInt("COLUMN_TYPE");
            if (columnType == DatabaseMetaData.procedureColumnOut || columnType == DatabaseMetaData.procedureColumnInOut) {
                int dataType = rs.getInt("DATA_TYPE");
                switch (dataType) {
                    case OracleTypes.VARCHAR:
                        // 处理字符串类型的输出参数
                        System.out.println(11111);
                        break;
                    case OracleTypes.NUMBER:
                        // 处理数字类型的输出参数
                        System.out.println(11111);
                        break;
                    case OracleTypes.DATE:
                        // 处理日期类型的输出参数
                        System.out.println(11111);
                        break;
                    case OracleTypes.TIMESTAMP:
                        // 处理时间戳类型的输出参数
                        System.out.println(11111);
                        break;
                    case OracleTypes.CURSOR:
                        // 处理结果集类型的输出参数
                        System.out.println(11111);
                        break;
                    // 可以根据需要添加其他输出参数类型
                }
            }
        }



    }
}
