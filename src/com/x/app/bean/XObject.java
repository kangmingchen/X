package com.x.app.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p>
 * Description:实体基类，实现序列化
 * </p>
 * 
 * @Author Chenkangming @Date 2013-10-15
 * @version 1.0
 */
public abstract class XObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String UTF8 = "UTF-8";
	public final static String NODE_ROOT="x";
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
