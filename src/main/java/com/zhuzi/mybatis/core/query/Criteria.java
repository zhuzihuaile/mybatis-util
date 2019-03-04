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
	
}
