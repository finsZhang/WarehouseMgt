package com.zx.whm.common.json;

import net.sf.json.util.PropertyFilter;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class NotIgnoreFieldPropertyFilterImpl implements PropertyFilter {

	/**
	 * 不需要过滤的属性名称
	 */
	private String[] fields;

	public NotIgnoreFieldPropertyFilterImpl() {
	}

	public NotIgnoreFieldPropertyFilterImpl(String[] pars) {
		this.fields = pars;
	}

	public boolean apply(Object source, String name, Object value) {

		if (value == null) { // 值为空的属性 不转换为json
			return true;
		}

		if (fields == null) { // fields为空 代表所有的非空属性都转换为json
			return false;
		}

		if (fields != null && fields.length > 0) {
			return !juge(fields, name);
		}

		return false;
	}

	/**
	 * 不需要过滤的属性
	 * 
	 * @param s
	 * @param s2
	 * @return
	 */
	public boolean juge(String[] s, String s2) {
		for (String sl : s) {
			if (s2.equals(sl)) {
				return false;
			}
		}
		return true;
	}
}
