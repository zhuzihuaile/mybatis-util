package com.zhuzi.mybatis.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
		bean.setTestName("testName");
		bean.setCreateTime(new Date());
		assertTrue(template.insert(bean));
		assertNotNull(bean.getId());
	}
	
	@Test
	@Transactional
	public void update() {
		TestBean bean = new TestBean();
		bean.setName("name");
//		bean.setId(1);
		assertNotEquals(template.update(bean), 0);
	}
	
	@Test
	@Transactional
	public void delete() {
		TestBean bean = new TestBean();
		bean.setName("name");
		bean.setId(1);
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
		query.addCriteria(Criteria.equal("name", "name"));
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
		query.addCriteria(Criteria.rightLike("test_name", "test"));
		List<TestBean> beans = template.select(query.getWhereMap(), TestBean.class);
		assertNotNull(beans);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void selectBetween() {
		Query query = new Query();
		query.addCriteria(Criteria.between("create_time", new Date(118, 01, 01), new Date(120, 01, 01)));
		Map<String, Object> map = query.getWhereMap();
		List<TestBean> beans = template.select(map, TestBean.class);
		System.out.println(beans);
		assertNotNull(beans);
	}
	
}
