package com.mvc.test;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mvc.pojo.Bazy;


public class TestJson {
    public static void main(String[] args) {
        //json字符串声明并赋值
        String jsonStr ="[{\n" +
                "\t\"姓名\": \"吴连香\",\n" +
                "\t\"住院号\": 1427,\n" +
                "\t\"主页ID\": 13\n" +
                "}, {\n" +
                "\t\"姓名\": \"吴连香\",\n" +
                "\t\"住院号\": 1427,\n" +
                "\t\"主页ID\": 14\n" +
                "}, {\n" +
                "\t\"placeHolder3\": \"dfsfklsjflasjflsajfljasf\"\n" +
                "}, {\n" +
                "\t\"placeHolder4\": \"2023-04-06\"\n" +
                "}, {\n" +
                "\t\"placeHolder5\": 12.56\n" +
                "}]";
        Object jsonObj = JSON.parse(jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        System.out.println(jsonObject);

        Bazy bazy = new Bazy();
        bazy.setZyid(1);
        bazy.setBrid(13);
        bazy.setXm("袁训涛");
        bazy.setZyh(2009);

        String string = JSON.toJSONString(bazy);
        //System.out.println(string);
        //
    }
}
