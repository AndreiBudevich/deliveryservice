<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/commonBefore.js" defer></script>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/storage.js" defer></script>
<script type="text/javascript" src="resources/js/saveWithIdProduct.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="product.title"/></h3>
        <button type="button" class="btn btn-primary" onclick="add()" data-bs-toggle="modal"
                data-bs-target="#editProduct">
            <spring:message code="common.add"/>
        </button>
        <br/>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="product.name"/></th>
                <th><spring:message code="product.description"/></th>
                <th><spring:message code="product.price"/></th>
                <th><spring:message code="product.discount"/></th>
                <th><spring:message code="product.quantity"/></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow" data-bs-backdrop="static" data-bs-keyboard="true" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitle"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">
                    <div class="form-group">
                        <label for="quantity" class="col-form-label"><spring:message code="product.quantity"/></label>
                        <input type="number" class="form-control" id="quantity" name="quantity"
                               placeholder="<spring:message code="product.quantity"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><spring:message
                        code="common.cancel"/></button>
                <button type="button" class="btn btn-primary" onclick="saveWithIdProduct()"><spring:message
                        code="common.save"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editProduct" data-bs-backdrop="static" data-bs-keyboard="true" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitleProduct"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="detailsFormProduct">
                    <input type="hidden" id="idProduct" name="id">
                    <div class="form-group">
                        <label for="name" class="col-form-label"><spring:message code="product.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
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
                        <input type="text" class="form-control" id="price" name="price"
                               placeholder="<spring:message code="product.price"/>">
                    </div>
                    <div class="form-group">
                        <label for="discount" class="col-form-label"><spring:message code="product.discount"/></label>
                        <input type="text" class="form-control" id="discount" name="discount"
                               placeholder="<spring:message code="product.discount"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><spring:message
                        code="common.cancel"/></button>
                <button type="button" class="btn btn-primary" onclick="saveProduct()"><spring:message
                        code="common.save"/></button>
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
