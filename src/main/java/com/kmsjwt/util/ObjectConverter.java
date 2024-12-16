package com.kmsjwt.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ObjectConverter {
	private final ObjectMapper objectMapper = new ObjectMapper();

	public <T> T convertObject(Object sourceObj, Class<T> targerObj) {
		if (sourceObj == null) {
			return null;
		}

		try {
			String jsonObj = objectMapper.writeValueAsString(sourceObj);

			return objectMapper.readValue(jsonObj, targerObj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
