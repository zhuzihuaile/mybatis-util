package com.zhuzi.mybatis.annotation.query;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD})
@Documented
public @interface BeanField {
	/**
	 * bean对象参数名称
	 * @return
	 */
	String fieldName();
}
