package com.zhuzi.mybatis.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;

public class MapToBeanUtil {
	private static Logger log = LoggerFactory.getLogger(MapToBeanUtil.class);

	private MapToBeanUtil() {
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(List<Map<String, ?>> list, Class<T> c) {
		List<T> lists = Lists.newArrayList();
		Object t = null;
		for (Map<String, ?> map : list) {
			try {
				t = c.newInstance();
				populate(t, map);
			} catch (Exception e) {
				log.warn(e.getMessage());
				t = null;
			}
			if(t == null) {
				continue;
			}
			lists.add((T) t);
		}
		return lists;
	}

	public static void populate(final Object bean, final Map<String, ? extends Object> properties)
	        throws IllegalAccessException, InvocationTargetException {
	        if ((bean == null) || (properties == null)) {
	            return;
	        }
	        if (log.isDebugEnabled()) {
	            log.debug("BeanUtils.populate(" + bean + ", " +
	                    properties + ")");
	        }

	        for(final Map.Entry<String, ? extends Object> entry : properties.entrySet()) {
	            // Identify the property name and value(s) to be assigned
	            final String name = entry.getKey();
	            if (name == null) {
	                continue;
	            }
	            String nameLower = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
	            BeanUtilsBean.getInstance().setProperty(bean, nameLower, entry.getValue());

	        }

	    }
}
