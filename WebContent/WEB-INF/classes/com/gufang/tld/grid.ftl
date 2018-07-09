
<#assign hasDate= "false">
<table id="${id}" title="${title}" class="easyui-datagrid"  
            url="${ctx}${url}"  
            pagination="${pagination}" pageList="[${pageDim}]" pageSize="${pageSize}"
            rownumbers="${rownumbers}" fitColumns="true" singleSelect="${selectOne}" 
            remoteSort="${remoteSort}" 
	            <#if tb??>
	            	toolbar="#${tb.id}"
	            </#if>
            >
        <thead>  
            <tr>
<#list cols as it>
                <th data-options="field:'${it.id}',sortable:${it.sortable},hidden:${it.hidden},width:${it.width}
	                <#if it.type=="checkbox">
						,checkbox:true
					</#if>
	                <#if it.type=="date">
						,formatter:${id}formatDatebox
						<#assign hasDate= "true">
						,sorter:sortDate
					<#else>
		                <#if it.formatter?default("")?trim?length gt 1>
							,formatter:${it.formatter}
						</#if>
						,sorter:sortStringInt
					</#if>
                ">${it.name}</th>
</#list> 
            </tr>
        </thead>  
</table>


<#list btns as it>
	<a class="easyui-linkbutton" iconcls="icon-ok" href="javascript:void(0)" onclick="${it.click}">${it.name}</a>
</#list>

<#if tb??>
	<div id="${tb.id}" style="padding:2px 5px;">
		<#list tb.lines as line>
			<#list line.flds as fld>
		        <#if fld.type=="combobox">
						${fld.label}:
						<select id="${fld.id}" class="easyui-combobox" name="${fld.id}" valueField="${fld.idField}" textField="${fld.nameField}" style="width:${fld.width}px;">
						   <#list fld.options as opt>
						   		<option value="${opt.id}">${opt.name}</option>
						   </#list> 
						</select> 
						<script>
							$('#${fld.id}').combobox({
								onSelect: function(param){
									${fld.onclick}(param)
								}
							});

						</script>
				</#if>
		        <#if fld.type=="text">
						${fld.label}:
						<input class="easyui-textbox" id="${fld.id}" name="${fld.id}" value="" style="width:${fld.width}px"> 
				</#if>
		        <#if fld.type=="date">
						${fld.label}:
						<input class="easyui-datebox" id="${fld.id}" name="${fld.id}"
							value="3/4/2010" style="width:${fld.width}px"> 
				</#if>
		        <#if fld.type=="datetime">
						${fld.label}:
						<input class="easyui-datetimebox" id="${fld.id}" name="${fld.id}"
							data-options="required:true,showSeconds:true" value="3/4/2010 2:3" style="width:${fld.width}px"> 
				</#if>
				
		        <#if fld.type=="divcheckbox">
					<div style="margin-left:20px;margin-bottom:0px">
						<div style="float:left">${fld.label}&nbsp;&nbsp;:</div>
						<div style="margin-left:20px;margin-bottom:0px" id="${fld.id}"></div> 
					</div>
				</#if>
		        <#if fld.type=="divradio">
					<div style="margin-left:20px;margin-bottom:0px">
						<div style="float:left">${fld.label}&nbsp;&nbsp;:</div>
						<div style="margin-left:20px;margin-bottom:0px" id="${fld.id}"></div> 
					</div>
				</#if>
		        <#if fld.type=="divcombobox">
					<div style="margin-left:20px;margin-bottom:0px">
						<div style="float:left">${fld.label}&nbsp;&nbsp;:</div>
						<select id="${fld.id}" class="easyui-combobox"  data-options="label:'${fld.label}'" name="${fld.id}" valueField="${fld.idField}" textField="${fld.nameField}" style="width:200px;">
						</select> 
					</div>
				</#if>
		        <#if fld.type=="checkbox">
					<div style="margin-left:20px;margin-bottom:0px">
						<div style="float:left">${fld.label}&nbsp;&nbsp;:</div>
					   <#list fld.options as opt>
					   		<input type='checkbox' value="${opt.id}" id='${fld.id}' name='${fld.id}' onclick='${fld.onclick}'/>${opt.name}
					   </#list> 
					</div>
				</#if>
		        <#if fld.type=="radio">
					<div style="margin-left:20px;margin-bottom:0px">
						<div style="float:left">${fld.label}&nbsp;&nbsp;:</div>
					   <#list fld.options as opt>
					   		<input type='radio' value="${opt.id}" id='${fld.id}' name='${fld.id}' onclick='${fld.onclick}'/>${opt.name}
					   </#list> 
					</div>
				</#if>
			</#list> 
			<#list line.btns as btn>
				<a class="easyui-linkbutton" iconcls="icon-ok" href="javascript:void(0)" onclick="${btn.click}">${btn.name}</a>
			</#list>
			<BR>
		</#list>
	</div>

</#if>



