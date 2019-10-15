<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>

<script>
    $(function () {
        $("#bannerList").jqGrid({
            rownumbers: true,
            url: "${app}/ablum/queryAll",
            editurl: "${app}/ablum/edit",
            datatype: "json",
            closeAfterAdd: true,
            colNames: ["编号", "标题", "分数", "作者", "播音员", "章节数", "专辑简介", "状态", "发行时间", "上传时间", "插图"],
            colModel: [
                {name: "id", align: "center", hidden: true},
                {name: "title", align: "center", editable: true},
                {name: "score", align: "center", editable: true},
                {name: "author", align: "center", editable: true},
                {name: "announcer", align: "center", editable: true},
                {name: "count", align: "center", editable: true, formatoptions: {defaultValue: '0'}},
                {name: "content", align: "center", editable: true},
                {
                    name: "status",
                    align: "center",
                    editable: true,
                    edittype: 'select',
                    editoptions: {value: "展示:展示;不展示:不展示"},
                },
                {name: "time", align: "center", editable: true, edittype: 'date',},
                {name: "upTime", align: "center", editable: true, edittype: 'date',},
                {
                    name: "src", align: "center", editable: true, edittype: "file",
                    formatter: function (a, b, c) {
                        return "<img style='width:100px;height:50px' src='${app}/img/" + a + "' />"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            pager: "#b",
            rowNum: 3,
            height: "300px",
            rowList: [3, 6, 9],
            viewrecords: true,
            multiselect: true,
            subGrid: true,
            subGridRowExpanded: function (subgrid_id, albumId) {
                addSubGrid(subgrid_id, albumId);
            }
        }).jqGrid("navGrid", "#b",
            {//处理页面几个按钮的样式
                search: false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit: true,
                beforeShowForm: function (obj) {

                    obj.find("#src").attr("disabled", true)
                    obj.find("#count").attr("readonly", true)

                }
            },
            {//在添加数据 之前或者之后进行额外的操作
                closeAfterAdd: true,
                beforeShowForm: function (obj) {

                    obj.find("#count").attr("readonly", true)

                },
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${app}/ablum/upload",
                        fileElementId: "src",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            $("#bannerList").trigger("reloadGrid");
                        }
                    });
                    return response
                }

            },
            {//在删除数据之前或者之后进行额外的操作

            }
        )
    })

    function addSubGrid(subgrid_id, albumId) {
        var tableId = subgrid_id + "table";
        var divId = subgrid_id + "div";
        $("#" + subgrid_id).html(
            "<table id='" + tableId + "' ></table>" + "<div id='" + divId + "' ></div>"
        );
        console.log(albumId);
        $("#" + tableId).jqGrid({
            url: "${app}/chapter/findAllPage?albumid=" + albumId,
            editurl: "${app}/chapter/edit?albumid=" + albumId,
            datatype: "json",
            colNames: ["主键", "标题", "大小", "时长", "上传时间", "音频", "操作"],
            colModel: [
                {name: "id", align: "center", hidden: true},
                {name: "title", editable: true, align: "center"},
                {name: "size", align: "center",},
                {name: "date", align: "center",},
                {name: "upDate", editable: true, align: "center", edittype: "date"},
                {name: "name", editable: true, align: "center", edittype: "file"},
                {
                    name: "name",
                    formatter: function (cellValue, options, rowObject) {
                        console.log(cellValue);
                        return "<a onclick=\"aa('" + cellValue + "')\" href='#'><span class='glyphicon glyphicon-play-circle'></span></a>" + "    " +
                            "<a href='${app}/chapter/download?filename=" + rowObject.name + "'><span class='glyphicon glyphicon-download'></span></a>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#" + divId,
            page: 1,
            rowNum: 2,
            multiselect: true,
            rowList: [2, 4, 6],
            viewrecords: true
        }).jqGrid("navGrid", "#" + divId,
            {},
            {
                closeAfterEdit: true,
                beforeShowForm: function (obj) {

                    obj.find("#name").attr("disabled", true)
                    obj.find("#size").attr("readonly", true)
                    obj.find("#date").attr("readonly", true)
                    obj.find("#upDate").attr("readonly", true)

                }
            }, {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${app}/chapter/upload",
                        fileElementId: "name",
                        data: {id: bannerId},
                        success: function (data) {
                            $("#bannerList").trigger("reloadGrid");
                            $("#" + tableId).trigger("reloadGrid");
                        }
                    });
                    return response
                }
            }, {

                afterSubmit: function () {
                    $("#bannerList").trigger("reloadGrid");
                    $("#" + tableId).trigger("reloadGrid");
                    return "ddasd"
                }
            }
        )

    }

    function aa(d) {
        $("#ff").modal("show")
        $("#audios").attr("src", "${app}/audio/" + d)
    }
</script>


<div id="ff" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audios" controls src=""></audio>
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<div class="page-header">
    <h1>专辑管理</h1>
</div>

<table id="bannerList"></table>
<div style="height: 40px" id="b"></div>

