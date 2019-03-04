package com.zhuzi.mybatis.core.query;

import java.util.List;

public class InCriteria extends Criteria {
	private String key;
	private List<?> value;
	public InCriteria(String key, List<?> value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<?> getValue() {
		return value;
	}
	public void setValue(List<?> value) {
		this.value = value;
	}
	
}
