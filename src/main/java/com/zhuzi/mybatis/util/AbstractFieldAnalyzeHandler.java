package com.zhuzi.mybatis.util;

public interface AbstractFieldAnalyzeHandler<T> {
	T handler(Object str);
	
	/**
	 * 警告：存储只支持字符串
	 * @param t
	 * @return
	 */
	String rehandler(Object t);
}

