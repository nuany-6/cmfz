<%@ page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    $(function () {
        var val = '${param.val}'

        $.ajax({
            url: "${pageContext.request.contextPath}/article/es",
            type: "post",
            datatype: "json",
            data: {val: val},
            success: function (data) {
                $.each(data, function (index, values) {
                    $("#dd").append(
                        '<div class="media">\n' +
                        '  <div class="media-left">\n' +
                        '    <a href="#">\n' +
                        '      <img style="width:100px;height:70px"class="media-object" src="${pageContext.request.contextPath}/img/1570512087291_1.jpg" alt="...">\n' +
                        '    </a>\n' +
                        '  </div>\n' +
                        '  <div class="media-body">\n' +
                        '    <h4 class="media-heading">' + values.title + '</h4>\n' +
                        '    ' + values.author + '\n' + values.content +
                        '  </div>\n' +
                        '</div>'
                    )
                })
            }
        })
    })


</script>
<div id="dd"></div>