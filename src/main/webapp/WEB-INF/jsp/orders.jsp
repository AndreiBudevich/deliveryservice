<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/orders.js" defer></script>
<script type="text/javascript" src="resources/js/getParamClient.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="order.title"/></h3>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="order.client"/></th>
                <th><spring:message code="order.registered"/></th>
                <th><spring:message code="order.deliveryAddress"/></th>
                <th><spring:message code="order.totalCost"/></th>
                <th><spring:message code="order.shipmentStatus"/></th>
                <th></th>
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
                    <input type="hidden" id="totalCost" name="totalCost">
                    <div class="form-group">
                        <label for="deliveryAddress" class="col-form-label"><spring:message
                                code="order.deliveryAddress"/></label>
                        <input type="text" class="form-control" id="deliveryAddress" name="deliveryAddress"
                               placeholder="<spring:message code="order.deliveryAddress"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><spring:message
                        code="common.cancel"/></button>
                <button type="button" class="btn btn-primary" onclick="save()"><spring:message
                        code="common.save"/></button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="order"/>
</jsp:include>
</html>
