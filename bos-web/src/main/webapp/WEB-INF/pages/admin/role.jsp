<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<!-- 导入ztree类库 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>	
<script type="text/javascript">



	$(function(){
		// 数据表格属性
		$("#grid").datagrid({
			toolbar : [
				{
					id : 'add',
					text : '添加角色',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/page_admin_role_add.action';
					}
				},{
					id : 'edit',
					text : '修改角色',
					iconCls : 'icon-edit',
					handler : editRole
				}           
			],
			url : '${pageContext.request.contextPath}/roleAction_pageQuery',
			pagination:true,
			fit:true,
			columns : [[
				{
					field : 'id',
					title : '编号',
					width : 200,
					checkbox : true // 勾选框
				},
				{
					field : 'name',
					title : '名称',
					width : 200
				}, 
				{
					field : 'description',
					title : '描述',
					width : 200
					
				} 
			]]
		});
		
		// 修改角色窗口
		$('#editWindow').window({
			title : '角色修改',
			width : 540,
			modal : true,
			shadow : true,
			closed : true,
			height : 420,
			resizable : false
		});
		
		
	// 修改保存
	$("#edit_role").click(function(){
		
		var v = $("#editRoleForm").form("validate");
		if (v){
			// 获得当前Ztree对象
			var treeObj = $.fn.zTree.getZTreeObj("functionTree");
			// 获得zTree上选择的节点，返回数组
			var nodes = treeObj.getCheckedNodes(true);
			// 为隐藏域添加值
			var array = new Array();
			for (var i = 0 ; i< nodes.length;i++){
				array.push(nodes[i].id)
			}
			var functionIds = array.join(",");
			$("input[name=functionIds]").val(functionIds);
			$("#editRoleForm").submit();
			}
			alert(1);
		});
	});
	
		// 授权树初始化
		var setting = {
			data : {
				key : {
					title : "t"
				},
				simpleData : {
					enable : true
				}
			},
			check : {
				enable : true, // 出现选择框
			}
		};
		
		
		
		
		zTreeObj :{
		  var treeObj = $.fn.zTree.getZTreeObj("functionTree");
		  
		  var nodes = treeObj.getNodes();
		  
		  if (nodes.length>0) {
		  	
			  treeObj.selectNode(nodes[0]);
			}
			
		}
		
	// 编辑角色
	function editRole(){
	    //定义全局变量，存所勾选用户角色的权限对象
		var roleFunction = null;
		var rows = $("#grid").datagrid("getSelections");
		if (rows.length == 1){
		    $("#editWindow").window("open");
			$("#editRoleForm").form("load",rows[0]);
			// 通过选中的角色查看限权,使用同步加载
			 $.ajax({
			 	url : "${pageContext.request.contextPath}/functionAction_findFunctionByRoleId",
			 	async: false,
				data:{roleId:rows[0].id},
				success:function(data){
				  roleFunction = data;
				  }
			}); 
			// 异步加载出所有的限权
			$.ajax({
			url : '${pageContext.request.contextPath}/functionAction_listAjax',
			type : 'POST',
			dataType : 'json',
			success : function(data) {
				//alert("后到这里");
				//var zNodes = eval("(" + data + ")"); // 把字符串装成json格式解析
				// 获得zTree对象
			    var treeObj = $.fn.zTree.init($("#functionTree"), setting, data);
			    for (var i = 0; i < roleFunction.length; i++){
			    	// 更具id搜索，获得与id匹配的节点，返回的是一个数组
			    	var nodes = treeObj.getNodesByParam("id", roleFunction[i].id, null);
			    	// 对匹配的节点进行勾选
			    	treeObj.checkNode(nodes[0], true, false);
			    }
			    
			   // var nodes1 = treeObj.getNodesByParam("id", "112", null);
			    //var nodes1 = treeObj.getNodesByParam("id", "11", null);
			    /* treeObj.checkNode(nodes1[0], true, false);
			    treeObj.checkNode(nodes[0], true, false); */
			   // treeObj.checkNode(nodes2[0], true, flase);
				//alert(roleFunction.length);
			},
			error : function(msg) {
				alert('树加载异常!');
			}
		});
			
		}else{
			$.messager.alert("提示信息","请选择一个角色！","warning");
		}
	}
</script>	
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="grid"></table>
	</div>
	
	<!-- 修改角色 -->
	<div class="easyui-window" title="角色修改" id="editWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false"
			border="false">
			<div class="datagrid-toolbar">
				<a id="edit_role" icon="icon-save"  class="easyui-linkbutton"
					plain="true">保存</a>
			</div>
		</div>
	
	<div region="center" id="editWindow" style="overflow:auto;padding:5px;" border="false">
			<form id="editRoleForm"  action="${pageContext.request.contextPath }/roleAction_add" method="post">
				<input type="hidden" name="id">
				<input type="hidden" name="functionIds">
				<table class="table-edit" width="85%" align="center">
					<tr class="title">
						<td colspan="2">角色信息</td>
					</tr>
					<tr>
						<td>名称</td>
						<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>描述</td>
						<td>
							<textarea name="description" rows="4" cols="60"></textarea>
						</td>
					</tr>
					<tr>
						<td>授权</td>
						<td>
							 <ul id="functionTree" class="ztree"></ul> 
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>