<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/clients.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="client.title"/></h3>
        <button type="button" class="btn btn-primary" onclick="add()" data-bs-toggle="modal" data-bs-target="#editRow">
            <spring:message code="common.add"/>
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="client.name"/></th>
                <th><spring:message code="client.surname"/></th>
                <th><spring:message code="client.middleName"/></th>
                <th><spring:message code="client.residentialAddress"/></th>
                <th><spring:message code="client.birthday"/></th>
                <th><spring:message code="client.registered"/></th>
                <th><spring:message code="client.orders"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
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
                        <label for="name" class="col-form-label"><spring:message code="client.name"/></label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="<spring:message code="client.name"/>">
                    </div>
                    <div class="form-group">
                        <label for="surname" class="col-form-label"><spring:message code="client.surname"/></label>
                        <input type="text" class="form-control" id="surname" name="surname"
                               placeholder="<spring:message code="client.surname"/>">
                    </div>
                    <div class="form-group">
                        <label for="middleName" class="col-form-label"><spring:message code="client.middleName"/></label>
                        <input type="text" class="form-control" id="middleName" name="middleName"
                               placeholder="<spring:message code="client.middleName"/>">
                    </div>
                    <div class="form-group">
                        <label for="residentialAddress" class="col-form-label"><spring:message code="client.residentialAddress"/></label>
                        <input type="text" class="form-control" id="residentialAddress" name="residentialAddress"
                               placeholder="<spring:message code="client.residentialAddress"/>">
                    </div>
                    <div class="form-group">
                        <label for="birthday" class="col-form-label"><spring:message code="client.birthday"/></label>
                        <input type="date" class="form-control" id="birthday" name="birthday"
                               placeholder="<spring:message code="client.birthday"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><spring:message code="common.cancel"/></button>
                <button type="button" class="btn btn-primary" onclick="save()"><spring:message code="common.save"/></button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="category"/>
</jsp:include>
</html>
