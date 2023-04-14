package com.mvc.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadFile {
    /**
     * 读取src/resources/sql/...目录下的sql文件，返回sql字符串
     * @param fileName sql文件名，文件类型默认 .sql 类型
     * @return 返回文本字符串
     */
    public static String readSqlFile(HttpServletRequest request, String fileName) {
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = null;
        String rootPath = request.getServletContext().getRealPath("/");// 获取Web应用程序的根目录
        String relativePath = "WEB-INF\\classes\\resources\\sql\\"; // 相对路径
        String filePath = rootPath + relativePath + fileName + ".sql";
        try {
            // 创建FileInputStream对象，并传入文件路径作为参数
            fis = new FileInputStream(filePath);
            // 创建一个字节数组，用于存储读取到的数据 1kb
            byte[] buffer = new byte[1024];
            // 定义变量，记录读取到的字节数
            int byteCount;
            // 循环读取文件内容，直到读取到文件末尾（返回值为-1）
            while ((byteCount = fis.read(buffer)) != -1) {
                // 将读取到的数据转换为字符串并追加到StringBuilder中
                String s = new String(buffer, 0, byteCount,StandardCharsets.UTF_8);
                sb.append(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 如果fis不为空，关闭流，释放资源
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        // 返回读取到的内容
        return sb.toString();
    }

    public static String readSqlFile(String filePath) {
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = null;
        try {
            // 创建FileInputStream对象，并传入文件路径作为参数
            fis = new FileInputStream(filePath);
            // 创建一个字节数组，用于存储读取到的数据 1kb
            byte[] buffer = new byte[1024];
            // 定义变量，记录读取到的字节数
            int byteCount;
            // 循环读取文件内容，直到读取到文件末尾（返回值为-1）
            while ((byteCount = fis.read(buffer)) != -1) {
                // 将读取到的数据转换为字符串并追加到StringBuilder中
                String s = new String(buffer, 0, byteCount, StandardCharsets.UTF_8);
                sb.append(s);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 如果fis不为空，关闭流，释放资源
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        // 返回读取到的内容
        return sb.toString();
    }
}
