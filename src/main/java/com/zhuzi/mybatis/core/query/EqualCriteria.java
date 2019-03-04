package com.zhuzi.mybatis.core.query;

public class EqualCriteria extends Criteria {
	private String key;
	private Object value;
	public EqualCriteria(String key, Object value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
