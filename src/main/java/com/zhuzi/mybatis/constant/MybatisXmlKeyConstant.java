package com.zhuzi.mybatis.constant;

public enum MybatisXmlKeyConstant {
	/** 表名 */
	TABLE_NAME("table_name", "表名")
	/** 插入字段 */
	,TABLE_INSERT_FIELD("insert_field", "插入字段")
	/** 查询字段:结果 */
	,TABLE_SELECT_FIELD("select_field", "查询字段:结果")
	/** 查询字段:等于 */
	,TABLE_WHERE_EQUAL_FIELD("where_equal_field", "查询字段:等于")
	/** set字段  */
	,TABLE_UPDATE_SET_FIELD("update_set_field", "set字段")
	/** 加载查询字段 */
	,INCLUDE_SELECT_FIELD("includeSelectField", "加载查询字段")
	;

	private String name;
	private String desc;

	private MybatisXmlKeyConstant(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
