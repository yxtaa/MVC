package com.mvc.servlet;

import com.alibaba.fastjson2.JSON;
import com.mvc.service.SpService;
import com.mvc.service.impl.SpServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleTypes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/Servlet/ZfblServlet")
public class ZfblServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置响应的文本类型
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //获取请求参数
        String ybbm = request.getParameter("inputText");

        //调用存储过程
        SpService spService = new SpServiceImpl();
        String spName = "JSP_添加自负比例";
        String[] inParams = {ybbm};
        int[] outParams = {OracleTypes.CURSOR,OracleTypes.VARCHAR};
        List<Map<String, Object>> mapList = spService.executeStoredProcedure(spName, inParams, outParams);
        String jsonString = JSON.toJSONString(mapList);
        out.print(jsonString);

    }
}
