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
<script type="text/javascript">
	$(function() {
		//页面加载完成后，为上面的按钮绑定事件
		
	$("#but12").click(function() {
			// 判断选择的面板是否存在
			var exit = $("#mytabs1").tabs("exists","系统管理");
			//alert(exit);
			if (exit){
			   // 存在，选中皆可以可以
			   $("#mytabs1").tabs("select","系统管理");
			}else{
				$("#mytabs1").tabs("add", {
					title:'系统管理',
					iconCls:'icon-edit',
					closable:true, // 显示关闭图标
					// 所要显示的界面
					content:'<iframe frameborder="0" width="100%" height="100%" src="#"></iframe>'
			   });
		  
			
	       }
		 });	
	});
</script>

<body class="easyui-layout">
	<!-- 使用div元素描述每个区域 -->
	<div title="xxx管理系统" style="height: 100px"
		data-options="region:'north'">北部区域</div>
	<div style="width: 200px" data-options="region:'west'">
		<div class="easyui-accordion" data-options="fit:true">
			<div title="用户管理" data-options="iconCls:'icon-cut'">
				<a id="but12" class="easyui-linkbutton">添加用户</a>

			</div>
			<div title="面板二">
				<!-- 展示ztree效果：使用标准json数据构造 -->
				<ul id="ztree1" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						// 页面加载完成执行，动态创建ztree
						var setting ={};
						// 构造节点数据
						var zNodes=[
							{"name":"节点一","children":
													[{"name":"节点一_1"},
													{"name":"节点一_2"}]
							
							},
							{"name":"节点二"},
							{"name":"节点三"}
						];
						//调用API初始化ztree
						$.fn.zTree.init($("#ztree1"), setting, zNodes);
					});
				
				</script>
			
			</div>
			<div title="面板三">
			<ul id="ztree2" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						// 页面加载完成执行，动态创建ztree
	
					var setting1 = {
							data : {
								simpleData : {
									enable : true  // 使用简单json构造
							
								}
							}
						};
						// 构造节点数据
						var zNodes = [ 
							{"id":"1","pId":"3","name":"节点一"}, // 每个json对象
							{"id":"2","pId":"3" ,"name": "节点二"},
							{"id":"3","pId":"0" ,"name": "节点三"} 
							 ]; 
						//调用API初始化ztree
						$.fn.zTree.init($("#ztree2"), setting1, zNodes);
					});
				</script>
			</div>
			
			<div title="面板四">
			<ul id="ztree3" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						// 页面加载完成执行，动态创建ztree
	
					var setting1 = {
							data : {
								simpleData : {
									enable : true  // 使用简单json构造
							
								}
								
							},
							callback: { // 回调函数
								    // 点击节点时触发该方法
									onClick: function(event, treeId, treeNode){
									     // 只有是叶子节点才选中
										if (treeNode.page != undefined){
											
									     // 判断该选项卡是否存在
									     var e = $("#mytabs1").tabs("exists",treeNode.name);
									     // 如果存在则选择
									    if (e){
									    	$("#mytabs1").tabs("select",treeNode.name);
									    }else{
											 // 添加一个选项卡
											 $("#mytabs1").tabs("add", {
											 		title:treeNode.name,
											 		closable:true,
											 		content:'<iframe frameborder="0" width="100%" height="100%" src="'+treeNode.page+'"></iframe>'
											 })
										    
									    }
										}
										 
									}
								}
						};
						var url = "${pageContext.request.contextPath}/json/menu.json";
						// 使用ajax从文件中读取构建
						$.post(
							url,
							function(data){
							  //调用API初始化ztree
						      $.fn.zTree.init($("#ztree3"), setting1, data);
							},
							"json"
						);
						
					});
				</script>
			</div>
		</div>

	</div>
	<div data-options="region:'center'">

		<div id="mytabs1" class="easyui-tabs" data-options="fit:true">
			<div data-options="iconCls:'icon-cut'" title="面板一">111111111</div>
			<div data-options="closable:true" title="面板二">2222222222</div>
			<div title="面板三">3333333333</div>
		</div>
	</div>
	<div style="width: 100px" data-options="region:'east'">东部区域</div>
	<div style="height: 50px" data-options="region:'south'">南部区域</div>
</body>
</html>
