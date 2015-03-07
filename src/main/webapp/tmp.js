Ext.ns("Rca.grid", "Rca.form","Rca.panel");
Rca.form.MainProcessForm= function(config) {
	Rca.form.MainProcessForm.superclass.constructor.call(this, Ext.apply(
					config, {
						parent : config.parent
						
					}));
};

//查询表单
Rca.form.MainProcessForm = Ext.extend(Ext.form.FormPanel, {
	frame : true,
	autoWidth:true,
	autoHeigth : true,
	layout : "form",
	initComponent : function() {
		this.items=[
			{layout:'column',xtype:'fieldset',title:'查询',items:[
				{columnWidth:'.5',
					layout:'form',
					defaultType : "textfield",
					labelWidth :80,
					labelAlign : "left",
					bodyStyle:'padding-top:10px',
					defaults:{width:130},
					items:[
					{fieldLabel:'主流程ID'},{fieldLabel:'业务条线'}
				]},
				{columnWidth:'.5',
					layout:'form',
					defaultType : "textfield",
					labelWidth :80,
					labelAlign : "left",
					bodyStyle:'padding-top:10px',
					defaults:{width:130},
					items:[
					{fieldLabel:'主流程名称'},{fieldLabel:'流程状态'}
				]}
			]}
		];
		this.buttons=[
			{text:'查询'},
			{text:'重围'}
		];
		
		Rca.form.MainProcessForm.superclass.initComponent.call(this);
	}
});
Ext.reg("Rca.form.MainProcessForm ",Rca.form.MainProcessForm );

//显示列表
Rca.grid.MainProcessGrid=function(config){	
	Rca.grid.MainProcessGrid.superclass.constructor.call(this,Ext.apply(config,{
		parent:config.parent
	}));
};

Rca.grid.MainProcessGrid=Ext.extend(Ext.grid.GridPanel,{
	autoWidth : true,
	autoScroll : true,
	enableColumnMove : false,
	enableHdMenu : true,
	forceFit:false,
	columnLines: true,
	windowWidth : window.screen.width,
	viewConfig: {
	  scrollOffset:0,
	  autoFit: true,
	  sortAscText: '升序',
	  columnsText: '显示列',
	  sortDescText: '降序'
	 },
	initComponent:function(){
		
		
		
		
		Rca.grid.MainProcessGrid.superclass.initComponent.call(this);
		
	},loadAllData:function(){//加载
		
	},addProject:function(){//新增
		
	},editProject:function(){//编辑
		
	},
	deleteProject:function(){//删除
	
	}
});

Ext.reg("Rca.grid.MainProcessGrid",Rca.grid.MainProcessGrid);


//组装
Rca.panel.mainProcessPanel=Ext.extend(Ext.Panel,{
	frame : true,
	autoWidth:true,
	autoHeigth : true,
	layout : "form",
	initComponent:function(){
		//this.mainProcessForm=new Rca.form.MainProcessForm({parent:this});
		this.mainProcessGrid=new Rca.grid.MainProcessGrid({parent:this});
		
		this.items=[this.mainProcessGrid];
		Rca.panel.mainProcessPanel.superclass.initComponent.call(this);
	}
});

Ext.reg("Rca.panel.mainProcessPanel",Rca.panel.mainProcessPanel);
