package com.zhuzi.mybatis.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhuzi.mybatis.ApplicationTests;
import com.zhuzi.mybatis.bean.TestBean;
import com.zhuzi.mybatis.template.MybatisTemplate;


public class TemplateTest extends ApplicationTests{

	@Autowired
	private MybatisTemplate template;
	
	@Test
	public void insertOne() {
		assertTrue(template.insertOne(new TestBean()));
	}
	
	@Test
	public void selectOne() {
		assertNotNull(template.selectOne(TestBean.class));
	}
}
