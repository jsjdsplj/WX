
<html>
<#include "header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
        <#include "nav.ftl">
    <#--主体内容content-->
<div id="page-content-wrapper">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-condensed table-bordered">
                    <thead>
                    <tr>
                        <th>订单id</th>
                        <th>手机号</th>
                        <th>客户</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTOPage.content as orderDTO>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.orderStatus}</td>
                        <td>${orderDTO.payStatus}</td>
                        <td>${orderDTO.updateTime}</td>
                        <td>
                            <a href="/wxsell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a>
                        </td>
                        <td>
                            <#if orderDTO.getOrderStatusEnum().message!="已取消">
                                <a href="/wxsell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>

        <#--分页-->
            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                <#if currentPage lte 1>
                    <li class="disable"><a href="#">上一页</a></li>
                <#else>
                    <li class="disable"><a href="http://localhost/wxsell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                </#if>
                <#list 0..orderDTOPage.getTotalPages() as index>
                    <#if currentPage==index>
                        <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                        <li><a href="http://localhost/wxsell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>
                <#if currentPage gte orderDTOPage.getTotalPages()>
                    <li class="disable"><a href="#">下一页</a></li>
                <#else>
                    <li><a href="http://localhost/wxsell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                </#if>
                </ul>
            </div>
        </div>
    </div>
</div>

</div>

<#--弹窗-->

            <div id="myModal" class="modal  fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="myModalLabel">
                        标题栏
                    </h3>
                </div>
                <div class="modal-body">
                    <p>
                        显示信息
                    </p>
                </div>
                <div class="modal-footer">
                    <button onclick="javascript: document.getElementById('notice').pause()" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                    <button onclick="location.reload()" class="btn btn-primary">保存设置</button>
                </div>
            </div>

<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/wxsell/mp3/song.mp3" type="audio/mpeg"/>
</audio>



<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>

<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket=null;
    if('WebSocket' in window){
        websocket=new WebSocket('ws://localhost/wxsell/webSocket');
    }else {
        alert('该浏览器不支持')
    }

    websocket.onopen=function (event) {
        console.log('建立链接')
    }
    websocket.onclose=function (event) {
        console.log('链接关闭')
    }
    websocket.onmessage=function (event) {
        console.log('收到消息'+event.data)
        //弹窗提醒，播放音乐
      $('#myModal').modal('show')
     document.getElementById('notice').play();
    }
    websocket.onerror=function (event) {
        alert('websocket出现错误')
    }
    window.onbeforeunload=function () {
        websocket.close()
    }
</script>

</body>
</html>
