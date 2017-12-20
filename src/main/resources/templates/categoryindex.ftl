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
                    <form role="form" method="post" action="/wxsell/seller/category/save">
                        <div class="form-group">
                            <label>名字</label>
                            <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类型</label>
                            <input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!''}"/>
                        </div>
                        <input hidden name="categoryId" type="text"  value="${(category.categoryId)!''}"/>


                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>