<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
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
	<script type="text/javascript">
        function doAdd(){
            //alert("增加...");
            $('#addStaffWindow').window("open");
        }
        // 任务查询
        function doView(){
            $('#searchWindow1').window("open");
        }



        // 删除取派员，逻辑删除
        function doDelete(){
            var rows = $("#grid").datagrid("getSelections");
            if (rows !=0){
                // 确认提示
                $.messager.confirm("确认删除","您确认要删除所选则的取派员吗？",function(flg){
                    var array = new Array(); // 创建一个数组
                    //获得所选取派员的id
                    if (flg){ // 确定删除
                        for (var i = 0; i < rows.length; i ++){
                            // 从数组中获得每一个对象
                            var staff = rows[i];  // 是一个json对象
                            var id = staff.id;
                            array.push(id);
                        }
                        // 将数组拼接成字符串
                        var ids  = array.join(",");  // 1,2,2,3
                        // 将所有的用户id传到后台进行逻辑删除
                        location.href ="${pageContext.request.contextPath }/staffAction_delBatch?ids="+ids;
                    }

                });

            }else{
                $.messager.alert("提示","请选择需要删除的取派员!","warning");
            }
        }

        // 拾取任务
        function pickUp(){
            var rows = $("#grid").datagrid("getSelections");
            if (1 == rows.length){
				var taskDto = rows[0];  // 是一个json对象
				if (taskDto.status == "派送中" ||taskDto.status == "已签收" ){
                    $.messager.alert("提示","该任务以拾取!","warning");
                    return ;
				}
				var id = taskDto.taskNo;
				var safferNo = taskDto.safferNo;
                location.href ="${pageContext.request.contextPath }/findGroupTask_pickUpTask?wordIds="+id +"&"
					+"safferNo=" + safferNo;
            }else{
                $.messager.alert("提示","请选择一个需要拾取的任务!","warning");
            }
        }

        function doRestore(){
            var rows = $("#grid").datagrid("getSelections");
            var num = rows.length;
            // 创建一个数组
            var array1 = new  Array();
            if (num == '0'){
                $.messager.alert("提示","请选择需要恢复的取派员!","warning");
            }else{
                for (var i = 0;i < num; i++){
                    // 取出每一个对象
                    var staff = rows[i];
                    if (staff.deltag == '0'){ // 表示以正常使用的
                        $.messager.alert("提示","不能选择未删除的数据!","warning");
                        return;
                    }
                    var id = staff.id;
                    array1.push(id);
                }
                // 将数组拼接成字符串
                var ids = array1.join(",");

                // 将所有的用户id传到后台进行逻辑恢复

            }
        }
        //工具栏
        var toolbar = [ {
            id : 'button-view',
            text : '查询',
            iconCls : 'icon-search',
            handler : doView
        }, {
			id : 'button-view',
			text : '拾取任务',
			iconCls : 'icon-redo',
			handler : pickUp
		}
            <%--<shiro:hasPermission name="staff.delete">--%>
            <%--{--%>
                <%--id : 'button-delete',--%>
                <%--text : '删除',--%>
                <%--iconCls : 'icon-cancel',--%>
                <%--handler : doDelete--%>
            <%--},--%>
            <%--</shiro:hasPermission>--%>
            // {
            //     id : 'button-save',
            //     text : '还原',
            //     iconCls : 'icon-save',
            //     handler : doRestore
            // }
		      ];
        // 定义列
        var columns = [ [ {
            field : 'taskNo',
            checkbox : true,
        },{
            field : 'product',
            title : '商品名',
            width : 120,
            align : 'center'
        }
        ,{
			field : 'safferNo',
			title : '取派员编号',
			width : 120,
			align : 'center'
            }, {
			field : 'safferName',
			title : '取派员姓名',
			width : 120,
			align : 'center' ,
            }, {
            field : 'safferTelephone',
            title : '取派员电话',
            width : 120,
            align : 'center'
        }, {
			field : 'startCity',
			title : '起始城市',
			width : 120,
			align : 'center'
            }, {
			field : 'arrivecity',
			title : '到达城市',
			width : 120,
			align : 'center',
            }, {
            field : 'pickaddress',
            title : '取件详细地址',
            width : 200,
            align : 'center'
        },{
          	field : 'status',
			title : '任务状态',
			width : 100,
            align : 'center'
		} ] ];

        $(function(){

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

            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility:"visible"});

            // 取派员信息表格
            $('#grid').datagrid( {
                iconCls : 'icon-forward',
                fit : true,  // 分页条是否自动适应
                border : true,
                rownumbers : true, // 显示行数
                striped : true,
                pageList: [10,20,30],    // 页面显示数据大小
                pagination : true,   // 是否显示 分页
                toolbar : toolbar,   // 显示工具栏
                url : "${pageContext.request.contextPath}/findGroupTask_taskList",  // 请求地址
                idField : 'taskNo',  // id
                columns : columns,  // 显示的字段
                // onDblClickRow : doDblClickRow
            });

            // 添加取派员窗口
            $('#addStaffWindow').window({
                title: '添加取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable:false
            });

            // 查询取派员窗口
            $('#searchWindow1').window({
                title: '任务查询',
                width: 380,
                modal: true,
                shadow: true,
                closed: true,
                height: 350,
                resizable:false
            });
            // 点击查询
            $("#btn").click(function(){
                // 将指定form表单中的输入项转换成json格式
                var json = $("#searchStaffFrom").serializeJson();
                // 调用数据表格load方法，发送ajax加载
                $("#grid").datagrid("load", json);
                //把输入框中的值设置为空
                $("#searchName").val("");
                $("#searchTelephone").val("");
                $("#searchStation").val("");
                // 关闭窗口
                $('#searchWindow1').window("close");
            });

        });


        // 添加派送员对手机进行验证,页面加载完执行
        $(function(){
            //为保存按钮绑定事件
            $("#save").click(function(){
                //表单校验，如果通过，提交表单
                var v = $("#addSatffForm").form("validate");
                if (v) {
                    //$("#addStaffForm").form("submit");
                    $("#addSatffForm").submit();
                }
            });

            // 书写正则表达式
            var reg = /^1[3|4|5|7|8][0-9]{9}$/;
            // 扩展手机号验证规则
            $.extend($.fn.validatebox.defaults.rules, {
                telephore : {
                    validator : function(value, param) {

                        // 对输入的值进行正则验证
                        return reg.test(value);

                    },
                    message : '手机号输入错误！'

                }
            });

            //为编辑保存按钮添加事件
            $("#edit").click(function(){
                //表单校验，如果通过，提交表单
                var v = $("#editSatffForm").form("validate");
                if (v) {
                    $("#editSatffForm").submit();
                }
            });
            // 书写正则表达式
            var reg = /^1[3|4|5|7|8][0-9]{9}$/;
            // 扩展手机号验证规则
            $.extend($.fn.validatebox.defaults.rules, {
                telephore : {
                    validator : function(value, param) {

                        // 对输入的值进行正则验证
                        return reg.test(value);

                    },
                    message : '手机号输入错误！'

                }
            });

        });
	</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
	<table id="grid"></table>
