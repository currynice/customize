package com.cxy.customize.json.Jackson.parse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ArrayStr2ListTets {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //json数组
            String jsonArrayStr = "[{\"name\":\"cxy\",\"age\":22,\"position\":[\"CTO\",\"MANAGER\"],\"skills\":[\"Java\",\"C\",\"Python\"],\"salary\":{\"2018\":14000,\"2012\":12000,\"2010\":10000}},{\"name\":\"cxy2\",\"age\":23,\"position\":[\"CEO\",\"BOSS\"],\"skills\":[\"Java\",\"Python\"],\"salary\":{\"2018\":90000,\"2012\":12000,\"2010\":10000}}]";
            //way1 : json -> Staff[] ->List<Staff>
            List<Staff> list = Arrays.asList(objectMapper.readValue(jsonArrayStr, Staff[].class));
            //way2: json -> List<Staff>
            List<Staff> list2 = objectMapper.readValue(jsonArrayStr, new TypeReference<List<Staff>>() {});
            System.out.println("way1:\n");
            list.forEach(System.out::println);
            System.out.println("way2:\n");
            list2.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
