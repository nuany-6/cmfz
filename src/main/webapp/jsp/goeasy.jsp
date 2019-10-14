<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
​
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js​"></script>
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {

                $.ajax({
                    url: "${pageContext.request.contextPath}/goeasy/goeasy",
                    datatype: "json",
                    type: "post",
                    data: {"aa": $("#inp").val()},
                    success: function (data) {
                        var goEasy = new GoEasy({
                            appkey: "BC-a29d976b67a941dbaa2783636a024159"
                        });
                        goEasy.subscribe({
                            channel: "aa",
                            onMessage: function (message) {
                                $("#b").append("<p>" + message.content + "</p>")
                                $("#inp").val("")
                            }
                        });

                    }
                })
            })
            $("#btn1").click(function () {
                $("#b").html("")
            })
        })

    </script>
</head>
<style>

</style>
<body>
<div id="div" align="center">

        <input  id="inp" type="text" name="aa" style="">
        <button  id="btn">发送</button>
        <button  id="btn1">清空</button>
    <div id="b"></div>
</div>

</body>
</html>