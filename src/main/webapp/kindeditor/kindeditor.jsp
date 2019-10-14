<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="kindeditor-all-min.js"></script>
    <script src="lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function (k) {
            window.editor = k.create('#editor_id',{
                allowFileManager:true,
                uploadJson:"${pageContext.request.contextPath}/kindeditor/upload",
                filePostName:"img",
                fileManagerJson:"${pageContext.request.contextPath}/kindeditor/allImages"

            })
        })
    </script>
</head>
<body>
    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
    &lt;strong&gt;HTML内容&lt;/strong&gt;
    </textarea>
</body>
</html>