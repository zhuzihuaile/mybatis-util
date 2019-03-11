package com.zhuzi.mybatis.template.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhuzi.mybatis.constant.MybatisXmlKeyConstant;
import com.zhuzi.mybatis.constant.SortAndLimitConstant;
import com.zhuzi.mybatis.constant.SortAndLimitConstant.Sort;
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
	public <T> List<?> select(Class<T> c) {
		return select(c, null);
	}
	
	@Override
	public <T> List<?> select(Class<T> c, Sort sort) {
		Map<String, Object> map = ClassToMapUtil.getTableMap(c);
		return select(map, c, sort);
	}

	@Override
	public <T> List<T> select(Map<String, Object> map, Class<T> c, SortAndLimitConstant.Sort sort) {
		map.putAll(SortAndLimitConstant.getSelectBySort(sort));
		return select(map, c);
	}

	@Override
	public <T> List<T> select(Map<String, Object> map, Class<T> c) {
		if(map == null || c == null) {
			return null;
		}
		if(map.get(MybatisXmlKeyConstant.TABLE_SELECT_FIELD.getName()) == null) {
			map.put(MybatisXmlKeyConstant.TABLE_SELECT_FIELD.getName(), ClassToMapUtil.getTableSelectField(c));
		}
		if(map.get(MybatisXmlKeyConstant.TABLE_NAME.getName()) == null) {
			map.put(MybatisXmlKeyConstant.TABLE_NAME.getName(), ClassToMapUtil.getTableName(c));
		}
		
		map = sortCheck(map);
		
		List<Map<String, ?>> list = mapper.select(map);
		return MapToBeanUtil.toList(list, c);
	}
	
	private Map<String, Object> sortCheck(Map<String, Object> map) {
		Object limit = map.get(SortAndLimitConstant.LIMIT);
		if(limit == null) {
			map.putAll(SortAndLimitConstant.getSelectDefult());
		} else {
			try {
				int l = (int) limit;
				if(l > SortAndLimitConstant.LIMIT_MAX) {
					map.put(SortAndLimitConstant.LIMIT, SortAndLimitConstant.LIMIT_MAX);
				} else if(l < SortAndLimitConstant.ONE) {
					map.put(SortAndLimitConstant.LIMIT, SortAndLimitConstant.LIMIT_DEFAULT);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				map.put(SortAndLimitConstant.LIMIT, SortAndLimitConstant.LIMIT_DEFAULT);
			}
		}
		
		Object offset = map.get(SortAndLimitConstant.OFFSET);
		if(limit != null) {
			try {
				int o = (int) offset;
				if(o < SortAndLimitConstant.OFFSET_DEFAULT) {
					map.put(SortAndLimitConstant.OFFSET, SortAndLimitConstant.OFFSET_DEFAULT);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				map.put(SortAndLimitConstant.OFFSET, SortAndLimitConstant.OFFSET_DEFAULT);
			}
		}
		
		return map;
	}

	@Override
	public <T> T selectOne(Class<T> c) {
		return selectOne(null, c, null);
	}
	
	@Override
	public <T> T selectOne(Class<T> c, Sort sort) {
		return selectOne(null, c, sort);
	}
	
	@Override
	public <T> T selectOne(T t, Class<T> c) {
		return selectOne(t, c, null);
	}
	
	@Override
	public <T> T selectOne(T t, Class<T> c, Sort sort) {
		Map<String, Object> map;
		if(t == null) {
			map = ClassToMapUtil.getTableMap(c);
		} else {
			map = ClassToMapUtil.getSelectMap(t);
		}
		map.putAll(SortAndLimitConstant.getSelectBySort(sort));
		map.putAll(SortAndLimitConstant.getSelectOne());
		List<T> list = select(map, c);
		if(list == null || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}
}
