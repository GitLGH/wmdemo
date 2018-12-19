package com.example.wmdemo.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @Description 基础model
 * @author houchangren
 * @date 2015-3-13 上午9:54:47
 */
public class BaseDto implements Serializable, Cloneable {

	private static final long serialVersionUID = -5152170884916847629L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@SuppressWarnings("unchecked")
	public <T> T clone(Class<T> cls) throws CloneNotSupportedException {
		return (T) clone();
	}
}
