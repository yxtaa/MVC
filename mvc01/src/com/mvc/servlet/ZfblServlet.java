package com.mvc.servlet;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mvc.service.SpService;
import com.mvc.service.impl.SpServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet({"/Servlet/ZfblServlet", "/Servlet/ZfblServlet2", "/Servlet/ZfblSelect","/Servlet/ZfblUpdate","/Servlet/dwzfblSelect","/Servlet/dwzfblUpdate"})
public class ZfblServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的文本类型
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");//设置文本类型
        request.setCharacterEncoding("UTF-8");//请求对象设置字符集编码
        response.setHeader("Cache-Control", "no-cache");//响应对象设置头部缓存
        PrintWriter out = response.getWriter();

        String servletPath = request.getServletPath();
        SpService spService = new SpServiceImpl();

        //处理第一个请求
        if ("/Servlet/ZfblServlet".equals(servletPath)) {
            //获取请求参数
            String ybbm = request.getParameter("inputText");
            String spName = "JSP_添加自负比例";
            String[] inParams = {ybbm};
            int[] outParams = {OracleTypes.CURSOR, OracleTypes.VARCHAR};
            List<Map<String, Object>> mapList = spService.executeStoredProcedure(spName, inParams, outParams);
            String jsonString = JSON.toJSONString(mapList);
            out.print(jsonString);
            out.close();

        } else if ("/Servlet/ZfblServlet2".equals(servletPath)) {
            //获取请求参数
            String dayTime = request.getParameter("inputText");
            //调用存储过程
            String spName = "JSP_同步更新HIS比例";
            String[] inParams = {dayTime};
            int[] outParams = {OracleTypes.CURSOR, OracleTypes.VARCHAR};
            List<Map<String, Object>> mapList = spService.executeStoredProcedure(spName, inParams, outParams);
            //hashMap转化为json字符
            String jsonString = JSON.toJSONString(mapList);
            out.print(jsonString);
            out.close();

        } else if ("/Servlet/ZfblSelect".equals(servletPath)) {
/*            //获取请求参数 application/x-www-form-urlencoded
            String zyh = request.getParameter("input1");
            String ybbm = request.getParameter("input2");*/

            //获取请求参数 application/json
            // 读取请求体中的JSON数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // 将JSON字符串转换为JSON对象
            JSONObject data = JSON.parseObject(sb.toString());

            // 从JSON对象中获取数据
            String input1 = data.getString("input1");//住院号
            String input2 = data.getString("input2");//医保编码
            //String zyh = "22458";
            //String ybbm = "C14020227200020002950000001";

            //调用存储过程
            String spName = "JSP_住院费用比例_select";
            String[] inParams = {input1,input2};
            int[] outParams = {OracleTypes.CURSOR,OracleTypes.VARCHAR};
            List<Map<String, Object>> mapList = spService.executeStoredProcedure(spName, inParams, outParams);
            //hashMap转化为json字符
            String jsonString = JSON.toJSONString(mapList);
            out.print(jsonString);
            out.close();

        } else if ("/Servlet/ZfblUpdate".equals(servletPath)) {
            // 读取请求体中的JSON数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            // 将JSON字符串转换为JSON对象
            JSONObject data = JSON.parseObject(sb.toString());

            // 从JSON对象中获取数据
            String zyh = data.getString("zyh");//住院号
            String fyid = data.getString("fyid");//医保编码
            String fssj = data.getString("fssj");//发生时间
            String bxbm = data.getString("bxbm");//医保编码
            String zfbl = data.getString("zfbl");//自付比例
            String blbcr = data.getString("blbcr");//比例保存人
            String sfsc = data.getString("sfsc");//是否上传

            //调用存储过程
            String spName = "JSP_住院费用比例_update";
            String[] inParams = {zyh,fyid,bxbm,zfbl,sfsc};
            int[] outParams = {OracleTypes.VARCHAR};
            List<Map<String, Object>> mapList = spService.executeStoredProcedure(spName, inParams, outParams);

            //hashMap转化为json字符
            String jsonString = JSON.toJSONString(mapList);
            out.print(jsonString);
            out.close();

        } else if ("/Servlet/dwzfblSelect".equals(servletPath)) {
            /*查询地纬自付比例*/
            // 读取请求体中的JSON数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            // 将JSON字符串转换为JSON对象
            JSONObject data = JSON.parseObject(sb.toString());

            // 从JSON对象中获取数据
            String zyh = data.getString("input1");//住院号
            String bxbm = data.getString("input2");//医保编码

            //调用存储过程
            //String zyh = "11372";
            //String bxbm = "210300001e";
            String spName = "JSP_地纬费用比例_select";
            String[] inParams = {zyh,bxbm};
            int[] outParams = {OracleTypes.CURSOR,OracleTypes.VARCHAR};
            List<Map<String, Object>> mapList = spService.executeStoredProcedure(spName, inParams, outParams);

            //hashMap转化为json字符
            String jsonString = JSON.toJSONString(mapList);
            out.print(jsonString);
            out.close();

        } else if ("/Servlet/dwzfblUpdate".equals(servletPath)) {
            // 读取请求体中的JSON数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            // 将JSON字符串转换为JSON对象
            JSONObject data = JSON.parseObject(sb.toString());

            // 从JSON对象中获取数据
            String fypdh = data.getString("fypdh");//费用凭单号
            String sxh = data.getString("sxh");//顺序号
            String zfbl = data.getString("zfbl");//自付比例

            //调用存储过程
            String spName = "JSP_地纬费用比例_update";
            String[] inParams = {fypdh,sxh,zfbl};
            int[] outParams = {OracleTypes.VARCHAR};
            List<Map<String, Object>> mapList = spService.executeStoredProcedure(spName, inParams, outParams);

            //hashMap转化为json字符
            String jsonString = JSON.toJSONString(mapList);
            out.print(jsonString);
            out.close();
        }


    }


}
