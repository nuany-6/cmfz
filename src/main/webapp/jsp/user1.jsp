<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">


    var days = new Array();

    //当天
    var Date8 = new Date();
    var Date9 = new Date().toLocaleDateString();
    //前一天
    var Date7 = new Date(Date8.getTime() - 24*60*60*1000).toLocaleDateString();

    //前两天
    var Date6 = new Date(Date8.getTime() - 48*60*60*1000).toLocaleDateString();
    console.log(Date6);
    //前三天
    var Date5 = new Date(Date8.getTime() - 72*60*60*1000).toLocaleDateString();
    //前四天
    var Date4 = new Date(Date8.getTime() - 96*60*60*1000).toLocaleDateString();
    //前五天
    var Date3 = new Date(Date8.getTime() - 120*60*60*1000).toLocaleDateString();
    //前六天
    var Date2 = new Date(Date8.getTime() - 144*60*60*1000).toLocaleDateString();
    //前七天
    var Date1 = new Date(Date8.getTime() - 168*60*60*1000).toLocaleDateString();
    days.push(Date9,Date7,Date6,Date5,Date4,Date3,Date2)
    console.log(days);

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '近七日用户注册总量'
        },
        tooltip: {},
        legend: {
            data:['注册量']
        },
        xAxis: {
            data:[1,2,2,2,2,2,2]
        },
        yAxis: {},
        series: [{
            name: '注册量',
            type: 'line'
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.ajax({
        url:"${pageContext.request.contextPath}/user/findAll",
        datatype:"json",
        type:"POST",
        success:function (da) {
            console.log(da);
            myChart.setOption({
                series:[{data:da}],
                xAxis:{data:days}
            });



        }
    })


</script>


    <div id="main" style="width: 1200px;height:400px;"></div>

