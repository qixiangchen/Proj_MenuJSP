<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/c1601U" prefix="c1601U"%>

<c1601U:body>
	<script>
		function formatOper(cellVal,rowObj,rowNo)
		{
			var btns = "<input type='button' onclick='openwin("+rowNo+",true)' value='修改'>";
			btns = btns + "<input type='button' value='删除'>";
			return btns;
		}
		function formatType(cellVal,rowObj,rowNo)
		{
			if(cellVal == '1')
				return '科技';
			if(cellVal == '2')
				return '哲学';
			if(cellVal == '3')
				return '音乐';
		}
		function doquery()
		{
			var typeId = '';
			$('input[name="queryTypeId"]:checked').each(function(){
				typeId = typeId + this.value + ","
			})
			if(typeId.length>0)
				typeId = typeId.substring(0,typeId.length-1);
			var name = $('#query').val();
			$('#dg').datagrid({
				url:'/findadminbook?name='+name+'&typeId='+typeId
			});
			
		}
		function dologout()
		{
			window.open('/logout','_self');
		}
		function dolikes()
		{
			window.open('/likebook','_blank');
		}
	</script>
	<div>登陆用户：${userName }<input type="button" onclick="dologout()" value="退出"/></div>
	<c1601U:grid id="dg" url="/findadminbook" title="图书列表" 
		pageSize="4" pageDim="4,8,16" selectOne="false" pagination="false">
		<c1601U:column id="id" sortable="" width="20" name="图书ID"  hidden="" formatter="" type="text"/>
		<c1601U:column id="name" sortable="" width="20" name="图书名称"  hidden="" formatter="" type="text"/>
		<c1601U:column id="typeId" sortable="" width="20" name="类别"  hidden="" formatter="formatType" type="text"/>
		<c1601U:column id="author" sortable="" width="20" name="作者"  hidden="" formatter="" type="text"/>
		<c1601U:column id="count" sortable="" width="20" name="受欢迎程度"  hidden="" formatter="" type="text"/>
		<c1601U:toolbar>
			<c1601U:line>
				<c1601U:field type="checkbox" id="queryTypeId" label="类别"
					 width="200" onclick="doquery()">
					<c1601U:option id="1" name="科技"/>
					<c1601U:option id="2" name="哲学"/>
					<c1601U:option id="3" name="音乐"/>
				</c1601U:field>
			</c1601U:line>
			<c1601U:line>
				<c1601U:field type="text" id="query" label="名称" width="200"/>
				<c1601U:button click="doquery()" name="查询"/>
				<c1601U:button click="dolikes()" name="喜欢图书"/>
			</c1601U:line>
		</c1601U:toolbar>
	</c1601U:grid>

</c1601U:body>