package com.mvc.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GPT3Demo {
    public static void main(String[] args) {
        try {
            // OpenAI API密钥
            String apiKey = "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";

            // 请求参数
            String prompt = "Once upon a time";
            String model = "text-davinci-002";
            String maxTokens = "5000";

            // 构造HTTP请求
            URL url = new URL("https://api.openai.com/v1/engines/" + model + "/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            // 发送请求数据
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write("{\"prompt\": \"" + prompt + "\", \"max_tokens\": " + maxTokens + "}");
            writer.flush();

            // 获取API返回的数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // 解析API返回的数据
            String text = response.toString().split("\"text\": \"")[1].split("\"")[0];
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
