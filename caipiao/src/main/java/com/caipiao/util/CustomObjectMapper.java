package com.caipiao.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class CustomObjectMapper extends ObjectMapper{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {  
        // 数字也加引号  
        this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true); 
        // 空值输出 空字符串""
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {  
            @Override  
            public void serialize(  
                    Object value,  
                    JsonGenerator jg,  
                    SerializerProvider sp) throws IOException, JsonProcessingException {  
                jg.writeString("");  
            }  
        });  
    }
	
}
