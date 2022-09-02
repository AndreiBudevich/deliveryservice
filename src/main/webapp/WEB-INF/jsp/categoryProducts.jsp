<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/commonBefore.js"></script>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/filterCategories.js" defer></script>
<script type="text/javascript" src="resources/js/filter.js" defer></script>
<script type="text/javascript" src="resources/js/productTablePlus.js" defer></script>
<script type="text/javascript" src="resources/js/categoryProducts.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center" id="orderProducts"> <h3 class="text-center"><spring:message code="product.addCategoryTitle"/> "${param.categoryName}"</h3>
            <div id="orderTotalCost"></div>
        </h3>
        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">
                    <div class="row">
                        <div class="col-2">
                            <label for="nameContains"><spring:message code="product.nameContains"/></label>
                        </div>
                        <div class="col-2">
                            <label for="descriptionContains"><spring:message
                                    code="product.descriptionContains"/></label>
                        </div>
                        <div class="col-3">
                            <label for="shopNameContains"><spring:message code="product.shopNameContains"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-2">
                            <input class="form-control" name="nameContains" id="nameContains" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <input class="form-control" name="descriptionContains" id="descriptionContains"
                                   autocomplete="off">
                        </div>
                        <div class="col-2">
                            <input class="form-control" name="shopNameContains" id="shopNameContains"
                                   autocomplete="off">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-2">
                            <label for="priceFrom"><spring:message code="product.priceFrom"/></label>
                            <input class="form-control" name="priceFrom" id="priceFrom" autocomplete="off" value="0">
                        </div>
                        <div class="col-2">
                            <label for="priceUpTo"><spring:message code="product.priceUpTo"/></label>
                            <input class="form-control" name="priceUpTo" id="priceUpTo" autocomplete="off" value="0">
                        </div>
                        <div class="col-2">
                            <label for="discountFrom"><spring:message code="product.discountFrom"/></label>
                            <input class="form-control" name="discountFrom" id="discountFrom" autocomplete="off" value="0">
                        </div>
                        <div class="col-2">
                            <label for="discountUpTo"><spring:message code="product.discountUpTo"/></label>
                            <input class="form-control" name="discountUpTo" id="discountUpTo" autocomplete="off" value="0">
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-12">
                            <table class="table table-striped" id="datatableCategory">
                                <thead>
                                <tr>
                                    <th><spring:message code="product.categories"/></th>
                                    <th></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer text-right">
                <button class="btn btn-danger" onclick="clearFilter()">
                    <span class="fa fa-remove"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button class="btn btn-primary" onclick="runFilter ()">
                    <span class="fa fa-filter"></span>
                    <spring:message code="product.filter"/>
                </button>
            </div>
        </div>
        <br/>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="product.name"/></th>
                <th><spring:message code="product.description"/></th>
                <th><spring:message code="product.price"/></th>
                <th><spring:message code="product.discount"/></th>
                <th><spring:message code="product.shop"/></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="product"/>
</jsp:include>
</html>
