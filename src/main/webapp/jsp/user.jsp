<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据

    // 指定图表的配置项和数据
    option = {
        title: {
            text: '用户注册分布量',
            subtext: '内容666',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['用户']
        },
        visualMap: {
            min: 0,
            max: 3000,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '用户',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                }

            }


        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.ajax({
        url: "${pageContext.request.contextPath}/user/queryAll",
        datatype: "json",
        type: "POST",
        success: function (da) {
            console.log(da);
            myChart.setOption({
                series: [{data: da}]
            });
        }
    })


</script>
<div class="col-sm-2"></div>
<div class="col-sm-8">

    <div id="main" style="width: 600px;height:400px;">123</div>

</div>
<div class="col-sm-2"></div>
