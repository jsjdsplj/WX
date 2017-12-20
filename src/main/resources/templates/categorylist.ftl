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
                            <th>类目id</th>
                            <th>名字</th>
                            <th>类型</th>
                            <th>修改时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list categoryList as category>
                        <tr>
                            <td>${category.categoryId}</td>
                            <td>${category.categoryName}</td>
                            <td>${category.categoryType}</td>
                            <td>${category.updateTime}</td>
                          <td>
                                <a href="/wxsell/seller/category/index?categoryId=${category.categoryId}">修改</a>
                            </td>

                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
</div>

</body>
</html>
