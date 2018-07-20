<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function doadd()
	{
		$('#win').window('open');
		loadtype();
	}
	function loadtype(typeId)
	{
		$.ajax({
			url:'/findtype',
			success:function(data){
				$('#typeId').empty();
				for(var i=0;i<data.length;i++)
				{
					if(data[i].id==typeId)
						$('#typeId').append('<option value='+data[i].id+' selected>'+data[i].name+'</option>');
					else
						$('#typeId').append('<option value='+data[i].id+'>'+data[i].name+'</option>');
				}
			}
		})
	}
	function dosave()
	{
		var data = $('#frm').serialize();
		$.ajax({
			url:'/saveblog',
			data:data,
			success:function(data){
				$('#win').window('close');
				$('#dg').datagrid('reload');
			}
		})
	}
	function doquery()
	{
		var name = $('#query').textbox('getValue');
		$('#dg').datagrid({
			url:'/findsolrblog?name='+name
		});
	}
	function showbtn(cellVal,rowObj,rowIndex)
	{
		var status = rowObj.status;
		var btns = '<input type="button" value="编辑" onclick="openwin('+rowIndex+')">';
		if(status == '0')//未发布
		{
			btns = btns + '<input type="button" value="发布" onclick="publishhtml(\''+rowObj.id+'\')">';
		}
		else//已发布
		{
			btns = btns + '<input type="button" value="发布" onclick="publishhtml(\''+rowObj.id+'\')">';
			btns = btns + '<input type="button" value="查看" onclick="showhtml(\''+rowObj.id+'\')">';
		}
		return btns;
	}
	function showhtml(id)
	{
		var url = '/html/'+id+".html";
		window.open(url,'_blank');
	}
	function publishhtml(id)
	{
		var rows = $('#dg').datagrid('getRows');
		var status = '';
		for(i=0;i<rows.length;i++)
		{
			if(rows[i].id == id)
			{
				status = rows[i].status;
				break;
			}
		}
		if(status=='1')
		{
			$.messager.alert('','已经发布');
			return;	
		}
		
		$.ajax({
			url:'/publishblog?id='+id,
			success:function(data){
				$('#dg').datagrid('reload');
			}
		})
	}
	function openwin(rowIndex)
	{
		var rows = $('#dg').datagrid('getRows');
		var rowObj = rows[rowIndex];
		$('#win').window('open');
		$('#name').textbox('setValue',rowObj.name);
		$('#dt').textbox('setValue',rowObj.dt);
		$('#content').textbox('setValue',rowObj.content);
		$('#id').val(rowObj.id);
		$('#status').val(rowObj.status);
		alert(rowObj.typeId);
		loadtype(rowObj.typeId);
	}
	function dodelete()
	{
		var ids = $('#dg').datagrid('getSelections');
		var id = '';
		for(i=0;i<ids.length;i++)
			id = id + ids[i].id+',';
		$.ajax({
			url:'/deleteblog?id='+id,
			success:function(data){
				$('#dg').datagrid('reload');
			}
		})
	}
	$(document).ready(function(){
		$('#win').window('close');
	})
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center',title:'博文列表'" style="padding:5px;background:#eee;">
		<input id="query" name="query" class="easyui-textbox" data-options="label:'名称'" style="width:300px">
		<a id="btn" href="#" onclick="doquery()" class="easyui-linkbutton">查询</a> 
		<a id="btn" href="#" onclick="doadd()" class="easyui-linkbutton">新增博文</a>
		<a id="btn" href="#" onclick="dodelete()" class="easyui-linkbutton">批量删除</a>
		<table id="dg" class="easyui-datagrid" style="width:100%;height:250px"   
		        data-options="url:'/findsolrblog',fitColumns:true,singleSelect:false,pagination:true">   
		    <thead>   
		        <tr>
		        	<th data-options="field:'id',width:100,checkbox:true">博文ID</th>  
		            <th data-options="field:'name',width:100">博文名称</th>
		            <th data-options="field:'typeName',width:100">博文分类</th>
		            <th data-options="field:'typeId',width:100,hidden:true">博文分类</th>    
		            <th data-options="field:'status',width:100">博文状态</th>
		            <th data-options="field:'dt',width:100">写作时间</th>
		            <th data-options="field:'content',width:100">内容</th>  
		            <th data-options="field:'opt',width:100,formatter:showbtn">操作</th>   
		        </tr>   
		    </thead>   
		</table>  
		<div id="win" class="easyui-window" title="博文" style="width:600px;height:400px"   
		        data-options="iconCls:'icon-save',modal:true">   
		    <form id="frm" method="post" url="/saveblog">
		    	<input type="hidden" id="id" name="id">
		    	<input type="hidden" id="status" name="status">
		    	<div style="margin-left:30px;margin-top:20px">
		    		<input id="name" name="name" class="easyui-textbox" data-options="label:'博文名称'" style="width:300px">
		    	</div>
		    	<div style="margin-left:30px;margin-top:20px">
		    		<input id="dt" name="dt" class="easyui-datebox" data-options="label:'博文时间'" style="width:300px">
		    	</div>
		    	<div style="margin-left:30px;margin-top:20px">
		    		<input id="content" name="content" class="easyui-textbox" data-options="label:'博文内容'" style="width:300px">
		    	</div>	
		    	<div style="margin-left:30px;margin-top:20px">
		    		分类：
		    		<select id="typeId" name="typeId">
		    		
		    		</select>
		    	</div>
		    	 
		    	<div style="margin-left:30px;margin-top:20px">
		    		<a id="btn" href="#" onclick="dosave()" class="easyui-linkbutton">保存</a> 
		    	</div>
		   	</form> 
		</div>  
	</div> 
</body>
</html>