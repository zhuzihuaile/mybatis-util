package com.zhuzi.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TemplateMapper {
	
	Integer insertOne(Map<String, ?> map);
	
	List<Map<String, ?>> select(Map<String, ?> map);

	List<?> select2(Map<String, Object> map);
	
}
