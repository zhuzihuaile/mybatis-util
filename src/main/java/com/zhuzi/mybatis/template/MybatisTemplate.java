package com.zhuzi.mybatis.template;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhuzi.mybatis.constant.MybatisXmlKeyConstant;
import com.zhuzi.mybatis.constant.SortAndLimitConstant;
import com.zhuzi.mybatis.mapper.TemplateMapper;
import com.zhuzi.mybatis.util.ClassToMapUtil;
import com.zhuzi.mybatis.util.MapToBeanUtil;

@Component
public class MybatisTemplate {
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private TemplateMapper mapper;
	
	public <T> boolean insertOne(T t) {
		Map<String, ?> map = ClassToMapUtil.getInsertMap(t);
		Integer count = mapper.insertOne(map);
		if(count != null && count > 0) {
			return true;
		}
		return false;
	}
	
	public <T> T selectOne(T t, Class<T> c) {
		Map<String, Object> map;
		if(t == null) {
			map = ClassToMapUtil.getInsertMap(c);
		} else {
			map = ClassToMapUtil.getInsertMap(t);
		}
		map.putAll(SortAndLimitConstant.getSelectOne());
		List<T> list = select(map, c);
		if(list == null || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}
	
	public <T> T selectOne(Class<T> c) {
		return selectOne(null, c);
	}
	
	public <T> List<?> select(Class<T> c) {
		Map<String, Object> map = ClassToMapUtil.getInsertMap(c);
		return select(map, c);
	}
	
	public <T> List<T> select(Map<String, Object> map, Class<T> c) {
		if(map.get(MybatisXmlKeyConstant.TABLE_SELECT_FIELD.getName()) == null) {
			map.put(MybatisXmlKeyConstant.TABLE_SELECT_FIELD.getName(), ClassToMapUtil.getTableSelectField(c));
		}
		List<Map<String, ?>> list = mapper.select(map);
		return MapToBeanUtil.toList(list, c);
	}
}
