package com.x.app.bean;

/**
 * <p>
 * Description:实体类
 * </p>
 * 
 * @author Chenkm
 * @version 1.0
 * @date 2014年5月17日
 */
public abstract class Entity extends XObject {

	private static final long serialVersionUID = -2234209859789061627L;

	protected int id;

	public int getId() {
		return id;
	}
	
	protected String cacheKey;

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
}
