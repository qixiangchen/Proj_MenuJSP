
<#assign hasDate= "false">
<table id="${id}" title="${title}" class="easyui-datagrid"  
            url="${ctx}${url}"  
            pagination="true" pageList="[${pageDim}]" pageSize="${pageSize}"
            rownumbers="false" fitColumns="true" singleSelect="${selectOne}">  
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
					<#else>
		                <#if it.formatter?default("")?trim?length gt 1>
							,formatter:${it.formatter}
						</#if>
					</#if>
                ">${it.name}</th>
</#list> 
            </tr>
        </thead>  
</table>
<#list btns as it>
	<a class="easyui-linkbutton" iconcls="icon-ok" href="javascript:void(0)" onclick="${it.click}">${it.name}</a>
</#list>

<script>
    <#if hasDate =="true">
		//定制datagrid时间一列单元格的显示控件
		function ${id}formatDatebox(value,row,index){
			var unixTimestamp = new Date(value);
			return formatDate(value); 
		}
	</#if>
</script>
