package com.cxy.customize.json.Jackson.JsonProperty;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonPropertyTest {
    public static void main(String[] args) {
    try{
        ObjectMapper objectMapper = new ObjectMapper();
        Staff staff = new Staff();
        staff.setAge(20);
        staff.setName("cxy");
        String jsonStr = objectMapper.writeValueAsString(staff);
        System.out.println(jsonStr);//{"age":20,"name2":"cxy"}
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
