package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class HttpUtil {
	String value;
	
	public HttpUtil(String value) {
		this.value = value;
	}
	
	/*public String of (BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while((line = reader.readLine())!= null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
}*/
	public static HttpUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while((line = reader.readLine())!= null) {
				sb.append(line);
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HttpUtil(sb.toString());
	}
	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
