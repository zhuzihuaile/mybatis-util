package com.zhuzi.mybatis.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.zhuzi.mybatis.annotation.GeneratedValue;

public class ReflectMathodUtil {
	private ReflectMathodUtil() {}

	public static Object getValue(Object obj, String name) {
		try {
			if (name == null || "".equals(name.trim())) {
				return null;
			}
			Method method = obj.getClass().getMethod(getGetMethodName(name));
			return method.invoke(obj);
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
	 * 获取当前类的自增字段名
	 * @param c
	 * @return
	 */
	public static <T> String getGeneratedName(Class<T> c) {
		Field[] fields = c.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				GeneratedValue id = field.getAnnotation(GeneratedValue.class);
				if(id != null) {
					String name = field.getName();
					return name;
				}
			}
		}
		return null;
	}
	
	public static <T> boolean setValue(T t, String name, Object obj) {
		try {
			if (name == null || "".equals(name.trim())) {
				return false;
			}
			BeanUtilsBean.getInstance().setProperty(t, name, obj);
			return true;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String getSetMethodName(String name) {
		return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	/**
	 * 根据属性名获取get方法名
	 */
	public static String getGetMethodName(String name) {
		return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
	}
}
