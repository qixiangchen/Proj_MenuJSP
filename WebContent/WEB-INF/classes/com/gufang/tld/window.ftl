
<div id="${id}" class="easyui-window" title="${title}" style="width:${width}px;height:${height}px;" 
data-options="iconCls:'icon-save',modal:true,closed:true"> 
	<br>
	<form id="${fromId}" method="post" action="${ctx}${url}">
<#list flds as it>
        <#if it.type=="divcheckbox">
			<div style="margin-left:20px;margin-bottom:20px">
				<div style="float:left">${it.label}&nbsp;&nbsp;:</div>
				<div style="margin-left:20px;margin-bottom:20px" id="${it.id}"></div> 
			</div>
		</#if>
        <#if it.type=="divradio">
			<div style="margin-left:20px;margin-bottom:20px">
				<div style="float:left">${it.label}&nbsp;&nbsp;:</div>
				<div style="margin-left:20px;margin-bottom:20px" id="${it.id}"></div> 
			</div>
		</#if>
        <#if it.type=="divcombobox">
			<div style="margin-left:20px;margin-bottom:20px">
				<select id="${it.id}" class="easyui-combobox"  data-options="label:'${it.label}'" name="${it.id}" valueField="${it.idField}" textField="${it.nameField}" style="width:200px;">
				</select>
				<script>
					$('#${it.id}').combobox({
						onSelect: function(param){
							${it.onclick}(param)
						}
					});

				</script>
			</div>
		</#if>
        <#if it.type=="combobox">
			<div style="margin-left:20px;margin-bottom:20px">
				<select id="${it.id}" class="easyui-combobox"  data-options="label:'${it.label}'" name="${it.id}" valueField="${it.idField}" textField="${it.nameField}" style="width:200px;">
				   <#list it.options as opt>
				   		<option value="${opt.id}">${opt.name}</option>
				   </#list> 
				</select> 
				<script>
					$('#${it.id}').combobox({
						onSelect: function(param){
							${it.onclick}(param)
						}
					});

				</script>
			</div>
		</#if>
        <#if it.type=="checkbox">
			<div style="margin-left:20px;margin-bottom:20px">
				<div style="float:left">${it.label}&nbsp;&nbsp;:</div>
			   <#list it.options as opt>
			   		<input type='checkbox' value="${opt.id}" id='${it.id}' name='${it.id}' onclick="${it.onclick}"/>${opt.name}
			   </#list> 
			</div>
		</#if>
        <#if it.type=="radio">
			<div style="margin-left:20px;margin-bottom:20px">
				<div style="float:left">${it.label}&nbsp;&nbsp;:</div>
			   <#list it.options as opt>
			   		<input type='radio' value="${opt.id}" id='${it.id}' name='${it.id}' onclick="${it.onclick}"/>${opt.name}
			   </#list> 
			</div>
		</#if>
        <#if it.type=="text">
			<div style="margin-left:20px;margin-bottom:20px">
				<input class="easyui-textbox" id="${it.id}" name="${it.id}" value="" style="width:70%" data-options="label:'${it.label}',multiline:${it.multiline}"> 
			</div>
		</#if>
        <#if it.type=="date">
			<div style="margin-left:20px;margin-bottom:20px">
				<input class="easyui-datebox" id="${it.id}" name="${it.id}"
			data-options="required:true,label:'${it.label}'" value="3/4/2010" style="width:250px"> 
			</div>
		</#if>
        <#if it.type=="datetime">
			<div style="margin-left:20px;margin-bottom:20px">
				<input class="easyui-datetimebox" id="${it.id}" name="${it.id}"
			data-options="required:true,showSeconds:true,label:'${it.label}'" value="3/4/2010 2:3" style="width:250px"> 
			</div>
		</#if>
        <#if it.type=="hidden">
			<input type="hidden" id="${it.id}" name="${it.id}"/>
		</#if>
</#list> 
	</form>
	<div id="${id}_btndiv" style="text-align:center;padding:5px 0">
