package com.zhuzi.mybatis.core.query;

public class EqualCriteria extends BaseCriteria {
	private Object value;
	public EqualCriteria(String key, Object value) {
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
