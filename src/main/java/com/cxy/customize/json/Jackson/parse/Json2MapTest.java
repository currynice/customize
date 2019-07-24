package com.cxy.customize.json.Jackson.parse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Json2MapTest {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //json
            String jsonStr = "{\"name\":\"cxy\",\"age\":\"22\"}";
            //way1
            Map<String,String> map1 = objectMapper.readValue(jsonStr, Map.class);
            //way2
            Map<String,String> map2 = objectMapper.readValue(jsonStr, new TypeReference<Map<String,String>>() {});
            System.out.println("way1:\n");
            map1.forEach((k,v)-> System.out.format("[key]:%s \t[value]:%s\n", k, v));
            System.out.println("way2:\n");
            map2.forEach((k,v)-> System.out.format("[key]:%s \t[value]:%s\n", k, v));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
