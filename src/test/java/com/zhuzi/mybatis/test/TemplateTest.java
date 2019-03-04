package com.zhuzi.mybatis.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.zhuzi.mybatis.ApplicationTests;
import com.zhuzi.mybatis.bean.TestBean;
import com.zhuzi.mybatis.core.query.Criteria;
import com.zhuzi.mybatis.core.query.Query;
import com.zhuzi.mybatis.template.MybatisTemplate;

public class TemplateTest extends ApplicationTests{

	@Autowired
	private MybatisTemplate template;
	
	@Test
	@Transactional
	public void insertOne() {
		TestBean bean = new TestBean();
		bean.setName("name");
		assertTrue(template.insert(bean));
		assertNotNull(bean.getId());
	}
	
	@Test
	@Transactional
	public void update() {
		TestBean bean = new TestBean();
		bean.setName("name");
//		bean.setId(81);
		assertNotEquals(template.update(bean), 0);
	}
	
	@Test
	@Transactional
	public void delete() {
		TestBean bean = new TestBean();
		bean.setName("name");
		bean.setId(81);
		assertNotEquals(template.delete(bean), 0);
	}
	
	@Test
	public void selectOne() {
		TestBean bean = template.selectOne(TestBean.class);
		assertNotNull(bean);
		assertNotNull(bean.getId());
	}
	
	@Test
	public void selectIn() {
		Query query = new Query();
		List<Integer> list = Lists.newArrayList();
		list.add(0);
		list.add(1);
		query.addCriteria(Criteria.in("id", list));
		List<TestBean> beans = template.select(query.getWhereMap(), TestBean.class);
		assertNotNull(beans);
	}
	
	@Test
	public void selectEqual() {
		Query query = new Query();
		query.addCriteria(Criteria.equal("id", 1));
		List<TestBean> beans = template.select(query.getWhereMap(), TestBean.class);
		assertNotNull(beans);
	}
	
	@Test
	public void selectNotEqual() {
		Query query = new Query();
		query.addCriteria(Criteria.notEqual("id", 1));
		List<TestBean> beans = template.select(query.getWhereMap(), TestBean.class);
		assertNotNull(beans);
	}
	
	@Test
	public void selectRightLike() {
		Query query = new Query();
		query.addCriteria(Criteria.rightLike("name", "na"));
		List<TestBean> beans = template.select(query.getWhereMap(), TestBean.class);
		assertNotNull(beans);
	}
	
}
