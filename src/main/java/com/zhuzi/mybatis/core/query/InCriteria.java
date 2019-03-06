package com.zhuzi.mybatis.core.query;

import java.util.List;

public class InCriteria extends BaseCriteria {
	private List<?> value;
	public InCriteria(String key, List<?> value) {
		super.key = key;
		this.value = value;
	}
	public List<?> getValue() {
		return value;
	}
	public void setValue(List<?> value) {
		this.value = value;
	}
	
}
