package com.zhuzi.mybatis.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.zhuzi.mybatis.annotation.FieldAnalyzeHandler;

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
				t = baseClass(map, c);
				if(t == null) {
					t = c.newInstance();
					populate(t, map);
				}
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

	private static Object baseClass(Map<String, ?> map, Class<?> c) {
		Object t = null;
		try {
			Object val = map.get(map.keySet().toArray()[0]);
			if(c == String.class) {
				t = String.valueOf(val);
			} else if(c == Integer.class) {
				t = Integer.parseInt(String.valueOf(val));
			} else if(c == Long.class) {
				t = Long.parseLong(String.valueOf(val));
			} else if(c == Float.class) {
				t = Float.parseFloat(String.valueOf(val));
			} else if(c == Double.class) {
				t = Double.parseDouble(String.valueOf(val));
			}
		} catch (Exception e) {
		}
		return t;
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
	            Object value = entry.getValue();
	            try {
	            	FieldAnalyzeHandler handler = bean.getClass().getDeclaredField(nameLower).getAnnotation(FieldAnalyzeHandler.class);
	            	if(handler != null) {
	            		AbstractFieldAnalyzeHandler<?> abstractHandler = handler.handler().newInstance();
	            		value = abstractHandler.handler(value);
	            	}
	            } catch (NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
	            BeanUtilsBean.getInstance().setProperty(bean, nameLower, value);

	        }

	    }
}
