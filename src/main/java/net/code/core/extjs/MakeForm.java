package net.code.core.extjs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.code.bean.FormBean;
import net.code.core.MakeParent;

import org.junit.Test;

public class MakeForm extends MakeParent {
	@Test
	public void make2ColumnForm() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("package", "Ldc");// 包名
		data.put("className", "LoseEventForm");
		data.put("desc", "损失时间录入表单");
		data.put("lablewin", 150);

		List<FormBean> cols1 = new ArrayList<FormBean>();
		FormBean b1 = new FormBean("损失事件ID", "name1", "textfield", true,"1");
		FormBean b2 = new FormBean("基本情况描述", "name2", "textarea", false);
		FormBean b3 = new FormBean("发生日期", "name3", "datefield", false);
		FormBean b4 = new FormBean("事件创建人", "name4", "radiogroup", false);
		FormBean b5 = new FormBean("损失事件报告人", "name5", "radiogroup", false);
		FormBean b6 = new FormBean("报告人电话 ", "name6", "radiogroup", false);
		FormBean b7 = new FormBean("币种", "name7", "combo", false);
		FormBean b8 = new FormBean("已确认损失金额", "name8", "numberfield", false);
		FormBean b9 = new FormBean("损失事件", "name9", "radiogroup", false);
		FormBean b10 = new FormBean("是否涉及客户", "name10", "radiogroup", false);
		FormBean b12 = new FormBean("是否关联信用风险", "name12", "radiogroup", false);
		FormBean b13 = new FormBean("事件机密性", "name13", "radiogroup", false);
		FormBean b14 = new FormBean("新资本协议事件类型", "name14", "trigger", false);
		FormBean b15 = new FormBean("意见", "name15", "textarea", false);

		cols1.add(b1);
		cols1.add(b2);
		cols1.add(b3);
		cols1.add(b4);
		cols1.add(b5);
		cols1.add(b6);
		cols1.add(b7);
		cols1.add(b8);
		cols1.add(b9);
		cols1.add(b10);
		cols1.add(b12);
		cols1.add(b13);
		cols1.add(b14);
		cols1.add(b15);

		List<FormBean> cols2 = new ArrayList<FormBean>();
		FormBean c1 = new FormBean("损失事件名称", "name21", "textfield", true);
		FormBean c2 = new FormBean("为纠正或预防事件而采取的初步行动 ", "name22", "textarea", false);
		FormBean c3 = new FormBean("发现日期", "name23", "datefield", false);
		FormBean c4 = new FormBean("事件上报日期", "name24", "radiogroup", false);
		FormBean c5 = new FormBean("报告人电子邮箱", "name25", "radiogroup", false);
		FormBean c6 = new FormBean("44 ", "name26", "text", false);
		FormBean c7 = new FormBean("涉及损失金额", "name27", "numberfield", false);
		FormBean c8 = new FormBean("已确认损失净额 ", "name28", "numberfield", false);
		FormBean c9 = new FormBean("是否监管要求上报的重大损失事件", "name29", "radiogroup", false);
		FormBean c10 = new FormBean("是否涉及员工", "name30", "radiogroup", false);
		FormBean c12 = new FormBean("是否关联市场风险", "name31", "radiogroup", false);
		FormBean c13 = new FormBean("是否重复事件", "name32", "radiogroup", false);
		
		cols2.add(c1);
		cols2.add(c2);
		cols2.add(c3);
		cols2.add(c4);
		cols2.add(c5);
		cols2.add(c6);
		cols2.add(c7);
		cols2.add(c8);
		cols2.add(c9);
		cols2.add(c10);
		cols2.add(c12);
		cols2.add(c13);
		
		data.put("cols1", cols1);
		data.put("cols2", cols2);
		make("form2col.html", data);
	}
	
	
}
