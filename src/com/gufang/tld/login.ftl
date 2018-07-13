<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${ctx}/easyui/themes/icon.css">   
<script type="text/javascript" src="${ctx}/easyui/jquery.min.js"></script>   
<script type="text/javascript" src="${ctx}/easyui/jquery.easyui.min.js"></script> 
<title>Login</title>
<script>
function login(){
	loginForm.submit();
}
</script>
</head>

<body>
    <div id="loginWin" class="easyui-window" title="登录" style="width:350px;height:188px;padding:5px;"
         minimizable="false" maximizable="false" resizable="false" collapsible="false">
        
        <div class="easyui-layout" fit="true">
        
            <div region="center" border="false" style="padding:5px;background:#fff;border:1px solid #ccc;">
                <form id="loginForm" action="${ctx}${url}" method="post">
                    <div style="padding:5px 0;">
                    	<input class="easyui-textbox" name="${nameId}" data-options="prompt:'${nameLabel}',validType:'name'" iconCls="icon-man" iconAlign=left style="width:100%;height:32px"/>
                    </div>
                    <div style="padding:5px 0;">
                    	<input type="password" class="easyui-textbox" name="${pwdId}" data-options="prompt:'${pwdLabel}',validType:'password'" iconCls="icon-lock" iconAlign=left style="width:100%;height:32px">

                    </div>
                    <div style="padding:5px 0;text-align: center;color: red;" id="showMsg"></div>
                </form>
            </div>
            <div region="south" border="false" style="text-align:right;padding:5px 0;">
                <a class="easyui-linkbutton" iconcls="icon-ok" href="javascript:void(0)" onclick="login()">${loginLabel}</a>
            </div>
            
        </div>
        
    </div>
</body>
</html>