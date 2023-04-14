package com.mvc.test;

import com.mvc.utils.ReadFile;

import java.nio.charset.Charset;

public class ReadFileTest {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Administrator\\IdeaProjects\\mvc\\mvc01\\src\\resources\\sql\\22.sql";
        String filePath1 = "C:\\Users\\Administrator\\IdeaProjects\\mvc\\out\\artifacts\\mvc01_war_exploded\\WEB-INF\\classes\\resources\\sql\\22.sql";
        String s = ReadFile.readSqlFile(filePath);
        System.out.println(s);
        Charset charset = Charset.defaultCharset();
        System.out.println(charset);
        Charset.availableCharsets();

    }
}
