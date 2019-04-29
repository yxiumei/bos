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
            $('#transferSearchWindow').window("open");
        }

        // 任务中转
        function transfer(){
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 1){
                if (rows[0].status != 1) {
                    $.messager.alert("提示","该任务已完结!","warning");
                    return;
                }
                $("#middiePostions").val('');
                $('#transferSearchWindow').window("open");
                $("#id").val(rows[0].taskId);
                $("#startPostions").val(rows[0].startPostion);
                $("#endPostions").val(rows[0].endPostion);
                $('#transfer').form("load",rows[0]);
            }else{
                $.messager.alert("提示","请选择一个需要中转的任务!","warning");
            }
        }

        //工具栏
        var toolbar = [ {
            id : 'button-view',
            text : '中转',
            iconCls : 'icon-redo',
            handler : transfer
        }
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
                pagination : false,   // 是否显示 分页
                toolbar : toolbar,   // 显示工具栏
                url : "${pageContext.request.contextPath}/personalTask_personalList",  // 请求地址
                idField : 'taskNo',  // id
                columns : columns,  // 显示的字段
            });

            // 中转任务窗口
            $('#transferSearchWindow').window({
                title: '中转任务',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable:false
            });

            //为保存按钮绑定事件
            $("#save").click(function(){
                //表单校验，如果通过，提交表单
                var v = $("#transfer").form("validate");
                if (v) {
                    $("#transfer").submit();
                }
            });

        });
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>


<div class="easyui-window" title="中转任务" id="transferSearchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save"  class="easyui-linkbutton" plain="true" >保存</a>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="transfer" action="${pageContext.request.contextPath }/personalTask_transferTask" method="get">
            <input type="hidden" name="id" id="id">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">中转任务</td>
                </tr>
                <tr>
                    <td>起始城市</td>
                    <td><input type="text" name="startPostions" id="startPostions"  class="easyui-validatebox"
                               readonly="true"/></td>
                </tr>
                <tr>
                    <td>目标城市</td>
                    <td><input type="text" name="endPostions" id="endPostions"
                               class="easyui-validatebox" readonly="true"/></td>
                </tr>
                <tr>
                    <td>您中转的城市</td>
                    <td><input type="text" name="middiePostions"
                               class="easyui-validatebox"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>