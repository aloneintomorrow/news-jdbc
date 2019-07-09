package com.laptrinhjavaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> tClazz, HttpServletRequest request) {
		T object = null;
		try {
			 object = tClazz.newInstance();
			 BeanUtils.populate(object, request.getParameterMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
