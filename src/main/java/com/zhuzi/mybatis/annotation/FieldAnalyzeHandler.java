package com.zhuzi.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zhuzi.mybatis.util.AbstractFieldAnalyzeHandler;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface FieldAnalyzeHandler {
	Class<? extends AbstractFieldAnalyzeHandler<?>> handler();
}
