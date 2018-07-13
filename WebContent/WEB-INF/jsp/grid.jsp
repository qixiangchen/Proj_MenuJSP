<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gufang.bean.*,com.gufang.tld.*,java.util.*"%>
<%@ taglib uri="/c1510bc" prefix="c1510b"%>


<c1510b:body>
		
	<script>
		function formatOper(val,row,index){
		    return '<input type="button" onclick="openwin('+index+',true)" value="详情"></input>&nbsp;';  
		}
		function doadd()
		{
			addwin()
		}
		function dodelete()
		{
			$.messager.confirm('删除提示', '是否删除所选?', function(r){
				$.ajax({ 
					url : '${ctx}/stu/delete?ids='+getSelected("id"),//将选中的id追加到url
					type:"post",
					success : function(result) {
						//刷新datagrid列表框
						$('#dg').datagrid({   
						    url:'${ctx}/stu/findall'
						});
						$.messager.alert('结果','删除成功');
					},
					failure:function(msg)
					{
						$.messager.alert(msg);
					}
				});
			});
		}
		function clickrange(a)
		{

		}
	</script>
		
	<c1510b:grid id="dg" url="/stu/findall" title="列表">
		<c1510b:column id="id" name="全选" type="checkbox"/>
		<c1510b:column id="no" name="编号"/>
		<c1510b:column id="name" name="名称"/>
		<c1510b:column id="dt" name="入学时间"/>
		<c1510b:column id="hobby" name="爱好"/>
		<c1510b:column id="chkid" name="爱好" />
		<c1510b:column id="operate" name="操作" formatter="formatOper"/>
		<c1510b:toolbar>
			<c1510b:line>
				<c1510b:field type="divradio" id="rangeId2" onclick="clickrange()" label="分布范围" width="200" url="/stu/findhobby">
			
				</c1510b:field>
			</c1510b:line>
			<c1510b:line>
				<c1510b:field type="combobox" id="rangeId3" onclick="clickrange" label="分布范围" width="200">
					<c1510b:option id="1" name="Java"/>
					<c1510b:option id="2" name="C++"/>
				</c1510b:field>
			</c1510b:line>
			<c1510b:line>
				<c1510b:field type="text" id="query" label="编号" width="200"/>
				<c1510b:button click="addwin()" name="新增"/>
				<c1510b:button click="dodelete()" name="删除"/>
			</c1510b:line>
		</c1510b:toolbar>
	</c1510b:grid>

	<c1510b:window id="win" url="/stu/save" title="新增学生" 
		fromId="addfrm" width="500" height="400" datagrid="dg">
		<c1510b:field type="text" id="no" label="编号" width="200"/>
		<c1510b:field type="text" id="name" label="姓名" width="200"/>
		<c1510b:field type="date" id="dt" label="日期" width="200"/>
		<c1510b:field type="hidden" id="id" label="编号" width="200"/>
		<c1510b:field type="divcheckbox" id="chkid" idField="hid" nameField="hname" label="分布" width="200" url="/stu/findhobby">
	
		</c1510b:field>
		<c1510b:button click="savewin()" name="保存"/>
		<c1510b:button click="closewin()" name="关闭"/>
	</c1510b:window>
	
</c1510b:body>

	
