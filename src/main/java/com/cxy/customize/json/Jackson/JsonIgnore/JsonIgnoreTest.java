package com.cxy.customize.json.Jackson.JsonIgnore;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonIgnoreTest {
    public static void main(String[] args) {
    try{
        ObjectMapper objectMapper = new ObjectMapper();
        //enable pretty print
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonInString = "{\"name\":\"cxy\",\"age\":22,\"position\":[\"CTO\",\"MANAGER\"],\"skills\":[\"Java\",\"C\",\"Python\"],\"salary\":{\"2018\":14000,\"2012\":12000,\"2010\":10000}}";

        Staff staff = objectMapper.readValue(jsonInString,Staff.class);
        String string = objectMapper.writeValueAsString(staff);
        System.out.println(string);
        /**
         * 成功忽略了position,salary字段
         {
         "name" : "cxy",
         "age" : 22,
         "skills" : [ "Java", "C", "Python" ]
         }
         */
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
