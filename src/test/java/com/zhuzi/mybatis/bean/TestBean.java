package com.zhuzi.mybatis.bean;

import com.zhuzi.mybatis.annotation.TableName;

@TableName(name="t_test")
public class TestBean {
	private Integer id;
	private String testName;
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "TestBean [id=" + id + ", testName=" + testName + "]";
	}
}
