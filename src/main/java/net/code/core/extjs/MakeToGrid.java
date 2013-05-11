package net.code.core.extjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.code.bean.FormBean;
import net.code.core.MakeParent;

import org.junit.Test;

public class MakeToGrid extends MakeParent {

	@Test
	public void create() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("package", "Ldc");// 包名
		data.put("className", "LoseEventFormTo");
		data.put("desc", "损失时间录入表单");
		data.put("lablewin", 150);//label默认宽度
		data.put("areawin",200);//文本框默认宽度

		FormBean b1 = new FormBean("损失事件ID", "name1", "textfield", true);
		FormBean b2 = new FormBean("基本情况描述", "name2", "textfield", false);
		FormBean b3 = new FormBean("发生日期", "name3", "datefield", false);
		FormBean b4 = new FormBean("事件创建人", "name4", "radiogroup", false);
		FormBean b5 = new FormBean("损失事件报告人", "name5", "radiogroup", false);
		FormBean b6 = new FormBean("报告人电话 ", "name6", "radiogroup", false);
		FormBean b7 = new FormBean("币种", "name7", "combo", false);
		FormBean b8 = new FormBean("已确认损失金额", "name8", "numberfield", false);
		FormBean b9 = new FormBean("损失事件", "name9", "radiogroup", false);
		FormBean b10 = new FormBean("是否涉及客户", "name10", "radiogroup", false);
		FormBean b11 = new FormBean("是否关联信用风险", "name12", "radiogroup", false);
		FormBean b12 = new FormBean("事件机密性", "name13", "radiogroup", false);
		FormBean b13 = new FormBean("新资本协议事件类型", "name14", "trigger", false);
		FormBean b14 = new FormBean("意见", "name15", "textarea", false);
		
		FormBean b15 = new FormBean("损失事件名称", "name21", "textfield", true);
		FormBean b16 = new FormBean("为纠正或预防事件而采取的初步行动 ", "name22", "textarea",
				false);
		FormBean b17 = new FormBean("发现日期", "name23", "datefield", false);
		FormBean b18 = new FormBean("事件上报日期", "name24", "radiogroup", false);
		FormBean b19 = new FormBean("报告人电子邮箱", "name25", "radiogroup", false);
		FormBean b20 = new FormBean("44 ", "name26", "text", false);
		FormBean b21 = new FormBean("涉及损失金额", "name27", "numberfield", false);
		FormBean b22 = new FormBean("已确认损失净额 ", "name28", "numberfield", false);
		FormBean b23 = new FormBean("是否监管要求上报的重大损失事件", "name29", "radiogroup",
				false);
		FormBean b24 = new FormBean("是否涉及员工", "name30", "radiogroup", false);
		FormBean b25 = new FormBean("是否关联市场风险", "name31", "radiogroup", false);
		FormBean b26 = new FormBean("是否重复事件", "name32", "radiogroup", false);

		List<List<List<FormBean>>> lists=new ArrayList<List<List<FormBean>>>();
		List<List<FormBean>> l1=new ArrayList<List<FormBean>>();//row
		List<FormBean> beans1=new ArrayList<FormBean>();
		beans1.add(b1);
		List<FormBean> beans2=new ArrayList<FormBean>();
		beans2.add(b15);
		
		l1.add(beans1);
		l1.add(beans2);
		
		List<List<FormBean>> l2=new ArrayList<List<FormBean>>();//row
		List<FormBean> beans3=new ArrayList<FormBean>();
		beans3.add(b2);
		List<FormBean> beans4=new ArrayList<FormBean>();
		beans4.add(b16);
		
		l2.add(beans3);
		l2.add(beans4);
		
		lists.add(l1);
		lists.add(l2);
		data.put("cols", lists);
		
		
		make("form2colFz.html", data);
	}
}
