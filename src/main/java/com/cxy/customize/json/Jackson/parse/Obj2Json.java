package com.cxy.customize.json.Jackson.parse;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Java Object to json
 */
public class Obj2Json {

    public static void main(String[] args) {
        Staff staff = createStaff();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Java objects to JSON file
            //{"name":"cxy","age":22,"position":["CTO","MANAGER"],"skills":["Java","C","Python"],"salary":{"2018":14000,"2012":12000,"2010":10000}}
            objectMapper.writeValue(new File("d:\\test.json"), staff);

            //compact-print 紧凑
            //{"name":"cxy","age":22,"position":["CTO","MANAGER"],"skills":["Java","C","Python"],"salary":{"2018":14000,"2012":12000,"2010":10000}}
            String jsonString = objectMapper.writeValueAsString(staff);
            System.out.println(jsonString);

            //pretty-print 美观
            //{
            //  "name" : "cxy",
            //  "age" : 22,
            //  "position" : [ "CTO", "MANAGER" ],
            //  "skills" : [ "Java", "C", "Python" ],
            //  "salary" : {
            //    "2018" : 14000,
            //    "2012" : 12000,
            //    "2010" : 10000
            //  }
            //}
            String jsonPrettyString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
            System.out.println(jsonPrettyString);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static Staff createStaff(){
        Staff staff = new Staff();
        staff.setAge(22);
        staff.setName("cxy");
        staff.setPosition(new String[]{"CTO","MANAGER"});
        Map<String, BigDecimal> salary = new HashMap();
        salary.put("2010", new BigDecimal(10000));
        salary.put("2012", new BigDecimal(12000));
        salary.put("2018", new BigDecimal(14000));
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("Java","C","Python"));
        return staff;
    }
}
