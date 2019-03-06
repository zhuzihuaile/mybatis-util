package com.zhuzi.mybatis.core.query;

public class NotEqualCriteria extends BaseCriteria {
	private Object value;
	public NotEqualCriteria(String key, Object value) {
		super.key = key;
		this.value = value;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
