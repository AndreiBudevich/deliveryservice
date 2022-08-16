<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="shop" type="by.deliveryservice.model.Shop" scope="request"/>
    <h3><spring:message code="${shop.isNew() ? 'shop.add' : 'shop.edit'}"/></h3>
    <hr>
    <form method="post" action="shops">
        <input type="hidden" name="id" value="${shop.id}">
        <dl>
            <dt><spring:message code="shop.name"/>:<dt>
            <dd><input type="text" value="${shop.name}" size=25 name="name" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="shop.description"/>:<dt>
            <dd><input type="text" value="${shop.description}" size=25 name="description" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="shop.address"/>:<dt>
            <dd><input type="text" value="${shop.address}" size=25 name="address" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="shop.contact"/>:<dt>
            <dd><input type="text" value="${shop.contact}" size=25 name="contact" required><dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

