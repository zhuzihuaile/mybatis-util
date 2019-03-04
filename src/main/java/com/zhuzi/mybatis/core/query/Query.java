package com.zhuzi.mybatis.core.query;

import java.util.Map;

import com.google.common.collect.Maps;
import com.zhuzi.mybatis.constant.MybatisXmlKeyConstant;

public class Query {
	private Map<String, Object> equal = null;
	private Map<String, Object> notEqual = null;
	private Map<String, Object> in = null;
	private Map<String, Object> rightLike = null;
	
	public Query addCriteria(Criteria criteria) {
		if(criteria instanceof EqualCriteria) {
			if(equal == null) {
				equal = Maps.newHashMap();
			}
			EqualCriteria c = (EqualCriteria) criteria;
			equal.put(c.getKey(), c.getValue());
		} else if(criteria instanceof NotEqualCriteria) {
			if(notEqual == null) {
				notEqual = Maps.newHashMap();
			}
			NotEqualCriteria c = (NotEqualCriteria) criteria;
			notEqual.put(c.getKey(), c.getValue());
		} else if(criteria instanceof InCriteria) {
			if(in == null) {
				in = Maps.newHashMap();
			}
			InCriteria c = (InCriteria) criteria;
			in.put(c.getKey(), c.getValue());
		} else if(criteria instanceof RightLikeCriteria) {
			if(rightLike == null) {
				rightLike = Maps.newHashMap();
			}
			RightLikeCriteria c = (RightLikeCriteria) criteria;
			rightLike.put(c.getKey(), c.getValue());
		}
		return this;
	}
	
	public Map<String, Object> getWhereMap(){
		Map<String, Object> map = Maps.newHashMap();
		
		if(equal != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_EQUAL_FIELD.getName(), equal);
		}
		
		if(notEqual != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_NOT_EQUAL_FIELD.getName(), notEqual);
		}
		
		if(in != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_IN_FIELD.getName(), in);
		}
		
		if(rightLike != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_RIGHT_LIKE_FIELD.getName(), rightLike);
		}
		
		if(map.size() < 1) {
			return null;
		}
		return map;
	}
}
