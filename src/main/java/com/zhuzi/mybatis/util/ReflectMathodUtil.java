package com.zhuzi.mybatis.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectMathodUtil {
	private ReflectMathodUtil() {}

	public static <T> Object getValue(T t, String name) {
		try {
			if (name == null || "".equals(name.trim())) {
				return null;
			}
			Method method = t.getClass().getMethod(getGetMethodName(name));
			return method.invoke(t);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据属性名获取get方法名
	 */
	public static String getGetMethodName(String name) {
		return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}
}
