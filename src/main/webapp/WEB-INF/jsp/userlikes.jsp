<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/c1601U" prefix="c1601U"%>

<c1601U:body>
	<script>
		function formatSelected(cellVal,rowObj,rowNo)
		{
			if(cellVal == '1')
			{
				return '<input type="checkbox" id="isSelected"	name="isSelected" value="'+rowObj.id+'"/>';
			}
			else
			{
				return '<img src="/like.png"/>';	
			}
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
		function doclose()
		{
			window.close();
		}
		function dosave()
		{
			var ids = getSelected('isSelected');
			var url = '/userlike?ids='+ids;
			$.ajax({
				url:url,
				success:function(data)
				{
					$('#dg').datagrid('reload')
				}
			})
		}
	</script>
	<div>登陆用户：${userName }</div>
	<c1601U:grid id="dg" url="/finduserbook" title="图书列表" 
		pageSize="4" pageDim="4,8,16" selectOne="false" pagination="false">
		<c1601U:column id="id" sortable="" width="20" name="图书ID"  hidden="" formatter="" type="text"/>
		<c1601U:column id="name" sortable="" width="20" name="图书名称"  hidden="" formatter="" type="text"/>
		<c1601U:column id="typeId" sortable="" width="20" name="类别"  hidden="" formatter="formatType" type="text"/>
		<c1601U:column id="author" sortable="" width="20" name="作者"  hidden="" formatter="" type="text"/>
		<c1601U:column id="isSelected" sortable="" width="20" name="选择"  hidden="" formatter="formatSelected" type="text"/>
		<c1601U:toolbar>
			<c1601U:line>
				<c1601U:button click="dosave()" name="确定"/>
				<c1601U:button click="doclose()" name="关闭"/>
			</c1601U:line>
		</c1601U:toolbar>
	</c1601U:grid>

</c1601U:body>