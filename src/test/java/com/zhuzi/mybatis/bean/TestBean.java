package com.zhuzi.mybatis.bean;

import java.util.Date;

import com.zhuzi.mybatis.annotation.GeneratedValue;
import com.zhuzi.mybatis.annotation.TableName;

@TableName(name="test")
public class TestBean {
	@GeneratedValue
	private Integer id;
	private String name;
	private String testName;
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "TestBean [id=" + id + ", name=" + name + ", testName=" + testName + ", createTime=" + createTime + "]";
	}
	
}
