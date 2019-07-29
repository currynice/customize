package com.cxy.customize.json.Jackson.JacksonStream;


import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * 过时方法
 * 借助JsonGnerator 生成json
 */
@Deprecated
public class WriteTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args){
        try (JsonGenerator jGenerator =
                     mapper.getFactory().createGenerator(
                             new File("d:\\user2.json")
                             , JsonEncoding.UTF8)) {

            // pretty print
            jGenerator.useDefaultPrettyPrinter();

            // start array
            jGenerator.writeStartArray();                                   // [

            jGenerator.writeStartObject();                                  // {

            jGenerator.writeStringField("name", "mkyong");  				// "name" : "mkyong"
            jGenerator.writeNumberField("age", 38);         				// "age" : 38

            jGenerator.writeFieldName("messages");                          // "messages" :

            jGenerator.writeStartArray();                                   // [

            jGenerator.writeString("msg 1");                            	// "msg 1"
            jGenerator.writeString("msg 2");                            	// "msg 2"
            jGenerator.writeString("msg 3");                            	// "msg 3"

            jGenerator.writeEndArray();                                     // ]

            jGenerator.writeEndObject();                                    // }

            // next object, pls

            jGenerator.writeStartObject();                                  // {

            jGenerator.writeStringField("name", "lap");  					// "name" : "lap"
            jGenerator.writeNumberField("age", 5);         					// "age" : 5

            jGenerator.writeFieldName("messages");                          // "messages" :

            jGenerator.writeStartArray();                                   // [

            jGenerator.writeString("msg a");                            	// "msg a"
            jGenerator.writeString("msg b");                            	// "msg b"
            jGenerator.writeString("msg c");                            	// "msg c"

            jGenerator.writeEndArray();                                     // ]

            jGenerator.writeEndObject();                                    // }

            jGenerator.writeEndArray();                                     // ]

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
