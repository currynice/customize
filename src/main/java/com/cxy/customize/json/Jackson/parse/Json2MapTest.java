package com.cxy.customize.json.Jackson.parse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.Map;

public class Json2MapTest {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //json
            String jsonStr = "{\"0\":\"内部\",\"1\":\"秘密 \",\"2\":\"机密 \",\"3\":\"绝密 \",\"4\":\"非密 \"}";
            //json to Map way1
            Map<String,String> map1 = objectMapper.readValue(jsonStr, Map.class);
            //json to Map way2
            Map<String,String> map2 = objectMapper.readValue(jsonStr, new TypeReference<Map<String,String>>() {});
            System.out.println("json to Map:");
            System.out.println("way1:\n");
            map1.forEach((k,v)-> System.out.format("[key]:%s \t[value]:%s\n", k, v));
            System.out.println("way2:\n");
            map2.forEach((k,v)-> System.out.format("[key]:%s \t[value]:%s\n", k, v));

            //Map to json

            System.out.println("Map to json:");
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String jsonMapStr = objectMapper.writeValueAsString(map1);
            System.out.println(jsonMapStr+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
