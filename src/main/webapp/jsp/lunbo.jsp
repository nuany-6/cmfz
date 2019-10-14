<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>

<script>
    $(function () {
        $("#bannerList").jqGrid({
            rownumbers:true,
            url:"${app}/banner/findAll",
            editurl:"${app}/banner/edit",
            datatype:"json",
            closeAfterAdd: true,
            colNames:["编号","标题","状态","描述","时间","图片"],
            colModel:[
                {name:"id" ,align:"center",hidden:true},
                {name:"title",align:"center",editable:true},
                {name:"status",align:"center",editable:true,edittype:'select',editoptions:{value:"展示:展示;不展示:不展示"},
                },
                {name:"intr",align:"center",editable:true},
                {name:"time",align:"center",editable:true,edittype:'date',},
                {name:"src",align:"center",editable:true,edittype:"file",
                    formatter:function (a, b, c) {
                        return "<img style='width:100px;height:50px' src='${app}/img/"+a+"' />"
                    }
                }
            ],
            styleUI:"Bootstrap",
            autowidth:true,
            pager:"#b",
            rowNum:3,
            height:"300px",
            rowList:[3,6,9],
            viewrecords:true,
            multiselect:true
        }).jqGrid("navGrid","#b",
            {//处理页面几个按钮的样式
                search:false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit:true,
                beforeShowForm: function (obj) {
                    obj.find("#src").attr("disabled",true)
                }
            },
            {//在添加数据 之前或者之后进行额外的操作
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url:"${app}/banner/upload",
                        fileElementId:"src",
                        data:{bannerId:bannerId},
                        success:function (data) {
                        $("#bannerList").trigger("reloadGrid");
                        }
                    });
                    return response
                }

            },
            {//在删除数据之前或者之后进行额外的操作

            }
        )
        $("#a").click(function () {
            var s = confirm("您要继续操作吗?")
            if (s){
                location.href="${app}/banner/queryAll"
            }else {
                location.href="#"
            }
        })
    })


</script>

    <!-- Nav tabs -->

<div class="page-header">
    <h1>轮播图管理</h1>
    <button style="float: right;margin-top: -25px" type="button" id="a" data-loading-text="Loading..." class="btn btn-primary" >
        导出Excel
    </button>

</div>
<table id="bannerList"></table>
<div style="height: 40px" id="b"></div>

