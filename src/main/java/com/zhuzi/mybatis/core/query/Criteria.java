package com.zhuzi.mybatis.core.query;

import java.util.List;

public class Criteria {
	
	public static EqualCriteria equal(String key, Object value) {
		return new EqualCriteria(key, value);
	}
	
	public static NotEqualCriteria notEqual(String key, Object value) {
		return new NotEqualCriteria(key, value);
	}
	
	public static InCriteria in(String key, List<?> value) {
		return new InCriteria(key, value);
	}
	
	public static RightLikeCriteria rightLike(String key, Object value) {
		return new RightLikeCriteria(key, value);
	}
	
	public static BetweenCriteria between(String key, Object gte, Object lt) {
		return new BetweenCriteria(key, gte, lt);
	}
	
	public static GtCriteria gt(String key, Object value) {
		return new GtCriteria(key, value);
	}
	
	public static GteCriteria gte(String key, Object value) {
		return new GteCriteria(key, value);
	}
	
	public static LtCriteria lt(String key, Object value) {
		return new LtCriteria(key, value);
	}
	
	public static LteCriteria lte(String key, Object value) {
		return new LteCriteria(key, value);
	}
}
