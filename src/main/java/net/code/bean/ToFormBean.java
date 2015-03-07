package net.code.bean;

import java.util.ArrayList;

/**
 * 两列bean 结构 树
 * 
 * @author tangdu
 * 
 * @time 2013-4-24 下午12:23:16
 */
public class ToFormBean {

	ArrayList<FormBean> lists;

	public void init() {
		lists = new ArrayList<FormBean>();
	}

	public ArrayList<FormBean> put(FormBean bean) {
		if (lists == null) {
			init();
		} else {
			lists.add(bean);
		}
		return lists;
	}

}
