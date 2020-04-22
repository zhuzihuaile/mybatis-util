package com.zhuzi.mybatis.util;

import java.awt.List;
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
		map.put(MybatisXmlKeyConstant.TABLE_NAME.getName(), getTableName(t.getClass()));
		map.put(MybatisXmlKeyConstant.TABLE_INSERT_FIELD.getName(), getFieldMap(t));
		return map;
	}
	
	public static <T> Map<String, Object> getDleteMap(T t) {
		Map<String, Object> map = Maps.newHashMap();
		
		Map<String, Object> fieldMap = getWhereMap(t, true);
		if(fieldMap == null || fieldMap.size() < 1) {
			return null;
		}
		map.putAll(fieldMap);
		map.put(MybatisXmlKeyConstant.TABLE_NAME.getName(), getTableName(t.getClass()));
		
		return map;
	}
	
	public static <T> Map<String, Object> getSelectMap(T t) {
		Map<String, Object> map = Maps.newHashMap();
		map.put(MybatisXmlKeyConstant.TABLE_NAME.getName(), getTableName(t.getClass()));
		map.putAll(getWhereMap(t, false));
		return map;
	}
	
	public static <T> Map<String, Object> getWhereMap(T t, boolean exist) {
		Map<String, Object> map = Maps.newHashMap();
		boolean flag = exist;
		
		Map<String, Object> fieldMap = getFieldMap(t);
		if(fieldMap.size() > 0) {
			flag = false;
		}
		
		Map<String, Object> equal = Maps.newHashMap();
		Map<String, Object> in = Maps.newHashMap();
		for(String key : fieldMap.keySet()) {
			Object v = fieldMap.get(key);
			// 判断是否为List
			if(List.class.isAssignableFrom(v.getClass())) {
				in.put(key, v);
			} else {
				equal.put(key, v);
			}
		}
		map.put(MybatisXmlKeyConstant.TABLE_WHERE_EQUAL_FIELD.getName(), equal);
		map.put(MybatisXmlKeyConstant.TABLE_WHERE_IN_FIELD.getName(), in);
		
		if(flag) {
			return null;
		}
		return map;
	}

	/**
	 * 获取对象中非Null的字段
	 * @param t
	 * @return
	 */
	public static <T> Map<String, Object> getFieldMap(T t) {
		Map<String, Object> map = Maps.newHashMap();
		if(t == null) {
			return map;
		}
		
		Class<?> c = t.getClass();
		if(c.isEnum() || c.isAnnotation() || c.isArray()) {
			log.warn("param is error");
			return map;
		}
		Field[] fields = c.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				String name = field.getName();
				Object object = ReflectMathodUtil.getValue(t, name);
				if (object == null) {
					continue;
				}
				map.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name), object);
			}
		}
		return map;
	}

	public static Map<String, Object> getTableMap(Class<?> c) {
		Map<String, Object> map = Maps.newHashMap();
		String tableName = getTableName(c);
		if (tableName == null) {
			return null;
		}
		map.put(MybatisXmlKeyConstant.TABLE_NAME.getName(), tableName);
		return map;
	}
	
	public static String getTableName(Class<?> c) {
		TableName table = c.getAnnotation(TableName.class);
		if (table == null) {
			return null;
		}
		return table.name();
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

	public static <T> Map<String, Object> getUpdateMap(T t) {
		Map<String, Object> map = Maps.newHashMap();
		
		String name = ReflectMathodUtil.getGeneratedName(t.getClass());
		if(name == null) {
			return null;
		}
		
		Map<String, Object> fieldMap = getFieldMap(t);
		if(fieldMap.get(name) == null) {
			return null;
		}
		
		Map<String, Object> equal = Maps.newHashMap();
		equal.put(name, fieldMap.get(name));
		map.put(MybatisXmlKeyConstant.TABLE_WHERE_EQUAL_FIELD.getName(), equal);;
		
		fieldMap.remove(name);
		if(fieldMap.size() < 1) {
			return null;
		}
		map.put(MybatisXmlKeyConstant.TABLE_UPDATE_SET_FIELD.getName(), fieldMap);;
		
		map.put(MybatisXmlKeyConstant.TABLE_NAME.getName(), getTableName(t.getClass()));
		
		return map;
	}
}
