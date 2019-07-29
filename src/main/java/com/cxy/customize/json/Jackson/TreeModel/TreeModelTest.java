package com.cxy.customize.json.Jackson.TreeModel;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 *在json映射java结构不是很nice的情况下，可以使用Tree Model代表json结构,借助JsonNode进行CRUD操作，比发生过XML DOM tree 方便
 *
 *
 */
public class TreeModelTest {
    private static final ObjectMapper mapper  = new ObjectMapper();


    public static void main(String[] args){
        try {
           //读取单个
//            JsonNode root = mapper.readTree(" {\n" +
//                    "\t\"name\": {\n" +
//                    "\t\t\"first\": \"cheng\",\n" +
//                    "\t\t\"last\": \"xinyu\"\n" +
//                    "\t},\n" +
//                    "\t\"age\": 22,\n" +
//                    "\t\"contact\": [{\n" +
//                    "\t\t\"type\": \"phone/home\",\n" +
//                    "\t\t\"ref\": \"110\"\n" +
//                    "\t}, {\n" +
//                    "\t\t\"type\": \"phone/work\",\n" +
//                    "\t\t\"ref\": \"120\"\n" +
//                    "\t}]\n" +
//                    "        }");

            //读取array
            JsonNode rootArray = mapper.readTree(new File("d:\\test.json"));
            for(JsonNode root:rootArray ){
                //获取age
                int age = root.path("age").asInt();
                System.out.println("age:" + age);

                //获取name
                JsonNode nameNode = root.path("name");

                if (!nameNode.isMissingNode()) {//name存在
                    System.out.println("first:" + nameNode.path("first").asText());
                    System.out.println("mid:" + nameNode.path("mid").asText());
                    System.out.println("last:" + nameNode.path("last").asText());
                }
                //获取contact
                JsonNode contactNode = root.path("contact");
                if (contactNode.isArray()) {
                    //turn to ArrayNode
                    System.out.println("Is this node an Array? " + contactNode.isArray());

                    for (JsonNode node : contactNode) {
                        String type = node.path("type").asText();
                        String ref = node.path("ref").asText();
                        System.out.println("type : " + type);
                        System.out.println("ref : " + ref);

                    }
                }
            }



        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
