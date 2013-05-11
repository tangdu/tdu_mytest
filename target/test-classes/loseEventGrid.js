/**
*损失事件列表
*@author tdu
*@time 2013-4-23 9:20:34
**/

Ext.ns("Ldc.grid");

Ldc.grid.LoseEventGrid=Ext.extend(Ext.grid.GridPanel,{
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
					header:'损失事件名称',
			    	dataIndex:'name',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'当前流转用户',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'当前流转机构',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'新资本协议事件类型',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'已确认损失金额',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'已确认损失净额',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'发生机构',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'发生部门',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'发生日期',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'创建日期',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
					header:'上次修改日期',
			    	dataIndex:'date',		    	
			    	width:150,
			    	sortable:true
			    },{
			    	header:'状态',
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
			fields : ['name','date','date','date','date','date','date','date','date','date','date','date']
	    });
	    
		this.tbar=[
			{text:'新增',handler:this.addData,scope:this,iconCls:'xy-add'},
			{text:'编辑',handler:this.editData,scope:this,iconCls:'xy-edit'},
			{text:'删除',handler:this.delData,scope:this,iconCls:'xy-delete'},
			{text:'浏览',handler:this.quyData,scope:this,iconCls:'xy-view-select'}
		];
		
		this.store.load({params:{start:0,limit:20}});
		Ldc.grid.LoseEventGrid.superclass.initComponent.call(this);
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

Ext.reg("Ldc.grid.LoseEventGrid",Ldc.grid.LoseEventGrid);