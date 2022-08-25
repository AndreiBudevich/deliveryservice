<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/commonBefore.js"></script>
<script type="text/javascript" src="resources/js/clientOrders.js" defer></script>
<script type="text/javascript" src="resources/js/common.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="order.title"/></h3>
        <div class="card border-dark">
            <div class="card-body pb-0">
                <div class="form-group">
                    <div class="row">
                        <div class="col-2">
                            <h6 class="text-lg-start"><spring:message code="order.clientName"/>:</h6>
                        </div>
                        <div class="col-5">
                            <h6 class="text-lg-start"><p class="clientName">${param.clientName}</p></h6>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-2">
                            <h6 class="text-lg-start"><spring:message code="client.residentialAddress"/>:</h6>
                        </div>
                        <div class="col-5">
                            <h6 class="text-lg-start"><p class="clientName">${param.residentialAddress}</p></h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <button type="button" class="btn btn-primary" onclick="addWithSetAddress()" data-bs-toggle="modal"
                data-bs-target="#editRow">
            <spring:message code="common.add"/>
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="order.registered"/></th>
                <th><spring:message code="order.deliveryAddress"/></th>
                <th><spring:message code="order.totalCost"/></th>
                <th><spring:message code="order.shipped"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
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
