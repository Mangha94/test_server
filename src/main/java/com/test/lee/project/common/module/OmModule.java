package com.test.lee.project.common.module;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * Object Mapper 모듈
 *
 * Created by RED on 2016-05-11.
 */
@Component
public class OmModule
{
	private ObjectMapper om;

	@PostConstruct
	void init ()
	{
		om = new ObjectMapper ();
		om.configure (SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		om.setSerializationInclusion (JsonInclude.Include.NON_NULL);
	}

	public <T> T readValue(String content, Class<T> valueType)
	{
		try
		{
			return om.readValue (content, valueType);
		}
		catch (IOException e)
		{
			return null;
		}
	}

	public <T> T readValue(String content, TypeReference valueType)
	{
		try
		{
			return om.readValue (content, valueType);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public Map<String, Object> convertToMap (Object obj){
		return (Map<String,Object>) om.convertValue(obj, Map.class);
	}

	public String writeValueAsString (Object obj)
	{
		try
		{
			return om.writeValueAsString (obj);
		}
		catch (IOException e)
		{
			return "";
		}
	}
}
