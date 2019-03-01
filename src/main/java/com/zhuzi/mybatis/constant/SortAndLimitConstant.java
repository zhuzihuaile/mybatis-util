package com.zhuzi.mybatis.constant;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Sort  and limit
 */
public class SortAndLimitConstant {
	public final static String SORTBY = "sortby";
	public final static String ORDER = "order";
	public final static String IS_ORDER = "isOrder";
	public final static String OFFSET = "offset";
	public final static String LIMIT = "limit";
	public final static String SORTBY_DEFAULT = "id";
	public final static String ORDER_DEFAULT = "desc";
	public final static int OFFSET_DEFAULT = 0;
	public final static int LIMIT_DEFAULT = 20;
	public final static int ONE = 1;
	
	public static Map<String, Object> getSelectOne() {
		Map<String, Object> map = Maps.newHashMap();
		map.put(OFFSET, OFFSET_DEFAULT);
		map.put(LIMIT, ONE);
		return map;
	}
}
