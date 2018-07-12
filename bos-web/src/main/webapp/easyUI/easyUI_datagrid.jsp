<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'easyUI_layout.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>layout</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	<!-- 使用zTree -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


</head>


<body>
<!-- 静态html使用渲染 -->
	<table class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>小明</td>
				<td>90</td>
			</tr>
			<tr>
				<td>002</td>
				<td>老王</td>
				<td>3</td>
			</tr>
		</tbody>
	</table>
	<hr>
	<!-- 发送ajax请求获取json数据创建datagrid -->
	<table data-options="url:'${pageContext.request.contextPath }/json/datagrid_data.json'" 
			class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
	</table>
	<hr>
	<!-- 使用easyUI提供的API创建datagrid -->
	<table id="mytable" class="easyui-datagrid">
		
	</table>
	<script type="text/javascript">
		// 页面加载完成后创建datagrid表格
		$("#mytable").datagrid({
			columns:[[
				{title:'编号',field:'id',checkbox:true},
				{title:'姓名',field:'name'},
				{title:'年龄',field:'age'}
			]],
			// 指定数表格发送ajax请求的地址
			url:'${pageContext.request.contextPath }/json/datagrid_data.json',
			// 显示行数
			rownumbers:true,
			// 只能选择一行
			singleSelect:true,
			// 自定义工具栏
			toolbar:[
				         {text:'添加',iconCls:'icon-add',
				        	 //为按钮绑定单击事件
				        	 handler:function(){
				        	 	alert('add...');
				         	 }
				         },
				         {text:'删除',iconCls:'icon-remove'},
				         {text:'修改',iconCls:'icon-edit'},
				         {text:'查询',iconCls:'icon-search'}
				         ],
			// 显示分页
			pagination:true
		});
	</script>
</body>
</html>
