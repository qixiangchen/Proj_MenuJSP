
<div id="${id}" class="easyui-window" title="${title}" style="width:${width}px;height:${height}px;" 
data-options="iconCls:'icon-save',modal:true,closed:true"> 
	<br>
	<form id="${fromId}" method="post" action="${url}">
<#list flds as it>
        <#if it.type=="divcheckbox">
			<div style="margin-left:20px;margin-bottom:20px">
				<div style="margin-left:20px;margin-bottom:20px" id="${it.id}"></div> 
			</div>
		</#if>
        <#if it.type=="divradio">
			<div style="margin-left:20px;margin-bottom:20px">
				<div style="margin-left:20px;margin-bottom:20px" id="${it.id}"></div> 
			</div>
		</#if>
        <#if it.type=="combobox">
			<div style="margin-left:20px;margin-bottom:20px">
				<select id="${it.id}" class="easyui-combobox"  data-options="label:'${it.label}'" name="${it.id}" valueField="${it.idField}" textField="${it.nameField}" style="width:200px;" url="${it.url}" >   
				</select> 
			</div>
		</#if>
        <#if it.type=="text">
			<div style="margin-left:20px;margin-bottom:20px">
				<input class="easyui-textbox" id="${it.id}" name="${it.id}" value="" style="width:70%" data-options="label:'${it.label}'"> 
			</div>
		</#if>
        <#if it.type=="date">
			<div style="margin-left:20px">
				<input class="easyui-datetimebox" id="${it.id}" name="${it.id}"
			data-options="required:true,showSeconds:true,label:'${it.label}'" value="3/4/2010 2:3" style="width:250px"> 
			</div>
		</#if>
        <#if it.type=="hidden">
			<input type="hidden" id="${it.id}" name="${it.id}"/>
		</#if>
</#list> 
	</form>
	<div style="text-align:center;padding:5px 0">
<#list btns as it>
	<a class="easyui-linkbutton" iconcls="icon-ok" href="javascript:void(0)" onclick="${it.click}">${it.name}</a>
</#list>
	</div>
</div>
<script>
	$('#win').window({
		onOpen:function()
		{
<#list flds as it>
        <#if it.type=="divcheckbox">
			$.ajax({ 
				url : '${it.url}',
				type:"get",
				success : function(result) {
					$("#${it.id}").empty();
					for(i=0;i<result.length;i++)
					{
						$("#${it.id}").append("<input type='checkbox' value='"+result[i].id+"' id='regchk' name='regchk'/>"+result[i].name);
					}
				},
				failure:function(msg){
					alert(msg);
				}
			});
		</#if>
        <#if it.type=="divradio">
			$.ajax({ 
				url : '${it.url}',
				type:"get",
				success : function(result) {
					$("#${it.id}").empty();
					for(i=0;i<result.length;i++)
					{
						$("#${it.id}").append("<input type='radio' value='"+result[i].id+"' id='regchk' name='regchk'/>"+result[i].name);
					}
				},
				failure:function(msg){
					alert(msg);
				}
			});
		</#if>
</#list>
		}
	});
	
	function openwin(index)
	{
		//取得列表控件datagrid的全部数据集合
		var rows = $('#${datagrid}').datagrid('getRows');
		//取得选中行对象
		var selRow = rows[index];
		$("#${id}").window('open');
<#list flds as it>
        <#if it.type=="divcheckbox">
			$.ajax({ 
				url : '${it.url}',
				type:"get",
				success : function(result) {
					$("#${it.id}").empty();
					for(i=0;i<result.length;i++)
					{
						$("#${it.id}").append("<input type='checkbox' value='"+result[i].id+"' id='${it.id}_chk' name='${it.id}_chk'/>"+result[i].name);
					}
					//根据数据库中值为checkbox赋值
					$("input[name='${it.id}_chk']").each(function(){
						if(selRow['${it.datagridId}'].indexOf($(this).val()) >=0 )
						{
							$(this).prop("checked",true);
						}
						else
						{
							$(this).prop("checked",false);
						}
					});
				}
			});
		</#if>
        <#if it.type=="divradio">
			$.ajax({ 
				url : '${it.url}',
				type:"get",
				success : function(result) {
					$("#${it.id}").empty();
					for(i=0;i<result.length;i++)
					{
						$("#${it.id}").append("<input type='radio' value='"+result[i].id+"' id='${it.id}_radio' name='${it.id}_radio'/>"+result[i].name);
					}
					//根据数据库中值为checkbox赋值
					$("input[name='${it.id}_radio']").each(function(){
						if(selRow['${it.datagridId}'].indexOf($(this).val()) >=0 )
						{
							$(this).prop("checked",true);
						}
						else
						{
							$(this).prop("checked",false);
						}
					});
				}
			});
		</#if>
        <#if it.type=="text">
			$('#${it.id}').textbox('setValue',selRow['${it.id}']);
		</#if>
        <#if it.type=="date">
		    var unixTimestamp = new Date(selRow['${it.id}']);
			$('#${it.id}').datetimebox('setValue',formatDate(unixTimestamp));
		</#if>
        <#if it.type=="combobox">
			$('#${it.id}').combobox('select',selRow['${it.datagridId}']);
		</#if>
        <#if it.type=="hidden">
			$('#${it.id}').val(selRow['${it.id}']);
		</#if>
</#list>
	}
	
function close${id}(){
	$('#${id}').window('open');
}
</script>