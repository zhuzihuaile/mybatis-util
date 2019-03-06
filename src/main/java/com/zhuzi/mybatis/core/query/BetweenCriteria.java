package com.zhuzi.mybatis.core.query;

public class BetweenCriteria extends BaseCriteria {
	private Object gte;
	private Object lt;
	public BetweenCriteria(String key, Object gte, Object lt) {
		super.key = key;
		this.gte = gte;
		this.lt = lt;
	}
	public Object getGte() {
		return gte;
	}
	public void setGte(Object gte) {
		this.gte = gte;
	}
	public Object getLt() {
		return lt;
	}
	public void setLt(Object lt) {
		this.lt = lt;
	}
	
}
