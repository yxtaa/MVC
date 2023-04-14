package com.mvc.servlet;

import com.mvc.exceptions.NotFoundBazyException;
import com.mvc.service.BazyService;
import com.mvc.service.impl.BazyServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/servlet/bazyServlet")
public class BazyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        //字符串转换
        Integer brid = Integer.valueOf(request.getParameter("brid"));
        Integer zyid = Integer.valueOf(request.getParameter("zyid"));
        Integer brid1 = Integer.valueOf(request.getParameter("brid1"));
        Integer zyid1 = Integer.valueOf(request.getParameter("zyid1"));
        Double fyh = Double.valueOf(request.getParameter("fyh"));

        BazyService bazyService = new BazyServiceImpl();

        try {
            bazyService.zhuanZhang(brid,zyid,fyh,brid1,zyid1);
        } catch (NotFoundBazyException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/success.jsp");

    }
}
