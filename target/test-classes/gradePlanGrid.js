/**
*这是一个测试的例子
*@author tdu
*@time 2013-4-21 11:37:44
**/

Ext.ns("Ldc.grid");

Ldc.grid.GradePlanGrid=Ext.extend(Ext.grid.GridPanel,{
	autoWidth : true,
	enableColumnMove : false,
	enableHdMenu : true,
	columnLines: true,
	viewConfig:{
	  scrollOffset:0,
	  autoFit: true,
	  forceFit:false
	},
	initComponent:function(){
		this.sm=new Ext.grid.CheckboxSelectionModel({scope:this});
    	this.colModel=new Ext.grid.ColumnModel([
			    new Ext.grid.RowNumberer(),this.sm,{dataIndex:'id',hidden:true
			    },{
					header:'评估年度',
			    	dataIndex:'name',		    	
			    	width:150,
			    	sortable:true
			    },{
			    	header:'计划制定日期',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    }	
		]);
		
		this.store=new Ext.data.JsonStore({
			url : "/ldc/a.action",//update
			root : "data",
			method : "post",
			totalProperty : "totalCount",
			fields : ['name','date']
	    });
	    
		this.tbar=[
			{text:'新增',handler:this.addData,scope:this,iconCls:'xy-add'},
			{text:'编辑',handler:this.editData,scope:this,iconCls:'xy-edit'},
			{text:'删除',handler:this.delData,scope:this,iconCls:'xy-delete'},
			{text:'浏览',handler:this.quyData,scope:this,iconCls:'xy-view-select'}
		];
		
		this.store.load({params:{start:0,limit:20}});
		Ldc.grid.GradePlanGrid.superclass.initComponent.call(this);
	},addData:function(){//新增
		//TODO
	},editData:function(){//编辑
		//TODO
	},delData:function(){//删除
		var selections=this.selModel.getSelections();
		var record=this.getSelectionModel().getSelected();
		if(selections.length>0){
			//TODO
		}else{
			Ext.Msg.alert('提示','请选中记录');
		}
	},quyData:function(){//查看
		//TODO
	}
});

Ext.reg("Ldc.grid.GradePlanGrid",Ldc.grid.GradePlanGrid);