<script>
<#if tb??>
	$('#${id}').datagrid({
		onBeforeLoad: function(index,field,value){
<#list tb.lines as line>
	<#list line.flds as fld>
        <#if fld.type=="divcheckbox">
			$.ajax({ 
				url : '${ctx}${fld.url}',
				type:"get",
				success : function(result) {
					$("#${fld.id}").empty();
					for(i=0;i<result.length;i++)
					{
						$("#${fld.id}").append("<input type='checkbox' value='"+result[i].${fld.idField}+"' id='${fld.id}' name='${fld.id}' onclick='${fld.onclick}'/>"+result[i].${fld.nameField});
					}
				}
			});
		</#if>
        <#if fld.type=="divradio">
			$.ajax({ 
				url : '${ctx}${fld.url}',
				type:"get",
				success : function(result) {
					$("#${fld.id}").empty();
					for(i=0;i<result.length;i++)
					{
						$("#${fld.id}").append("<input type='radio' value='"+result[i].${fld.idField}+"' id='${fld.id}' name='${fld.id}' onclick='${fld.onclick}'/>"+result[i].${fld.nameField});
					}
				}
			});
		</#if>
	</#list>
</#list>
		}
	});
</#if>

	function sortStringInt(a,b)
	{
		return (a>b?1:-1);
	}
	function sortDate(a,b)
	{
        var time1 = Date.parse(a);
        var time2 = Date.parse(b);
        return (time1>time2?1:-1);
	}
	function getCheckboxIds(id)
	{
		var names = [];
		//取得选中将要删除的Checkbox对应的值,以逗号分割
		$("input[name='"+id+"']").each(function(){
			if($(this).prop("checked"))
			{
				names.push($(this).prop("value"));
			}
		});
		return names;
	}
	function getDeleteIds(colname){
		var names = [];
		var checkedItems = $('#${id}').datagrid('getChecked');
		$.each(checkedItems, function(index, item){
			names.push(item[colname]);
		}); 
		return names;
	}
    <#if hasDate =="true">
		//定制datagrid时间一列单元格的显示控件
		function ${id}formatDatebox(value,row,index){
			var unixTimestamp = new Date(value);
			return formatDate(value); 
		}
	</#if>
	var ${id} = 	'Grid Demo 代码------>\n'+
	'<c1601U:grid id="dg" url="/stu/findall" title="列表" \n'+
	'	pageSize="4" pageDim="4,8,16" selectOne="false">\n'+
	'	<c1601U:column id="id" sortable="" width="20" name="全选"  hidden="" formatter="" type="checkbox"/>\n'+
	'	<c1601U:column id="name" sortable="" width="20" name="Name"  hidden="" formatter="" type="text"/>\n'+
	'	<c1601U:column id="dt" sortable="" width="20" name="入学时间"  hidden="" formatter="" type="text"/>\n'+
	'	<c1601U:column id="hobby" sortable="" width="20" name="爱好"  hidden="" formatter="" type="text"/>\n'+
	'	<c1601U:column id="chkid" sortable="" width="20" name="爱好"  hidden="" formatter="" type="text"/>\n'+
	'	<c1601U:column id="operate" sortable="" width="20" name="操作"  hidden="" formatter="formatOper" type="text"/>\n'+
	'	<c1601U:toolbar>\n'+
	'		<c1601U:line>\n'+
	'			<c1601U:field type="text" id="query" label="编号" width="200"/>\n'+
	'			<c1601U:button click="doadd()" name="新增"/>\n'+
	'			<c1601U:button click="dodelete()" name="删除"/>\n'+
	'		</c1601U:line>\n'+
	'	</c1601U:toolbar>\n'+
	'</c1601U:grid>\n'+
	'<------Grid Demo 代码\n'+
	
	'删除与查询Javascrip 代码------>;\n'+
	'	function dodelete()\n'+
	'	{\n'+
	'		$.messager.confirm("删除提示", "是否删除所选?",function(rtn){\n'+
	'			if(rtn)\n'+
	'			{\n'+
	'				$.ajax({ \n'+
	'					url : "${ctx}/stu/delete?ids="+getSelected("id"),//将选中的id追加到url\n'+
	'					type:"post",\n'+
	'					success : function(result) {\n'+
	'						//刷新datagrid列表框\n'+
	'						$("#dg").datagrid({   \n'+
	'						    url:"${ctx}/stu/findall"\n'+
	'						});\n'+
	'						$.messager.alert("结果","删除成功");\n'+
	'					},\n'+
	'					failure:function(msg)\n'+
	'					{\n'+
	'						$.messager.alert(msg);\n'+
	'					}\n'+
	'				});\n'+
	'			}\n'+
	'		});\n'+
	'		\n'+
	'	}\n'+
	'	function query()\n'+
	'	{\n'+
	'		//刷新datagrid列表框\n'+
	'		$("#dg").datagrid({   \n'+
	'		    url:"${ctx}/stu/query?no="+$("#no").val()\n'+
	'		});\n'+
	'	}\n'+
	'<------删除与查询Javascrip 代码\n';
	
	console.log(${id});
</script>
