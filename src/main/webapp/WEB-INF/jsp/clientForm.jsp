<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="client" type="by.deliveryservice.model.Client" scope="request"/>
    <h3><spring:message code="${client.isNew() ? 'client.add' : 'client.edit'}"/></h3>
    <hr>
    <form method="post" action="clients">
        <input type="hidden" name="id" value="${client.id}">
        <dl>
            <dt><spring:message code="client.name"/>:<dt>
            <dd><input type="text" value="${client.name}" size=25 name="name" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="client.Surname"/>:<dt>
            <dd><input type="text" value="${client.surname}" size=25 name="surname" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="client.MiddleName"/>:<dt>
            <dd><input type="text" value="${client.middleName}" size=25 name="middleName" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="client.ResidentialAddress"/>:<dt>
            <dd><input type="text" value="${client.residentialAddress}" size=25 name="residentialAddress" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="client.birthday"/>:<dt>
            <dd><input type="date" value="${client.birthday}" size=25 name="birthday" required><dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

