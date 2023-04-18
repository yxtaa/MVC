package com.mvc.servlet;

import com.mvc.dao.SpDao;
import com.mvc.dao.impl.SpDaoImpl;
import com.mvc.utils.ReadFile;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@WebServlet("/servlet/SpServlet")
public class SpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        SpDao spDao = new SpDaoImpl();

        String s = ReadFile.readSqlFile("C:\\Users\\Administrator\\IdeaProjects\\mvc\\out\\artifacts" +
                "\\mvc01_war_exploded\\WEB-INF\\classes\\resources\\sql\\22.sql");
        out.println(s);

        String s1 = ReadFile.readSqlFile(request, "22");
        out.println(s1);
        System.out.println(s1);

        String name = "YXT_TEST";
        String[] inParams = {"吴连香"};
        int[] outParams = {OracleTypes.CURSOR,OracleTypes.VARCHAR,OracleTypes.DATE,OracleTypes.NUMBER};
        List mapList = spDao.executeStoredProcedure(name, inParams, outParams);

        for ( Object obj: mapList){
            //判断该对象的类型，如果是Map类型，则将该对象转换为Map类型
            if (obj instanceof Map<?,?>){
                Map<String, Object> map = (Map<String, Object>) obj;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    out.println(key + ":" + value + "<BR>");
                }
            }else {
                out.println(obj.toString());
            }
        }

    }
}
