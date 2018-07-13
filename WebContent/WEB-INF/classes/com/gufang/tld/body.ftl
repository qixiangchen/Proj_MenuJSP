<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/icon.css">   
<script type="text/javascript" src="${ctx}/easyui/jquery.min.js"></script>   
<script type="text/javascript" src="${ctx}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/easyui/easyui-lang-zh_CN.js"></script>
<title>Main</title>

<script>
//格式化时间方法，通用 
function formatDate(value) {
	var date = new Date(value);
	var year = date.getFullYear().toString();
	var month = (date.getMonth() + 1);
	var day = date.getDate().toString();
	var hour = date.getHours().toString();
	var minutes = date.getMinutes().toString();
	var seconds = date.getSeconds().toString();
	if (month < 10) {
	month = "0" + month;
	}
	if (day < 10) {
	day = "0" + day;
	}
	if (hour < 10) {
	hour = "0" + hour;
	}
	if (minutes < 10) {
	minutes = "0" + minutes;
	}
	if (seconds < 10) {
	seconds = "0" + seconds;
	}
	return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
}
function getSelected(id){
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
</script>

</head>
<body class="easyui-layout">


<div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">


