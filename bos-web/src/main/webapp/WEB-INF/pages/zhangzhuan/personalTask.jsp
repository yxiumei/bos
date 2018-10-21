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
        function transfer(){
            var rows = $("#grid").datagrid("getSelections");
            if (1 == rows.length){
                var taskDto = rows[0];  // 是一个json对象
                var id = taskDto.taskNo;
                var safferNo = taskDto.safferNo;
                location.href ="${pageContext.request.contextPath }/findGroupTask_pickUpTask?wordIds="+id +"&"
                    +"safferNo=" + safferNo;
            }else{
                $.messager.alert("提示","请选择一个需要拾取的任务!","warning");
            }
        }

        //工具栏
        var toolbar = [ {
            id : 'button-view',
            text : '中转',
            iconCls : 'icon-redo',
            handler : transfer
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
            field : 'taskId',
            checkbox : true,
        },{
            field : 'productName',
            title : '商品名',
            width : 100,
            align : 'center'
        },{
            field : 'startPostion',
            title : '起始城市',
            width : 100,
            align : 'center'
            }, {
                field : 'endPostion',
                title : '目的城市',
                width : 100,
                align : 'center' ,
            }, {
                field : 'middiePostion',
                title : '中转地',
                width : 200,
                align : 'center'
            }, {
                field : 'customerName',
                title : '邮寄人',
                width : 120,
                align : 'center'
            }, {
                field : 'receiver',
                title : '收件人',
                width : 120,
                align : 'center',
            }, {
                field : 'receiverAddress',
                title : '收件地址',
                width : 200,
                align : 'center'
            },{
                field : 'telephone',
                title : '收件人电话',
                width : 100,
                align : 'center'
            },{
                field : 'remark',
                title : '备注',
                width : 100,
                align : 'center'
            },{
                field : 'staffName',
                title : '配送员',
                width : 100,
                align : 'center'
              }
            ,{
                field : 'status',
                title : '任务状态',
                width : 100,
                align : 'center',
               formatter : function(data,row, index){
                if(data=="1"){
                    return "配送中";
                }else{
                    return "已签收";
                }
            }
            },{
                field : 'taskTimeStr',
                title : '拾取任务时间',
                width : 100,
                align : 'center'
            }
        ]];

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

            // 任务信息表格
            $('#grid').datagrid( {
                iconCls : 'icon-forward',
                fit : true,  // 分页条是否自动适应
                border : true,
                rownumbers : true, // 显示行数
                striped : true,
                pageList: [10,20,30],    // 页面显示数据大小
                pagination : true,   // 是否显示 分页
                toolbar : toolbar,   // 显示工具栏
                url : "${pageContext.request.contextPath}/personalTask_personalList",  // 请求地址
                idField : 'taskNo',  // id
                columns : columns,  // 显示的字段
            });

        });
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
</body>
</html>