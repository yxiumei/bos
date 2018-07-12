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
	
		
	
	<!-- 使用easyUI提供的API创建datagrid -->
	<table id="mytable" class="easyui-datagrid">
		
	</table>
	<script type="text/javascript">
	var myindex = -1;
		// 页面加载完成后创建datagrid表格
		$("#mytable").datagrid({
			columns:[[
				{width:150,title:'编号',field:'id',checkbox:true},
				{width:150,title:'姓名',field:'name',editor:{
					type:'validatebox',
					options:{}
					
				}},
				{width:150,title:'日期',field:'age',editor:{
						type:'datebox',
						options:{}
				}}
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
				        	 	$("#mytable").datagrid('insertRow',{
				        	 		index:0,
				        	 		row:{}
				        	 	});
				        	 	
				        	 	$("#mytable").datagrid("beginEdit",0);
				        	 	myindex = 0;
				         	 }
				         },
				         {text:'删除',iconCls:'icon-remove',handler:function(){
				         		// 获得选中的行
				         		var rows = $("#mytable").datagrid("getSelections");
				         		 if (rows.length == 1){
				         			var row = rows[0];
				        		 //获得指定行对象的索引
				        		 myIndex = $("#mytable").datagrid("getRowIndex",row);
				         		} 
				         		 $("#mytable").datagrid("deleteRow",myIndex);
				         		
				         }},
				         {text:'修改',iconCls:'icon-edit',handler:function(){
				         	     var rows = $("#mytable").datagrid("getSelections");
				         		 if (rows.length == 1){
				         			  var row = rows[0];
				        		 //获得指定行对象的索引
				        		      myIndex = $("#mytable").datagrid("getRowIndex",row);
				         		} 
				         			$("#mytable").datagrid("beginEdit",myIndex);
				         			myindex = myIndex;
				         					}},
				         {text:'保存',iconCls:'icon-search',handler:function(){
				         		
				         			$("#mytable").datagrid("endEdit",myindex);
				         }}
				         ],
			// 显示分页
			pagination:true,
			// 数据表格用于提示监听结束事件
			onAfterEdit:function(index,data,change){
				alert(data.name);
			}
		});
	</script>
</body>
</html>
