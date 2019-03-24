<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理分区</title>
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
<!-- 引入hightCharts -->
<script
	src="${pageContext.request.contextPath }/js/hightCharts/js/highcharts.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/hightCharts/js/modules/exporting.js"
	type="text/javascript"></script>
 <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
	
<script type="text/javascript">
	function doAdd(){
		$('#addSubareaWindow').window("open");
	}
	// 编辑分区
	function doEdit(){
		var rows = $("#grid").datagrid("getSelections");
		if(rows.length == 1){
		   //alert(rows[0].region.province);
			$('#editSubareaWindow').window("open");
			// 为隐藏域添加定区的id 
			$("#regionId").val(rows[0].region.id);
			// 回显数据
			
			$("#editSubareaFrom").form("load", rows[0]);
		}else{
			$.messager.alert("提示信息","请选择一个分区!","warning");
		}
	}
	// 删除分区
	function doDelete(){
		var rows = $("#grid").datagrid("getSelections");
		if (rows !=0){
			// 确认提示
			$.messager.confirm("确认删除","您确认要删除所选则的分区吗？",function(flg){
			var array = new Array(); // 创建一个数组
				//获得所选取派员的id
				if (flg){ // 确定删除
					for (var i = 0; i < rows.length; i ++){
						// 从数组中获得每一个对象
						var region = rows[i];  // 是一个json对象
						var id = region.id;
						array.push(id);
					}
					// 将数组拼接成字符串
					var ids  = array.join(",");  // 1,2,2,3
					
					// 将所有的用户id传到后台进行逻辑删除
					location.href ="${pageContext.request.contextPath }/subareaAction_delBatch?ids="+ids;
					
				}
				
			});
		
		}else{
			$.messager.alert("提示","请选择需要删除的分区!","warning");
		}
	}
	
	function doSearch(){
		$('#searchWindow').window("open");
	}
	
	function doExport(){
		window.location.href="${pageContext.request.contextPath}/subareaAction_exportEls"
	}
	
	// function doImport(){
	// 	alert("导入");
	// }
	
	//工具栏
	var toolbar = [ {
		id : 'button-search',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doSearch
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-edit',	
		text : '修改',
		iconCls : 'icon-edit',
		handler : doEdit
	},{
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-export',
		text : '导出',
		iconCls : 'icon-undo',
		handler : doExport
	},{
		id : 'button-showHightCharts',
		text : '显示区域分区分布图',
		iconCls : 'icon-search',
		handler : doShowCharts
	}
	];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'showid',
		title : '分拣编号',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.id;
		}
	},{
		field : 'province',
		title : '省',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.province;
		}
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.city;
		}
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center',
		formatter : function(data,row ,index){
			return row.region.district;
		}
	}, {
		field : 'addresskey',
		title : '关键字',
		width : 120,
		align : 'center'
	}, {
		field : 'startnum',
		title : '起始号',
		width : 100,
		align : 'center'
	}, {
		field : 'endnum',
		title : '终止号',
		width : 100,
		align : 'center'
	} , {
		field : 'single',
		title : '单双号',
		width : 100,
		align : 'center'
	} , {
		field : 'position',
		title : '位置',
		width : 200,
		align : 'center'
	} ] ];
	
	
	 // 定义一个工具方法将指定表单的输入项转换成json数据 {key:vaue,key:value}
		$.fn.serializeJson=function(){  
            var serializeObj={};  
            var array=this.serializeArray();
            $(array).each(function(){  
                if(serializeObj[this.name]){  
                    if($.isArray(serializeObj[this.name])){  
                        serializeObj[this.name].push(this.value);  
                    }else{  
                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
                    }  
                }else{  
                    serializeObj[this.name]=this.value;   
                }  
            });  
            return serializeObj;  
        };
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/subareaAction_pageQuery",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加、分区
		$('#addSubareaWindow').window({
	        title: '添加修改分区',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
	   // 修改定区 
	    $('#editSubareaWindow').window({
	        title: '修改分区',
	        width: 600,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
	   // 显示 区域饼状图窗口
	    $('#showSubareasHightChartsWindow').window({
	        width: 680,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 480,
	        resizable:false
	    });
		
		// 查询分区
		$('#searchWindow').window({
	        title: '查询分区',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
	    
	   
	    
		$("#btn").click(function(){
			// 将指定form表单中的输入项转换成joson格式
			var json = $("#searchFrom").serializeJson();
			// 调用数据表格load方法，发送ajax加载
			$("#grid").datagrid("load", json);
			// 关闭窗口
			$('#searchWindow').window("close");
		});
		
		
		
	// 为添加分区做验证
	$("#save").click(function() {
			var r = $("#addSubareaFrom").form("validate");
			if (r) {
				$("#addSubareaFrom").submit();
			}
		});

	});
	
	// 修改分区
	function edit(){
		var r = $("#editSubareaFrom").form("validate");
		  if (r) {
			  $("#editSubareaFrom").submit();
			}
	}
	
	// 编辑分区
	function doDblClickRow(rowIndex, rowData) {
		$('#editSubareaWindow').window("open"); // 打开窗口
		// 数据回显
		$("#regionId").val(rowData.region.id);
		$('#editSubareaFrom').form("load",rowData);
	}
	
	// 显示HightCharts
	function doShowCharts(){
		$("#showSubareasHightChartsWindow").window("open");
		$.post(
			'${pageContext.request.contextPath}/subareaAction_findSubareasGrupByProvince',
			function(data){
				$('#container').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text: '区域分区分布图'
			        },
			        tooltip: {
			            headerFormat: '{series.name}<br>',
			            pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: '区域分区数量',
			            data: data
			        }]
			    });
    	}); 
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<!-- 添加分区 -->
	<div class="easyui-window" title="分区添加修改" id="addSubareaWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="addSubareaFrom" action="${pageContext.request.contextPath }/subareaAction_addsubarea" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
					
					<tr>
						<td>选择区域</td>
						<td>
							<input class="easyui-combobox" name="region.id"  
    							data-options="valueField:'id',textField:'name',mode:'remote',url:'${pageContext.request.contextPath }/regionAction_ajaxGetList'" />  
						</td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td>
							<select class="easyui-combobox" name="single" style="width:150px;">  
							    <option value="0">单双号</option>  
							    <option value="1">单号</option>  
							    <option value="2">双号</option>  
							</select> 
						</td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position" class="easyui-validatebox" required="true" style="width:250px;"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 修改分区 -->
	<div class="easyui-window" title="分区添加修改" id="editSubareaWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit1_1" icon="icon-save" onclick="edit()"  class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div style="overflow:auto;padding:5px;" border="false">
			<form id="editSubareaFrom" action="${pageContext.request.contextPath }/subareaAction_editsubarea" method="post">
				<!-- 定区的id -->
				<input type="hidden" name="region.id" id="regionId">
				<input type="hidden" name="id" id="regionId">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">分区信息</td>
					</tr>
					
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>起始号</td>
						<td><input type="text" name="startnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>终止号</td>
						<td><input type="text" name="endnum" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单双号</td>
						<td>
							<select class="easyui-combobox" name="single" style="width:150px;">  
							    <option value="0">单双号</option>  
							    <option value="1">单号</option>  
							    <option value="2">双号</option>  
							</select> 
						</td>
					</tr>
					<tr>
						<td>位置信息</td>
						<td><input type="text" name="position" class="easyui-validatebox" required="true" style="width:250px;"/></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 查询分区 -->
	<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div style="overflow:auto;padding:5px;" border="false" >
			<form id="searchFrom">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">查询条件</td>
					</tr>
					<tr>
						<td>省</td>
						<td><input type="text" name="region.province"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text" name="region.city"/></td>
					</tr>
					<tr>
						<td>区（县）</td>
						<td><input type="text" name="region.district"/></td>
					</tr>
					<tr>
						<td>关键字</td>
						<td><input type="text" name="addresskey"/></td>
					</tr>
					<tr>
						<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<!-- 显示区域分布图 -->
	<div class="easyui-window" title="显示区域分布图" id="showSubareasHightChartsWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div  id="container"  split="false" border="false" >
			
		</div>
		
	</div>
</body>
</html>