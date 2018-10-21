<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>    
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
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table class="easyui-datagrid" fit="true">
			<thead>
				<tr>
					<th data-options="field:'taskId',width:120">编号</th>
					<th data-options="field:'productName',width:120">商品</th>
					<th data-options="field:'startPostion',width:120">起始城市</th>
					<th data-options="field:'middiePostion',width:120">中转城市</th>
					<th data-options="field:'endPostion',width:120">到达城市</th>
					<th data-options="field:'customerName',width:120">发件人</th>
					<th data-options="field:'receiver',width:120">收件人</th>
					<th data-options="field:'receiverAddress',width:120">收件人地址</th>
					<th data-options="field:'telephone',width:120">收件人电话</th>
					<th data-options="field:'remark',width:120">客户备注</th>
					<th data-options="field:'status',width:120">状态</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#allList" var="task">
				<tr>
					<td> <s:property value="#task.taskId"/> </td>
					<td> <s:property value="#task.productName"/></td>
					<td> <s:property value="#task.startPostion"/></td>
					<td> <s:property value="#task.middiePostion"/></td>
					<td> <s:property value="#task.endPostion"/></td>
					<td> <s:property value="#task.customerName"/></td>
					<td> <s:property value="#task.receiver"/></td>
					<td> <s:property value="#task.receiverAddress"/></td>
					<td> <s:property value="#task.telephone"/></td>
					<td> <s:property value="#task.remark"/></td>
					<td> <s:property value="#task.status"/></td>
					<%--<td> --%>
						<%--<s:a action="workordermanage_check" cssClass="easyui-linkbutton" iconCls="icon-edit">审核--%>
							<%--<s:param name="id" value="#workOrderManage.id"></s:param>--%>
						<%--</s:a>--%>
					<%--</td>--%>
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</body>
</html>