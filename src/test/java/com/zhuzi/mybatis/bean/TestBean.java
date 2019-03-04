package com.zhuzi.mybatis.bean;

import com.zhuzi.mybatis.annotation.GeneratedValue;
import com.zhuzi.mybatis.annotation.TableName;

@TableName(name="test")
public class TestBean {
	@GeneratedValue
	private Integer id;
	private String name;
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
	@Override
	public String toString() {
		return "TestBean [id=" + id + ", name=" + name + "]";
	}
	
}
