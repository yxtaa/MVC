package com.mvc.servlet;

import com.alibaba.fastjson2.JSON;
import com.mvc.dao.SpDao;
import com.mvc.dao.impl.SpDaoImpl;
import com.mvc.service.SpService;
import com.mvc.service.impl.SpServiceImpl;
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

@WebServlet("/servlet/SpServlet01")
public class SpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        SpService spService = new SpServiceImpl();

        //接收请求参数
        String zyh = request.getParameter("zyh");
        String spName = "jsp_住院处";
        String[] inParams = {zyh};
        int[] outParams = {OracleTypes.CURSOR,OracleTypes.VARCHAR};
        List mapList = spService.executeStoredProcedure(spName, inParams, outParams);
        String jsonString = JSON.toJSONString(mapList);
        out.print(jsonString);

       /* for ( Object obj: mapList){
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
        }*/

    }
}
