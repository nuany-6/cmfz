<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>



    <script>
        var goEasy = new GoEasy({
            appkey: "BC-a29d976b67a941dbaa2783636a024159"
        });

        $(function () {
            var pubMsg="";

            $("#message-send").click(function () {
                var msg=$("#message-input").val();
                pubMsg=msg;
                goEasy.publish({
                    channel:"lts",
                    message:msg,
                    onSuccess:function(){
                        var msgDiv=$(
                            "<div style='float: right;background: green'>"+
                            msg+
                            "</div><br/><br/>"
                        );
                        $("#message-show").append(msgDiv);
                    }

                });
                $("#message-input").val("");



            });

            goEasy.subscribe({
                channel: "lts",
                onMessage: function (message) {
                    var subMsg=message.content;
                    if(pubMsg!=subMsg){
                        var msgDiv=$(
                            "<div style='float: left;background: gray'>"+
                            subMsg+
                            "</div><br/><br/>"
                        );
                        $("#message-show").append(msgDiv);
                    }else {
                        pubMsg=""
                    }


                }
            });

        });
    $("#btn").click(function () {
        $("#message-show").html("")
    })


    </script>


<div class="col-sm-2"></div>
<div class="col-sm-8">
    <div style="width: 800px; height: 400px;border: solid red 1px ">
        <div id="message-show" style=" overflow-y:auto; width: 800px; height: 400px;border: solid blue 1px "> </div>

        <div style="width: 800px; height: 100px;border: solid green 1px ">
            <textarea id="message-input" style="width: 798px;height: 50px"></textarea>

            <button id="message-send" style="float: right;width: 350px;height: 50px">发送</button>
            <button id="btn" style="float: left;width: 350px;height: 50px">清空</button>

        </div>
    </div>
</div>
<div class="col-sm-2"></div>

