<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://by.deliveryservice.util/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/product.js" defer></script>
<script type="text/javascript" src="resources/js/category.js" defer></script>
<script type="text/javascript" src="resources/js/filter.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div id="grid"></div>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="product.title"/></h3>
        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">
                    <div class="row">
                        <div class="col-2">
                            <label for="nameContains"><spring:message code="product.nameContains"/></label>
                        </div>
                        <div class="col-2">
                            <label for="descriptionContains"><spring:message code="product.descriptionContains"/></label>
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
                            <input class="form-control" name="descriptionContains" id="descriptionContains" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <input class="form-control" name="shopNameContains" id="shopNameContains" autocomplete="off">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-2">
                            <label for="priceFrom"><spring:message code="product.priceFrom"/></label>
                            <input class="form-control" name="priceFrom" id="priceFrom" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <label for="priceUpTo"><spring:message code="product.priceUpTo"/></label>
                            <input class="form-control" name="priceUpTo" id="priceUpTo" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <label for="discountFrom"><spring:message code="product.discountFrom"/></label>
                            <input class="form-control" name="discountFrom" id="discountFrom" autocomplete="off">
                        </div>
                        <div class="col-2">
                            <label for="discountUpTo"><spring:message code="product.discountUpTo"/></label>
                            <input class="form-control" name="discountUpTo" id="discountUpTo" autocomplete="off">
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
        <button class="btn btn-primary" onclick="">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>
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

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="product.name"/></label>
                        <input class="form-control" id="name" name="name" autocomplete="off"
                               placeholder="<spring:message code="product.name"/>">
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message
                                code="product.description"/></label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="product.description"/>">
                    </div>
                    <div class="form-group">
                        <label for="price" class="col-form-label"><spring:message code="product.price"/></label>
                        <input type="number" class="form-control" id="price" name="price" placeholder="1000">
                    </div>
                    <div class="form-group">
                        <label for="discount" class="col-form-label"><spring:message code="product.discount"/></label>
                        <input type="number" class="form-control" id="discount" name="discount" placeholder="10">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="product"/>
</jsp:include>
</html>
