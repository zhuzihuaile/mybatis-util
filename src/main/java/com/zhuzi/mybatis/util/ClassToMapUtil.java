package com.zhuzi.mybatis.util;

import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import com.zhuzi.mybatis.annotation.TableName;
import com.zhuzi.mybatis.constant.MybatisXmlKeyConstant;

public class ClassToMapUtil {
	private static Logger log = LoggerFactory.getLogger(ClassToMapUtil.class);

	private ClassToMapUtil() {
	}

	public static <T> Map<String, Object> getInsertMap(T t) {
		Map<String, Object> map = Maps.newHashMap();
		Class<?> c = t.getClass();
		TableName table = c.getAnnotation(TableName.class);
		if (table == null) {
			return null;
		}
		map.put(MybatisXmlKeyConstant.TABLE_NAME.getName(), table.name());
		Field[] fields = c.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				String name = field.getName();
				Object object = ReflectMathodUtil.getValue(c, name);
				if (object == null) {
					continue;
				}
				map.put(name, object);
			}
		}
		return map;
	}

	public static <T> Map<String, Object> getInsertMap(Class<T> c) {
		Map<String, Object> map = Maps.newHashMap();
		TableName table = c.getAnnotation(TableName.class);
		if (table == null) {
			return null;
		}
		map.put(MybatisXmlKeyConstant.TABLE_NAME.getName(), table.name());
		return map;
	}

	public static String getTableSelectField(Class<?> c) {
		StringBuilder sb = new StringBuilder("");
		Field[] fields = c.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				String name = field.getName();
				sb.append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name));
				sb.append(",");
			}
		} else {
			return null;
		}
		String name = sb.toString();
		log.info(name);
		return name.substring(0, name.length() - 1);
	}
}
