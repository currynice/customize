package com.cxy.customize.json.Jackson.TreeModel;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.io.IOException;

public class TreeModelCRUD {
    private static final ObjectMapper mapper  = new ObjectMapper();
    public static void main(String[] args) {

        try {
            //读取单个
            JsonNode root = mapper.readTree(" {\n" +
                    "\t\"name\": {\n" +
                    "\t\t\"first\": \"cheng\",\n" +
                    "\t\t\"last\": \"xinyu\"\n" +
                    "\t},\n" +
                    "\t\"age\": 22,\n" +
                    "\t\"contact\": [{\n" +
                    "\t\t\"type\": \"phone/home\",\n" +
                    "\t\t\"ref\": \"110\"\n" +
                    "\t}, {\n" +
                    "\t\t\"type\": \"phone/work\",\n" +
                    "\t\t\"ref\": \"120\"\n" +
                    "\t}]\n" +
                    "        }");

            //before crud
            String before = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
            System.out.println(before);


            //update age 22->30
            ((ObjectNode)root).put("age",30);


            //if mid:""  ,update mid = "---"
            ObjectNode nameNode = (ObjectNode)root.path("name");
            if("".equals(nameNode.path("mid").asText())){
                nameNode.put("mid","---");
            }
            //remove last
            nameNode.remove("last");

            //create new ObjectNode add to root
            ObjectNode positionNode = mapper.createObjectNode();
            positionNode.put("name","developer");
            positionNode.put("level",6);
            ((ObjectNode) root).set("position",positionNode);



            //create new ArrayNode add to root
            ArrayNode skillNode = mapper.createArrayNode();


            ObjectNode skill1 = mapper.createObjectNode().objectNode();
            skill1.put("name", "Java");
            skill1.put("degree", 2);

            ObjectNode skill2 = mapper.createObjectNode().objectNode();
            skill2.put("name", "C");
            skill2.put("degree", 5);

            skillNode.add(skill1);
            skillNode.add(skill2);
            ((ObjectNode) root).set("skill",skillNode);


            //append new field to contactNode(ArrayNode)
            ArrayNode contactNode = (ArrayNode)root.path("contact");
            ObjectNode email = mapper.createObjectNode().objectNode();
            email.put("type", "email");
            email.put("ref", "1@qq.com");
            contactNode.add(email);


            String afterCrud = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
            System.out.println("after\n"+afterCrud);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
