package com.zhuzi.mybatis.constant;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Sort  and limit
 */
public class SortAndLimitConstant {
	/** sort field */
	public final static String SORTBY = "sortby";
	/** asc desc */
	public final static String ORDER = "order";
	public final static String IS_ORDER = "isOrder";
	public final static String OFFSET = "offset";
	public final static String LIMIT = "limit";
	public final static String SORTBY_DEFAULT = "id";
	public final static String ORDER_DEFAULT = "desc";
	public final static int OFFSET_DEFAULT = 0;
	public final static int LIMIT_DEFAULT = 20;
	/** limit max */
	public final static int LIMIT_MAX = 200000;
	public final static int ONE = 1;
	
	public static Map<String, Object> getSelectOne() {
		Map<String, Object> map = Maps.newHashMap();
		map.put(OFFSET, OFFSET_DEFAULT);
		map.put(LIMIT, ONE);
		return map;
	}
	
	public static Map<String, Object> getSelectDefult() {
		Map<String, Object> map = Maps.newHashMap();
		map.put(OFFSET, OFFSET_DEFAULT);
		map.put(LIMIT, LIMIT_DEFAULT);
		return map;
	}
	
	public static Map<String, Object> getSelectBySort(Sort sort) {
		Map<String, Object> map = Maps.newHashMap();
		if(sort == null) {
			return map;
		}
		map.put(OFFSET, sort.getOffset());
		map.put(LIMIT, sort.getLimit());
		map.put(SORTBY, sort.getSortby());
		if(sort.getOrder() !=  null) {
			map.put(ORDER, sort.getOrder().type);
		}
		return map;
	}
	
	public static Sort Sort(int offset, int limit) {
		return  new Sort(offset, limit);
	}
	
	public static Sort Sort(String sortby, Order order) {
		return new Sort(sortby, order);
	}
	
	public static Sort Sort(String sortby, Order order, int offset, int limit) {
		return new Sort(sortby, order, offset, limit);
	}
	
	public static enum Order {
		ASC("asc"),
		DESC("desc");
		String type;
		Order(String type){
			this.type = type;
		}
	}
	
	public static class Sort {
		private String sortby;
		private Order order;
		private Integer offset;
		private Integer limit;
		public Sort() {
			offset = OFFSET_DEFAULT;
			limit = LIMIT_DEFAULT;
		}
		public Sort(String sortby, Order order) {
			super();
			this.sortby = sortby;
			this.order = order;
		}
		public Sort(int offset, int limit) {
			super();
			this.offset = offset;
			this.limit = limit;
		}
		public Sort(String sortby, Order order, int offset, int limit) {
			super();
			this.sortby = sortby;
			this.order = order;
			this.offset = offset;
			this.limit = limit;
		}
		
		public String getSortby() {
			return sortby;
		}
		public void setSortby(String sortby) {
			this.sortby = sortby;
		}
		public Order getOrder() {
			return order;
		}
		public void setOrder(Order order) {
			this.order = order;
		}
		public Integer getOffset() {
			return offset;
		}
		public void setOffset(Integer offset) {
			this.offset = offset;
		}
		public Integer getLimit() {
			return limit;
		}
		public void setLimit(Integer limit) {
			this.limit = limit;
		}
		
	}
}
