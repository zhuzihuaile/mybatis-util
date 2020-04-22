package com.zhuzi.mybatis.template;

import java.util.List;
import java.util.Map;

import com.zhuzi.mybatis.constant.SortAndLimitConstant.Sort;

public interface MybatisTemplate {
	<T> boolean insert(T t);
	
	<T> int delete(T t);
	
	<T> int update(T t);
	
	<T> T selectOne(Class<T> c);
	
	<T> T selectOne(Class<T> c, Sort sort);
	
	<T> T selectOne(T t);
	
	<T> T selectOne(T t, Sort sort);
	
	<T> List<T> select(Class<T> c);
	
	<T> List<T> select(Class<T> c, Sort sort);
	
	<T> List<T> select(T c, Sort sort);
	
	<T> List<T> select(Map<String, Object> where, Class<T> c, Sort sort);
	
	<T> List<T> select(Map<String, Object> map, Class<T> c);

	<T> T selectOne(Map<String, Object> map, Class<T> c);

	/**
	 * 
	 * @param selectObj 查询对象（非集合）
	 * @param sort
	 * @param resultClass
	 * @return
	 */
	<T> List<T> select(Object selectObj, Sort sort, Class<T> resultClass);
	

}
