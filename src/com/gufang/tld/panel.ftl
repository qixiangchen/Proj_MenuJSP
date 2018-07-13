<#if region??>
	<div data-options="region:'${region}',split:true,title:'${title}'" style="width:${width};padding:10px;">
</#if>

<div class="easyui-panel" title="" style="width:${width}px;height:${height}px;"> 
	<br>
	<form id="${id}" method="post" action="${ctx}${url}">
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
				<select id="${it.id}" class="easyui-combobox"  data-options="label:'${it.label}'" name="${it.id}" valueField="${it.idField}" textField="${it.nameField}" style="width:200px;">
				   <#list it.options as opt>
				   		<option value="${opt.id}">${opt.name}</option>
				   </#list> 
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
				<input class="easyui-datebox" id="${it.id}" name="${it.id}"
			data-options="required:true,label:'${it.label}'" value="3/4/2010" style="width:250px"> 
			</div>
		</#if>
        <#if it.type=="datetime">
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
	<div id="${id}_btndiv" style="text-align:center;padding:5px 0">
<#list btns as it>
	<a class="easyui-linkbutton" iconcls="icon-ok" href="javascript:void(0)" onclick="${it.click}">${it.name}</a>
</#list>
	</div>
</div>


<#if region??>
	</div>
</#if>

<script>
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
		    console.log('保存窗口事件  save${id} divradio para='+para);
		</#if>
</#list>
		//submiturl = submiturl + '?' + para;
		console.log('保存窗口事件  save${id} submiturl='+submiturl);
		console.log('save${id} ${id}='+$('#${id}').serialize());
		$('#${id}').form('submit', {
			url: submiturl,//将选中id追加url
			success: function(data){
				console.log('保存窗口事件  save${id} success: function(data)='+data);
				if('true'==data)
				{
					$.messager.alert('结果','保存成功');
				}
				else
				{
					$.messager.alert('结果','保存失败');
				}				
			}
		});
	}
		
	var ${id} = 	'Panel 生成JS代码------>\n'+
	'save${id} 保持表单\n'+
	'<------Panel 生成JS代码\n';
	
	console.log(${id});
</script>