<#list btns as it>
	<a class="easyui-linkbutton" iconcls="icon-ok" href="javascript:void(0)" onclick="${it.click}">${it.name}</a>
</#list>
	</div>
</div>
<script>
	function add${id}()
	{
		console.log('新增事件 add${id}');
		$('#${id}_btndiv').show();
		$("#${id}").window('open');
<#list flds as it>
        <#if it.type=="divcheckbox">
			$.ajax({ 
				url : '${ctx}${it.url}',
				type:"get",
				success : function(result) {
					console.log('新增事件  add${id} ${ctx}${it.url} result='+result);
					$("#${it.id}").empty();
					for(i=0;i<result.length;i++)
					{
						console.log('新增事件  divcheckbox ${ctx}${it.url} '+i+' id='+result[i].id+',name='+result[i].name);
						$("#${it.id}").append("<input type='checkbox' value='"+result[i].${it.idField}+"' id='${it.id}' name='${it.id}' onclick='${it.onclick}'/>"+result[i].${it.nameField});
					}
				}
			});
		</#if>
        <#if it.type=="divradio">
			$.ajax({ 
				url : '${ctx}${it.url}',
				type:"get",
				success : function(result) {
					console.log('新增事件  add${id} ${ctx}${it.url} result='+result);
					$("#${it.id}").empty();
					for(i=0;i<result.length;i++)
					{
						console.log('新增事件  divradio ${ctx}${it.url} '+i+' id='+result[i].id+',name='+result[i].name);
						$("#${it.id}").append("<input type='radio' value='"+result[i].${it.idField}+"' id='${it.id}' name='${it.id}' onclick='${it.onclick}'/>"+result[i].${it.nameField});
					}
				}
			});
		</#if>
        <#if it.type=="divcombobox">
        	$('#${it.id}').valueField='${it.idField}';
        	$('#${it.id}').textField='${it.nameField}';
        	$('#${it.id}').combobox('reload','${ctx}${it.url}');
		</#if>
        <#if it.type=="text">
			$('#${it.id}').textbox('setValue','');
		</#if>
        <#if it.type=="date">
        	var unixTimestamp = new Date();
			$('#${it.id}').datebox('setValue',formatDate(unixTimestamp));
		</#if>
        <#if it.type=="datetime">
		    var unixTimestamp = new Date();
			$('#${it.id}').datetimebox('setValue',formatDate(unixTimestamp));
		</#if>
        <#if it.type=="hidden">
			$('#${it.id}').val('');
		</#if>
</#list>
	};
	
	function open${id}(index,ismodify)
	{
		console.log('打开窗口事件  open${id} index='+index+',ismodify='+ismodify);
		if(!ismodify)
		{
			$('#${id}_btndiv').hide();
		}
		//取得列表控件datagrid的全部数据集合
		var rows = $('#${datagrid}').datagrid('getRows');
		//取得选中行对象
		var selRow = rows[index];
		console.log('窗口事件 open${id} selRow='+selRow);
		$("#${id}").window('open');
<#list flds as it>
        <#if it.type=="divcheckbox">
			$.ajax({ 
				url : '${ctx}${it.url}',
				type:"get",
				success : function(result) {
					console.log('打开窗口事件 open${id} divcheckbox result='+result);
					$("#${it.id}").empty();
					for(i=0;i<result.length;i++)
					{
						console.log('打开窗口事件  divcheckbox '+i+' id='+result[i].${it.idField}+',name='+result[i].${it.nameField}+',selRowId[${it.id}]='+selRow['${it.id}']);
						if(selRow['${it.id}'] != null)
						{
							if((selRow['${it.id}']+'').indexOf((result[i].${it.idField}+'')) >=0)
							{
								$("#${it.id}").append("<input type='checkbox' value='"+result[i].${it.idField}+"' id='${it.id}' name='${it.id}' onclick='${it.onclick}' checked/>"+result[i].${it.nameField});
							}
							else
							{
								$("#${it.id}").append("<input type='checkbox' value='"+result[i].${it.idField}+"' id='${it.id}' name='${it.id}' onclick='${it.onclick}'/>"+result[i].${it.nameField});
							}
						}
						else
						{
							$("#${it.id}").append("<input type='checkbox' value='"+result[i].${it.idField}+"' id='${it.id}' name='${it.id}' onclick='${it.onclick}'/>"+result[i].${it.nameField});
							console.log('divcheckbox错误,请设置FieldInfo.id属性必须与Datagrid中列Id相同,或者单元格是空值');
						}
					}
				}
			});
		</#if>
        <#if it.type=="divradio">
			$.ajax({ 
				url : '${ctx}${it.url}',
				type:"get",
				success : function(result) {
					console.log('打开窗口事件  open${id} divradio result='+result);
					$("#${it.id}").empty();
					for(i=0;i<result.length;i++)
					{
						console.log('打开窗口事件  divradio '+i+' id='+result[i].${it.idField}+',name='+result[i].${it.nameField}+',selRowId[${it.id}] ='+selRow['${it.id}']);
						if(selRow['${it.id}'] != null)
						{
							if(selRow['${it.id}']==result[i].${it.idField})
							{
								$("#${it.id}").append("<input type='radio' value='"+result[i].${it.idField}+"' id='${it.id}' name='${it.id}' onclick='${it.onclick}' checked/>"+result[i].${it.nameField});
							}
							else
							{
								$("#${it.id}").append("<input type='radio' value='"+result[i].${it.idField}+"' id='${it.id}' name='${it.id}' onclick='${it.onclick}'/>"+result[i].${it.nameField});
							}
						}
						else
						{
							$("#${it.id}").append("<input type='radio' value='"+result[i].${it.idField}+"' id='${it.id}' name='${it.id}' onclick='${it.onclick}'/>"+result[i].${it.nameField});
							console.log('divradio错误,请设置FieldInfo.id属性必须与Datagrid中列Id相同 ,或者单元格是空值');
						}
					}
				}
			});
		</#if>
        <#if it.type=="divcombobox">
        	$('#${it.id}').valueField='${it.idField}';
        	$('#${it.id}').textField='${it.nameField}';
        	$('#${it.id}').combobox('reload','${ctx}${it.url}');
        	var val = selRow['${it.id}'];
        	if(val != null)
        	{
        		$('#${it.id}').combobox('setValue', val);
        		//if(val.indexOf(',')>=0)
        		//{
	        	//	var dim = val.split(',');
	        	//	for(var i=0;i<dim.length;i++)
	 			//		$('#${it.id}').combobox('setValue', dim[i]);
 				//}
 				//else
 				//{
 				//	$('#${it.id}').combobox('setValue', val);
 				//}
 			}		
		</#if>
        <#if it.type=="checkbox">
			$("input[name='${it.id}']").each(function(){
				$(this).prop("checked",false);
			});
			$("input[name='${it.id}']").each(function(){
				var val = selRow['${it.id}']+'';
				if(val != null)
				{
					if(val.indexOf($(this).val()) >=0 )
					{
						$(this).prop("checked",true);
					}
					else
					{
						$(this).prop("checked",false);
					}
				}
			});		
		</#if>
        <#if it.type=="radio">
			$("input[name='${it.id}']").each(function(){
				$(this).prop("checked",false);
			});
			$("input[name='${it.id}']").each(function(){
				var val = selRow['${it.id}']+'';
				if(val != null)
				{
					if(val.indexOf($(this).val()) >=0 )
					{
						$(this).prop("checked",true);
					}
					else
					{
						$(this).prop("checked",false);
					}
				}
			});		
		</#if>
        <#if it.type=="text">
			$('#${it.id}').textbox('setValue',selRow['${it.id}']);
		</#if>
        <#if it.type=="date">
		    //var unixTimestamp = new Date(selRow['${it.id}']);
			$('#${it.id}').datebox('setValue',selRow['${it.id}']);
		</#if>
        <#if it.type=="datetime">
		    var unixTimestamp = new Date(selRow['${it.id}']);
			$('#${it.id}').datetimebox('setValue',selRow['${it.id}']);
		</#if>
        <#if it.type=="combobox">
			$('#${it.id}').combobox('select',selRow['${it.id}']);
		</#if>
        <#if it.type=="hidden">
			$('#${it.id}').val(selRow['${it.id}']);
		</#if>
</#list>
	};
	//保存数据的方法
	function save${id}(){
		var submiturl = '${ctx}${url}';
		console.log('保存窗口事件  save${id} submiturl='+submiturl);
		var para = "";
<#list flds as it>
        <#if it.type=="divcheckbox">
			var chkval = "";
			//循环选中的checkbox,以逗号分割
		    $('input[name="${it.id}"]:checked').each(function(i){
		       if(0==i){
		    	   chkval = $(this).val();
		       }else{
		    	   chkval+= (","+$(this).val());
		       }
		    });
		    para = '${it.datagridId}=' + chkval + '&';
		    console.log('保存窗口事件  save${id} divcheckbox para='+para);
		</#if>
        <#if it.type=="divradio">
			var chkval = "";
			//循环选中的checkbox,以逗号分割
		    $('input[name="${it.id}"]:checked').each(function(i){
		       if(0==i){
		    	   chkval = $(this).val();
		       }else{
		    	   chkval+= (","+$(this).val());
		       }
		    });
		    para = para + '${it.datagridId}=' + chkval + '&';
		    console.log('保存窗口事件  save${id} divradio para='+para);
		</#if>
</#list>
		//submiturl = submiturl + '?' + para;
		console.log('保存窗口事件  save${id} submiturl='+submiturl);
		var dgurl = '${ctx}'+$('#${datagrid}').datagrid('options').url;
		console.log('保存窗口事件  save${id} dgurl='+dgurl);
		console.log('save${id} ${fromId}='+$('#${fromId}').serialize());
		$('#${fromId}').form('submit', {
			url: submiturl,//将选中id追加url
			success: function(data){
				//如果同步调用消息返回true,刷新datagrid
				console.log('保存窗口事件  save${id} success: function(data)='+data);
				if('true'==data)
				{
					console.log('保存窗口事件  ${datagrid}.url)='+$('#${datagrid}').datagrid('options').url);
					$('#${datagrid}').datagrid({   
					    url:$('#${datagrid}').datagrid('options').url
					});
					$.messager.alert('结果','保存成功');
					$('#${id}').window('close');
				}
				else
				{
					$.messager.alert('结果','保存失败');
				}				
			}
		});
	}
	function close${id}(){
		$('#${id}').window('close');
	}
	
	var ${id} = 	'Window Demo 代码------>\n'+
	'<c1601U:window id="win" url="/stu/save" title="新增学生" \n'+
	'	fromId="addfrm" width="500" height="400" datagrid="dg">\n'+
	'	<c1601U:field type="text" id="no" label="编号" width="200"/>\n'+
	'	<c1601U:field type="text" id="name" label="姓名" width="200"/>\n'+
	'	<c1601U:field type="date" id="dt" label="日期" width="200"/>\n'+
	'	<c1601U:field type="hidden" id="id" label="编号" width="200"/>\n'+
	'	<c1601U:field type="divcheckbox" id="chkid" label="编号" width="200" url="/stu/findhobby">\n'+
	'\n'+
	'	</c1601U:field>\n'+
	'	<c1601U:button click="savewin()" name="保存"/>\n'+
	'	<c1601U:button click="closewin()" name="关闭"/>\n'+
	'</c1601U:window>\n'+
	'生成的Javascript方法：\n'+
	'close${id}()：关闭当前窗口\n'+
	'save${id}()： 提交当前窗口\n'+
	'open${id}(index,ismodify)：打开当前窗口显示详情,index为Datagrid某行数据的行序号,ismodify是否运行更新，布尔类型\n'+
	'add${id}()：打开新增当前窗口\n'+
	'<------Window Demo 代码\n';
	console.log(${id});
</script>