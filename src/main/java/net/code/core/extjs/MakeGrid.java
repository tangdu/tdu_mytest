package net.code.core.extjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.code.bean.FormBean;
import net.code.core.MakeParent;

import org.junit.Test;

/**
 * 生成表格
 * 
 * @author tangdu
 * 
 * @time 2013-4-21 上午9:14:35
 */
public class MakeGrid extends MakeParent {

	@Test
	public void makeGrid() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("package", "Ldc");// 包名
		data.put("className", "LoseEventGrid");
		data.put("requesturl", "/ldc/a.action");
		data.put("desc", "损失事件列表");

		List<FormBean> cols = new ArrayList<FormBean>();
		FormBean b1=new FormBean("损失事件名称","name");
		FormBean b2=new FormBean("当前流转用户","date");
		FormBean b3=new FormBean("当前流转机构","date");
		FormBean b4=new FormBean("新资本协议事件类型","date");
		FormBean b5=new FormBean("已确认损失金额","date");
		FormBean b6=new FormBean("已确认损失净额","date");
		FormBean b7=new FormBean("发生机构","date");
		FormBean b8=new FormBean("发生部门","date");
		FormBean b9=new FormBean("发生日期","date");
		FormBean b10=new FormBean("创建日期","date");
		FormBean b11=new FormBean("上次修改日期","date");
		FormBean b12=new FormBean("状态","date");

		cols.add(b1);
		cols.add(b2);
		cols.add(b3);
		cols.add(b4);
		cols.add(b5);
		cols.add(b6);
		cols.add(b7);
		cols.add(b8);
		cols.add(b9);
		cols.add(b10);
		cols.add(b11);
		cols.add(b12);

		data.put("cols", cols);
		make("grid.html", data);
	}
}
