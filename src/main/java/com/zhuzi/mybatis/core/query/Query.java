package com.zhuzi.mybatis.core.query;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.zhuzi.mybatis.constant.MybatisXmlKeyConstant;

public class Query {
	private Logger log = LoggerFactory.getLogger(getClass());
	private Map<String, Object> equal = null;
	private Map<String, Object> notEqual = null;
	private Map<String, Object> in = null;
	private Map<String, Object> rightLike = null;
	private Map<String, Object> gt = null;
	private Map<String, Object> gte = null;
	private Map<String, Object> lt = null;
	private Map<String, Object> lte = null;
	
	public Query addCriteria(BaseCriteria criteria) {
		if(criteria instanceof EqualCriteria) {
			if(equal == null) {
				equal = Maps.newHashMap();
			}
			EqualCriteria c = (EqualCriteria) criteria;
			equal.put(c.getKey(), c.getValue());
		} else if(criteria instanceof NotEqualCriteria) {
			if(notEqual == null) {
				notEqual = Maps.newHashMap();
			}
			NotEqualCriteria c = (NotEqualCriteria) criteria;
			notEqual.put(c.getKey(), c.getValue());
		} else if(criteria instanceof InCriteria) {
			if(in == null) {
				in = Maps.newHashMap();
			}
			InCriteria c = (InCriteria) criteria;
			in.put(c.getKey(), c.getValue());
		} else if(criteria instanceof RightLikeCriteria) {
			if(rightLike == null) {
				rightLike = Maps.newHashMap();
			}
			RightLikeCriteria c = (RightLikeCriteria) criteria;
			rightLike.put(c.getKey(), c.getValue());
		} else if(criteria instanceof BetweenCriteria) {
			if(gte == null) {
				gte = Maps.newHashMap();
			}
			if(lt == null) {
				lt = Maps.newHashMap();
			}
			BetweenCriteria c = (BetweenCriteria) criteria;
			gte.put(c.getKey(), c.getGte());
			lt.put(c.getKey(), c.getLt());
		} else if(criteria instanceof GtCriteria) {
			if(gt == null) {
				gt = Maps.newHashMap();
			}
			GtCriteria c = (GtCriteria) criteria;
			gt.put(c.getKey(), c.getValue());
		} else if(criteria instanceof GteCriteria) {
			if(gte == null) {
				gte = Maps.newHashMap();
			}
			GteCriteria c = (GteCriteria) criteria;
			gte.put(c.getKey(), c.getValue());
		} else if(criteria instanceof LtCriteria) {
			if(lt == null) {
				lt = Maps.newHashMap();
			}
			LtCriteria c = (LtCriteria) criteria;
			lt.put(c.getKey(), c.getValue());
		} else if(criteria instanceof LteCriteria) {
			if(lte == null) {
				lte = Maps.newHashMap();
			}
			LteCriteria c = (LteCriteria) criteria;
			lte.put(c.getKey(), c.getValue());
		}
		return this;
	}
	
	public Map<String, Object> getWhereMap(){
		Map<String, Object> map = Maps.newHashMap();
		
		if(equal != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_EQUAL_FIELD.getName(), equal);
		}
		
		if(notEqual != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_NOT_EQUAL_FIELD.getName(), notEqual);
		}
		
		if(in != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_IN_FIELD.getName(), in);
		}
		
		if(rightLike != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_RIGHT_LIKE_FIELD.getName(), rightLike);
		}
		
		if(gt != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_GT_FIELD.getName(), gt);
		}
		
		if(gte != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_GTE_FIELD.getName(), gte);
		}
		
		if(lt != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_LT_FIELD.getName(), lt);
		}
		
		if(lte != null) {
			map.put(MybatisXmlKeyConstant.TABLE_WHERE_LTE_FIELD.getName(), lte);
		}
		
		if(map.size() < 1) {
			return null;
		}
		if(log.isDebugEnabled()) {
			log.debug("map where {}", map);
		}
		return map;
	}
}