</div>
<div class="easyui-window" title="对收派员进行添加" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
	<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save"  class="easyui-linkbutton" plain="true" >保存</a>
		</div>
	</div>

	<div region="center" style="overflow:auto;padding:5px;" border="false">
		<form id="addSatffForm"  action="${pageContext.request.contextPath }/staffAction_add" method="post">
			<table class="table-edit" width="80%" align="center">
				<tr class="title">
					<td colspan="2">收派员信息</td>
				</tr>
				<!-- TODO 这里完善收派员添加 table -->

				<tr>
					<td>姓名</td>
					<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>手机</td>
					<!-- 添加自定义的验证规则 -->
					<td><input type="text" data-options="validType:'telephore'" name="telephone" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>单位</td>
					<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
				</tr>
				<tr>
					<td>取派标准</td>
					<td>
						<input type="text" name="standard" class="easyui-validatebox" required="true"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<!-- 查询取派员-->
<div class="easyui-window" title="查询任务窗口" id="searchWindow1" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
	<div style="overflow:auto;padding:5px;" border="false" >
		<form id="searchStaffFrom">
			<table class="table-edit" width="80%" align="center">

				<tr class="title">
					<td colspan="2">查询条件</td>
				</tr>
				<tr>
					<td>配送员编号</td>
					<td><input type="text" id="safferNo" name="staff.id"/></td>
				</tr>
				<%--<tr>--%>
					<%--<td>配送员电话</td>--%>
					<%--<td><input type="text" id="searchTelephone" name="telephone"/></td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td>起始城市</td>--%>
					<%--<td><input type="text" id="startCity" name="startCity"/></td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td>到达城市</td>--%>
					<%--<td><input type="text" id="arrivecity" name="arrivecity"/></td>--%>
				<%--</tr>--%>
				<tr>
					<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> </td>
				</tr>
			</table>
		</form>
	</div>
</div>

</body>
</html>