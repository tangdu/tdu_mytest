/**
*损失时间录入表单
*@author tdu
*@time 2013-4-25 12:41:53
**/
Ext.ns("Ldc.form");


Ldc.form.LoseEventFormTo = Ext.extend(Ext.form.FormPanel, {
	frame : true,
	layout:'form',
	labelWidth :100,
	labelAlign : "left",
	initComponent : function() {
		this.items=[
			{layout:'column',items:[
				{columnWidth:'.5',
					layout:'form',
					labelWidth :150,
					labelAlign : "left",
					defaults:{width:120},
					items:[
						{fieldLabel:'损失事件ID',name:'name1',xtype:'textfield',allowBlank:false}
				]},
				{columnWidth:'.5',
					layout:'form',
					labelAlign : "left",
					labelWidth :150,
					defaults:{width:120},
					items:[
						{fieldLabel:'损失事件名称',name:'name21',xtype:'textfield',allowBlank:false}
				]}
			]},
			{layout:'column',items:[
				{columnWidth:'.5',
					layout:'form',
					labelWidth :150,
					labelAlign : "left",
					defaults:{width:120},
					items:[
						{fieldLabel:'基本情况描述',name:'name2',xtype:'textfield'}
				]},
				{columnWidth:'.5',
					layout:'form',
					labelAlign : "left",
					labelWidth :150,
					defaults:{width:120},
					items:[
						{fieldLabel:'为纠正或预防事件而采取的初步行动 ',name:'name22',xtype:'textarea'}
				]}
			]}
		];
		Ldc.form.LoseEventFormTo.superclass.initComponent.call(this);
	}
});
Ext.reg("Ldc.form.LoseEventFormTo",Ldc.form.LoseEventFormTo);
