<#if region??>
	<div data-options="region:'${region}',split:true,title:'${title}'" style="width:${panelWidth};padding:10px;">
</#if>
	<!-- 定义树状控件 -->
	<div class="easyui-panel" style="padding:0px" data-options="border:false">
		<ul id="${id}" class="easyui-tree" style="width:${width}" data-options="url:'${ctx}/${url}',method:'${method}',checkbox:${checkbox}"></ul>
	</div>
<#if region??>
	</div>
</#if>

<script>
	//当点击功能菜单时，在导航区域显示导航树
	function reload${id}()
	{
		$('${id}').tree({
		    url:'${ctx}/${url}'
		});
	}
	//当点击Tree控件上节点时，在右侧办公区域打开窗口
	$('#${id}').tree({
		onClick: function(node){
			if(node.id != '')
			{
				${id}Onclick(node);
			}
		}
	});
	//获得选中树状节点
	function get${id}Checked(){
		var nodes = $('#${id}').tree('getChecked');
		var s = '';
		for(var i=0; i<nodes.length; i++){
			if (s != '') s += ',';
			s += nodes[i].id;
		}
		return s;
	}
		
	var ${id} = 	'Tree 生成JS代码------>\n'+
	'reload${id}Tree() 自动刷新${id}树\n'+
	'当点击${id}上的节点回调tree${id}Onclick方法\n'+
	'开发者需要编写方法tree${id}Onclick,方法接收参数树节点node\n'+
	'获得选中树状节点get${id}Checked()\n'+
	'<------Tree 生成JS代码\n';
	
	console.log(${id});
</script>
