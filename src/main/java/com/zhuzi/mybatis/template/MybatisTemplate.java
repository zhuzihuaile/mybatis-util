package com.zhuzi.mybatis.template;

import java.util.List;
import java.util.Map;

public interface MybatisTemplate {
	<T> boolean insert(T t);
	
	<T> int delete(T t);
	
	<T> int update(T t);
	
	<T> T selectOne(T t, Class<T> c);
	
	<T> T selectOne(Class<T> c);
	
	<T> List<?> select(Class<T> c);
	
	<T> List<T> select(Map<String, Object> map, Class<T> c);
}
