package com.zhuzi.mybatis.template.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhuzi.mybatis.constant.MybatisXmlKeyConstant;
import com.zhuzi.mybatis.constant.SortAndLimitConstant;
import com.zhuzi.mybatis.mapper.TemplateMapper;
import com.zhuzi.mybatis.template.MybatisTemplate;
import com.zhuzi.mybatis.util.ClassToMapUtil;
import com.zhuzi.mybatis.util.MapToBeanUtil;
import com.zhuzi.mybatis.util.ReflectMathodUtil;

@Component
public class MybatisTemplateImpl implements MybatisTemplate{
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private TemplateMapper mapper;
	
	@Override
	public <T> boolean insert(T t) {
		Map<String, ?> map = ClassToMapUtil.getInsertMap(t);
		Integer count = mapper.insertOne(map);
		if(count != null && count > 0) {
			String name = ReflectMathodUtil.getGeneratedName(t.getClass());
			if(name != null) {
				Object obj = mapper.lastInsertID();
				ReflectMathodUtil.setValue(t, name, obj);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public <T> int delete(T t) {
		Map<String, ?> map = ClassToMapUtil.getDleteMap(t);
		if(map == null) {
			log.warn("sql delete warn, where must exist!");
			return 0;
		}
		Integer count = mapper.delete(map);
		if(count != null) {
			return count;
		}
		return 0;
	}
	
	@Override
	public <T> int update(T t) {
		Map<String, ?> map = ClassToMapUtil.getUpdateMap(t);
		if(map == null) {
			log.warn("sql update warn, where must exist!");
			return 0;
		}
		Integer count = mapper.update(map);
		if(count != null) {
			return count;
		}
		return 0;
	}
	
	@Override
	public <T> T selectOne(T t, Class<T> c) {
		Map<String, Object> map;
		if(t == null) {
			map = ClassToMapUtil.getTableMap(c);
		} else {
			map = ClassToMapUtil.getSelectMap(t);
		}
		map.putAll(SortAndLimitConstant.getSelectOne());
		List<T> list = select(map, c);
		if(list == null || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}
	
	@Override
	public <T> T selectOne(Class<T> c) {
		return selectOne(null, c);
	}
	
	@Override
	public <T> List<?> select(Class<T> c) {
		Map<String, Object> map = ClassToMapUtil.getInsertMap(c);
		return select(map, c);
	}
	
	@Override
	public <T> List<T> select(Map<String, Object> map, Class<T> c) {
		if(map.get(MybatisXmlKeyConstant.TABLE_SELECT_FIELD.getName()) == null) {
			map.put(MybatisXmlKeyConstant.TABLE_SELECT_FIELD.getName(), ClassToMapUtil.getTableSelectField(c));
		}
		List<Map<String, ?>> list = mapper.select(map);
		return MapToBeanUtil.toList(list, c);
	}

	
}
