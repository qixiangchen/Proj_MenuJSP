<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/c1601U" prefix="c1601U"%>

<c1601U:body>
	<script>
		function formatOper(cellVal,rowObj,rowNo)
		{
			var btns = '<input type="button" onclick=showhtml('+rowObj.aid+') value="查看"/>';
			btns = btns + '<input type="button" onclick="openwin('+rowNo+',true)"value="修改"/>';
			return btns;
		}
		function formatSex(cellVal,rowObj,rowNo)
		{
			if(cellVal == '1')
			{
				return '<img src="/male.png"/>';
			}
			else
				return '女';
		}
		function dodelete()
		{
			$.ajax({ 
				url : "/delanimal?aid="+getSelected("aid"),//将选中的id追加到url
				type:"post",
				success : function(result) {
					//刷新datagrid列表框
					$("#dg").datagrid("reload");
					$.messager.alert("结果","删除成功");
				},
				failure:function(msg)
				{
					$.messager.alert(msg);
				}
			});
		}
		function doquery()
		{
			var q = $('#query').val();
			$('#dg').datagrid({
				url:'/findanimal?query='+q
			})
		}
		function showhtml(aid)
		{
			var url = '/html/'+aid+'.html';
			window.open(url,'_blank');
		}
	</script>
	<c1601U:grid id="dg" url="/findanimal" title="动物列表" 
		pageSize="4" pageDim="4,8,16" selectOne="false" rownumbers="true" >
		<c1601U:column id="aid" sortable="" width="20" name="全选"  hidden="" formatter="" type="checkbox"/>
		<c1601U:column id="name" width="20" name="Name"/>
		<c1601U:column id="weight" sortable="true" width="20" name="体重"  hidden="" formatter="" type="text"/>
		<c1601U:column id="regionName" sortable="" width="20" name="分布范围"  hidden="" formatter="" type="text"/>
		<c1601U:column id="regionId" sortable="" width="20" name="分布范围"  hidden="true" formatter="" type="text"/>
		<c1601U:column id="sex" sortable="" width="20" name="性别"  hidden="" formatter="formatSex" type="text"/>
		<c1601U:column id="operate" sortable="" width="20" name="操作"  hidden="" formatter="formatOper" type="text"/>
		<c1601U:toolbar>
			<c1601U:line>
				<c1601U:field type="text" id="query" label="名称" width="200"/>
				<c1601U:button click="addwin()" name="新增"/>
				<c1601U:button click="dodelete()" name="批量删除"/>
				<c1601U:button click="doquery()" name="查询"/>
			</c1601U:line>
		</c1601U:toolbar>
	</c1601U:grid>

	<c1601U:window id="win" url="/saveanimal" title="新增动物" 
		width="500" height="400" datagrid="dg">
		<c1601U:field type="hidden" id="aid" label="ID" width="200"/>
		<c1601U:field type="text" id="name" label="名称" width="200"/>
		<c1601U:field type="text" id="weight" label="体重" width="200"/>
		<c1601U:field type="divcheckbox" id="regionId" label="分布范围" 
			width="200" url="/findregion">
	
		</c1601U:field>
		<c1601U:field type="combobox" id="sex" label="性别" width="200">
			<c1601U:option id="1" name="男"/>
			<c1601U:option id="2" name="女"/>
		</c1601U:field>
		
		<c1601U:button click="savewin()" name="保存"/>
		<c1601U:button click="closewin()" name="关闭"/>
	</c1601U:window>
</c1601U:body>