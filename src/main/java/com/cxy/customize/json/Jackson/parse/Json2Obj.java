package com.cxy.customize.json.Jackson.parse;



import cn.hutool.core.lang.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * json to object  https://www.mkyong.com/java/jackson-how-to-parse-json/
 */
public class Json2Obj {
    public static void main(String[] args) {
        try {
        ObjectMapper objectMapper = new ObjectMapper();
//        Staff staff = objectMapper.readValue(new File("d:\\test.json"),Staff.class);
//        System.out.println(staff.toString());

        String jsonInString = "{\"name2\":\"cxy\",\"age\":22,\"position\":[\"CTO\",\"MANAGER\"],\"skills\":[\"Java\",\"C\",\"Python\"],\"salary\":{\"2018\":14000,\"2012\":12000,\"2010\":10000}}";

       Staff staff2 = objectMapper.readValue(jsonInString, Staff.class);
       System.out.println(staff2.toString());

       String prettyS2 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff2);
            System.out.println(prettyS2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
