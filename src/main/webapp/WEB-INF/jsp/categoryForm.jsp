<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="category" type="by.deliveryservice.model.Category" scope="request"/>
    <h3><spring:message code="${category.isNew() ? 'category.add' : 'category.edit'}"/></h3>
    <hr>
    <form method="post" action="categories">
        <input type="hidden" name="id" value="${category.id}">
        <dl>
            <dt><spring:message code="category.name"/>:<dt>
            <dd> <input type="text" value="${category.name}" size=25 name="name" required><dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

