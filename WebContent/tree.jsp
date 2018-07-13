<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gufang.bean.*,com.gufang.tld.*,java.util.*"%>
<%@ taglib uri="/c1510bc" prefix="c1510b"%>

<%
List<FieldInfo> flds = new ArrayList<FieldInfo>();
FieldInfo f4 = new FieldInfo("divradio","chkid","","/stu/findhobby");
flds.add(f4);

FieldInfo f1 = new FieldInfo("text","no","编号","");
flds.add(f1);
FieldInfo f2 = new FieldInfo("text","name","姓名","");
flds.add(f2);
FieldInfo f3 = new FieldInfo("date","dt","日期","");
flds.add(f3);
FieldInfo f7 = new FieldInfo("hidden","id","编号","");
flds.add(f7);
List<ButtonInfo> btns2 = new ArrayList<ButtonInfo>();
ButtonInfo wbi = new ButtonInfo("saveaddfrm()","保存");
btns2.add(wbi);


pageContext.setAttribute("flds", flds);
pageContext.setAttribute("btns2", btns2);
%>

<c1510b:body>
		
	<script>
		function ttOnclick(node)
		{
			$('#no').textbox('setValue',node.text);
		}
	</script>

	<c1510b:tree id="tt" width="30%" url="/stu/findorg" region="west" title="部门"/>
	
	<c1510b:panel id="addfrm" url="/stu/save" title="新增学生" region="center"
		width="500" height="400"
		flds="${pageScope.flds}" 
		btns="${pageScope.btns2}"/>
</c1510b:body>

	
