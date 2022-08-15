<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://by.deliveryservice.util/functions" %>

<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/bodyHeader.jsp"/>

<section>
   <h3><spring:message code="client.title"/></h3>
    <table class="table">
        <thead>
        <tr>
            <th><spring:message code="client.name"/></th>
            <th><spring:message code="client.Surname"/></th>
            <th><spring:message code="client.MiddleName"/></th>
            <th><spring:message code="client.ResidentialAddress"/></th>
            <th><spring:message code="client.birthday"/></th>
            <th><spring:message code="client.registered"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.clients}" var="client">
            <jsp:useBean id="client" type="by.deliveryservice.model.Client"/>
            <tr>
                <td><c:out value="${client.name}"/></td>
                <td>${client.surname}</td>
                <td>${client.middleName}</td>
                <td>${client.residentialAddress}</td>
                <td>${client.birthday}</td>
                <td>${fn:formatDateTime(client.registered)}</td>
                <td><a href="clients/update?id=${client.id}"><spring:message code="common.update"/></a></td>
                <td><a href="clients/delete?id=${client.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
        <hr>
        <a href="clients/create"><spring:message code="client.add"/></a>
        <hr>
    </table>
</section>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>