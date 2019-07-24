package com.cxy.customize.json.Jackson.JsonView;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 限制不同用户的字段显示
 */
public class JsonViewTest {
    public static void main(String[] args) {
    try{
        ObjectMapper objectMapper = new ObjectMapper();
        //让pretty print 生效
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Staff staff =createStaff();

        //Normal
        String normalView = objectMapper.writerWithView(CompanyViews.Normal.class).writeValueAsString(staff);
        System.out.format("Normal view\n %s",normalView);
        System.out.println("\n");
        //Manager
        String managerView = objectMapper.writerWithView(CompanyViews.Manager.class).writeValueAsString(staff);
        System.out.format("Manager view\n %s",managerView);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    private  static Staff createStaff(){
        Staff staff = new Staff();
        staff.setAge(20);
        staff.setName("cxy");
        staff.setPosition(new String[]{"Founder", "CTO", "Writer"});
        Map<String, BigDecimal> salary = new HashMap() {{
            put("2010", new BigDecimal(10000));
            put("2012", new BigDecimal(12000));
            put("2018", new BigDecimal(14000));
        }};
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));
        return staff;
    }
}
