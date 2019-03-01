package com.zhuzi.mybatis.test;

import com.google.common.base.CaseFormat;
import com.zhuzi.mybatis.bean.TestBean;

public class DemoTest {
	@org.junit.Test
	public void test() {
		System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,"table_name"));
		System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,"tableName"));
		Class<?> c = TestBean.class;
		System.out.println(c.getFields());
	}
}
